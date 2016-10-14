package com.sds.chatting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatA extends JFrame implements ActionListener,KeyListener{
	JTextArea area;
	JPanel p;
	JTextField txt;
	JButton bt;
	JScrollPane scroll;
	ChatB chatb;
	ChatC chatc;
	
	
	public ChatA() {
		area = new JTextArea();
		p=new JPanel();
		txt =new JTextField(15);
		bt= new JButton("새창");
		bt.addActionListener(this);
		txt.addKeyListener(this);
		
		
		//스크롤을 적용하고자 하는 컴포넌트를 인수로 넘긴다.!
		scroll=new JScrollPane(area);
		
		//센터에 area 부착
		add(scroll);
		
		//패널에 txt와 bt부착후 남쪽에 패널을 부착!!
		p.add(txt);
		p.add(bt);
		add(p,BorderLayout.SOUTH);		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 200, 300, 400);
		setSize(300,400);
		setVisible(true);
	
	}
	
	
	//버튼 누르면 ChatB 띄우기
	@Override
	public void actionPerformed(ActionEvent e) {
		chatb=new ChatB(this);
		chatc=new ChatC(this);
		bt.setEnabled(false);//버튼 비활성화
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_ENTER){
			String msg=txt.getText();
			area.append(msg+"\n");
			
			//입력메세지 초기화
			txt.setText("");
			
			//ChatB의 area에 값을 누적하자!!
			chatb.area.append(msg+"\n");
			chatc.area.append(msg+"\n");
			
		}		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		new ChatA();
	}
}
