package com.sds.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class EventTest2 extends JFrame{
	JButton  bt;
	JTextField txt;
	
	
	public EventTest2() {
		bt= new JButton("버튼");
		txt= new JTextField(15);
		MyListener ml = new MyListener();
		
		setLayout(new FlowLayout());
		add(txt);
		add(bt);
		
		//버튼과 리스너와의 연결
		//bt.addActionListener(ml);
		bt.addActionListener(ml);// 도 가능
		
		System.out.println("setEt()메서드 호출전 내 주소값"+this);
		ml.setEt(this);// Call by reference
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(300, 400);
		
		
	}
	
	

	public static void main(String[] args) {
		new EventTest2();
	}

}
