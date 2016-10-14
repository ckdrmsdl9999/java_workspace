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
	
	Socket client;//��ȭ�� ����!!
	String ip="70.12.112.119";
	int port=8889;
	BufferedReader buffr;//�Է¿� ��Ʈ��
	BufferedWriter buffw;//��¿� ��Ʈ��
	
	
	public ChatClient() {
		txt= new JTextField(15);
		txtarea=new JTextArea();
		button = new JButton("��  ��");
		scroll =new JScrollPane(txtarea);
		pan =new JPanel();
		
		
		button.addActionListener(this);
		
		pan.add(txt);
		pan.add(button);
		
		
		add(scroll);
		add(pan,BorderLayout.SOUTH);

		//�ؽ�Ʈ �ʵ�� ������ ����
		txt.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==KeyEvent.VK_ENTER){
					//������ �޼��� ������
					sendMsg();
				}
			}
		});
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 400);
		setLocationRelativeTo(null);
		
	}
	//������ �����ϴ� �޼��� ����
	public void connect(){
		//ip�� ��Ʈ ��ȣ�� �̿��Ͽ� ������ ������ ����
		try {
			client=new Socket(ip, port);// ������ �߻�
			
			buffr=new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			System.out.println("ip�� Ȯ�����ּ���");
		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println("��Ʈ��ũ ������ �߻��߾��!!");
		}
		
	}
	//������ �޼��� ���� �޼���
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
