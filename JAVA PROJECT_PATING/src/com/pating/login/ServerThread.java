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
import java.util.HashMap;

import org.json.simple.JSONArray;
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
	int bytecount=1024;

	// 접속한 친구이면서 채팅방 멤버에게 데이터를 보내기 위해 HashMap 생성
	HashMap<Long, ServerThread> RoommateList = new HashMap<Long, ServerThread>();

	public ServerThread(PatingServer patingServer, Socket client) {
		this.patingServer = patingServer;
		this.client = client;
		con = patingServer.con;
		System.out.println("서버 쓰레드 1 출동!");
		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 占쏙옙占�
	public void listen() {

		try {
			String msg = buffr.readLine();

			System.out.println("buffr.readLine() : " + msg);
			// msg占쏙옙 클占쏙옙占싱억옙트占쏙옙 占쏙옙청 타占쌉울옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
			// 占쏙옙占쏙옙占쏙옙
			// 占쌨띰옙占쏙옙 占쏙옙 占쌍댐옙 (占쏙옙 占쏙옙占쏙옙占쏙옙 채占쏙옙占쏙옙 占싣니댐옙.)
			jsonParser = new JSONParser();
			sb.delete(0, sb.length());
			// 占식쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占싶댐옙 占쏙옙占쌘울옙占쏙옙 占쌀곤옙占쌩댐옙 占쏙옙占싱쏙옙
			// 占쏙옙占쏙옙占쏙옙
			// 占쏙옙占쏙옙占싶몌옙 占쏙옙치 占쏙옙체 처占쏙옙 占쏙옙占� 占쏙옙 占쏙옙 占쌍댐옙.
			jsonObject = (JSONObject) jsonParser.parse(msg);
			// 클占쏙옙占싱억옙트占쏙옙 占쏙옙청占쏙옙 占싸깍옙占쏙옙 占싱띰옙占�....
			if (jsonObject.get("request").equals("login")) {
				patingServer.area.append("占싸깍옙占쏙옙占쏙옙 占쏙옙占싹댐옙 占쏙옙占쏙옙");

				// 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
				String sql = "select * from member where m_id=? and m_pwd=?";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, jsonObject.get("m_id").toString());
				pstmt.setString(2, jsonObject.get("m_pwd").toString());

				rs = pstmt.executeQuery();// select占쏙옙占쏙옙 占쏙옙占�

				// StringBuffer 占쏙옙占쏙옙占� 占쏙옙占� 占쏙옙占쏙옙!!1
				sb.delete(0, sb.length());

				// rs占쏙옙 占쏙옙占쌘울옙占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙占쏙옙占쏙옙
				sb.append("{");
				sb.append("\"response\" : \"login\",");
				if (rs.next()) {
					String name = rs.getString("m_name");
					int member_id = rs.getInt("member_id");
					myId = (long) member_id;
					String nickname = rs.getString("m_nickname");
					String pic = rs.getString("m_pic");
					String status = rs.getString("m_status");
					sb.append("\"result\" :\"ok\",");
					sb.append("\"data\": {");
					sb.append("\"m_name\" : \"" + name + "\",");
					sb.append("\"member_id\" : \"" + member_id + "\",");
					sb.append("\"nickname\" : \"" + nickname + "\",");
					sb.append("\"pic\" : \"" + pic + "\",");
					sb.append("\"status\" : \"" + status + "\",");
					// sb.append("\"map\" : \"" + patingServer.AccessList +
					// "\",");
					sb.append("}");
				} else {
					sb.append("\"result\" :\"fail\",");
					sb.append("\"data\": {");
					sb.append("}");
				}
				sb.append("}");
				System.out.println("sb.toString()" + sb.toString());
				// release(pstmt,rs);
				/*
				 * patingServer.AccessList.put(myId, this);
				 * patingServer.memberIdList.add(myId);
				 */

			} else if (jsonObject.get("request").equals("member_join")) {// 클占쏙옙占싱억옙트占쏙옙
																			// 占쏙옙청占쏙옙
																			// 占쏙옙占쏙옙占싱띰옙占�...
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

				// rs占쏙옙 占쏙옙占쌘울옙占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙占쏙옙占쏙옙
				sb.append("{");
				sb.append("\"response\" : \"member_join\",");
				if (result != 0) {
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
				System.out.println("sb.toString" + sb.toString());

				// 占쏙옙청占쏙옙
				// 占쏙옙화占쏙옙占�...

			} else if (jsonObject.get("request").equals("regist")) {// 클占쏙옙占싱억옙트占쏙옙
																	// 占쏙옙청占쏙옙
																	// 회占쏙옙
																	// 占쏙옙占쏙옙占싱띰옙占�.....

			} else if (jsonObject.get("request").equals("chat")) {// 클占쏙옙占싱억옙트占쏙옙

				/*
				 * } else if (jsonObject.get("request").equals("chattingList"))
				 * {// 클占쏙옙占싱억옙트占쏙옙 long me = (Long)jsonObject.get("member_id");
				 * 
				 * String
				 * sql="select r.ROOM_NO as room_no,  r.R_TITLE as r_title , c.c_msg as c_msg, max(c_time) from room r left outer join "
				 * ; sql=sql+" (select * from chat where chat_no in (";
				 * sql=sql+" select max(chat_no) as chat_no from chat";
				 * sql=sql+" group by c_roomno)) c";
				 * sql=sql+" on c.C_ROOMNO = r.ROOM_NO";
				 * sql=sql+" group by r.ROOM_NO,  r.R_TITLE, c.c_msg ";
				 * 
				 * pstmt = con.prepareStatement(sql,
				 * ResultSet.TYPE_SCROLL_INSENSITIVE,
				 * ResultSet.CONCUR_READ_ONLY); rs = pstmt.executeQuery();
				 * 
				 * rs.last(); int total = rs.getRow();
				 * 
				 * sb.delete(0, sb.length());
				 * 
				 * sb.append("{");
				 * sb.append("\"response\" : \"chattingList\",");
				 * sb.append("\"result\" :\"ok\",");
				 * sb.append("\"memberIdList\" : ["); for(int
				 * i=0;i<patingServer.memberIdList.size();i++){ sb.append("{");
				 * sb.append("\"m_id\" :");
				 * if(i==patingServer.memberIdList.size()-1){
				 * sb.append(patingServer.memberIdList.get(i)); }else{
				 * sb.append(patingServer.memberIdList.get(i)+","); }
				 * sb.append("}"); } sb.append("],"); sb.append("\"data\": [");
				 * 
				 * System.out.println("이거 "+sb.toString());
				 * 
				 * int count = 0; rs.beforeFirst(); while (rs.next()) { count++;
				 * 
				 * long room_no = rs.getInt("room_no"); String r_title =
				 * rs.getString("r_title"); String c_msg =
				 * rs.getString("c_msg");
				 * 
				 * sb.append("{"); sb.append("\"room_no\" : " + room_no + ",");
				 * sb.append("\"r_title\" : \"" + r_title + "\",");
				 * sb.append("\"c_msg\" : \"" + c_msg + "\"");
				 * 
				 * if (count <= total - 1) { sb.append("},"); } else {
				 * sb.append("}"); } } sb.append("]"); sb.append("}");
				 * System.out.println("梨꾪똿紐⑸줉 JSON:"+sb.toString());
				 */

			} else if (jsonObject.get("request").equals("addChattingRoom")) {
				System.out.println("룸생성 성공");
				System.out.println(msg);

				// 채팅하기 원하는 그룹 파싱
				JSONObject jsonObj = (JSONObject) jsonParser.parse(msg);
				JSONArray roommateId = (JSONArray) jsonObj.get("roommateNo");

				for (int i = 0; i < roommateId.size(); i++) {
					RoommateList.put((Long) roommateId.get(i), this);
					System.out.println(RoommateList.size() + "제발 되고 집에 가자 제발");
				}

				String sql = "insert into room(room_no, r_title, r_captain) values(seq_room.nextval, ?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, jsonObject.get("r_title").toString());
				pstmt.setLong(2, (Long) jsonObject.get("member_id"));

				int result = pstmt.executeUpdate();
				if (result != 0) {

					// 클라이언트에게 피드백 해주고, 룸 리스트 화면 갱신
					// result = ok와 클라이언트에게 room_no와,r_title과,c_msg를 보낸다.
					sb.append("{");
					sb.append("\"response\" : \"addChattingRoom\",");
					sb.append("\"result\" : \"ok\"");
					sb.append("}");
				}
			} else if (jsonObject.get("request").equals("friendList")) {
				long me = (Long) jsonObject.get("member_id");
				String sql = "select member_id,m_name,m_nickname,m_pic,m_status from member where member_id in (select f_you from friend where f_me="
						+ me + ")";
				pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = pstmt.executeQuery();

				// rs占쏙옙 커占쏙옙 占쏙옙치占쏙옙 占쏙옙占쌘듸옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占신깍옙占�
				rs.last();
				int total = rs.getRow();// 占쏙옙占쏙옙 占쏙옙占쌘듸옙占쏙옙 占쏙옙치占쏙옙 占쏙옙환

				sb.delete(0, sb.length());// StringBuffer 占십깍옙화
				sb.append("{");
				sb.append("\"response\" : \"friendList\",");
				sb.append("\"result\" :\"ok\",");
				sb.append("\"data\": [");

				int count = 0;
				rs.beforeFirst();
				while (rs.next()) {
					count++;

					long member_id = rs.getInt("member_id");
					String name = rs.getString("m_name");
					String nickname = rs.getString("m_nickname");
					String pic = rs.getString("m_pic");
					String status = rs.getString("m_status");

					sb.append("{");
					sb.append("\"member_id\" : " + member_id + ",");
					sb.append("\"name\" : \"" + name + "\",");
					sb.append("\"nickname\" : \"" + nickname + "\",");
					sb.append("\"pic\" : \"" + pic + "\",");
					sb.append("\"status\" : \"" + status + "\"");

					if (count <= total - 1) {
						sb.append("},");
					} else {
						sb.append("}");
					}
				}
				sb.append("]");
				sb.append("}");
			}else if(jsonObject.get("request").equals("imgchange")){//Ŭ���̾�Ʈ�� ��û�� ���� �����̶�� .....
				String sql="update member set m_pic=? where m_id =?";
				
				pstmt=con.prepareStatement(sql);
				
				pstmt.setString(1, jsonObject.get("m_pic").toString());
				pstmt.setString(2, jsonObject.get("m_id").toString());

				int rs=pstmt.executeUpdate();
				String m_pic=jsonObject.get("m_pic").toString();
				m_pic1=m_pic;
				System.out.println(m_pic);
				//rs�� ���ڿ��� �����Ͽ�  ��������
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
				
			}else if (jsonObject.get("request").equals("imgchangefile")){
				try {
						//String msg = buffr.readLine();
						JSONParser jsonParser = new JSONParser();
						JSONObject jsonObject = (JSONObject) jsonParser.parse(msg);
						
							String m_pic= jsonObject.get("m_pic").toString();
							String path="C:/product_img/"+m_pic1;

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

			sendMsg(sb.toString());// 占쌕쏙옙 占쏙옙占쏙옙占싹깍옙
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (

		IOException e) {
			e.printStackTrace();
		}

	}

	// 占쏙옙占싹곤옙
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

	// 占쏙옙占쏙옙占쏙옙 占쏙옙占싱쏙옙 占쏙옙占쏙옙 占쏙옙체 占쌥댐옙 占쌨쇽옙占쏙옙!!
	// DML(insert,update,delete) 占쏙옙 占쏙옙占쏙옙
	public void release(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// select 占쏙옙占쏙옙 占쏙옙占�
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
