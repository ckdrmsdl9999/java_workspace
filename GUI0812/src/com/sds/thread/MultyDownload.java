package com.sds.thread;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;

public class MultyDownload extends JFrame {
	JButton bt;
	ProgresBar bar1,bar2,bar3;
	Thread t1,t2,t3;
	
	public MultyDownload() {
		setLayout(new FlowLayout());
		bt=new JButton("다운로드");
		bar1 = new ProgresBar(1000);
		bar2 = new ProgresBar(500);
		bar3 = new ProgresBar(1500);
		
		add(bt);
		add(bar1);
		add(bar2);
		add(bar3);
		
		//Runnable로 run메서드를 재정의할 경우
		// Thread 생성시 Runnable 객체를 인수로 넣어야
		//Runnable의 run 메서드가 동작하게 된다!!
		//언제 쓰나? Thread 클래스를 상속받아 코드 작성하려고 하였으나
		//이미 다른 클래스의 자식 클래스인경우 다중 상속이 불가능 하므로
		//이때 인터페이스인 Runnable를 이용할 수 있다.
		t1 =new Thread(bar1);
		t2 =new Thread(bar2);
		t3 =new Thread(bar3);
		
		bt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				t1.start();
				t2.start();
				t3.start();
			}
		});
		
		
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 500);
	}
	
	
	
	public static void main(String[] args) {
		new MultyDownload();
	}

}
