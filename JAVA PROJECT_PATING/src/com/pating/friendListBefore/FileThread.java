package com.pating.friendListBefore;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileThread extends Thread{
	Socket client;
	BufferedReader buffr;
	BufferedWriter buffw;

	StringBuffer sb = new StringBuffer();
	
	FileOutputStream fos;
	InputStream is;
	String m_pic_x;
	int bytecount=1024;
	public FileThread(Socket client,BufferedReader buffr,BufferedWriter buffw,String m_pic_x ) {
		this.client=client;
		this.buffr=buffr;
		this.buffw=buffw;
		this.m_pic_x=m_pic_x;

	}
	public void run() {
		fileListen();
	}
	
	public void fileListen(){
		try {
		/*	//String msg = buffr.readLine();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(msg);
			
				String m_pic= jsonObject.get("m_pic").toString();*/
				String path="C:/product_img/"+m_pic_x;

				System.out.println("path는"+path);
				try {
					is=client.getInputStream();
					BufferedInputStream in2= new BufferedInputStream(is, 1024);
					fos = new FileOutputStream(path);
					byte[] b = new byte[1024];
					
					int data=0;
					while((data=in2.read(b,0,1024))!=-1){
						bytecount+=bytecount;
						fos.write(b,0,data);
						System.out.println("data는"+data);
						fos.flush();
					
						System.out.println(bytecount);
					}
					
					
				
					//System.out.println("전송이 완료되었습니다.");
					} catch (IOException e) {
						e.printStackTrace();
				
					}
	
		
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
}