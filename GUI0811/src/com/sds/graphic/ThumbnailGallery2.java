package com.sds.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ThumbnailGallery2 extends JFrame {
	JPanel p_left, p_right;
	Image currentImg; //유저가 지금 선택한 썸네일의 이미지!
	String[] path={
			"C:/java_workspace/GUI0811/res/1.jpg",
			"C:/java_workspace/GUI0811/res/2.jpg",
			"C:/java_workspace/GUI0811/res/3.jpg",
			"C:/java_workspace/GUI0811/res/4.jpg",
			"C:/java_workspace/GUI0811/res/5.jpg",
			"C:/java_workspace/GUI0811/res/6.jpg",
			"C:/java_workspace/GUI0811/res/7.jpg",
			"C:/java_workspace/GUI0811/res/8.jpg",
			"C:/java_workspace/GUI0811/res/9.jpg",
			"C:/java_workspace/GUI0811/res/10.jpg"
	};
	
	public ThumbnailGallery2(){
		p_left = new JPanel();
		
		p_right = new JPanel(){
			/*			
			public void paint(Graphics g) {
				g.drawImage(currentImg, 0, 0, 400, 350, this);
			}
 			*/			
		};
		
		for(int i=0;i<path.length;i++){
			MyCanvas can  =new MyCanvas(path[i]);
			can.setPreferredSize(new Dimension(50, 50));
			p_left.add(can);
		}
		
		setLayout(new GridLayout(1,2));
		
		p_left.setBackground(Color.YELLOW);
		p_right.setBackground(Color.GREEN);
		
		p_left.setPreferredSize(new Dimension(400, 600));
		p_right.setPreferredSize(new Dimension(400, 600));
		
		add(p_left);
		add(p_right);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,600);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ThumbnailGallery2();
	}

}
