package com.pating.friendListBefore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.pating.friendListBefore.AFriend_memory;
import com.pating.friendListBefore.Top;

public class TestMain_FriendList_memory extends JFrame implements ActionListener{
	JLabel la_myProfile;
	JLabel la_list;
	// JLabel la_friendcount 친구수 integer.toString(count)?
	JPanel p,p_top,p_category,p_category1,p_category2,p_category3,p_category4;	
	JTextField tf_search;
	JTextArea txt;
	AFriend_memory[] af= new AFriend_memory[10];
	Top top;
	int count;		

	JScrollPane scroll; 	
	Toolkit kit= Toolkit.getDefaultToolkit();
	Image img,img1,img2,img3;
	
	FriendList_memory friendList_memory;		
	

	public TestMain_FriendList_memory() {
		p = new JPanel();
		p_top = new JPanel();		
		top = new Top();
		la_myProfile = new JLabel("   내프로필");		
		scroll=new JScrollPane(p);
		
		Color orange = new Color(255, 165, 0);
		Color orange_deep = new Color(255, 88, 0);
		Color green = new Color(51, 102, 0);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screen_w = screenSize.getWidth();
		double screen_h = screenSize.getHeight();
		int window_w = 400;
		int window_h=700;
		int x = (int) screen_w - window_w;	

		int img_w = 25;
		int p_w= img_w+65;
		int p_h=img_w+45;
				
		top.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.setBackground(Color.WHITE);
		
		img=kit.getImage("./res/friendlist/friend.png");			
		p_category1 = new JPanel(){			
			public void paint(Graphics g) {
				g.drawImage(img,25,15,img_w,img_w,Color.WHITE,getParent());
			}
		};
		p_category1.setLayout(new BorderLayout());
		p_category1.setPreferredSize(new Dimension(p_w,p_h));		
		p_category1.setBackground(Color.WHITE);
		p_category1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		
		img1=kit.getImage("./res/friendlist/chatting.png");		
		p_category2 = new JPanel(){			
			public void paint(Graphics g) {
				g.drawImage(img1,25,15,img_w,img_w,Color.WHITE,getParent());
			}
		};	
		p_category2.setLayout(new BorderLayout());
		p_category2.setPreferredSize(new Dimension(p_w,p_h));
		p_category2.setBackground(Color.WHITE);
		p_category2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		/*
		img2=kit.getImage("C:/java_workspace/GUI0818/res/restricted.png");		
		p_category3 = new JPanel(){			
			public void paint(Graphics g) {
				g.drawImage(img2,25,15,img_w,img_w,Color.WHITE,getParent());
			}
		};		
		*/	
		p_category3 = new JPanel();
		p_category3.setLayout(new BorderLayout());
		p_category3.setPreferredSize(new Dimension(p_w,p_h));		
		p_category3.setBackground(Color.WHITE);
		p_category3.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
			
		img3=kit.getImage("./res/friendList/more.png");		
		p_category4 = new JPanel(){		
			public void paint(Graphics g) {
				g.drawImage(img3,25,15,img_w,img_w,Color.WHITE,getParent());
			}
		};	
		p_category4.setLayout(new BorderLayout());
		p_category4.setPreferredSize(new Dimension(p_w,p_h));
		p_category4.setBackground(Color.WHITE);
		p_category4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		
		p_category = new JPanel();
		p_category.setPreferredSize(new Dimension(300, 100));
		p_category.setBackground(Color.WHITE);
		p_category.setLayout(new GridLayout(1,4));	
		p_category.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		
		p_category.add(p_category1);
		p_category.add(p_category2);
		p_category.add(p_category3);
		p_category.add(p_category4);	
		
		p_top.setLayout(new BorderLayout());
		p_top.setBackground(Color.WHITE);
		p_top.setPreferredSize(new Dimension(350, 110));
		p_top.add(top,BorderLayout.NORTH);
		p_top.add(p_category,BorderLayout.CENTER);			
		
		friendList_memory = new FriendList_memory();
				
		p.setLayout(new BorderLayout());		
		p.setPreferredSize(new Dimension(350, 265+((AFriend_memory.h+5)*friendList_memory.count)));
		System.out.println(friendList_memory.count);
		System.out.println(AFriend_memory.h);
		p.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.GRAY));
		p.add(p_top, BorderLayout.NORTH);
		p.add(friendList_memory, BorderLayout.CENTER);	
		add(scroll);
		
		//p.setBorder(BorderFactory.createLineBorder(green, 16, true));	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(x,0,window_w,window_h);// to do 스크린에 맞춰서.. 변수 스크립트 screen.width
		setVisible(true);		
		//setUndecorated(true);		
	}
	
	public void chat_or_profile() {		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		chat_or_profile();
	}

	public static void main(String[] args) {
		new TestMain_FriendList_memory();
	}

	

}
