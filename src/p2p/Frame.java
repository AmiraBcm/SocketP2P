package p2p;

import javax.swing.JFrame;
import javax.json.Json;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Frame extends JFrame{
	public JTextField textField_ID;
	public JTextField textField_Port;
	public JTextField textField_IDPort;
	private JTextField textField_SendingMessage;
	public JButton btnSave;
	ServerThread serverThread;
	public Socket socket = null ;
	public JTextField textField_MessageReceived;
	public JTextArea textArea;
	
	public Frame() {
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		
		JLabel lblMessageReceived = new JLabel("Received Message");
		
		textField_MessageReceived = new JTextField();
		textField_MessageReceived.setEditable(false);
		textField_MessageReceived.setColumns(10);
		
		/////////////////////////SAVE BUTTON///////////////////////////////
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				FileWriter fw=null;
				BufferedWriter bw=null;
				try {
					fw = new FileWriter("Saved_Messages.txt",true);
					bw = new BufferedWriter(fw);
					PrintWriter pw=new PrintWriter(bw);
					pw.println(MainClass.frame.textField_MessageReceived.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {

					try {	if (bw != null)
								bw.close();

							if (fw != null)
								fw.close();

						} 
					catch (IOException ex) {ex.printStackTrace();}
					}
				
			}
		});
		btnSave.setEnabled(false);
		//////////////////////////////////////////////////////////////////////
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(textField_MessageReceived, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblMessageReceived)
							.addGap(277)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSave)
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblMessageReceived)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_MessageReceived, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
					.addGap(9))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
					.addGap(9))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
					.addGap(9))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JLabel lblMessageSend = new JLabel("Sending Message");
		
		textField_SendingMessage = new JTextField();
		textField_SendingMessage.setColumns(10);
		
		//////////////////////////SEND BUTTON////////////////////////////////////
		JButton btnSend = new JButton("Send");
		btnSend.setEnabled(false);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					StringWriter stringwriter=new StringWriter();
						
					Json.createWriter(stringwriter).writeObject(Json.createObjectBuilder()
								                        .add("username",MainClass.frame.textField_ID.getText())
								                        .add("message",MainClass.frame.textField_SendingMessage.getText())
								                        .build());
						
					serverThread.sendMessage(stringwriter.toString());
					
						
					
				
				} catch(Exception ex) {}
			}
			
		});
		//////////////////////////////////////////////////////////////////////////////////
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(textField_SendingMessage, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnSend, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
						.addComponent(lblMessageSend))
					.addGap(18))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSend)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblMessageSend)
							.addGap(14)
							.addComponent(textField_SendingMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblIDPort = new JLabel("IPDest PortDest");
		
		JLabel lblID = new JLabel();
		lblID.setText("ID");
		
		textField_ID = new JTextField();
		
		JLabel lblPort = new JLabel();
		lblPort.setText("Port ");
		
		textField_Port = new JTextField();
		
		////////////////////////////CONNECT BUTTON////////////////////////////////
		JButton btnConnect = new JButton();
		btnConnect.setText("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnConnect.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				try {
					serverThread = new ServerThread(MainClass.frame.textField_Port.getText());
					serverThread.start();
					 MainClass.updateListenToPairs( MainClass.frame.textField_ID.getText(), serverThread);
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} 
			}
		});
		///////////////////////////////////////////////////////////////////////////
		
		/////////////////////////EXIT BUTTON///////////////////////////////
		JButton btnExit = new JButton();
		
		
		btnExit.setText("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		/////////////////////////////////////////////////////////////////////
		
		textField_IDPort = new JTextField();
		textField_IDPort.setColumns(10);
		
		///////////////////////////OK BUTTON///////////////////////////////
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = MainClass.frame.textField_IDPort.getText();
				String[] inputValues=input.split(" ");
				
						try {
							
							socket=new Socket(inputValues[0], Integer.valueOf(inputValues[1]));
							new PairThread(socket).start();
							MainClass.communicate(MainClass.frame.textField_ID.getText(), serverThread);
							btnSend.setEnabled(true);
							
						}
						catch(Exception ex) {
							if (socket !=null)
								try {
									socket.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							else MainClass.frame.textArea.append("\n Invalid entry");
						}
							
			}
		});
		///////////////////////////////////////////////////////////////////
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblIDPort)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_IDPort, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(42)
							.addComponent(lblID, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_ID, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblPort, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_Port, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
							.addComponent(btnConnect)
							.addGap(10)))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnOK, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
					.addGap(20))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Port, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExit)
						.addComponent(lblPort)
						.addComponent(lblID)
						.addComponent(btnConnect))
					.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIDPort)
						.addComponent(textField_IDPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOK))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);
	}
}
