package com.pating.friendListBefore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.pating.friendListBefore.TestMain_FriendList_memory;

public class FriendList_memory extends JPanel{
	private static final TestMain_FriendList_memory TestMain_FriendList_memory = null;
	JLabel la_myProfile;
	JLabel la_list;
	// JLabel la_friendcount 친구수 integer.toString(count)?
	JPanel p,p_myProfile,p_myProfile1,p_myProfile2,p_list,p_list1,p_list_back,p_search;//,p_top,p_category,p_category1,p_category2,p_category3,p_category4,p_search;		
	JTextField tf_search;
	//JTextArea txt;
	//Top top;
	int count;
	
	AFriend_memory[] af= new AFriend_memory[100];
	String[] Arr_nickname = { "Yt", "다솔이", "파파야", "지코", "설현이", "A",
			"","","","","",
			"","","","","",
			"","","","","",
			"","","","","",
			"","","","","31",
			"","","","","","","" ,"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	String[] Arr_statusMessage = { "인생은 한번뿐", "즐거운 인생", "그녀를 위해", "그를위해", "따라쟁이","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","" ,"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	String[] url = { 
				"./res/friendList/map_editted.png",
				"./res/friendList/dasol_editted.png",
				"./res/friendList/zico_editted.png", 
				"./res/friendList/sulhyeon_editted.png",
				"./res/friendList/sulhyeon_editted.png",
				"./res/friendList/sulhyeon_editted.png",
				"./res/friendList/sulhyeon_editted.png"
			};
	JPanel p_list2;
	JScrollPane scroll; 
	Color orange = new Color(255, 165, 0);
	Color orange_deep = new Color(255, 88, 0);
	Color green = new Color(51, 102, 0);
	TestMain_FriendList_memory Tf;
	Font font1 =new Font("",Font.PLAIN,10);
	
	//Toolkit kit= Toolkit.getDefaultToolkit();
	//Image img,img1,img2,img3;

	public FriendList_memory() {
		p = new JPanel();		
		p_list_back = new JPanel();			
		p_myProfile = new JPanel();
		p_myProfile1 = new JPanel();
		p_myProfile2 = new JPanel();
		p_list = new JPanel();		
		p_list1 = new JPanel();	
		p_list2 = new JPanel();
		p_search= new JPanel();
		scroll=new JScrollPane(p);
		
		la_myProfile = new JLabel("   내프로필");
		tf_search= new JTextField("친구 이름으로 검색해보세요.",30);
		
		tf_search.setPreferredSize(new Dimension(330, 25));
		//tf_search.setBounds(0,20,340,25);
		p_search.setPreferredSize(new Dimension(350, 35));
		
		p_search.setBackground(Color.WHITE);
		p_myProfile.setBackground(Color.WHITE);
		p_myProfile1.setBackground(Color.WHITE);
		p_myProfile2.setBackground(Color.WHITE);
		p_list.setBackground(Color.WHITE);
		p_list1.setBackground(Color.WHITE);		
		p_list_back.setBackground(Color.WHITE);			
		
		p_myProfile1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p_myProfile1.setPreferredSize(new Dimension(360, 20));
		p_myProfile1.add(la_myProfile);		
		la_myProfile.setFont(font1);
		p_myProfile2.setLayout(new BorderLayout());
		
		af[0] = new AFriend_memory(url[0]);
		af[0].la_nickname.setText(Arr_nickname[0]);
		af[0].la_statusMessage.setText(Arr_statusMessage[0]);		
		p_myProfile2.add(af[0]);	
		p_myProfile2.setBackground(Color.WHITE);	
		p_search.add(tf_search);
		p_myProfile.setLayout(new BorderLayout());
		p_myProfile.add(p_search,BorderLayout.NORTH);
		p_myProfile.add(p_myProfile1,BorderLayout.CENTER);
		p_myProfile.add(p_myProfile2,BorderLayout.SOUTH);			
		
		p_list.setLayout(new BorderLayout(0,0));
		
		for (int i = 1; i < url.length; i++) {			
			af[i] = new AFriend_memory(url[i]);
			af[i].la_nickname.setText(Arr_nickname[i]);
			af[i].la_statusMessage.setText(Arr_statusMessage[i]);			
			p_list2.add(af[i]);
			p_list2.setBackground(Color.WHITE);			
			p_list.add(p_list2,BorderLayout.CENTER);
			count++;					
		}		
		
		la_list = new JLabel("   친구 "+count);
		p_list1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p_list1.setPreferredSize(new Dimension(360, 20));
		p_list1.add(la_list);
		la_list.setFont(font1);
		
		p_list.add(p_list1,BorderLayout.NORTH);
		
		p_list.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		p_list1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		p_myProfile1.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));		
		
		setLayout(new BorderLayout());
		add(p_myProfile,BorderLayout.NORTH);
		add(p_list,BorderLayout.CENTER);	
		//add(p_top, BorderLayout.NORTH);	
		//setPreferredSize(new Dimension(350, 540));
				
	}	

	
	/*				
		//setUndecorated(true);
		//setShape(new RoundRectangle2D.Double(0,0,360,910,20,20));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(x, 0, 400, 700);// to do 스크린에 맞춰서.. 변수 스크립트 screen.width
		setVisible(true);		
	}
	
	public static void main(String[] args) {
		new FriendList_memory();

	}
	*/
	

}
