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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServerThread extends Thread {
	Socket client;
	BufferedReader buffr;
	BufferedWriter buffw;
	PatingServer patingServer;

	PreparedStatement pstmt;
	ResultSet rs;
	Connection con;
	StringBuffer sb = new StringBuffer();

	JSONParser jsonParser;
	JSONObject jsonObject;

	Long myId;

	public static String m_pic1;
	InputStream is;
	FileOutputStream fos;
	int bytecount = 1024;

	public ServerThread(PatingServer patingServer, Socket client) {
		this.patingServer = patingServer;
		this.client = client;
		con = patingServer.con;

		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �뜝�룞�삕�뜝占�
	public void listen() {
		// System.out.println("�뜝�룞�삕�뜝�룞�삕黎귛뜝占�?");
		try {
			String msg = buffr.readLine();
			// patingServer.area.append(msg+"�뜝�룞�삕�뜝�떎�뒗嫄곕뙋�삕?\n");
			System.out.println(msg);
			// msg�뜝�룞�삕 �겢�뜝�룞�삕�뜝�떛�뼲�삕�듃�뜝�룞�삕 �뜝�룞�삕泥� ���뜝�뙃�슱�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
			// �뜝�뙣�씛�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�뙇�뙋�삕 (�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 梨꾢뜝�룞�삕�뜝�룞�삕 �뜝�떍�땲�뙋�삕.)
			jsonParser = new JSONParser();
			
			sb.delete(0, sb.length());
			// �뜝�떇�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�떢�뙋�삕 �뜝�룞�삕�뜝�뙓�슱�삕�뜝�룞�삕 �뜝��怨ㅼ삕�뜝�뙥�뙋�삕 �뜝�룞�삕�뜝�떛�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
			// �뜝�룞�삕�뜝�룞�삕�뜝�떢紐뚯삕 �뜝�룞�삕移� �뜝�룞�삕泥� 泥섇뜝�룞�삕 �뜝�룞�삕�뜝占� �뜝�룞�삕 �뜝�룞�삕 �뜝�뙇�뙋�삕.
			jsonObject = (JSONObject) jsonParser.parse(msg);
			System.out.println("�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕?");
			// �겢�뜝�룞�삕�뜝�떛�뼲�삕�듃�뜝�룞�삕 �뜝�룞�삕泥��뜝�룞�삕 �뜝�떥源띿삕�뜝�룞�삕 �뜝�떛�씛�삕�뜝占�....
			if (jsonObject.get("request").equals("login")) {
				patingServer.area.append("�뜝�떥源띿삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�떦�뙋�삕 �뜝�룞�삕�뜝�룞�삕");

				// �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
				String sql = "select * from member where m_id=? and m_pwd=?";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, jsonObject.get("m_id").toString());
				pstmt.setString(2, jsonObject.get("m_pwd").toString());

				rs = pstmt.executeQuery();// select�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝占�

				// StringBuffer �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕!!1
				sb.delete(0, sb.length());

				// rs�뜝�룞�삕 �뜝�룞�삕�뜝�뙓�슱�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�슱�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
				sb.append("{");
				sb.append("\"response\" : \"login\",");
				if (rs.next()) {
					String name = rs.getString("m_name");
					int member_id = rs.getInt("member_id");
					myId=(long)member_id;
					String nickname = rs.getString("m_nickname");
					String pic = rs.getString("m_pic");
					String id = rs.getString("m_id");
					String status = rs.getString("m_status");
					System.out.println(name);
					sb.append("\"result\" :\"ok\",");
					sb.append("\"data\": {");
					sb.append("\"m_name\" : \"" + name + "\",");
					sb.append("\"m_id\" : \"" + id + "\",");
					sb.append("\"member_id\" : \"" + member_id + "\",");
					sb.append("\"nickname\" : \"" + nickname + "\",");
					sb.append("\"pic\" : \"" + pic + "\",");
					sb.append("\"status\" : \"" + status + "\",");
					//sb.append("\"map\" : \"" + patingServer.AccessList + "\",");
					sb.append("}");
				} else {
					sb.append("\"result\" :\"fail\",");
					sb.append("\"data\": {");
					sb.append("}");
				}
				sb.append("}");
				System.out.println(sb.toString());
				// release(pstmt,rs);
				patingServer.AccessList.put(myId, this);
				patingServer.memberIdList.add(myId);
/*					Set <Long> AccessSet =PatingServer.AccessList.keySet();
					System.out.println("�젒�냽�떆 accessList 二쇱냼: "+PatingServer.AccessList);
					
					long[] accessMemberNo= new long[AccessSet.size()];
					System.out.println("accessMemberNo �젒�냽�옄�닔: "+AccessSet.size());
					for(int i=0; i<AccessSet.size(); i++){
						accessMemberNo[i]= AccessSet.iterator().next();
						System.out.println("accessMember:"+accessMemberNo[i]);
					}
*/					
			} else if (jsonObject.get("request").equals("member_join")) {// �겢�뜝�룞�삕�뜝�떛�뼲�삕�듃�뜝�룞�삕
																			// �뜝�룞�삕泥��뜝�룞�삕
																			// �뜝�룞�삕�뜝�룞�삕�뜝�떛�씛�삕�뜝占�...
				StringBuffer sql = new StringBuffer();
				sql.append("insert into member(");
				sql.append("member_id,m_id,m_pwd,m_name,m_nickname");
				sql.append(")values(seq_member.nextval,?,?,?,?)");

				pstmt = con.prepareStatement(sql.toString());

				pstmt.setString(1, jsonObject.get("m_id").toString());
				pstmt.setString(2, jsonObject.get("m_pwd").toString());
				pstmt.setString(3, jsonObject.get("m_name").toString());
				pstmt.setString(4, jsonObject.get("m_nickname").toString());

				int result = pstmt.executeUpdate();
				sb.delete(0, sb.length());

				// rs�뜝�룞�삕 �뜝�룞�삕�뜝�뙓�슱�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�슱�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
				sb.append("{");
				sb.append("\"response\" : \"member_join\",");
				if (result!=0) {
					String name = jsonObject.get("m_name").toString();
					System.out.println(name);
					sb.append("\"result\" :\"ok\",");
					sb.append("\"data\": {");
					sb.append("\"m_name\" : \"" + name + "\",");
					sb.append("}");
				} else {
					sb.append("\"result\" :\"fail\",");
					sb.append("\"data\": {");
					sb.append("}");
				}
				sb.append("}");
				System.out.println(sb.toString());

				// �뜝�룞�삕泥��뜝�룞�삕
				// �뜝�룞�삕�솕�뜝�룞�삕�뜝占�...

			} else if (jsonObject.get("request").equals("makefriend")) {//영택 : 클라이언트의 요청이 친구맺기일때
				StringBuffer sql=new StringBuffer();
				sql.append("insert into friend(");
				sql.append("friend_no,f_me,f_you");
				sql.append(") values(seq_friend.nextval,?,?)");				
				pstmt=con.prepareStatement(sql.toString());				
				pstmt.setInt(1, Integer.parseInt(jsonObject.get("f_me").toString()));
				pstmt.setInt(2, Integer.parseInt(jsonObject.get("f_you").toString()));
				
				sql.delete(0, sql.length());
				
				sql.append("insert into friend(");
				sql.append("friend_no,f_me,f_you");
				sql.append(") values(seq_friend.nextval,?,?)");				
				pstmt=con.prepareStatement(sql.toString());				
				pstmt.setInt(1, Integer.parseInt(jsonObject.get("f_you").toString()));
				pstmt.setInt(2, Integer.parseInt(jsonObject.get("f_me").toString()));				
				
				int result = pstmt.executeUpdate();				
				System.out.println(result);
				String name=jsonObject.get("nickname").toString();
				System.out.println("insert쿼리문 수행후 이름"+name);
				sb.delete(0, sb.length());
				
				sb.append("{");
				sb.append("\"response\" : \"makefriend\",");
				if(result!=0){					
					sb.append("\"result\" :\"ok\",");
				    sb.append("\"data\": {");
					sb.append("\"nickname\" : \""+name+"\"");
					sb.append("}");
			   }else{
			        sb.append("\"result\" :\"fail\",");
				    sb.append("\"data\": {");
					sb.append("}");
			   }
				sb.append("}");
			
				
			}else if(jsonObject.get("request").equals("friendList")){				
				long me =(long)(jsonObject.get("member_id"));
				System.out.println(me);
				String sql="select * from member inner join friend on member.member_id=friend.f_you and f_me="+me+" order by friend.friend_no desc";
				pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs=pstmt.executeQuery();
				
				//rs의 커서 위치를 레코드의 제일 마지막으로 옮기기
				rs.last();
				int total=rs.getRow();//현재 레코드의 위치를 반환
				
				sb.delete(0, sb.length());//StringBuffer 초기화
				sb.append("{");
				sb.append(	"\"response\" : \"friendList\",");
				sb.append("\"result\" :\"ok\",");
				sb.append("\"data\": [");
				
				int count=0;
				rs.beforeFirst();
				while(rs.next()){
						count++;
						
						long member_id=rs.getLong("member_id");
						String name=rs.getString("m_name");
						String nickname=rs.getString("m_nickname");
						String pic=rs.getString("m_pic");
						String status=rs.getString("m_status");
						
					    sb.append("{");
						sb.append("\"member_id\" : "+member_id+",");
						sb.append("\"name\" : \""+name+"\",");
						sb.append("\"nickname\" : \""+nickname+"\",");
						sb.append("\"pic\" : \""+pic+"\",");
						sb.append("\"status\" : \""+status+"\"");
						
						if(count<=total-1){
							sb.append("},");
						}else{
							sb.append("}");
						}
				}
				sb.append("]");
				sb.append("}");			
			}else if(jsonObject.get("request").equals("memberList")){
				long me =(long)(jsonObject.get("member_id"));
				System.out.println("1");
				String sql="select member_id,m_name,m_nickname,m_pic,m_status from member";
						sql=sql+ " where not member_id="+me+"";
						sql=sql+ " and member_id not in ";
						sql=sql+ "(select f_you from member inner join friend on member.member_id=friend.f_you and f_me="+me+")order by member_id desc";
				pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs=pstmt.executeQuery();				
				
				rs.last();
				int total=rs.getRow();
				
				sb.delete(0, sb.length());
				
				sb.append("{");
				sb.append(	"\"response\" : \"memberList\",");
				sb.append("\"result\" :\"ok\",");
				sb.append("\"data\": [");
				System.out.println("2");
				int count=0;
				rs.beforeFirst();
				while(rs.next()){
						count++;
						
						long member_id=rs.getLong("member_id");
						String name=rs.getString("m_name");
						String nickname=rs.getString("m_nickname");
						String pic=rs.getString("m_pic");
						String status=rs.getString("m_status");
						
					    sb.append("{");
						sb.append("\"member_id\" : "+member_id+",");
						sb.append("\"name\" : \""+name+"\",");
						sb.append("\"nickname\" : \""+nickname+"\",");
						sb.append("\"pic\" : \""+pic+"\",");
						sb.append("\"status\" : \""+status+"\"");
						
						if(count<=total-1){
							sb.append("},");
						}else{
							sb.append("}");
						}
				}
				sb.append("]");
			    sb.append("}");
			} else if (jsonObject.get("request").equals("chat")) {// �겢�뜝�룞�삕�뜝�떛�뼲�삕�듃�뜝�룞�삕

			} else if (jsonObject.get("request").equals("chattingList")) {// �겢�뜝�룞�삕�뜝�떛�뼲�삕�듃�뜝�룞�삕
				long me =  (Long)jsonObject.get("member_id");
				
				String sql="select r.ROOM_NO as room_no,  r.R_TITLE as r_title , c.c_msg as c_msg, max(c_time) from room r left outer join ";
				sql=sql+" (select * from chat where chat_no in (";
				sql=sql+" select max(chat_no) as chat_no from chat";
				sql=sql+" group by c_roomno)) c";   
				sql=sql+" on c.C_ROOMNO = r.ROOM_NO";
				sql=sql+" group by r.ROOM_NO,  r.R_TITLE, c.c_msg ";
				
				pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = pstmt.executeQuery();

				rs.last();
				int total = rs.getRow();

				sb.delete(0, sb.length());
				
				sb.append("{");
				sb.append("\"response\" : \"chattingList\",");
				sb.append("\"result\" :\"ok\",");
				sb.append("\"memberIdList\" : [");
				for(int i=0;i<patingServer.memberIdList.size();i++){
					sb.append("{");
					sb.append("\"m_id\" :");
					if(i==patingServer.memberIdList.size()-1){	
						sb.append(patingServer.memberIdList.get(i));														
					}else{
						sb.append(patingServer.memberIdList.get(i)+",");
					}
					sb.append("}");
				}
				sb.append("],");
			sb.append("\"data\": [");
				
				System.out.println("�씠嫄� "+sb.toString());

				int count = 0;
				rs.beforeFirst();
				while (rs.next()) {
					count++;

					long room_no = rs.getInt("room_no");
					String r_title = rs.getString("r_title");
					String c_msg = rs.getString("c_msg");

					sb.append("{");
					sb.append("\"room_no\" : " + room_no + ",");
					sb.append("\"r_title\" : \"" + r_title + "\",");
					sb.append("\"c_msg\" : \"" + c_msg + "\"");

					if (count <= total - 1) {
						sb.append("},");
					} else {	
						sb.append("}");
					}
				}
				sb.append("]");
				sb.append("}");
				System.out.println("筌�袁る샒筌뤴뫖以� JSON:"+sb.toString());

			} else if (jsonObject.get("request").equals("addChattingRoom")){ 
				System.out.println("猷몄깮�꽦 �꽦怨�");
				String sql="insert into room(room_no, r_title, r_captain) values(seq_room.nextval, ?, ?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, jsonObject.get("r_title").toString());
				pstmt.setLong(2, (Long)jsonObject.get("member_id"));
				
				int result=pstmt.executeUpdate();
				if(result!=0){
					
					
					//�겢�씪�씠�뼵�듃�뿉寃� �뵾�뱶諛� �빐二쇨퀬, 猷� 由ъ뒪�듃 �솕硫� 媛깆떊 
					//result = ok�� �겢�씪�씠�뼵�듃�뿉寃� room_no��,r_title怨�,c_msg瑜� 蹂대궦�떎.
					sb.append("{");
					sb.append("\"response\" : \"addChattingRoom\",");
					sb.append("\"result\" : \"ok\"");
					sb.append("}");
				}
			}else if(jsonObject.get("request").equals("imgchange")){//클占쏙옙占싱억옙트占쏙옙 占쏙옙청占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占싱띰옙占? .....
				String sql="update member set m_pic=? where m_id =?";
				
				pstmt=con.prepareStatement(sql);
				
				pstmt.setString(1, jsonObject.get("m_pic").toString());
				pstmt.setString(2, jsonObject.get("m_id").toString());

				int rs=pstmt.executeUpdate();
				String m_pic=jsonObject.get("m_pic").toString();
				m_pic1=m_pic;
				System.out.println(m_pic);
				//rs占쏙옙 占쏙옙占쌘울옙占쏙옙 占쏙옙占쏙옙占싹울옙  占쏙옙占쏙옙占쏙옙占쏙옙
				sb.append("{");
				sb.append(	"\"response\" : \"imgchange\",");
				if(rs!=0){
					//System.out.println(m_pic);
					sb.append("\"result\" :\"ok\",");
				    sb.append("\"data\": {");
					sb.append("\"m_pic\" : \""+m_pic1+"\",");
					sb.append("}");
			   }else{
				   sb.append("\"result\" :\"fail\",");
				    sb.append("\"data\": {");
					sb.append("}");
			   }
				sb.append("}");
				
			}/*else if (jsonObject.get("request").equals("imgchangefile")){
				try {
						//String msg = buffr.readLine();
						JSONParser jsonParser = new JSONParser();
						JSONObject jsonObject = (JSONObject) jsonParser.parse(msg);
						
							String m_pic= jsonObject.get("m_pic").toString();
							String path="C:/product_img/"+m_pic1;

							System.out.println("path?뒗"+path);
							try {
								is=client.getInputStream();
								BufferedInputStream in2= new BufferedInputStream(is, 1024);
								fos = new FileOutputStream(path);
								byte[] b = new byte[1024];
								
								int data=0;
								while((data=in2.read(b,0,1024))!=-1){
									bytecount+=bytecount;
									fos.write(b,0,data);
									System.out.println("data?뒗"+data);
									fos.flush();
								
									System.out.println(bytecount);
								}
								
								
							
								//System.out.println("?쟾?넚?씠 ?셿猷뚮릺?뿀?뒿?땲?떎.");
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
				
			}*/
			
			sendMsg(sb.toString());// �뜝�뙐�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦源띿삕
		}catch(

	ParseException e)
	{
		e.printStackTrace();
	}catch(
	SQLException e)
	{
		e.printStackTrace();
	}catch(
	IOException e)
	{
		e.printStackTrace();
	}

	}

	// �뜝�룞�삕�뜝�떦怨ㅼ삕
	public void sendMsg(String msg) {
		try {
			buffw.write(msg + "\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		while (true) {
			listen();
		}

	}

	// �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�떛�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕泥� �뜝�뙠�뙋�삕
	// �뜝�뙣�눦�삕�뜝�룞�삕!!
	// DML(insert,update,delete) �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
	public void release(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// select �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝占�
	public void release(PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
