package com.sds.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class EventTest2 extends JFrame{
	JButton  bt;
	JTextField txt;
	
	
	public EventTest2() {
		bt= new JButton("��ư");
		txt= new JTextField(15);
		MyListener ml = new MyListener();
		
		setLayout(new FlowLayout());
		add(txt);
		add(bt);
		
		//��ư�� �����ʿ��� ����
		//bt.addActionListener(ml);
		bt.addActionListener(ml);// �� ����
		
		System.out.println("setEt()�޼��� ȣ���� �� �ּҰ�"+this);
		ml.setEt(this);// Call by reference
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(300, 400);
		
		
	}
	
	

	public static void main(String[] args) {
		new EventTest2();
	}

}
