package com.pating.login;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServerThread1 extends Thread{
	Socket client;
	BufferedReader buffr;
	BufferedWriter buffw;
	PatingServer patingServer;
	Connection con;
	StringBuffer sb = new StringBuffer();

	JSONParser jsonParser;
	JSONObject jsonObject;
	
	public static String m_pic1;
	InputStream is;
	FileOutputStream fos;
	int bytecount=1024;
	
	public ServerThread1(PatingServer patingServer, Socket client) {
		this.patingServer = patingServer;
		this.client = client;
		con = patingServer.con;
		System.out.println("서버쓰레드 1 출동");
		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void listen() {
		try {
			String msg = buffr.readLine();
			System.out.println("buffr.readLine() : " + msg);
			jsonParser = new JSONParser();
			sb.delete(0, sb.length());
			jsonObject = (JSONObject) jsonParser.parse(msg);
		if (jsonObject.get("request").equals("imgchangefile")){
			try {
				/*	//String msg = buffr.readLine();
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObject = (JSONObject) jsonParser.parse(msg);
					
						String m_pic= jsonObject.get("m_pic").toString();*/
						String path="C:/product_img/"+m_pic1;

						System.out.println("path?��"+path);
						is=client.getInputStream();
						BufferedInputStream in2= new BufferedInputStream(is, 1024);
						fos = new FileOutputStream(path);
						byte[] b = new byte[1024];
						
						int data=0;
						while((data=in2.read(b,0,1024))!=-1){
							bytecount+=bytecount;
							fos.write(b,0,data);
							System.out.println("data?��"+data);
							fos.flush();
						
							System.out.println(bytecount);
						}
						//System.out.println("?��?��?�� ?��료되?��?��?��?��.");
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		}
			
	public void run() {
		while (true) {
			listen();
		}

	}
}
