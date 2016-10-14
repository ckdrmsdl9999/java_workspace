package com.pating.chatting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MessagePanelMe extends JPanel{

	JPanel p_west;
	JLabel l_time;
	JTextArea area_txt;
	Calendar cal;
	SimpleDateFormat sdf;
	
	JFrame frame;
	
	public MessagePanelMe(String inputText) {
		// 이 패널에 대한 보더 레이아웃 설정
		this.setLayout(new BorderLayout());
		
		l_time = new JLabel(calTime());
		p_west = new JPanel();
		p_west.setLayout(new BorderLayout());
		p_west.add(l_time,BorderLayout.SOUTH);
		
		//카카오톡 메세지에 들어가는 메세지 정보 생성
		area_txt = new JTextArea();
		area_txt.setLineWrap(true);
		area_txt.setColumns(15);
		area_txt.append(inputText);
		
		
		//P-west를 채팅창 색과 동일하게 하기
		p_west.setBackground(new Color(255,165,0));;
		//패널에 붙이고 마무리
		this.add(p_west,BorderLayout.WEST);
		this.add(area_txt,BorderLayout.CENTER);
		
	/*	JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.add(this);
		frame.setSize(600, 600);
		frame.setVisible(true);*/
	}
	
	
	//카카오톡 메세지에 들어가는 시간 정보 생성
	public String calTime(){
		
		cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		String timeOutput = Integer.toString(hour) + ":" + Integer.toString(min);
		System.out.println(timeOutput);
		return timeOutput;
	}

/*	public static void main(String args[]){
		new MessagePanelMe();
	}*/
	
}
