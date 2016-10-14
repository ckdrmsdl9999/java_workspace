package com.sds.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.sds.client.chatting.UserdList;

public class AppMain extends JFrame{
	public static BufferedReader buffr;
	public static BufferedWriter buffw;
	public static int me;
	
	JPanel p_north;
	JButton[] btn=new JButton[4];
	JPanel[] p_content=new JPanel[4];
	String[] iconPath={"chat.png","Game.png","Profile.png","stock.png"};
	URL url;
	Color[] bg={Color.RED,Color.YELLOW,Color.BLUE,Color.GREEN};
	JPanel p_center;//BorderLayout 레이아웃을 적용할 패널!!
	JScrollPane scroll;
	
	public AppMain(BufferedReader buffr, BufferedWriter buffw, int me) {
		this.buffr=buffr;
		this.buffw=buffw;
		this.me=me;
		p_north =new JPanel();
		p_north.setPreferredSize(new Dimension(500, 50));
		p_north.setLayout(new GridLayout(1, 4));
		
		p_center= new JPanel();
		p_center.setLayout(new BorderLayout());
		p_center.setPreferredSize(new Dimension(400, 450));
		
		for(int i=0;i<btn.length;i++){
			url=this.getClass().getClassLoader().getResource(iconPath[i]);
			ImageIcon icon=new ImageIcon(url);
			Image img=icon.getImage();
			img=img.getScaledInstance(60	, 50, Image.SCALE_SMOOTH);
			icon.setImage(img);
			
			btn[i]=new JButton(icon);
			p_north.add(btn[i]);
			
			/*
			p_content[i]=new JPanel();
			
			p_content[i].setBackground(bg[i]);
			p_content[i].setPreferredSize(new Dimension(500, 450));
			p_content[i].setVisible(false);
			p_center.add(p_content[i]);*/
		}
		
		//채팅 목록 나오기!!!
		UserdList friendlist= new UserdList();
		scroll =new JScrollPane(friendlist);
		p_center.add(scroll);
		
		add(p_center);
		add(p_north,BorderLayout.NORTH);
		
		setSize(500, 500);
		setVisible(true);
		
	}

	/*public static void main(String[] args) {
		new AppMain();
	}*/
}
