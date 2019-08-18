package p2p;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread2 extends Thread{
	private ServerThread serverthread;
	private Socket socket;
	private PrintWriter printwriter;
	
	public ServerThread2(Socket socket, ServerThread serverthread) {
		this.serverthread=serverthread;
		this.socket=socket;
	}
	public void run() {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.printwriter= new PrintWriter(socket.getOutputStream(), true);
			while(true) serverthread.sendMessage(br.readLine());
		}
		catch(Exception e){serverthread.getServerThread2().remove(this);}
	}
	
	 public PrintWriter getprintwriter() {
		
		return printwriter;
	}
}
