import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;

public class Server{

    int count = 0;
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    TheServer server;
    private Consumer<GameInfo> callback;
    int port;


    Server(Consumer<GameInfo> call, int p){

        callback = call;
        port = p;
        server = new TheServer();
        server.start();

    }


    public class TheServer extends Thread{

        public void run() {

            try(ServerSocket mysocket = new ServerSocket(port)) {

                while(true) {

                    count += 1;

                    ClientThread c = new ClientThread(mysocket.accept(), count);
                    callback.accept(new GameInfo(0, count, -1, -1, '$', 'X', -1, false, false, false));
                    clients.add(c);
                    c.start();

                }
            }//end of try
            catch(Exception e) {
                callback.accept(new GameInfo(-1, count, -1, -1, '$', 'X', -1, false, false, false));
            }
        }//end of while
    }


    class ClientThread extends Thread {

        Socket connection;
        int count;
        ObjectInputStream in;
        ObjectOutputStream out;

        ClientThread(Socket s, int count){
            this.connection = s;
            this.count = count;
        }

        public void updateClients(GameInfo obj) {
            for(int i = 0; i < clients.size(); i++) {
                ClientThread t = clients.get(i);
                try {
                    t.out.writeObject(obj);
                }
                catch(Exception e) {}
            }
        }

        public void run(){

            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);
            }
            catch(Exception e) {
                System.out.println("Streams not open");
            }

            updateClients(new GameInfo(0, count, -1, -1, '$', 'X', -1, false, false, false));

            while(true) {
                try {

                    GameInfo inData = (GameInfo)in.readObject();
                    callback.accept(inData);
                    updateClients(inData);

                }
                catch(Exception e) {

                    GameInfo error = new GameInfo(-1, count, -1, -1, '$', 'X', -1, false, false, false);
                    callback.accept(error);
                    updateClients(error);
                    clients.remove(this);
                    break;

                }
            }
        }//end of run


    }//end of client thread
}