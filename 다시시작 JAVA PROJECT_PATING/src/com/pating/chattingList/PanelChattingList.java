package com.pating.chattingList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pating.chatting.ChattingScreen;
import com.pating.friendList.TestMain_FriendList;

public class PanelChattingList extends JPanel implements ActionListener{
	String TAG=this.getClass().getName();
	Image img;
	ImageIcon icon;	
	JLabel la_addChattingRoom;
	JButton bt_addChattingRoom;
	JPopupMenu p_menu;
	JMenuItem m_item;
	//-------------------------------------------------------------------디자인 끝
	FrameFriendInvitation ffi;
	
	public static HashMap<Long, Vector> map;
	ArrayList<PanelItem> list_chatting;
	//선택된 친구 member_no list
	ArrayList<Integer> list_friendsSelectedNo;
	
	BufferedReader bR;
	BufferedWriter bW;
	long me_no;
		
	String data=null;
	
	//접속중인 회원정보를 담기 위함
	long[] memberAccessList;
	TestMain_FriendList testMain_FriendList;
	public PanelChattingList(long me_no,TestMain_FriendList testMain_FriendList){
		this.testMain_FriendList = testMain_FriendList;
		//채팅방 추가 버튼
		URL url=this.getClass().getClassLoader().getResource("create.png");
		icon=new ImageIcon(url);
		//la_addChattingRoom=new JLabel(icon);
		bt_addChattingRoom=new JButton(icon);
		bt_addChattingRoom.setBackground(new Color(255, 204, 51));
		bt_addChattingRoom.setBorder(new EmptyBorder(0, 0, 0, 0));
		img=icon.getImage().getScaledInstance(50, 50, 1);
		icon.setImage(img);
		//add(la_addChattingRoom);
		add(bt_addChattingRoom);
		
		bt_addChattingRoom.addActionListener(this);
		p_menu=new JPopupMenu("addChattingRoom");
		p_menu.setBackground(new Color(102, 153, 0));
		m_item=new JMenuItem("일반채팅");
		m_item.addActionListener(this);
		m_item.setBackground(new Color(102, 153, 0));
		p_menu.add(m_item);
		p_menu.addSeparator();
		
		setPreferredSize(new Dimension(350, 1500));
		setLayout(new FlowLayout());
		setBackground(new Color(255, 204, 51));
		//------------------------------------------------------------------------------------
		map=new HashMap<Long, Vector>();
		list_chatting=new ArrayList<PanelItem>();
		bR=TestMain_FriendList.buffr;
		bW=TestMain_FriendList.buffw;
		this.me_no=me_no;
		
		getList();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent btn=(JComponent)e.getSource();
		if(e.getSource() == bt_addChattingRoom){
			p_menu.show(this, btn.getX()+(btn.getWidth()/2), btn.getY()+(btn.getHeight()/2));
		}
		else if(btn == m_item){
			ffi = new FrameFriendInvitation(me_no, this.memberAccessList, this);
		}
		
	}

	public void getList(){
		
		this.removeAll();
		list_chatting.clear();
		map.clear();
		add(bt_addChattingRoom);
		
		StringBuffer sb = new StringBuffer();		
		
		sb.append("{");
		sb.append(	"\"request\" :\"chattingList\",");
		sb.append(	" \"member_id\" : "+me_no+"");
		sb.append("}");
		
		try {
			bW.write(sb.toString()+"\n");
			bW.flush();
			
			String data=bR.readLine();
			System.out.println(TAG+": data is "+data);
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject=(JSONObject)jsonParser.parse(data);
			String result = (String)jsonObject.get("result");
			
			JSONArray jsonArrayAccessList = (JSONArray)jsonObject.get("memberIdList");
			memberAccessList = new long[jsonArrayAccessList.size()];
			for(int i=0;i<jsonArrayAccessList.size();i++){
				JSONObject jsonObjectAccessList = (JSONObject)jsonArrayAccessList.get(i);
				memberAccessList[i] = (long)jsonObjectAccessList.get("m_id");
			}
			
			JSONArray jsonArray=(JSONArray)jsonObject.get("data");
			if(result.equals("ok")){				
				for(int i=0;i<jsonArray.size();i++){
					JSONObject obj=(JSONObject)jsonArray.get(i);
					long room_no=(long)obj.get("room_no");
					String r_title=(String)obj.get("r_title");
					String c_msg=(String)obj.get("c_msg");
					
					
					
					list_chatting.add(new PanelItem(room_no, r_title, c_msg));
					list_chatting.get(i).addMouseListener(new MouseAdapter(){
						
						public void mouseClicked(MouseEvent e) {
							new ChattingScreen();
						}
					});
					map.put((Long)room_no, null);
				}				
				
				for(int i=0; i<list_chatting.size(); i++){
					add(list_chatting.get(i));
					
				}
				
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Connection c=TestMain_FriendList.con;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
	}
	
	public HashMap<Long, Vector> getMap(){
		return map;
	}
}
