package com.pating.friendList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Top extends JPanel{
	JLabel la_logo;
	JPanel p, p_back;
	
		
	public Top() {
		
		Font font1 =new Font("Arial",Font.BOLD,15);
		la_logo = new JLabel(" PApaya chatTING ");
		la_logo.setFont(font1);
		la_logo.setForeground(Color.WHITE);		
		
		Color orange_deep = new Color(255,88,0);		
		
		p= new JPanel();
				/*	{
			@Override
			public void paint(Graphics g) {
				g.drawLine(0, 10, 300, 10);
			}
		};*/
		p_back= new JPanel();
		//p.setPreferredSize(new Dimension(w,h));
		p.setBackground(orange_deep);
		p_back.setBackground(orange_deep);
		
		p.add(la_logo);
		
		p_back.setLayout(new FlowLayout(FlowLayout.LEFT));
		//p_back.setPreferred
		
		p_back.add(p);
		add(p_back);
		/*
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);*/
	}
	/*
	public static void main(String[] args){
		new Top();
		
	}*/
}
