/*
  Ű���带 ġ�� �ʰ�, ������ �޼����� ������ ���� ������
  û���ؾ� �ϹǷ�, ������ ������� �����Ͽ� �� �۾��� �ñ���. 
 * */

package com.sds.echo2;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.JOptionPane;

public class ClientThread extends Thread{
	ChatClient chatClient;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	
	public ClientThread(ChatClient chatClient) {
		this.chatClient=chatClient;
		try {
			buffr=new BufferedReader(new InputStreamReader(chatClient.client.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(chatClient.client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//������ ���� �޼��� û���ϱ�
	public void listen(){
		try {
			
			String msg=buffr.readLine();
			chatClient.area.append(msg+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void showMsg(String message){
		JOptionPane.showMessageDialog(chatClient, message);
	}
	
	
	//������ �޼��� �����ϱ�
		public void sendMsg(String msg){
			try {
				buffw.write(msg+"\n");
				buffw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	public void run() {
		while(true){
			listen();
		}
	}
}
