import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.function.Consumer;
import java.io.IOException;

public class Client extends Thread {
	
    String ip ; 
    int port;
    Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
    
	private Consumer<GameInfo> callback;
	
	Client(Consumer<GameInfo> call, String ip, int port){
		callback = call;
		this.port = port; 
		this.ip = ip;
	}

	public void run() {
		try {
			socketClient = new Socket(ip, port);
		    out = new ObjectOutputStream(socketClient.getOutputStream());
		    in = new ObjectInputStream(socketClient.getInputStream());
		    socketClient.setTcpNoDelay(true);
			}
			catch(Exception e) {}
			
			while(true) {
				try {
					GameInfo message = (GameInfo)in.readObject();
					callback.accept(message);
				}
				catch(Exception e) {}
			}
		} /* end run() */ 
	
	public void send (GameInfo data) {
		try {
			out.writeObject(data); 
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
}
    
