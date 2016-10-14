package com.sds.client.chatting;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sds.client.AppMain;

public class UserdList extends JPanel{
	
	
	String[] path={
		"profile.png",
		"profile.png",
		"profile_default.png",
		"profile_default.png"
	};
	String[] status={
		"오늘 잠수탐.",
		"벌써2년",
		"법인세 납부기간",
		"바람소리"
	};
	
	
	public UserdList() {
		setPreferredSize(new Dimension(400, 60));
		
		getList();
		
		
	}
	//전체 회원 목록 가져오기!!
	public void getList(){
		//소켓을 통해 userList요청하자!!!!!!
		StringBuffer sb= new StringBuffer();
		sb.append("{");
		sb.append("\"request\":\"userlist\"");
		sb.append("}");
		try {
			AppMain.buffw.write(sb.toString()+"\n");
			AppMain.buffw.flush();
			
			//서버로부터 결과 받기
			String data=AppMain.buffr.readLine();
			System.out.println(data);
			
			//파싱
			JSONParser parser= new JSONParser();
			JSONObject jsonObject=(JSONObject)parser.parse(data);
			
			String result=(String)jsonObject.get("result");
			
			if(result.equals("ok")){
				JSONArray jsonArray=(JSONArray)jsonObject.get("data");
				
				for(int i=0;i<jsonArray.size();i++){
					JSONObject obj=(JSONObject)jsonArray.get(i);
					Item item = new Item((String)obj.get("profile"),(String)obj.get("status"),(Long)obj.get("chatmember_id"));
					
					add(item);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}





