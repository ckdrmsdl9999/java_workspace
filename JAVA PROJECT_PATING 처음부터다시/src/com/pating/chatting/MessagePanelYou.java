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
	
	MessagePanelMe messagePanelMe; // �ð������� ��� ���� ���� ����
	
	public MessagePanelYou(String inputText) {
		//�ϴ� ��� �޸𸮿� �ø���
		
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
		l_name = new JLabel("�ӱ�â");
		area_txt = new JTextArea();
		l_time = new JLabel(new MessagePanelMe("�ƹ��ų� �͵� ��� ������").calTime());
		
		//�� �г��� ��ü ���̾ƿ��� ���� ���̾ƿ����� �����Ѵ�.�Ӹ� �ƴ϶� ��ü �гε� ���� ���̾ƿ����� �����ؾ��Ѵ�.
		this.setLayout(new BorderLayout());
		p_west.setLayout(new BorderLayout());
		p_center.setLayout(new BorderLayout());
		p_east.setLayout(new BorderLayout());
		
		//ũ�� �����ؾ��ϴ� ������Ʈ�鿡 ���ؼ��� �̸� ũ�⸦ �����ص���
		c_profile.setPreferredSize(new Dimension(30, 30));
		l_name.setPreferredSize(new Dimension(15, 13));
		
		//�ؽ�Ʈ area�� ���� �ٸ� ����
		area_txt.setLineWrap(true);
		area_txt.setColumns(15);
		area_txt.append(inputText);
		
		//�� �г��� ���ʿ� ������ �������� ���δ�.
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
