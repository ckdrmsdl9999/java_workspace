/*
 * �꽌踰꾩��쓽 濡쒓렇�씤�씠 �씠猷⑥뼱吏��뒗 �떆�젏遺��꽣 �꽌踰꾩��쓽 紐⑤뱺 �넻�떊�쓣 �떞�떦�븯�뒗 �벐�젅�뱶!!
 * */
package com.pating.login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pating.friendList.TestMain_FriendList;

public class StateThread extends Thread {
	String TAG = this.getClass().getName();
	LoginForm loginForm;
	Socket client;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	public long[] memberAccessList;
	
	boolean flag = true;
	public boolean updateChattingListFlag;
	public boolean updateMemberIdListFlag;
	JSONArray memberIdListArray;
	

	public StateThread(LoginForm loginForm, Socket client) {
		this.loginForm = loginForm;
		this.client = client;

		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �꽌踰꾩쓽 紐⑤뱺 �씠踰ㅽ듃 泥�痍�!!!
	public void listen() {
		try {
			String msg = buffr.readLine();

			System.out.println(TAG + ":" + msg);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(msg);

			/*-------------------------------------------------------------------------------
			  濡쒓렇�씤 �씠�썑�뿉, 濡쒓렇�씤 寃곌낵 �쓳�떟諛쏄린 
			  1.濡쒓렇�씤 �쑀���쓽 �떊�긽�젙蹂� 媛��졇�삤湲�
			  2.移쒓뎄紐⑸줉 媛��졇�삤湲�
			  3.梨꾪똿諛� 紐⑸줉 媛��졇�삤湲�
			 -------------------------------------------------------------------------------*/
			if (jsonObject.get("response").equals("login")) {
				System.out.println(TAG + "濡쒓렇�씤 寃곌낵 諛쏆쓬");

				if (jsonObject.get("result").equals("ok")) {
					JSONObject obj = (JSONObject) jsonObject.get("data");
					String name = (String) obj.get("m_name");
					String member_id = (String) obj.get("member_id");
					long me = Long.parseLong(member_id);

					String path = (String) obj.get("pic");//
					String nick = (String) obj.get("nickname");//
					String status = (String) obj.get("status"); //
					// 梨꾪똿 李� �쓣�슦湲�!!!
					
					loginForm.showMsg("�씤利앸릺�뿀�뒿�땲�떎.");
					// amember�뿉 媛믪쓣 �꽆寃⑥＜湲� �쐞�빐 �뵲濡� string[] �깮�꽦
					String[] myList = { member_id, name, nick, path, status };

					// 移쒓뎄�젙蹂대�� 吏묓빀�삎�깭(ArrayList)濡� 援ъ꽦�븯�뿬 ,�씤�닔濡� �쟾�떖...
					ArrayList<String[]> friendArrayList = new ArrayList<String[]>();
					// �엫沅뚯갹 �옉�꽦
					JSONArray jsonArray = (JSONArray) obj.get("friendList");

					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jObject = (JSONObject) jsonArray.get(i);
						String[] friendInfo = { Long.toString((Long) jObject.get("member_id")),
								(String) jObject.get("m_name"), (String) jObject.get("m_nickname"),
								(String) jObject.get("m_pic"), (String) jObject.get("m_status") };

						friendArrayList.add(friendInfo);
						// JOptionPane.showMessageDialog(loginForm,Long.toString((Long)jObject.get("member_id"))
						// );
					}
					
					//梨꾪똿 由ъ뒪�듃 媛��졇�삤湲�
					ArrayList<String[]> chattingArrayList = new ArrayList<String[]>();
					
					JSONArray chattingList=(JSONArray)obj.get("chattingList");
					
					for(int i=0;i<chattingList.size();i++){
						
						JSONObject chattingObject=(JSONObject)chattingList.get(i);
						String[] chatting = new String[3];
						
						chatting[0]=Long.toString((Long)chattingObject.get("room_no"));
						chatting[1]=(String)chattingObject.get("r_title");
						chatting[2]=(String)chattingObject.get("c_msg");
					
						chattingArrayList.add(chatting);
					}
					
					new TestMain_FriendList(friendArrayList, chattingArrayList, me, myList,this);
					
				} else {
					loginForm.showMsg("濡쒓렇�씤 �젙蹂닿� �삱諛붾Ⅴ吏� �븡�뒿�땲�떎.");
				}
			}
			/*-------------------------------------------------------------------------------
			  �떎瑜� �궗�슜�옄媛� 濡쒓렇�씤�떆, 洹� �젙蹂대�� 諛쏄린 
			 -------------------------------------------------------------------------------*/
			if (jsonObject.get("response").equals("roommateLogin")) {

				if (jsonObject.get("result").equals("ok")) {

					JSONObject obj = (JSONObject) jsonObject.get("data");
					String name = (String) obj.get("m_name");
					String member_id = (String) obj.get("member_id");
					int me = Integer.parseInt(member_id);

					String path = (String) obj.get("pic");//
					String nick = (String) obj.get("nickname");//
					String status = (String) obj.get("status"); //

					loginForm.showMsg("猷몃찓�씠�듃 �젙蹂대�� 諛쏆븯�뒿�땲�떎.");
				} else {
					loginForm.showMsg("猷몃찓�씠�듃 �젙蹂� 諛쏄린 �떎�뙣!!");
				}
			}
			/*-------------------------------------------------------------------------------
			  �꽌踰꾧� 梨꾪똿 ���솕瑜� 蹂대궪寃쎌슦 洹� �젙蹂대컺湲�
			 -------------------------------------------------------------------------------*/
			if (jsonObject.get("response").equals("chat")) {
				
			}
			/*-------------------------------------------------------------------------------
			채팅방 추가할 떄...
			 -------------------------------------------------------------------------------*/
			if(jsonObject.get("response").equals("addChattingRoom")){
				if(jsonObject.get("result").equals("ok")){
					memberAccessList = new long[memberIdListArray.size()];
					for(int i=0;i<memberIdListArray.size();i++){
						JSONObject memberIdInfo = (JSONObject)memberIdListArray.get(i);
						memberAccessList[i] = (long)memberIdInfo.get("m_id");
					}
					updateChattingListFlag=true;
				}
			}
			/*-------------------------------------------------------------------------------
			채팅리스트를 불러올때 현재 접속중인 memberIdList를 받아오는 곳.
			 -------------------------------------------------------------------------------*/
			if(jsonObject.get("response").equals("chattingList")){
				memberIdListArray = (JSONArray)jsonObject.get("memberIdList");
				memberAccessList = new long[memberIdListArray.size()];
				for(int i=0;i<memberIdListArray.size();i++){
					JSONObject memberIdInfo = (JSONObject)memberIdListArray.get(i);
					memberAccessList[i] = (long)memberIdInfo.get("m_id");
				}
				updateMemberIdListFlag = true;
			}
			
		} catch (

		IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public void send(String msg) {
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
}
