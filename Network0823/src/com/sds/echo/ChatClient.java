package com.sds.echo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame implements ActionListener{
	JTextField txt;
	JTextArea	txtarea;
	JButton	button;
	JScrollPane scroll;
	JPanel pan;
	
	Socket client;//대화형 소켓!!
	String ip="70.12.112.119";
	int port=8889;
	BufferedReader buffr;//입력용 스트림
	BufferedWriter buffw;//출력용 스트림
	
	
	public ChatClient() {
		txt= new JTextField(15);
		txtarea=new JTextArea();
		button = new JButton("접  속");
		scroll =new JScrollPane(txtarea);
		pan =new JPanel();
		
		
		button.addActionListener(this);
		
		pan.add(txt);
		pan.add(button);
		
		
		add(scroll);
		add(pan,BorderLayout.SOUTH);

		//텍스트 필드와 리스너 연결
		txt.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==KeyEvent.VK_ENTER){
					//서버에 메세지 보내기
					sendMsg();
				}
			}
		});
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 400);
		setLocationRelativeTo(null);
		
	}
	//서버에 접속하는 메서드 정의
	public void connect(){
		//ip와 포트 번호를 이용하여 서버에 접속을 시작
		try {
			client=new Socket(ip, port);// 접속이 발생
			
			buffr=new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			System.out.println("ip를 확인해주세요");
		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println("네트워크 문제가 발생했어요!!");
		}
		
	}
	//서버에 메세지 전송 메서드
	public void sendMsg(){
		String msg=txt.getText();
		try {
			buffw.write(msg+"\n");
			buffw.flush();
			String data =buffr.readLine();
			txtarea.append(data+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void actionPerformed(ActionEvent e) {
		connect();
	}
	public static void main(String[] args) {
		new ChatClient();

	}

}
