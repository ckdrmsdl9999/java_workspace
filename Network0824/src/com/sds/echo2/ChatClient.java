package com.sds.echo2;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame implements ActionListener{
	JPanel p_north;
	Choice ch_ip;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;
	Socket client;//대화용 소켓
	ClientThread ct;
	
	public ChatClient() {
		p_north =new JPanel();
		ch_ip = new Choice();
		t_port = new JTextField("8888",4);
		bt = new JButton("접속");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		t_input =new JTextField();
		
		for(int i=90;i<=121;i++){
			ch_ip.add("70.12.112."+i);
		}
		p_north.add(ch_ip);
		p_north.add(t_port);
		p_north.add(bt);
		
		//버튼과 리스너 연결
		bt.addActionListener(this);
		
		//텍스트 필드와 리스너 연결
		t_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					//서버에 메세지 전송
					ct.sendMsg(t_input.getText());
					ct.listen();
					t_input.setText("");//입력 내용 지우기
				}

			}
		});
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		add(t_input,BorderLayout.SOUTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300,100,300,400);
		setVisible(true);
	}
	//에코 서버에 접속
	public void connect(){
		String ip=ch_ip.getSelectedItem();
		String port=t_port.getText();
	
		
		try {
			client =new Socket(ip, Integer.parseInt(port));
			ct= new ClientThread(this);
			ct.start();//대화형 쓰레드 동작
			
		} catch (NumberFormatException e) {
			ct.showMsg("포트 번호는 정수로 입력해야 합니다.");
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			ct.showMsg("올바르지 않은 주소입니다.");
		} catch (IOException e) {
			e.printStackTrace();
			ct.showMsg("소켓 연결 실패.");
		}
	}
	public void actionPerformed(ActionEvent e) {
		connect();
		bt.setEnabled(false);
	}
	public static void main(String[] args) {
		new ChatClient();
	}

}
