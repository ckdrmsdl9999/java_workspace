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
import java.net.URL;
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

import com.pating.chatting.ChattingScreen;
import com.pating.friendList.TestMain_FriendList;
import com.pating.login.StateThread;

public class PanelChattingList extends JPanel implements ActionListener {
	String TAG = this.getClass().getName();
	Image img;
	ImageIcon icon;
	JLabel la_addChattingRoom;
	JButton bt_addChattingRoom;
	JPopupMenu p_menu;
	JMenuItem m_item;
	// -------------------------------------------------------------------디자인 끝
	FrameFriendInvitation ffi;

	public static HashMap<Long, Vector> map;
	ArrayList<PanelItem> list_chatting;
	// 선택된 친구 member_no list
	ArrayList<Integer> list_friendsSelectedNo;

	BufferedReader bR;
	BufferedWriter bW;
	long me_no;

	String data = null;
	StateThread stateThread;

	// 접속중인 회원정보를 담기 위함
	long[] memberAccessList;
	TestMain_FriendList testMain_FriendList;
	String[] chattingListString;

	ArrayList<String[]> chattingArrayList;
	ArrayList<String[]> friendArrayList;
	StateThread clientThread;

	public PanelChattingList(long me_no, ArrayList chattingArrayList, ArrayList friendArrayList,
			StateThread clientThread) {
		this.chattingArrayList = chattingArrayList;
		this.friendArrayList = friendArrayList;
		this.clientThread = clientThread;
		// 채팅방 추가 버튼
		URL url = this.getClass().getClassLoader().getResource("create.png");
		icon = new ImageIcon(url);
		// la_addChattingRoom=new JLabel(icon);
		bt_addChattingRoom = new JButton(icon);
		bt_addChattingRoom.setBackground(new Color(255, 204, 51));
		bt_addChattingRoom.setBorder(new EmptyBorder(0, 0, 0, 0));
		img = icon.getImage().getScaledInstance(50, 50, 1);
		icon.setImage(img);
		// add(la_addChattingRoom);
		add(bt_addChattingRoom);

		bt_addChattingRoom.addActionListener(this);
		p_menu = new JPopupMenu("addChattingRoom");
		p_menu.setBackground(new Color(102, 153, 0));
		m_item = new JMenuItem("일반채팅");
		m_item.addActionListener(this);
		m_item.setBackground(new Color(102, 153, 0));
		p_menu.add(m_item);
		p_menu.addSeparator();

		setPreferredSize(new Dimension(350, 1500));
		setLayout(new FlowLayout());
		setBackground(new Color(255, 204, 51));
		// ------------------------------------------------------------------------------------
		map = new HashMap<Long, Vector>();
		list_chatting = new ArrayList<PanelItem>();
		this.me_no = me_no;

		getList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent btn = (JComponent) e.getSource();
		if (e.getSource() == bt_addChattingRoom) {
			p_menu.show(this, btn.getX() + (btn.getWidth() / 2), btn.getY() + (btn.getHeight() / 2));
		} else if (btn == m_item) {
			this.memberAccessList = clientThread.memberAccessList;
			ffi = new FrameFriendInvitation(me_no, this.memberAccessList, this,clientThread);
		}

		
	}

	public void getList() {

		this.removeAll();
		list_chatting.clear();
		map.clear();
		add(bt_addChattingRoom);

		StringBuffer sb = new StringBuffer();

		sb.append("{");
		sb.append("\"request\" :\"chattingList\",");
		sb.append(" \"member_id\" : " + me_no + "");
		sb.append("}");

		clientThread.send(sb.toString());
		while (clientThread.updateMemberIdListFlag == true) {
			break;
		}
		for (int i = 0; i < chattingArrayList.size(); i++) {
			/* JSONObject obj=(JSONObject)jsonArray.get(i); */
			chattingListString = chattingArrayList.get(i);
			long room_no = Long.parseLong(chattingListString[0]);
			String r_title = chattingListString[1];
			String c_msg = chattingListString[2];

			list_chatting.add(new PanelItem(room_no, r_title, c_msg));
			list_chatting.get(i).addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					new ChattingScreen();
				}
			});
			map.put((Long) room_no, null);
		}

		for (int i = 0; i < list_chatting.size(); i++) {
			add(list_chatting.get(i));
		}
		clientThread.updateMemberIdListFlag = false;

		/*	
			
			*/

		/*
		 * JSONObject jsonObject=(JSONObject)jsonParser.parse(data); String
		 * result = (String)jsonObject.get("result");
		 * 
		 * JSONArray jsonArrayAccessList =
		 * (JSONArray)jsonObject.get("memberIdList"); memberAccessList = new
		 * long[jsonArrayAccessList.size()]; for(int
		 * i=0;i<jsonArrayAccessList.size();i++){ JSONObject
		 * jsonObjectAccessList = (JSONObject)jsonArrayAccessList.get(i);
		 * memberAccessList[i] = (long)jsonObjectAccessList.get("m_id"); }
		 * 
		 * JSONArray jsonArray=(JSONArray)jsonObject.get("data");
		 */
	} // getList

	public HashMap<Long, Vector> getMap() {
		return map;
	}

}
