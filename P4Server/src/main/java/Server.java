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

    ArrayList<ClientInfo> clientInfos = new ArrayList<>();


    Server(Consumer<GameInfo> call, int p){

        callback = call;
        port = p;
        server = new TheServer();
        server.start();

    }


    public class TheServer extends Thread{

        public void run() {

            try(ServerSocket mysocket = new ServerSocket(port);) {

                while(true) {

                    count += 1;

                    ClientThread c = new ClientThread(mysocket.accept(), count);
                    callback.accept(new GameInfo(0, count, -1, 6, 3, '$', 'X', -1, false, false, false));
                    clientInfos.add(new ClientInfo(count));
                    clients.add(c);
                    c.start();

                }
            }//end of try
            catch(Exception e) {
                callback.accept(new GameInfo(-1, count, -1, -1, -1, '$', 'X', -1, false, false, false));
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

            updateClients(new GameInfo(0, count, -1, 6, 3, '$', 'X', -1, false, false, false));

            while(true) {
                try {

                    GameInfo inData = (GameInfo)in.readObject();
                    int clientNum = inData.clientNum;
                    int numLetter = inData.numLetter, numGuess = inData.numRemainGuess, numAttempt = inData.numRemainAttempt, charLocation = inData.charLocation;
                    char guess = inData.guess, category = inData.category;
                    boolean win = inData.win, lose = inData.lose, letterInWord = inData.letterInWord;

                    ClientInfo thisClient = clientInfos.get(clientNum-1);
                    thisClient.numGuessLeft = inData.numRemainGuess;
                    thisClient.numAttemptLeft = inData.numRemainAttempt;
                    thisClient.categoryChoice = category;

                    if (numLetter == 0) {

                        String word = WordRepository.getRandWord(thisClient.categoryChoice);
                        WordRepository.holdString = word;
                        thisClient.givenWord = word;
                        numLetter = word.length();

                    }
                    else {

                        int check = thisClient.givenWord.indexOf(guess);
                        if (check == -1) {
                            letterInWord = false;
                            thisClient.numGuessLeft -= 1;
                            numGuess -= 1;
                        }
                        else {

                            letterInWord = true;
                            charLocation = check;

                            StringBuilder sb = new StringBuilder(thisClient.givenWord);
                            sb.replace(check, check + 1, " ");
                            thisClient.givenWord = sb.toString();

                        }

                        if (playerHasWon(thisClient)) {
                            win = true;
                        }
                        else if (playerHasLost(thisClient)) {
                            lose = true;
                        }

                    }

                    callback.accept(new GameInfo(1, clientNum, numLetter, numGuess, numAttempt, guess, category, charLocation, win, lose, letterInWord));
                    updateClients(new GameInfo(1, clientNum, numLetter, numGuess, numAttempt, guess, category, charLocation, win, lose, letterInWord));

                }
                catch(Exception e) {

                    GameInfo error = new GameInfo(-1, count, -1, -1, -1, '$', 'X', -1, false, false, false);
                    callback.accept(error);
                    updateClients(error);
                    clients.remove(this);
                    break;

                }
            }
        }//end of run

        boolean playerHasWon (ClientInfo theClient) {
            for (int i = 0; i < theClient.givenWord.length(); ++i) {
                if (theClient.givenWord.charAt(i) != ' ')
                    return false;
            }
            return true;
        }

        boolean playerHasLost (ClientInfo theClient) {
            if (theClient.numGuessLeft == 0)
                return true;
            return false;
        }


    }//end of client thread
}