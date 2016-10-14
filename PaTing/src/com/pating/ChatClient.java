package com.pating;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame implements ActionListener{
	JPanel p_north;
	JTextField t_ip;
	JTextField t_port;
	JButton bt_connect;
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt_file;
	Socket client;
	ClientThread ct;
	JFileChooser chooser;
	
	public ChatClient() {
		p_north = new JPanel();
		t_ip = new JTextField("70.12.112.90",12);
		t_port = new JTextField("7777",4);
		bt_connect = new JButton("立加");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(13);
		bt_file = new JButton("颇老眠啊");
		
		p_north.add(t_ip);
		p_north.add(t_port);
		p_north.add(bt_connect);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		p_south.add(t_input);
		p_south.add(bt_file);
		
		add(p_south, BorderLayout.SOUTH);
		
		bt_connect.addActionListener(this);
		bt_file.addActionListener(this);
		
		t_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if(key == KeyEvent.VK_ENTER){
					ct.send("requestType=message&data="+t_input.getText());
					t_input.setText("");
				}
			}
		});
		
		chooser = new JFileChooser();
		
		setBounds(200, 100, 300, 400);
		setVisible(true);		
	}
	
	public void connect(){
		try {
			client = new Socket(t_ip.getText(), Integer.parseInt(t_port.getText()));
			
			if(client!=null && client.isConnected()){
				area.append("立加窃\n");
				
				ct = new ClientThread(this,client);
				ct.start();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendFile(){
		int result=chooser.showOpenDialog(this);
		
		if(result==JFileChooser.APPROVE_OPTION){
			
			
			File file=chooser.getSelectedFile();
			
			ct.send("requestType=file&data=begin");
			
			Thread fileThread = new Thread(){
				public void run() {
					try {
						OutputStream os=ct.client.getOutputStream();
						FileInputStream fis=new FileInputStream(file);
						
						byte[] b = new byte[1024];

						int data;
						
						while( (data=fis.read(b))!=-1 ){
							os.write(b);
						}
						os.flush();
						ct.send("requestType=file&data=end");
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			fileThread.start();
		}
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_connect){
			connect();
		}else if(obj ==bt_file){
			sendFile();
		}
		
	}
	public static void main(String[] args) {
		new ChatClient();
	}
}
