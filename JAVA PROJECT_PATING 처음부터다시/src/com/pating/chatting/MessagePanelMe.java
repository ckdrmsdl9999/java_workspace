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
		// �� �гο� ���� ���� ���̾ƿ� ����
		this.setLayout(new BorderLayout());
		
		l_time = new JLabel(calTime());
		p_west = new JPanel();
		p_west.setLayout(new BorderLayout());
		p_west.add(l_time,BorderLayout.SOUTH);
		
		//īī���� �޼����� ���� �޼��� ���� ����
		area_txt = new JTextArea();
		area_txt.setLineWrap(true);
		area_txt.setColumns(15);
		area_txt.append(inputText);
		
		
		//P-west�� ä��â ���� �����ϰ� �ϱ�
		p_west.setBackground(new Color(255,165,0));;
		//�гο� ���̰� ������
		this.add(p_west,BorderLayout.WEST);
		this.add(area_txt,BorderLayout.CENTER);
		
	/*	JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.add(this);
		frame.setSize(600, 600);
		frame.setVisible(true);*/
	}
	
	
	//īī���� �޼����� ���� �ð� ���� ����
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
