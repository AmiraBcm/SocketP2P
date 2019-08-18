package p2p;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class ServerThread extends Thread {
	private ServerSocket serversocket;
	private Set<ServerThread2> stt=new HashSet<ServerThread2>();
	
	public ServerThread (String portNumber) throws IOException {
		serversocket = new ServerSocket(Integer.valueOf(portNumber));
	}
	public void run() {
		try {
			ServerThread2 serverthread2=new ServerThread2(serversocket.accept(), this);
			stt.add(serverthread2);
			serverthread2.start();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	public void sendMessage(String message) {
		try {
			
		stt.forEach(t-> t.getprintwriter().println(message));	
		
		}
		catch(Exception e) {e.printStackTrace();}
	}
	public Set<ServerThread2> getServerThread2 () {return stt;}
	
}