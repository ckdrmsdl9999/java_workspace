
package com.sds.gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CircleMove extends JFrame{
	JPanel p_north;
	JButton bt_left,bt_right,bt_up,bt_down;
	Canvas can;
	JButton[] btn= new JButton[4];
	int x=100;//멤버 변수는 내부 익명 클래스가 접근 가능
	int y=100;
	
		public CircleMove() {
			p_north = new JPanel();
			bt_left = new JButton("Left");
			bt_right = new JButton("Right");
			bt_up = new JButton("Up");
			bt_down = new JButton("Down");
			btn[0]=bt_left;
			btn[1]=bt_right;
			btn[2]=bt_up;
			btn[3]=bt_down;
			
			
			//내부 익명 클래스 MyCanvas를 가만들지 않고 도 가능함
			can= new Canvas(){
				public void paint(Graphics g) {
					//개발자 주도하에 그래픽 처리하자!!
					g.drawOval(x, y, 40, 40);
				}
			};
			
			add(can);
			
			p_north.add(bt_left);
			p_north.add(bt_right);
			p_north.add(bt_up);
			p_north.add(bt_down);
			
			add(p_north, BorderLayout.NORTH);
			
			//버튼과 리스너와의 연결 익명 클래스이기 때문에 인터페이스 라도 new 가능
			//내부 익명 클래스에서는 지역변수에 접근할 수 없다. final로 선언된 경우는 가능
			//for문도 가능
			final int k=9; 
			for(int i=0;i<btn.length;i++){
				btn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
					
						Object obj=e.getSource();
						System.out.println(obj);
						JButton b=(JButton)obj;
						System.out.println(b.getText()+"누름");
						//System.out.println(x);//x에 접근 가능
						//System.out.println(k);//k에 접근 가능
						
						switch(b.getText()){
							case "Left":moveHorizon(-5);break;
							case "Right":moveHorizon(+5);break;
							case "Up":moveVertical(-5);break;
							case "Down":moveVertical(+5);break;
						}
					}
				});
			}
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
			setSize(600, 600);
		}
		
		//좌우 이동 메서드
		public void moveHorizon(int x){
			this.x+=x;
			can.repaint();
		}
		
		
		//위아래 이동 메서드
		public void moveVertical(int y){
			this.y+=y;
			can.repaint();
		}
		
		
		
	public static void main(String[] args) {
		new CircleMove();
	}

}
