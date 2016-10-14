package com.sds.json;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Profile extends JPanel {
	JPanel p_west; // 켄버스를 포함할 왼쪽 영역
	JPanel p_center; // 그리드가 적용될 센터 패널
	Toolkit kit;
	Image img;
	Canvas can;
	JLabel la_name, la_gender, la_age;

	public Profile(String url) {
		p_west = new JPanel();
		p_center = new JPanel();

		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage(url);
		
		can = new Canvas() {
			public void paint(Graphics g) {
				g.drawImage(img,0, 0, 100, 100, this);	
			}
		};
		can.setPreferredSize(new Dimension(100, 100));
		
		la_name = new JLabel("이름");
		la_gender = new JLabel("성별");
		la_age = new JLabel("나이");
		
		p_west.setPreferredSize(new Dimension(120, 120));
		//p_west.setBackground(Color.GREEN);
		p_center.setLayout(new GridLayout(3, 1));

		p_west.add(can);

		p_center.add(la_name);
		p_center.add(la_gender);
		p_center.add(la_age);

		setLayout(new BorderLayout());
		add(p_west, BorderLayout.WEST);
		add(p_center);

		setBackground(Color.CYAN);
		setPreferredSize(new Dimension(680, 110));
	}

}
