package com.pating.friendListBefore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.pating.friendListBefore.AFriend_memory;

public class Connect extends JPanel{
	JPanel p, p_me, p_chatting, p_profileMgr, p_status, p_me1, p_chatting1, p_profileMgr1;
	JLabel lb_me, lb_chatting, lb_profileMgr, lb_status ;
	
	Toolkit kit= Toolkit.getDefaultToolkit();
	Image img,img2,img3;
	AFriend_memory friend;
	
	//String url="C:/java_workspace/GUI0818/res/settings.png";
	
	Font font1 =new Font("",Font.PLAIN,20);
	Font font2 =new Font("",Font.PLAIN,14);
	
	String[] url={"./res/friendlist/chatting.png","./res/friendlist/settings.png"};
	
	//전체 객체가져오기 
	public Connect(AFriend_memory friend) {
		this.friend=friend;
				//lb_me, lb_profileMgr, lb_status 값 가져오기
		lb_me = new JLabel(""+friend.la_nickname.getText()+"");		
		lb_chatting = new JLabel("채팅");	
		lb_profileMgr = new JLabel("프로필 관리");
		lb_status = new JLabel(""+friend.la_statusMessage.getText()+"");
		
		p = new JPanel();
		p_me = new JPanel();
		p_chatting = new JPanel();
		p_profileMgr = new JPanel();
		p_status = new JPanel();
		
		lb_me.setFont(font1);
		lb_chatting.setFont(font2);
		lb_profileMgr.setFont(font2);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screen_w = screenSize.getWidth();
		double screen_h = screenSize.getHeight();
		int window_w = 400;
		int window_h=700;
		int x = (int) screen_w - window_w-300;
		int y= friend.getY();
		
		p_me1 = new JPanel(){			
			public void paint(Graphics g) {
				g.drawImage(friend.img, 0, 0,180,180, Color.ORANGE, getParent());
			}
		};
				
		img2=kit.getImage(url[1]);
		p_chatting1 = new JPanel(){			
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0,90,90, Color.ORANGE, getParent());
			}
		};
		
		img3=kit.getImage(url[2]);
		p_profileMgr1 = new JPanel(){			
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0,90,90, Color.ORANGE, getParent());
			}
		};
		
		p.setLayout(new BorderLayout());
		p_me.setLayout(new BorderLayout());
		p_chatting.setLayout(new BorderLayout());
		p_profileMgr.setLayout(new BorderLayout());
		
		p_me.setPreferredSize(new Dimension(200, 200));
		p_chatting.setPreferredSize(new Dimension(100, 100));
		p_profileMgr.setPreferredSize(new Dimension(100, 100));
		
		p_me.add(lb_me,BorderLayout.SOUTH);
		p_chatting.add(lb_chatting,BorderLayout.SOUTH);
		p_profileMgr.add(lb_profileMgr,BorderLayout.SOUTH);
		
		p_me.add(p_me1,BorderLayout.CENTER);
		p_chatting.add(p_chatting1,BorderLayout.CENTER);
		p_profileMgr.add(p_profileMgr1,BorderLayout.CENTER);
				
		setPreferredSize(new Dimension(300, 500));
		setBounds(x,y,300, 500);
	}
		/*
		add(p);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(x,y,300, 500);
		setVisible(true);
	}
	
	public static void main(String[] args){
		
		new Connect(null);
	}*/
}
