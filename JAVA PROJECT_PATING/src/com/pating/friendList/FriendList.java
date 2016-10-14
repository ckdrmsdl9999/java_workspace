package com.pating.friendList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pating.login.Loginform;

public class FriendList extends JPanel{//JFrame{
	JLabel la_myProfile;
	JLabel la_list;	
	JPanel p_myProfile,p_myProfile1,p_list,p_list1,p_list_back,p_search;//,p_top,p_category,p_category1,p_category2,p_category3,p_category4,p_search;		
	JTextField tf_search;
	
	int count;
	
	JPanel p_list2,p_myProfile2;
	
	TestMain_FriendList Tf;
	Font font1 =new Font("",Font.PLAIN,10);
	
	AMember[] amArr;
	AMember amember;	
	Loginform loginform;
	
	BufferedReader buffr;
	BufferedWriter buffw;
	long me;
	
	String path;
	String nick;
	String status;
	
	public FriendList(long me, String path,String nick,String status) {	
		this.buffr = TestMain_FriendList.buffr;
		this.buffw = TestMain_FriendList.buffw;
		this.me = me;
		this.path=path;
		this.nick=nick;
		this.status=status;
		
		p_list_back = new JPanel();			
		p_myProfile = new JPanel();
		p_myProfile1 = new JPanel();
		p_myProfile2 = new JPanel();
		p_list = new JPanel();		
		p_list1 = new JPanel();	
		p_list2 = new JPanel();
		p_search= new JPanel();
			
		la_myProfile = new JLabel("   내 프로필");	
		tf_search= new JTextField("친구 검색",30);
			
		System.out.println(path);
		
		getMyprofile();
		this.setPreferredSize(new Dimension(350, 265+((AMember.h+5)*this.count)));
		la_list = new JLabel("   친구"+count);	
		p_search.setBackground(Color.WHITE);
		p_myProfile.setBackground(Color.WHITE);
		p_myProfile1.setBackground(Color.WHITE);
		p_myProfile2.setBackground(Color.WHITE);
		p_list.setBackground(Color.WHITE);
		p_list1.setBackground(Color.WHITE);		
		p_list_back.setBackground(Color.WHITE);		
			
		tf_search.setPreferredSize(new Dimension(330, 25));
		p_search.setPreferredSize(new Dimension(350, 35));
		p_myProfile1.setPreferredSize(new Dimension(360, 20));
		
		la_myProfile.setFont(font1);
		la_list.setFont(font1);
		
		p_myProfile.setLayout(new BorderLayout());
		p_myProfile1.setLayout(new FlowLayout(FlowLayout.LEFT));	
		p_list1.setLayout(new FlowLayout(FlowLayout.LEFT));	
		p_list.setLayout(new BorderLayout(0,0));		
		//p_myProfile2.setLayout(new BorderLayout());
		
		p_search.add(tf_search);		
		p_myProfile1.add(la_myProfile);		
		p_myProfile.add(p_search,BorderLayout.NORTH);
		p_myProfile.add(p_myProfile1,BorderLayout.CENTER);		
		p_myProfile.add(p_myProfile2,BorderLayout.SOUTH);
		
		p_list1.add(la_list);
		p_list.add(p_list1,BorderLayout.NORTH);
		p_list.add(p_list2,BorderLayout.CENTER);
		
		p_list.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));	
		p_list1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		p_myProfile1.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));			

		setLayout(new BorderLayout());		
		add(p_myProfile,BorderLayout.NORTH);
		add(p_list,BorderLayout.CENTER);		
		/*
		setSize(400,700);
		setVisible(true);*/
				
	}	
	
	public void getMyprofile(){
		System.out.println(path);
		amember = new AMember(path,nick,status,me);
		p_myProfile2.add(amember);
		
		try {				
			StringBuffer sb = new StringBuffer();
			
			sb.append("{");
			sb.append("\"request\" : \"friendList\",");
			sb.append(" \"member_id\" : "+me+"");
			sb.append("}");
			
			System.out.println(sb.toString());
			buffw.write(sb.toString()+"\n");
			buffw.flush();
			
			//�������� ģ����� �ҷ��ͼ� ���ݺ��� �ѷ��ش�.
			String data=buffr.readLine();
			System.out.println(data);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject=(JSONObject)jsonParser.parse(data);
			String result = (String)jsonObject.get("result");
			JSONArray jsonArray=(JSONArray)jsonObject.get("data");
			System.out.println(jsonArray.size());
			
			if(result.equals("ok")){				
				for(int i=0;i<jsonArray.size();i++){
					JSONObject obj=(JSONObject)jsonArray.get(i);
					long you=(long)obj.get("member_id");
					System.out.println(you);
					amember = new AMember((String)obj.get("pic"),(String)obj.get("nickname"),(String)obj.get("status"), you);
					p_list2.add(amember);
				}				
				count++;
				System.out.println(count);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
/*	
	public static void main(String[] args){
		new FriendList(1);
	}
*/
}
