package com.sds.thread;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AnimationApp extends JFrame{
	JButton bt;
	Canvas can;
	Toolkit kit=Toolkit.getDefaultToolkit();
	Image img;
	int x;
	Thread thread;
	
	public AnimationApp() {
		bt=new JButton("발사");
		img=kit.getImage("C:/java_workspace/GUI0812/res/bullet.png");
		
		
		can= new Canvas(){
			public void paint(Graphics g) {
				g.drawImage(img, x, 0, 50, 50, this);
			}
		};
		
		bt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				thread.start();
			}
		});
		
		//쓰레드 생성
		thread =new Thread(){

			public void run() {
				while(true){
					try {
						Thread.sleep(70);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					move();
					
				}
			}
			
		};
		
		add(bt, BorderLayout.NORTH);
		add(can);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(900,800);
	}
	//x축 이동
	public void move(){
		x+=10;
		can.repaint();
	}
	public static void main(String[] args) {
		new AnimationApp();
	}

}
