package com.pating.friendList;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class FileThread extends Thread{
	File file;
	BufferedReader buffr;
	BufferedWriter buffw;
	Socket client1;
	int bytecount=1024;
	 String host="70.12.112.119";
	 int port=7676;
	
	FileInputStream fis;
	BufferedOutputStream out;
	OutputStream os;
	
	public FileThread(File file) {
		this.file=file;
		
		try {
			client1=new Socket(host, port);
			
		//	buffr= new BufferedReader(new InputStreamReader(client1.getInputStream()));
			buffw= new BufferedWriter(new OutputStreamWriter(client1.getOutputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	public void run() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("{\"request\": \"imgchangefile\",");
		sb.append("\"m_pic\" :\"" + file.getName() + "\"");
		sb.append("}");
		//System.out.println(sb);
	
		
			try {
				buffw.write(sb.toString() + "\n");
				buffw.flush();
				
				os=client1.getOutputStream();
				out = new BufferedOutputStream(os, 1024);
				fis=new FileInputStream(file);
				byte[] b = new byte[1024];
				int data=0;
				while( (data=fis.read(b,0,1024))!= -1 ){
					
					out.write(b,0,data);
					//System.out.println(data);
					out.flush();
					bytecount+=bytecount;
					System.out.println("바이?��?��"+bytecount);
				}
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}finally{
				
					if(fis != null){
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(out !=null){
						try {
							out.close();
						} catch (IOException e) {

							e.printStackTrace();
						}
					}
					if(os != null){
						try {
							os.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(buffw !=null){
						try {
							buffw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(client1 !=null){
						try {
							client1.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
							
					}
					}
					
				
		//	System.out.println(client1);
	}
}
