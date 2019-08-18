package p2p;
import java.sql.ResultSet;
import javax.swing.JFrame;

public class MainClass {
	
	public static Frame frame;
		static java.sql.Connection connection;
		static java.sql.Statement statement;
		static ResultSet result;
	public static void main(String[] args) throws Exception {
		newFrame();
	}
	public static void newFrame() {
		MainClass.frame=new Frame();
		frame.setTitle("CHAT");
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		MainClass.frame.textArea.append("\n Enter your ID and Port then click on Connect");
		
	}
	public static void updateListenToPairs(String username, ServerThread serverthread) throws Exception
	{
		MainClass.frame.textArea.append("\n\n Enter the IP @ and port to whom you want to communicate (separated by a space)");
		MainClass.frame.textArea.append("\n Example: 127.0.0.1 5001 Then click on OK");
		MainClass.frame.textArea.append("\n NOTE: for a successful communication, the other part must be connected");
		
	}
  public static void communicate ( String username, ServerThread serverthread)  {
	  try {
		  MainClass.frame.textArea.append("\n\n You can now communicate. Enter the message then click on Send");
		  MainClass.frame.textArea.append("\n In addition you can save the messages by clicking on Save");
		  MainClass.frame.textArea.append("\n They will be saved in a file named \"Saved_Messages\" and located in the same location of the API ");
	  }
	  catch(Exception e){
		  
	  }
  }
}
