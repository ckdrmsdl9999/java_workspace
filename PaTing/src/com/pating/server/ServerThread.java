/*
 * ������ ���� 1:1�� ��û�� ó���ϱ� ���� ��������
 * ������!! ������ �����Ѵ�.
 * ��?? ���� ��û�̳� ��ȭ�� ������ ����
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
	
	//���
	public void listen(){
		//System.out.println("����ϴ�?");
		try {
			String msg=buffr.readLine();
			//patingServer.area.append(msg+"���Ǵ°Ŵ�?\n");
			System.out.println(msg);
			//msg�� Ŭ���̾�Ʈ�� ��û Ÿ�Կ� ���� �������� ���� ������
			//�޶��� �� �ִ� (�� ������ ä���� �ƴϴ�.)
			JSONParser jsonParser=new JSONParser();
		try {
			//�Ľ��� ���� ���� ���ʹ� ���ڿ��� �Ұ��ߴ� ���̽� ������
			//�����͸� ��ġ ��ü ó�� ��� �� �� �ִ�.
			JSONObject jsonObject=(JSONObject)jsonParser.parse(msg);
			
			//Ŭ���̾�Ʈ�� ��û�� �α��� �̶��....
			if(jsonObject.get("request").equals("login")){
				patingServer.area.append("�α����� ���ϴ� ����");
				
			
				//������ ����
				String sql="select * from member where m_id=? and m_pwd=?";
				
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, jsonObject.get("m_id").toString());
					pstmt.setString(2, jsonObject.get("m_pwd").toString());
					
					rs=pstmt.executeQuery();//select���� ���
					
					//StringBuffer ����� ��� ����!!1
					sb.delete(0, sb.length());
					
					//rs�� ���ڿ��� �����Ͽ�  ��������
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
			}else if(jsonObject.get("request").equals("member_join")){//Ŭ���̾�Ʈ�� ��û�� �����̶��...
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
				
				//rs�� ���ڿ��� �����Ͽ�  ��������
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
			
			}else if(jsonObject.get("request").equals("chat")){//Ŭ���̾�Ʈ�� ��û�� ��ȭ���...
				
				
			}else if(jsonObject.get("request").equals("regist")){//Ŭ���̾�Ʈ�� ��û�� ȸ�� �����̶��.....
				
				
			}else if(jsonObject.get("request").equals("userlist")){
				String sql="select * from chatmember";
				pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs=pstmt.executeQuery();
				
				//rs�� Ŀ�� ��ġ�� ���ڵ��� ���� ���������� �ű��
				rs.last();
				int total=rs.getRow();//���� ���ڵ��� ��ġ�� ��ȯ
				
				sb.delete(0, sb.length());//StringBuffer �ʱ�ȭ
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
			}else if(jsonObject.get("request").equals("imgchange")){//Ŭ���̾�Ʈ�� ��û�� ���� �����̶�� .....
				String sql="update member set m_photo=? where member_no =?";
				
				pstmt=con.prepareStatement(sql);
				
				pstmt.setString(1, jsonObject.get("m_photo").toString());
				pstmt.setString(2, jsonObject.get("member_no").toString());

				int rs=pstmt.executeUpdate();
				
				
				//rs�� ���ڿ��� �����Ͽ�  ��������
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
			
			sendMsg(sb.toString());//�ٽ� �����ϱ�
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			sb.delete(0, sb.length());
			
			//rs�� ���ڿ��� �����Ͽ�  ��������
			sb.append("{");
			sb.append(	"\"response\" : \"member_join\",");
		    sb.append("\"result\" :\"fail\",");
		    sb.append("\"data\": {");
			sb.append("}");
			sb.append("}");
			sendMsg(sb.toString());//�ٽ� �����ϱ�
		}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//���ϰ�
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
	//������ ���̽� ���� ��ü �ݴ� �޼���!!
	//DML(insert,update,delete) �� ����
	public void release(PreparedStatement pstmt){
		if(pstmt !=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//select ���� ���
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
