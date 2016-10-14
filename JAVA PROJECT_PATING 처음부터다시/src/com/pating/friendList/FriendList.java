package com.pating.friendList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pating.login.LoginForm;
import com.sun.glass.events.KeyEvent;

public class FriendList extends JPanel implements ActionListener{//JFrame{
	JLabel la_myProfile;
	JLabel la_list;	
	JPanel p_myProfile,p_myProfile1,p_list,p_list1,p_list_back,p_search;//,p_top,p_category,p_category1,p_category2,p_category3,p_category4,p_search;		
	JTextField tf_search;
	
	int count;
	
	JPanel p_list2,p_myProfile2;
	JRadioButton rbt_member,rbt_friend;		
	
	TestMain_FriendList Tf;
	Font font1 =new Font("",Font.PLAIN,10);
	
	AMember[] amArr;
	AMember amember;	
	LoginForm loginform;
	
	BufferedReader buffr;
	BufferedWriter buffw;
	long me;
	
	String path;
	String nick;
	String status;
	ArrayList<String[]> friendArrayList;
	String[] myList;
	
	public FriendList(ArrayList<String[]> friendArrayList,long me,String[] myList) {	
		this.friendArrayList = friendArrayList;
		this.me = me;
		this.myList = myList;
		
		/*	
		this.path=path;
		this.nick=nick;
		this.status=status;*/
		
		p_list_back = new JPanel();			
		p_myProfile = new JPanel();
		p_myProfile1 = new JPanel();
		p_myProfile2 = new JPanel();
		p_list = new JPanel();		
		p_list1 = new JPanel();	
		p_list2 = new JPanel();
		p_search= new JPanel();
		
		rbt_friend = new JRadioButton("친구");
		rbt_member = new JRadioButton("회원");		
			
		la_myProfile = new JLabel("   내 프로필");	
		tf_search= new JTextField("친구 검색",30);
		
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

		tf_search.addKeyListener(new KeyAdapter() {			
			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {
				 if ( e.getKeyCode()== KeyEvent.VK_ENTER){
			    	 //잠시 호출 멈추자 searchMember();
			     }
			}
		});
		
		tf_search.addFocusListener(new FocusAdapter() {
			
			public void focusGained(FocusEvent e) {
				tf_search.setText("");
			}
		});
		
		tf_search.addFocusListener(new FocusAdapter() {
			
			public void focusLost(FocusEvent e) {
				tf_search.setText("ģ�� �̸����� �˻��غ�����.");
			}
		});
		
		tf_search.addActionListener(this);			
		rbt_member.addActionListener(this);
		rbt_friend.addActionListener(this);
		
		setLayout(new BorderLayout());		
		add(p_myProfile,BorderLayout.NORTH);
		add(p_list,BorderLayout.CENTER);		
		/*
		setSize(400,700);
		setVisible(true);*/
				
	}	
	
	public void select_friend(){	
		p_list2.removeAll();
		
		getMyprofile();
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==rbt_member){
			
			rbt_member.setSelected(true);
			rbt_friend.setSelected(false);
			//호출 잠시 멈추자select_member();
		}else if(obj==rbt_friend){
			
			rbt_member.setSelected(false);
			rbt_friend.setSelected(true);
			select_friend();
		}
		
	}	
	
	
	public void getMyprofile(){
		System.out.println(path);
		amember = new AMember(myList);
		p_myProfile2.add(amember);
		
		for(int i=0;i<friendArrayList.size();i++){
			//JSONObject obj=(JSONObject)jsonArray.get(i);
			//long you=(long)obj.get("member_id");
			//System.out.println(you);
			//amember = new AMember((String)obj.get("pic"),(String)obj.get("nickname"),(String)obj.get("status"), you);
			amember = new AMember(friendArrayList.get(i));
			p_list2.add(amember);
		}				
		count++;
		System.out.println(count);
		//필요 없는 부분
		/*try {				
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
			
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
*/		
	}
	
	/*searchMember와select_member 주석처리 했음.
	 * public void searchMember(){
		count=0;
		p_list2.removeAll();
		
		String searchKey=tf_search.getText();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("{");
		sb.append("\"request\" : \"memberList\",");
		sb.append(" \"member_id\" : "+TestMain_FriendList.me+"");
		sb.append("}");
		
		try {
			TestMain_FriendList.buffw.write(sb.toString()+"\n");
			TestMain_FriendList.buffw.flush();
			
			String data = TestMain_FriendList.buffr.readLine();
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(data);
			String result =(String)jsonObject.get("result");
			JSONArray jsonArray=(JSONArray)jsonObject.get("data");
			
			String pic;
			String name;
			String status;
			if(result.equals("ok")){				
				for(int i=0; i<jsonArray.size();i++){
					JSONObject obj = (JSONObject)jsonArray.get(i);
					you=(long)obj.get("member_id");	
					pic = (String)obj.get("pic");
					name = (String)obj.get("name");
					status = (String)obj.get("status");											

					if(name.equals(searchKey)){
						amember = new AMember(this,pic,name,status,you,TestMain_FriendList.me);						
						p_list2.add(amember);
						count++;	
					}
					System.out.println(count);
				}
			
				repaint();
				updateUI();
				la_list.setText("�̸���'"+searchKey+"'�� ȸ��"+count);
			}			
			setPreferredSize(new Dimension(350, 185+((AMember.h+5)*this.count)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void select_member(){
		count=0;
		
		p_list2.removeAll();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("{");
		sb.append("\"request\" : \"memberList\",");
		sb.append(" \"member_id\" : "+TestMain_FriendList.me+"");
		sb.append("}");
		
		try {
			TestMain_FriendList.buffw.write(sb.toString()+"\n");
			TestMain_FriendList.buffw.flush();
			
			String data = TestMain_FriendList.buffr.readLine();
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(data);
			String result =(String)jsonObject.get("result");
			JSONArray jsonArray=(JSONArray)jsonObject.get("data");
			
			if(result.equals("ok")){
				for(int i=0; i<jsonArray.size();i++){
					JSONObject obj = (JSONObject)jsonArray.get(i);
					you=(long)obj.get("member_id");					
					amember = new AMember(this,(String)obj.get("pic"),(String)obj.get("nickname"),(String)obj.get("status"), you, TestMain_FriendList.me);
					amember.attachButton();
					p_list2.add(amember);
					count++;				
				}
				repaint();
				updateUI();
				la_list.setText("ȸ��"+count);
			}			
			setPreferredSize(new Dimension(350, 185+((AMember.h+5)*this.count)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	*/
/*	
	public static void main(String[] args){
		new FriendList(1);
	}
*/
	
}
