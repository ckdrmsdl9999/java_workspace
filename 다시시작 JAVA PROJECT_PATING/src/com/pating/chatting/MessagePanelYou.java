package com.pating.chatting;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MessagePanelYou extends JPanel{
	JPanel p_west,p_center,p_east;
	Canvas c_profile;
	JLabel l_name;
	JTextArea area_txt;
	JLabel l_time;
	Toolkit kit;
	Image img;
	String path;
	
	MessagePanelMe messagePanelMe; // 시간정보를 얻어 오기 위해 참조
	
	public MessagePanelYou(String inputText) {
		//일단 모두 메모리에 올리기
		
		p_west = new JPanel();
		p_center = new JPanel();
		p_east = new JPanel();
		path= "C:/java_workspace/JAVA_PROJECT_PATING/res/profile.png";
		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage(path);
		c_profile = new Canvas(){
			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, 30, 30, this);
			}
		};
		l_name = new JLabel("임권창");
		area_txt = new JTextArea();
		l_time = new JLabel(new MessagePanelMe("아무거나 와도 상관 없지롱").calTime());
		
		//이 패널의 전체 레이아웃을 보더 레이아웃으로 설정한다.뿐만 아니라 전체 패널도 보더 레이아웃으로 설정해야한다.
		this.setLayout(new BorderLayout());
		p_west.setLayout(new BorderLayout());
		p_center.setLayout(new BorderLayout());
		p_east.setLayout(new BorderLayout());
		
		//크기 설정해야하는 컴포넌트들에 대해서는 미리 크기를 설정해두자
		c_profile.setPreferredSize(new Dimension(30, 30));
		l_name.setPreferredSize(new Dimension(15, 13));
		
		//텍스트 area에 대한 다른 설정
		area_txt.setLineWrap(true);
		area_txt.setColumns(15);
		area_txt.append(inputText);
		
		//이 패널의 서쪽에 사진을 북쪽으로 붙인다.
		p_west.add(c_profile,BorderLayout.NORTH);
		p_center.add(l_name,BorderLayout.NORTH);
		p_center.add(area_txt,BorderLayout.CENTER);
		p_east.add(l_time, BorderLayout.SOUTH);
		
		add(p_west,BorderLayout.WEST);
		add(p_center,BorderLayout.CENTER);
		add(p_east,BorderLayout.EAST);
		
	/*	JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.add(this);
		frame.setSize(600, 600);
		frame.setVisible(true);
		*/
	}
	
/*	public static void main(String args[]){
		new MessagePanelYou();
	}*/
	
}
