package com.pating.chatting;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JPanel;

public class ChatThread extends Thread{
	
	BufferedReader buffr;
	BufferedWriter buffw;
	
	String receiveMsg;
	ChattingScreen chattingScreen;
	
	public ChatThread(ChattingScreen chattingScreen,Socket client) {
		
		this.chattingScreen = chattingScreen;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void listen(){
		try {
			receiveMsg = buffr.readLine();
			System.out.println(receiveMsg +"�� �����");
			
			MessagePanelYou mpy = new MessagePanelYou(receiveMsg);
			JPanel p_message = new JPanel(); //�޼����� �ø���, ���ܳ��� ��ü �г�
			JPanel p_binRight = new JPanel(); //�޼��� �г� �ȿ� �� ������ �ǹ��ϴ� �г�
			
			p_binRight.setPreferredSize(new Dimension(170, 30));
			p_binRight.setBackground(new Color(255,165,0));
			p_message.add(mpy);
			p_message.add(p_binRight);
			p_message.setBackground(new Color(255,165,0));
			
			chattingScreen.p_center.add(p_message,"wrap");
			chattingScreen.p_center.updateUI();
			chattingScreen.p_center.repaint();
			chattingScreen.p_center.revalidate();
			chattingScreen.scroll.updateUI();
			chattingScreen.scroll.revalidate();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMsg(String sendMsg){
		try {
			buffw.write(sendMsg +"\n");
			buffw.flush();
			chattingScreen.p_center.updateUI();
			chattingScreen.p_center.repaint();
			chattingScreen.scroll.revalidate();
			chattingScreen.scroll.repaint();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true){
			listen();
		}
	}
}
