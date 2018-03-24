package com;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import com.file.ConnectionInfo;
import com.file.FileReader;

public class Client extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2560331564228926713L;
	
	
	String textToSend, ip_address;
	private int socket_num;
	
	public static List<ConnectionInfo> connectionInfoList = new ArrayList<ConnectionInfo>();
	
	static JSlider j;
	static JLabel l1;
	
	FileReader FR;
	
	public static int SERVER_RETURN;
	
	SocketCom socket0;
	Thread socket0Thread;
	boolean ipAdded = false;

	public Client() {
		super();
		setSize(500, 500);
		setBounds(0, 0, 500, 500);
		setLayout(null);
		setLocationRelativeTo(null);		
		FR = new FileReader("connections.csv", 2);
		initialiseConnectionList();
		addUI();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initialiseConnectionList() {
		FR.openFile();
		FR.readFile();
		FR.closeFile();
//		System.out.println(connectionInfoList.size() );
	}

	public void generateComs() {
		
	}

	private void addUI() {
		JTextField tb0 = new JTextField();
		tb0.setSize(110, 40);
		tb0.setBounds(170, 110, 100, 40);
		
		JTextField tb1 = new JTextField();
		tb1.setSize(80, 40);
		tb1.setBounds(170, 50, 100, 40);
		try {tb1.setText(connectionInfoList.get(0).getIp());}catch(Exception e) {}
		
		JTextField tb2 = new JTextField();
		tb2.setSize(80, 40);
		tb2.setBounds(270, 50, 100, 40);
		try {tb2.setText(String.valueOf(connectionInfoList.get(0).getSocket()));}catch(Exception e) {}
		
		JLabel l = new JLabel(textToSend);
		l.setSize(80, 40);
		l.setBounds(270, 110, 100, 40);
		
		/**
		 * 
		 */
		
		l1 = new JLabel();
		l1.setSize(80, 40);
		l1.setBounds(370, 110, 100, 40);
		
		
		j = new JSlider();
		j.setBounds(50, 210, 300, 40);
		
		JButton b1 = new JButton("IP ADRESS");
		b1.setSize(80,40);
		b1.setBounds(50, 50,100, 40);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!ipAdded) {
					ip_address = tb1.getText();
					if(ip_address.equals("")) {
						socket0 = new SocketCom();
					}else {
						socket_num = Integer.parseInt(tb2.getText());
						socket0 = new SocketCom(ip_address, socket_num);												
					}
					socket0Thread = new Thread(socket0);
					socket0Thread.start();
				}
			}
		});
		JButton b = new JButton("Number");
		b.setSize( 80, 40);
		b.setBounds( 50, 110, 100, 40);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				textToSend = tb0.getText();
				socket0.setNumber(Integer.parseInt(textToSend));				
				l.setText("sent : " + textToSend);
				}catch(Exception e) {
//					
				}
			}
		});
		
		
		add(j);
		add(tb2);
		add(tb1);
		add(b1);
		add(l);
		add(b);
		add(l1);
		//add(tb0);
		

	}
	
	public static int getSliderValue() {
		return j.getValue();
	}
	
	public static void SetSReturnValue(int val) {
		l1.setText(Integer.toString(val));
	}
	public static void inputToConnecetionList(String IP_ADDRESS, int SOCKET) {
		ConnectionInfo cI = new ConnectionInfo(IP_ADDRESS, SOCKET);
		connectionInfoList.add(cI);
	}

}
