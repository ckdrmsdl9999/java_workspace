package com.pating.friendList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.pating.chattingList.PanelChattingList;

public class TestMain_FriendList extends JFrame{
	public static long me;
	public static BufferedReader buffr;
	public static BufferedWriter buffw;
	
	JLabel la_myProfile;
	JLabel la_list;	
	JPanel p,p_top,p_category,p_category1,p_category2,p_category3,p_category4;	
	JTextField tf_search;
	JTextArea txt;
	
	JLabel l_title;
	JScrollPane scroll2_chattingList;
	JScrollPane scroll3;
	
	JScrollPane scroll; 	
	Toolkit kit= Toolkit.getDefaultToolkit();
	Image img,img1,img2,img3;	
	
	//PANELS
	JPanel p_center;
	FriendList friendList;
	PanelChattingList chattingList;
	
	String path;
	String nick;
	String status;

	public TestMain_FriendList(BufferedReader buffr, BufferedWriter buffw, long me, String path,String nick,String status) {
		this.buffr = buffr;
		this.buffw = buffw;
		this.me = me;
		this.path = path;
		this.nick = nick;
		this.status = status;	
		
		Color orange = new Color(255, 165, 0);
		Color green = new Color(51, 102, 0);
		
		p = new JPanel();
		p_top = new JPanel();
		p_top.setPreferredSize(new Dimension(350, 90));
		p_top.setBackground(green);
		
		l_title=new JLabel("   ģ��");
		l_title.setPreferredSize(new Dimension(350, 30));
		l_title.setBackground(green);
		l_title.setForeground(Color.white);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screen_w = screenSize.getWidth();
		double screen_h = screenSize.getHeight();
		int window_w = 400;
		int window_h=700;
		int x = (int) screen_w - window_w;	

		int img_w = 25;
		int p_w= img_w+65;
		int p_h=img_w+45;	
		
		img=kit.getImage("./res/friend.png");			
		p_category1 = new JPanel(){			
			public void paint(Graphics g) {
				g.drawImage(img,25,15,img_w,img_w,Color.WHITE,getParent());
			}
		};
		p_category1.setLayout(new BorderLayout());
		p_category1.setPreferredSize(new Dimension(p_w,p_w));		
		p_category1.setBackground(Color.WHITE);
		p_category1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		
		img1=kit.getImage("./res/chatting.png");		
		p_category2 = new JPanel(){			
			public void paint(Graphics g) {
				g.drawImage(img1,25,15,img_w,img_w,Color.WHITE,getParent());
			}
		};
		p_category2.setLayout(new BorderLayout());
		p_category2.setPreferredSize(new Dimension(p_w,p_w));
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
		
		img3=kit.getImage("./res/more.png");		
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
		p_top.add(l_title,BorderLayout.NORTH);
		p_top.add(p_category,BorderLayout.CENTER);	
		//System.out.println(path);
		
		p_center=new JPanel(new FlowLayout());
		p_center.setBackground(Color.ORANGE);
		//TestMain�� static buffr, buffw �λ���ϹǷ� �Ű������� �Ѱ����� ����_�����
		friendList = new FriendList(this);		
		//chattingList=new PanelChattingList(me,this);
		//chattingList.setVisible(false);
		
		scroll=new JScrollPane(p);
		
		p.setLayout(new BorderLayout());
		//p.setPreferredSize(new Dimension(350, 250+((AMember.h+5)*friendList.count)));
		
		p.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.GRAY));
		p.add(p_top, BorderLayout.NORTH);		
		p.add(p_center, BorderLayout.CENTER);
		p_center.add(friendList);	
		//p_center.add(chattingList);
			
		add(scroll);		
		
		p_category1.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("ģ�����");
				friendList.setVisible(true);
				l_title.setText("  ģ��");
				//chattingList.setVisible(false);		
			}
		});
		
		p_category2.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("ä�ø��");
				friendList.setVisible(false);
				//chattingList.setVisible(true);
				l_title.setText("   ä��");		
			}
		});
		
		p_category4.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				System.out.println("������");
				friendList.setVisible(false);
				//chattingList.setVisible(false);
				l_title.setText("   ������");		
			}
		});				
					
		//p.setBorder(BorderFactory.createLineBorder(green, 16, true));
		
		addWindowListener(new WindowAdapter() {			
			public void windowClosing(WindowEvent arg0) {				
				//�����ͺ��̽� ���� ���� 
				if(buffr!=null){//Loginform.con
					try {
						buffr.close();//Loginform.con
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(buffw!=null){//Loginform.con
					try {
						buffw.close();//Loginform.con
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//���μ��� ����
				
				System.exit(0);
			}
		});
		
		setBounds(x,0,window_w,window_h);
		setVisible(true);		
		//setUndecorated(true);		
	}	
	
	public void setBasePanel(int count){
		p_center.setPreferredSize(new Dimension(350, 185+((AMember.h+5)*count)));
	}

	/*public static void main(String[] args) {
		new TestMain_FriendList(null,null,0);
	}*/
}
