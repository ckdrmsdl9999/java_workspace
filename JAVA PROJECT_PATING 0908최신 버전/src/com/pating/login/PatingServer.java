package com.pating.login;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PatingServer extends JFrame implements Runnable, ActionListener{
	JTextField txt;
	JButton bt;
	JTextArea area;
	JPanel p_north;

	Thread serverThread;
	ServerSocket server;
	int port=7676;
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@70.12.112.100:1521:XE";
	String user="pating1234";
	String password="pating1234";
	Connection con;
	
	public static HashMap<Long, ServerThread> AccessList = new HashMap<Long, ServerThread>();
	ArrayList<Long> memberIdList = new ArrayList<Long>();
	
	public PatingServer() {
		txt=new JTextField(Integer.toString(port),4);
		bt=new JButton("서버가동");
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
			area.append("서버 생성 완료�\n");
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,password);
			
			if(con != null){
				setTitle("오라클 접속 성공");
			}
			while(true){
				Socket client=server.accept();
				ServerThread st=new ServerThread(this,client);
				st.start();
				area.append("접속자 감지!!\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		serverThread= new Thread(this);
		serverThread.start();
		bt.setEnabled(false);
	}
	public void run() {
		startServer();
	}
	
	public static void main(String[] args) {
		new PatingServer();

	}

}
