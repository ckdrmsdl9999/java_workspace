package com.pating.chatting;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pating.friendList.TestMain_FriendList;

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
			String jsonReceiveMsg = TestMain_FriendList.buffr.readLine();
			System.out.println(jsonReceiveMsg+"�� �����");
			
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(jsonReceiveMsg);
			if(obj.get("result").equals("ok")){
				receiveMsg = (String)obj.get("msg");
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
				System.out.println("^____^");
			}else if(obj.get("result").equals("fail")){
				JOptionPane.showMessageDialog(chattingScreen, "메세지 전송이 실패하였습니다. 네트워크 상태를 확인해주세요");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMsg(String sendMsg){
		try {
			//임권창 작성 부분
			StringBuffer sb = new StringBuffer();
			
			sb.append("{");
			sb.append("\"request\" : \"chat\",");
			sb.append("\"msg\" : \""+sendMsg+"\"");
			sb.append("}");
			
			buffw.write(sb.toString() +"\n");
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
