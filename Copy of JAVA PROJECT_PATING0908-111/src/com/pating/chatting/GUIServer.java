package com.pating.chatting;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUIServer extends JFrame{
	
	ServerSocket server;
	JTextArea area; // 사용자 끼리 주고 받는 메세지를 서버에서 보기 위한 용도
	JScrollPane scroll;
	
	//클라이언트로 부터 메세지 등 다양한 데이터가 올 것이다. 이를 받을 준비를 스트림으로 하자.
	BufferedReader buffr;
	BufferedWriter buffw;
	
	//서버도 클라이언트의 요청에 대해서 독립적으로 수행하기 위해 스레드를 선언한다.
	ServerThread st;
	Socket client;
	Vector<ServerThread> list = new Vector<ServerThread>(); //ArrayList 와 동일 하지만 동시성 문제에 있어서는 vector가 우수하여 vector를 쓴다.
	
	public GUIServer() {
		
		setTitle("파팅 서버 GUI 창");
		area = new JTextArea();
		area.setBackground(Color.GREEN);
		scroll = new JScrollPane(area);
		this.add(scroll);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,600);
		setVisible(true);
		
		startServer();
	}
	
	public void startServer(){
		try {
			server = new ServerSocket(7878);
			area.append("파팅 서버가 가동 되었습니다.(port : 7878)     아직 클라리언트의 요청은 없습니다. \n");
			
			while(true){
				client = server.accept(); // 서버에 들어온 정보를 accept()메서드를 통해서 client 소켓에 정보를 저장한다. 전화기와 같다.
				st = new ServerThread(this,list);//매개변수로 값을 넘겨주자!
				list.add(st);
				st.start();
				
				String ip =client.getInetAddress().getHostAddress();
				area.append(ip+"님 접속중...\n");
				
				String data = st.receiveMsg; //어떤 메세지가 올 것이므로 일단 변수 선언해 놓자.
			    area.append(ip + "님 왈 " +data +"\n"); //내가 메세지를 안보내도 이미 수행된다. 스레드의 독립성 때문에...
					
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		new GUIServer();
	}
}
