/*
    1.Echo �ý���
    	-���� : ���� 1���� �����ڸ� ó���� �� �ִ�.
    
    2.Unicasting
    3.MultiCasting
    
     ���� ������ GUI�� ��������!!!!
     
     
 * */

package com.sds.echo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIServer extends JFrame implements ActionListener,Runnable{
	
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	ServerSocket server;// ���� ������ ����!! ��ȭ�� �ƴ�!!
	Thread accepThread;//������ ������ ������
	BufferedReader buffr;
	BufferedWriter buffw;
	

	public GUIServer() {
		p_north =new JPanel();
		t_port =new JTextField("8888",7);
		bt =new JButton("����");
		area=new JTextArea();
		scroll = new JScrollPane(area);
		
		
		bt.addActionListener(this);
		
		area.setBackground(Color.YELLOW);
		p_north.add(t_port);
		p_north.add(bt);
		
		add(p_north,BorderLayout.NORTH);
		add(scroll);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(600,100,300,400);
		setVisible(true);
		
	}
	//���� ���� �޼���
	
	public void startServer(){
		String port=t_port.getText();
		try {
			server=new ServerSocket(Integer.parseInt(port));
			area.append("���� ���� �Ϸ�\n");
			
			
			
			Socket client=server.accept();// ���� ��⿡ ������.
			String ip=client.getInetAddress().getHostAddress();
			
			area.append(ip+"�� ����\n");
			
			//������ ������ ��ȭ�� �ϱ� �����̹Ƿ�, ��Ʈ���� ��ٷ� ����!!!!
			buffr= new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw= new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
			//��� ������
			while(true){
				String msg=buffr.readLine();//Ŭ���̾�Ʈ�� ���� �޼���
				area.append(msg+"\n");//��ȭ ����͸�
				
				buffw.write(msg+"\n");
				buffw.flush();
				
			}
			
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "��Ʈ��ȣ�� ���ڷ� ��������!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		startServer();
	}
	
	public void actionPerformed(ActionEvent e) {
		//������ �����ϱ� ���� ����
		accepThread = new Thread(this);
		accepThread.start();
		
		//��ư ��Ȱ��ȭ
		bt.setEnabled(false);
	}
	
	public static void main(String[] args) {
		new GUIServer();
	}
	
}
