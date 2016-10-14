/*
 * 서버와의 로그인이 이루어지는 시점부터 서버와의 모든 통신을 담당하는 쓰레드!!
 * */
package com.pating.login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pating.friendList.TestMain_FriendList;

public class StateThread extends Thread {
	String TAG=this.getClass().getName();
	LoginForm loginForm;
	Socket client;
	BufferedReader buffr;
	BufferedWriter buffw;
	boolean flag = true;

	public StateThread( LoginForm loginForm, Socket client) {
		this.loginForm=loginForm;
		this.client = client;

		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 서버의 모든 이벤트 청취!!!
	public void listen() {
		try {
			String msg = buffr.readLine();
			
			System.out.println(TAG+", 서버로부터 날아온 json 데이터:"+msg);
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject=(JSONObject)jsonParser.parse(msg);
			
			/*-------------------------------------------------------------------------------
			  로그인 이후에, 로그인 결과 응답받기 
			  1.로그인 유저의 신상정보 가져오기
			  2.친구목록 가져오기
			 -------------------------------------------------------------------------------*/
			if(jsonObject.get("response").equals("login")){
				System.out.println(TAG+"로그인 결과 받음");	
				
				if(jsonObject.get("result").equals("ok")){
					JSONObject obj=(JSONObject)jsonObject.get("data");
					String name=(String)obj.get("m_name");
					String member_id = (String)obj.get("member_id");
					int me = Integer.parseInt(member_id) ;
					
					String path=(String)obj.get("pic");//
					String nick = (String)obj.get("nickname");//
					String status  = (String)obj.get("status"); 	//
					//채팅 창 띄우기!!!
					loginForm.showMsg("인증되었습니다.");
					
					//친구정보를 집합형태(ArrayList)로 구성하여 ,인수로 전달... 
					new TestMain_FriendList(me ,path,nick,status);
				}else{
					loginForm.showMsg("로그인 정보가 올바르지 않습니다.");
				}
			}
			/*-------------------------------------------------------------------------------
			  다른 사용자가 로그인시, 그 정보를 받기 
			 -------------------------------------------------------------------------------*/
			if(jsonObject.get("response").equals("roommateLogin")){
				
				if(jsonObject.get("result").equals("ok")){
					
					JSONObject obj=(JSONObject)jsonObject.get("data");
					String name=(String)obj.get("m_name");
					String member_id = (String)obj.get("member_id");
					int me = Integer.parseInt(member_id) ;
					
					String path=(String)obj.get("pic");//
					String nick = (String)obj.get("nickname");//
					String status  = (String)obj.get("status"); 	//

					loginForm.showMsg("룸메이트 정보를 받았습니다.");
				}else{
					loginForm.showMsg("룸메이트 정보 받기 실패!!");
				}
			}
			/*-------------------------------------------------------------------------------
			  서버가 채팅 대화를 보낼경우 그 정보받기
			 -------------------------------------------------------------------------------*/
			if(jsonObject.get("response").equals("chat")){
				
			}
			/*-------------------------------------------------------------------------------
				회원 가입 하기
			 -------------------------------------------------------------------------------*/

			if(jsonObject.get("result").equals("ok")){
				JSONObject obj=(JSONObject)jsonObject.get("data");
				String name=(String)obj.get("m_name");
				
				System.out.println("회원가입 성공");
			}else if(jsonObject.get("result").equals("fail")){
				System.out.println("회원가입 실패");
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}


	}

	public void send(String msg) {
		try {
			System.out.println(TAG+","+msg);
			buffw.write(msg + "\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (flag) {
			listen();
		}
	}
}
