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
	JTextArea area; // ����� ���� �ְ� �޴� �޼����� �������� ���� ���� �뵵
	JScrollPane scroll;
	
	//Ŭ���̾�Ʈ�� ���� �޼��� �� �پ��� �����Ͱ� �� ���̴�. �̸� ���� �غ� ��Ʈ������ ����.
	BufferedReader buffr;
	BufferedWriter buffw;
	
	//������ Ŭ���̾�Ʈ�� ��û�� ���ؼ� ���������� �����ϱ� ���� �����带 �����Ѵ�.
	ServerThread st;
	Socket client;
	Vector<ServerThread> list = new Vector<ServerThread>(); //ArrayList �� ���� ������ ���ü� ������ �־�� vector�� ����Ͽ� vector�� ����.
	
	public GUIServer() {
		
		setTitle("���� ���� GUI â");
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
			area.append("���� ������ ���� �Ǿ����ϴ�.(port : 7878)     ���� Ŭ�󸮾�Ʈ�� ��û�� �����ϴ�. \n");
			
			while(true){
				client = server.accept(); // ������ ���� ������ accept()�޼��带 ���ؼ� client ���Ͽ� ������ �����Ѵ�. ��ȭ��� ����.
				st = new ServerThread(this,list);//�Ű������� ���� �Ѱ�����!
				list.add(st);
				st.start();
				
				String ip =client.getInetAddress().getHostAddress();
				area.append(ip+"�� ������...\n");
				
				String data = st.receiveMsg; //� �޼����� �� ���̹Ƿ� �ϴ� ���� ������ ����.
			    area.append(ip + "�� �� " +data +"\n"); //���� �޼����� �Ⱥ����� �̹� ����ȴ�. �������� ������ ������...
					
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
