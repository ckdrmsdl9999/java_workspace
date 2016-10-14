

package com.sds.echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoSever {
	ServerSocket server;//대화용 x 접속 감지용임.
	BufferedReader buffr;
	BufferedWriter buffw;
	
	public EchoSever() {
		try {
			server= new ServerSocket(8889);//서버 생성
			System.out.println("서버 생성");
			
			//접속자 감지
			//소켓이란? 네트워크의 전반적 기능을 추상화한 객체
			//            따라서 개발자는 네트워크적 지식이 없어도 되며
			//            어떠한 플랫폼에서도 동작을 할 수 있다.
			Socket client=server.accept();
			
			String ip=client.getInetAddress().getHostAddress();
			System.out.println(ip+"님 접속");
		
			//입력과 출력을 무한루프로 처리
			
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
			String data=null;
			while(true){
				
				data=buffr.readLine();//클라이언트의 메서지 받기!!!(입력)
				System.out.println(data);//서버 콘솔에 출력하기
			
				//클라이언트에게 다시 보내기
				buffw.write(data+"\n");//한줄의 끝임을 알려준다.
				buffw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				buffw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		new EchoSever();
	}

}
