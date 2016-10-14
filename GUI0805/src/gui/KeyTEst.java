/*
  자바 기반의 어플리케이션의 이벤트 구현 순서!!!!
  1단계 - 알맞는 리스너를 선택 하기!!!!
  
  2단계 - 리스너의 메서드 재정의 하기!!!!!(불완전한 메서드 완성)
  
  3단계-이벤트 소스(이벤트를 일으킨 컴포넌트)와 리스너와의 연결 !!!!!
  
  
  */

package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class KeyTEst extends JFrame implements KeyListener,ActionListener{
	JButton bt;
	JTextField txt;
	
	public KeyTEst(){
		bt= new JButton("나 버튼");
		txt= new JTextField(15);
		FlowLayout flow =new FlowLayout();
		
		this.setLayout(flow);
		add(txt);
		add(bt);
		
		//현재 클래스는 프레임이자, 곧 key리스너 이므로....
		txt.addKeyListener(this);
		
		this.setSize(300,400);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("눌렀어?");
	}

	@Override
	public void keyReleased(KeyEvent e) {
			System.out.println("눌렀다 떼었어?");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new KeyTEst();
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
