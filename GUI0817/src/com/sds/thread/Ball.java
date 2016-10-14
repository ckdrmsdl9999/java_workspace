package com.sds.thread;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Ball extends JFrame implements KeyListener{
	Canvas can;
	int x;
	
	public Ball() {
		
		can =new Canvas(){
			public void paint(Graphics g) {
				g.drawOval(x, 100, 50, 50);
			}
			
		};
		//윈도우와 리스너 연결
		addKeyListener(this);
		
		add(can);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//윈도우를 모니터 크기와 상관없이 항상 가운데 띄우기!!
		setLocationRelativeTo(null);
		setVisible(true);
		setSize(700, 400);
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			//쓰레드 한개를 생성 해서 작성
			Thread thread= new Thread(){
				public void run() {
					while(true){
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						x+=5;
						can.repaint();
					}
				};
				
			};
			thread.start();
		}
	}



	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	public static void main(String[] args) {
		new Ball();
	}
}
