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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AFriend_memory extends JPanel implements ActionListener{
	JPanel p_west,p_center,p_east;
	JLabel la_nickname, la_statusMessage,la_vacancy;
	JButton bt;
	Toolkit kit= Toolkit.getDefaultToolkit();
	Image img;	
	
	Color orange = new Color(255,165,0);
	Color orange_deep = new Color(255,88,0);
	Color green= new Color(51,102,0);	
	
	static int h=80;
	
	public AFriend_memory(String url) {
		AFriend_memory me=this;	
		Font font1 =new Font("",Font.BOLD,18);	
		p_center = new JPanel();
		p_east = new JPanel();///
		bt = new JButton("模备肝扁");///
		la_nickname= new JLabel();
		la_statusMessage= new JLabel();		
		
		la_nickname.setFont(font1);
		la_nickname.setBackground(Color.WHITE);		
		//la_vacancy=new JLabel("");
		la_nickname.setBackground(Color.WHITE);
		la_statusMessage.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		
		img=kit.getImage(url);		
		p_west = new JPanel(){
			@Override
			public void paint(Graphics g) {
				g.drawImage(img,5, 5, 50, 50,getParent());
			}
		};
		p_west.setPreferredSize(new Dimension(80, 70));
		p_west.setBackground(Color.WHITE);
		
		p_center.setLayout(new BorderLayout());//3,1));//);BorderLayout());//			
		p_center.setBackground(Color.WHITE);		
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connect connect =new Connect(me);
			}
			
		});
		//p_east.add(bt);///
		p_center.add(la_nickname,BorderLayout.NORTH);//,BorderLayout.NORTH);
		//p_center.add(la_vacancy);
		p_center.add(la_statusMessage,BorderLayout.CENTER);//,BorderLayout.CENTER);		
		add(p_east,BorderLayout.EAST);///
		add(p_west,BorderLayout.WEST);
		add(p_center,BorderLayout.CENTER);
		
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		setLayout(new FlowLayout(FlowLayout.LEFT));		
		setPreferredSize(new Dimension(365, h));
		//setBounds(10, 0, 350, 80);
		
		//setLayout(new BorderLayout());
		/*	
		add(p);
		
		setBackground(orange);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 120);
		setVisible(true);	
	*/
	}
	/*
	public static void main(String[] args){
		
		new AFriend_memory("C:/Java_workspace/GUI0818/res/map_editted.png");
	}
	*/
	public void BecomeFriend(){
		System.out.println("me客 you甫 模备肝扁 insert executeUpdate" );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BecomeFriend();
		
	}
	
	
}
