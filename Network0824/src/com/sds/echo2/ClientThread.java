/*
  키보드를 치지 않고도, 서버의 메세지는 언제나 무한 루프로
  청취해야 하므로, 별도의 쓰레드로 정의하여 이 작업을 맡기자. 
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
	
	//서버로 부터 메세지 청취하기
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
	
	
	//서버에 메세지 전송하기
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
