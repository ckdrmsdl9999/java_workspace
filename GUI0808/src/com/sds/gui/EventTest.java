/*
 * 아래의 원칙을 잘 기억하자(=안드로이드 개발시 동일)
  1.알맞는 리스너 선택
  2.리스너의 메서드 재정의
  3.이벤트 소스(=이벤트를 일으킨 컴포넌트)와 리스너와 연결
  */


package com.sds.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;



public class EventTest extends JFrame implements ActionListener, KeyListener, WindowListener{
	JButton bt;
	JTextField text;
		
	public EventTest() {
	
		bt =new JButton("입력");
		text  = new JTextField(15);
		
		setLayout(new FlowLayout());
		
		add(bt);
		add(text);
		
		//버튼과 리스너와 연결
		bt.addActionListener(this);
		
		//텍스트필드와 리스너와 연결
		text.addKeyListener(this);
		
		//윈도우와 리스너와의 연결
		this.addWindowListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 400);
		setVisible(true);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("키 눌렀어?");
		
		int key =e.getKeyCode();
		//System.out.println(e);
		if (key==KeyEvent.VK_ENTER){
			System.out.println("엔터쳤어?");
			
		};
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("눌렀어?");
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
			
	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("windowActivated 호출");
	}
	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("windowClosed 호출");
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("windowClosing 호출");
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("windowClosing 호출");
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("windowDeiconified 호출");
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("windowIconified 호출");
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("windowOpened 호출");
		
	}
	public static void main(String[] args) {
		new EventTest();
	}

}

