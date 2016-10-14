package com.pating.login;

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
import java.util.HashMap;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServerThread extends Thread {
	String TAG=this.getClass().getName();
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
	boolean flag = true;
	

	// 접속한 친구이면서 채팅방 멤버에게 데이터를 보내기 위해 HashMap 생성
	HashMap<Long, ServerThread> RoommateList = new HashMap<Long, ServerThread>();

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

	// 占쏙옙占�
	public void listen() {

		try {
			String msg = buffr.readLine();

			System.out.println("buffr.readLine() : +++" + msg);
			// msg占쏙옙 클占쏙옙占싱억옙트占쏙옙 占쏙옙청 타占쌉울옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
			// 占쏙옙占쏙옙占쏙옙
			// 占쌨띰옙占쏙옙 占쏙옙 占쌍댐옙 (占쏙옙 占쏙옙占쏙옙占쏙옙 채占쏙옙占쏙옙 占싣니댐옙.)
			jsonParser = new JSONParser();
			try {
				sb.delete(0, sb.length());
				jsonObject = (JSONObject) jsonParser.parse(msg);
				
				/*-------------------------------------------------------------------------------
				 * 로그인요청이 들어오면 
				 * 1) 로그인한 사람의 신상 정보 보내기 
				 * 2) 현재 접속자 목록 보내기
				 * 3) 채팅방 목록 보내기
				 -------------------------------------------------------------------------------*/
				if (jsonObject.get("request").equals("login")) {
					String sql = "select * from member where m_id=? and m_pwd=?";

					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, jsonObject.get("m_id").toString());
					pstmt.setString(2, jsonObject.get("m_pwd").toString());
					rs = pstmt.executeQuery();
					
					sb.delete(0, sb.length());
					
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
						//현재 접속자 정보 가져오기
						sb.append("\"memberIdList\" : [");
						for (int i = 0; i < patingServer.memberIdList.size(); i++) {
							sb.append("{");
							sb.append("\"m_id\" :");
							if (i == patingServer.memberIdList.size() - 1) {
								sb.append(patingServer.memberIdList.get(i));
								sb.append("}");
							} else {
								sb.append(patingServer.memberIdList.get(i) + ",");
								sb.append("},");
							}
						}
						sb.append("],");
						//나의 친구들..가져오기 
						sql = "select member_id,m_name,m_nickname,m_pic,m_status from member where member_id in (select f_you from friend where f_me="+ member_id + ")";
						
						pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						rs=pstmt.executeQuery();
						
						sb.append("\"friendList\":[");
						
						rs.last();
						int total=rs.getRow();
						rs.beforeFirst();
						
						for(int i=0;i<total;i++){
							if(!rs.next())break;
							sb.append("{");
							sb.append("\"member_id\":"+rs.getInt("member_id"));
							sb.append(",\"m_name\" : \""+rs.getString("m_name")+"\"");
							sb.append(",\"m_nickname\" : \""+rs.getString("m_nickname")+"\"");
							sb.append(",\"m_pic\" : \""+rs.getString("m_pic")+"\"");
							sb.append(",\"m_status\" :\""+rs.getString("m_status")+"\"");
							if(i < total-1){
								sb.append("},");
							}else{
								sb.append("}");
							}
						}
						sb.append("],");							
						sb.append("\"chattingList\" : [");
						
						sql = "select r.ROOM_NO as room_no,  r.R_TITLE as r_title , c.c_msg as c_msg, max(c_time) from room r left outer join ";
						sql = sql + " (select * from chat where chat_no in (";
						sql = sql + " select max(chat_no) as chat_no from chat";
						sql = sql + " group by c_roomno)) c";
						sql = sql + " on c.C_ROOMNO = r.ROOM_NO";
						sql = sql + " group by r.ROOM_NO,  r.R_TITLE, c.c_msg ";

						pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						rs = pstmt.executeQuery();

						rs.last();
						total = rs.getRow();
						rs.beforeFirst();
						
						for(int i=0;i<total;i++){
							rs.next();
							sb.append("{");
							sb.append("\"room_no\" : "+rs.getInt("room_no")+", ");
							sb.append("\"r_title\" : \""+rs.getString("r_title")+"\",");
							sb.append("\"c_msg\" : \""+rs.getString("c_msg")+"\"");
							
							if(i < total-1){
								sb.append("},");
							}else{
								sb.append("}");
							}
						}
						 sb.append("]");
						sb.append("}");
					} else {
						sb.append("\"result\" :\"fail\",");
						sb.append("\"data\": {");
						sb.append("}");
					}
					sb.append("}");
					
					System.out.println(TAG+":"+sb.toString());
					// release(pstmt,rs);
					
					//로그인 할 경우, 로그인 ID를 명단에 key값으로 추가한다!!
					patingServer.accessList.put(myId, this);
					Set<Long> set=patingServer.accessList.keySet();
					int total=set.size();
					patingServer.area.append("현재 "+total+"접속\n");
					
					//접속자 id 명단만 저장할 용도..
					patingServer.memberIdList.add(myId);

					/*-------------------------------------------------------------------------------
					 * 회원가입 요청이 들어오면
					 -------------------------------------------------------------------------------*/
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

					rs = pstmt.executeQuery();
					sb.delete(0, sb.length());

					// rs占쏙옙 占쏙옙占쌘울옙占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙占쏙옙占쏙옙
					sb.append("{");
					sb.append("\"response\" : \"member_join\",");
					if (rs.next()) {
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

					/*-------------------------------------------------------------------------------
					  잘모름..
					 -------------------------------------------------------------------------------*/
				} else if (jsonObject.get("request").equals("regist")) {

					/*-------------------------------------------------------------------------------
					  채팅요청이 들어오면..
					 -------------------------------------------------------------------------------*/
				} else if (jsonObject.get("request").equals("chat")) {// 클占쏙옙占싱억옙트占쏙옙
					// 임권창 작성부분
					// 와우 드디어 여기서 채팅을 하면된다.!!!!!!!!
					String sendMsgToRoommate = (String) jsonObject.get("msg");
					System.out.println(sendMsgToRoommate + "여기까진 나올듯" + patingServer.RoommateIdList.size());

					for (int i = 0; i < patingServer.RoommateIdList.size(); i++) {
						System.out.println("00" + i);
						ServerThread st = patingServer.accessList.get(patingServer.RoommateIdList.get(i));
						// RoommateList.put(RoommateIdList.get(i),st);
						sb.delete(0, sb.length());
						sb.append("{");
						sb.append("\"result\" : \"ok\",");
						
						sb.append("\"msg\" : \"" + sendMsgToRoommate + "\"");
						sb.append("}");
						st.sendMsg(sb.toString());
					}
					return;

					/*-------------------------------------------------------------------------------
					  채팅방 목록 요청이 들어오면..
					 -------------------------------------------------------------------------------*/
				} else if (jsonObject.get("request").equals("chattingList")) {
					long me = (Long) jsonObject.get("member_id");

					String sql = "select r.ROOM_NO as room_no,  r.R_TITLE as r_title , c.c_msg as c_msg, max(c_time) from room r left outer join ";
					sql = sql + " (select * from chat where chat_no in (";
					sql = sql + " select max(chat_no) as chat_no from chat";
					sql = sql + " group by c_roomno)) c";
					sql = sql + " on c.C_ROOMNO = r.ROOM_NO";
					sql = sql + " group by r.ROOM_NO,  r.R_TITLE, c.c_msg ";

					pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = pstmt.executeQuery();

					rs.last();
					int total = rs.getRow();

					sb.delete(0, sb.length());

					sb.append("{");
					sb.append("\"response\" : \"chattingList\",");
					sb.append("\"result\" :\"ok\",");
					sb.append("\"memberIdList\" : [");
					for (int i = 0; i < patingServer.memberIdList.size(); i++) {
						sb.append("{");
						sb.append("\"m_id\" :");
						if (i == patingServer.memberIdList.size() - 1) {
							sb.append(patingServer.memberIdList.get(i));
							sb.append("}");
						} else {
							sb.append(patingServer.memberIdList.get(i));
							sb.append("},");
						}
						
					}
					sb.append("],");
					sb.append("\"data\": [");

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
					System.out.println(TAG+"채팅방 요청에 대한 결과 JSON:" + sb.toString());

					/*-------------------------------------------------------------------------------
					  채팅방 추가 요청이 들어오면..
					  1.채팅방을 생성하면서 동시에 채팅을 나누게 될 룸메이트 정보도 넘어오게 됨
					 -------------------------------------------------------------------------------*/
				} else if (jsonObject.get("request").equals("addChattingRoom")) {
					System.out.println(TAG+" 룸생성 성공");
					System.out.println(TAG+"클라이언트가 넘긴 json : "+msg);

					// 채팅하기 원하는 그룹 파싱
					JSONObject jsonObj = (JSONObject) jsonParser.parse(msg);
					JSONArray roommateId = (JSONArray) jsonObj.get("roommateNo");

					for (int i = 0; i < roommateId.size(); i++) {
						JSONObject obj = (JSONObject) roommateId.get(i);
						/* RoommateList.put((Long)obj.get(i),this); */
						patingServer.RoommateIdList.add((Long) obj.get("memberNo"));
						System.out.println(patingServer.RoommateIdList.get(i) + "나는 몇인가?*****************************");
					}

					String sql = "insert into room(room_no, r_title, r_captain) values(seq_room.nextval, ?, ?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, jsonObject.get("r_title").toString());
					pstmt.setLong(2, (Long) jsonObject.get("member_id"));

					int result = pstmt.executeUpdate();
					if (result != 0) {

						sb.delete(0, sb.length());
						// 클라이언트에게 피드백 해주고, 룸 리스트 화면 갱신
						// result = ok와 클라이언트에게 room_no와,r_title과,c_msg를 보낸다.
						sb.append("{");
						sb.append("\"response\" : \"addChattingRoom\",");
						sb.append("\"result\" : \"ok\",");
						sb.append("\"memberIdList\" : [");
						for (int i = 0; i < patingServer.memberIdList.size(); i++) {
							sb.append("{");
							sb.append("\"m_id\" :");
							if (i == patingServer.memberIdList.size() - 1) {
								sb.append(patingServer.memberIdList.get(i));
								sb.append("}");
							} else {
								sb.append(patingServer.memberIdList.get(i) + ",");
								sb.append("},");
							}
						}
						sb.append("]");
						sb.append("}");
					} else {
						sb.append("{");
						sb.append("\"response\" : \"addChattingRoom\",");
						sb.append("\"result\" : \"fail\"");
						sb.append("}");

					}
					/*-------------------------------------------------------------------------------
					  친구 목록 요청이 들어오면..
					 -------------------------------------------------------------------------------*/
				} else if (jsonObject.get("request").equals("friendList")) {
					long me = (Long) jsonObject.get("member_id");
					String sql = "select member_id,m_name,m_nickname,m_pic,m_status from member where member_id in (select f_you from friend where f_me="
							+ me + ")";
					pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = pstmt.executeQuery();

					// rs占쏙옙 커占쏙옙 占쏙옙치占쏙옙 占쏙옙占쌘듸옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙
					// 占신깍옙占�
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
				}

				sendMsg(sb.toString());// 占쌕쏙옙 占쏙옙占쏙옙占싹깍옙
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				sb.delete(0, sb.length());

				sb.append("{");
				sb.append("\"response\" : \"member_join\",");
				sb.append("\"result\" :\"fail\",");
				sb.append("\"data\": {");
				sb.append("}");
				sb.append("}");
				sendMsg(sb.toString());// 占쌕쏙옙 占쏙옙占쏙옙占싹깍옙
			}

		} catch (IOException e) {
			// e.printStackTrace();
			flag = false;
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
		while (flag) {
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
