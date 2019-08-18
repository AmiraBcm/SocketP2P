package p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.json.Json;
import javax.json.JsonObject;


public class PairThread extends Thread{
	private BufferedReader br;
	public PairThread (Socket socket) throws IOException{
		br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
public void run() {
	boolean flag=true;
	while(flag)
	{
		try {
		JsonObject jsonobjet=Json.createReader(br).readObject();
		if(jsonobjet.containsKey("username"))
			MainClass.frame.textField_MessageReceived.setText(jsonobjet.getString("username")+":"+jsonobjet.getString("message"));
			MainClass.frame.btnSave.setEnabled(true);
			}
		catch(Exception e){
			flag=false;
			interrupt();
		}
	}
}
}
