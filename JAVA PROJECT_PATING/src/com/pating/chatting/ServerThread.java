package com.pating.chatting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

public class ServerThread extends Thread{
	
	BufferedReader buffr;
	BufferedWriter buffw;
		
	String receiveMsg;
	GUIServer serverUI;
	Vector<ServerThread> list;
	
	Socket client;
	boolean flag = true;
	public ServerThread(GUIServer serverUI,Vector<ServerThread> list) {
		try {
			this.list = list;
			this.serverUI = serverUI; // 서버 영역에 textarea에 값을 붙일때 쓴다.
			client = serverUI.client;
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listen(){
		try {
			while(flag){
				
				receiveMsg = buffr.readLine();
				serverUI.area.append(receiveMsg);
				
				for(int i=0;i<list.size();i++){
					System.out.println(list.size());
					ServerThread st = list.get(i);
					if(st == this){
						//나한테는 데
					}else {						
						st.sendMsg(receiveMsg);
					}
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag=false;
			list.remove(this);
		}
	}
	
	public void sendMsg(String sendMsg){
		try {
			if(sendMsg==""){
				buffw.flush();
				return;
			}
			buffw.write(sendMsg +"\n");
			buffw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		listen();
	}
}
