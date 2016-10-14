/*
 * 접속자 마다 1:1로 요청을 처리하기 위한 서버측의
 * 쓰레드!! 소켓을 보관한다.
 * 왜?? 각종 요청이나 대화를 나누기 위해
 * 
 * 
 * */

package com.pating.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pating.Loginform;

public class ServerThread extends Thread{
	Socket client;
	BufferedReader buffr;
	BufferedWriter buffw;
	PatingServer patingServer;
	
	PreparedStatement pstmt;
	ResultSet rs;
	Connection con;
	StringBuffer sb= new StringBuffer();
	
	public ServerThread(PatingServer patingServer,Socket client) {
		this.patingServer=patingServer;
		con=patingServer.con;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw= new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//듣고
	public void listen(){
		//System.out.println("듣긴하니?");
		try {
			String msg=buffr.readLine();
			//patingServer.area.append(msg+"나되는거니?\n");
			System.out.println(msg);
			//msg는 클라이언트의 요청 타입에 따라 서버측의 업무 내용은
			//달라질 수 있다 (즉 언제나 채팅은 아니다.)
			JSONParser jsonParser=new JSONParser();
		try {
			//파싱한 이후 시점 부터는 문자열에 불과했던 제이슨 형식의
			//데이터를 마치 객체 처럼 사용 할 수 있다.
			JSONObject jsonObject=(JSONObject)jsonParser.parse(msg);
			
			//클라이언트의 요청이 로그인 이라면....
			if(jsonObject.get("request").equals("login")){
				patingServer.area.append("로그인을 원하는 군요");
				
			
				//쿼리문 수행
				String sql="select * from member where m_id=? and m_pwd=?";
				
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, jsonObject.get("m_id").toString());
					pstmt.setString(2, jsonObject.get("m_pwd").toString());
					
					rs=pstmt.executeQuery();//select문일 경우
					
					//StringBuffer 사용전 모두 비우기!!1
					sb.delete(0, sb.length());
					
					//rs를 문자열로 가공하여  보내주자
					sb.append("{");
					sb.append(	"\"response\" : \"login\",");
					if(rs.next()){
						String name=rs.getString("m_name");
						System.out.println(name);
						sb.append("\"result\" :\"ok\",");
					    sb.append("\"data\": {");
						sb.append("\"m_name\" : \""+name+"\",");
						sb.append("}");
				   }else{
					   sb.append("\"result\" :\"fail\",");
					    sb.append("\"data\": {");
						sb.append("}");
				   }
					sb.append("}");
					System.out.println(sb.toString());
					//release(pstmt,rs);
			}else if(jsonObject.get("request").equals("member_join")){//클라이언트의 요청이 가입이라면...
				StringBuffer sql=new StringBuffer();
				sql.append("insert into member(");
				sql.append("member_no,m_id,m_pwd,m_name,m_nickname,m_pic,m_status");
				sql.append(")values(seq_member.nextval,?,?,?,?,?,?)");
				
				pstmt=con.prepareStatement(sql.toString());
				
				pstmt.setString(1, jsonObject.get("m_id").toString());
				pstmt.setString(2, jsonObject.get("m_pwd").toString());
				pstmt.setString(3, jsonObject.get("m_name").toString());
				pstmt.setString(4, jsonObject.get("m_nickname").toString());
				pstmt.setString(5, jsonObject.get("m_pic").toString());
				pstmt.setString(6, jsonObject.get("m_status").toString());
				
				rs=pstmt.executeQuery();
				sb.delete(0, sb.length());
				
				//rs를 문자열로 가공하여  보내주자
				sb.append("{");
				sb.append(	"\"response\" : \"member_join\",");
				if(rs.next()){
					String name=jsonObject.get("m_name").toString();
					System.out.println(name);
					sb.append("\"result\" :\"ok\",");
				    sb.append("\"data\": {");
					sb.append("\"m_name\" : \""+name+"\",");
					sb.append("}");
			   }else{
				   sb.append("\"result\" :\"fail\",");
				    sb.append("\"data\": {");
					sb.append("}");
			   }
				sb.append("}");
				System.out.println(sb.toString());
			
			}else if(jsonObject.get("request").equals("chat")){//클라이언트의 요청이 대화라면...
				
				
			}else if(jsonObject.get("request").equals("regist")){//클라이언트의 요청이 회원 가입이라면.....
				
				
			}else if(jsonObject.get("request").equals("userlist")){
				String sql="select * from chatmember";
				pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs=pstmt.executeQuery();
				
				//rs의 커서 위치를 레코드의 제일 마지막으로 옮기기
				rs.last();
				int total=rs.getRow();//현재 레코드의 위치를 반환
				
				sb.delete(0, sb.length());//StringBuffer 초기화
				sb.append("{");
				sb.append(	"\"response\" : \"userlist\",");
				sb.append("\"result\" :\"ok\",");
				sb.append("\"data\": [");
				
				int count=0;
				rs.beforeFirst();
				while(rs.next()){
						count++;
						
						int chatmember_id=rs.getInt("chatmember_id");
						String id=rs.getString("id");
						String password=rs.getString("password");
						String name=rs.getString("name");
						String profile=rs.getString("profile");
						String status=rs.getString("status");
						
					    sb.append("{");
						sb.append("\"chatmember_id\" : "+chatmember_id+",");
						sb.append("\"id\" : \""+id+"\",");
						sb.append("\"password\" : \""+password+"\",");
						sb.append("\"name\" : \""+name+"\",");
						sb.append("\"profile\" : \""+profile+"\",");
						sb.append("\"status\" : \""+status+"\"");
						
						if(count<=total-1){
							sb.append("},");
						}else{
							sb.append("}");
						}
				}
				sb.append("]");
				sb.append("}");
			}else if(jsonObject.get("request").equals("imgchange")){//클라이언트의 요청이 사진 수정이라면 .....
				String sql="update member set m_photo=? where member_no =?";
				
				pstmt=con.prepareStatement(sql);
				
				pstmt.setString(1, jsonObject.get("m_photo").toString());
				pstmt.setString(2, jsonObject.get("member_no").toString());

				int rs=pstmt.executeUpdate();
				
				
				//rs를 문자열로 가공하여  보내주자
				sb.append("{");
				sb.append(	"\"response\" : \"imgchange\",");
				if(rs==1){
					String m_photo=jsonObject.get("m_photo").toString();
					System.out.println(m_photo);
					sb.append("\"result\" :\"ok\",");
				    sb.append("\"data\": {");
					sb.append("\"m_photo\" : \""+m_photo+"\",");
					sb.append("}");
			   }else{
				   sb.append("\"result\" :\"fail\",");
				    sb.append("\"data\": {");
					sb.append("}");
			   }
				sb.append("}");
				
			}
			
			sendMsg(sb.toString());//다시 전송하기
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			sb.delete(0, sb.length());
			
			//rs를 문자열로 가공하여  보내주자
			sb.append("{");
			sb.append(	"\"response\" : \"member_join\",");
		    sb.append("\"result\" :\"fail\",");
		    sb.append("\"data\": {");
			sb.append("}");
			sb.append("}");
			sendMsg(sb.toString());//다시 전송하기
		}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//말하고
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
	//데이터 베이스 관련 객체 닫는 메서드!!
	//DML(insert,update,delete) 문 사용시
	public void release(PreparedStatement pstmt){
		if(pstmt !=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//select 문일 경우
	public void release(PreparedStatement pstmt, ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt !=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
