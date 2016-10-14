package com.pating.login;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PatingServer999 extends JFrame implements Runnable, ActionListener{
	JTextField txt;
	JButton bt;
	JTextArea area;
	JPanel p_north;

	Thread serverThread;
	ServerSocket server;
	int port=7676;
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@70.12.112.119:1521:XE";
	String user="pating1234";
	String password="pating1234";
	Connection con;
	
	JSONParser jsonParser;
	Socket client;
	Socket client1;
	BufferedReader buffr;
	JSONObject jsonObject;
	Thread serverThread1;
	boolean a=false;
	ServerThread1 st1;
	
	public static HashMap<Long, ServerThread> AccessList = new HashMap<Long, ServerThread>();
	ArrayList<Long> memberIdList = new ArrayList<Long>();
	
	public PatingServer999() {
		txt=new JTextField(Integer.toString(port),4);
		bt=new JButton("�꽌踰꾧��룞");
		area= new JTextArea();
		p_north= new JPanel();
		
		p_north.add(txt);
		p_north.add(bt);
		
		bt.addActionListener(this);

		add(p_north,BorderLayout.NORTH);
		add(area);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100,100,500,500);
	}
	
	public void startServer(){
		try {
			server =new ServerSocket(port);
			area.append("占쎄퐣甕곤옙 占쎄문占쎄쉐 占쎌끏�뙴占�\n");
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,password);
			
			if(con != null){
				setTitle("占쎌궎占쎌뵬占쎄깻 占쎌젔占쎈꺗 占쎄쉐�⑨옙");
			}
			while(true){
				client=server.accept();
				//System.out.println(client);
				ServerThread st=new ServerThread(this,client);
				st.start();
				area.append("占쎌젔占쎈꺗占쎌쁽 揶쏅Ŋ占�!!\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void startServer2(){
		try {
				try {
					buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
					String msg = buffr.readLine();
					//System.out.println("buffr.readLine() : " + msg);
					jsonParser = new JSONParser();
					jsonObject = (JSONObject) jsonParser.parse(msg);
					if(jsonObject.get("request").equals("imgchangefile")){
						a=true;
					}
					while(true){
						client1=server.accept();
						st1=new ServerThread1(this,client1);
						st1.start();
						a=false;
					}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		serverThread= new Thread(this);
		serverThread.start();
		bt.setEnabled(false);
	}	
	
	public void run() {
			
			if(a){
				startServer2();
			}else{
				startServer();
			}
			
		
	}
	
	
	public static void main(String[] args) {
		new PatingServer999();

	}

}
