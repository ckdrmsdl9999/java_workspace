/*
 * 클래스 안에 클래스를 둘수 있는가??
 * 있다
 * */

package com.sds.ocjp;

import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AnonyTest extends JFrame{
	Canvas can;
	JButton bt1,bt2;
	
	public AnonyTest() {
		
		add(can=new Canvas(){//내부 익명 클래스
			public void paint(Graphics g) {
				g.drawLine(0, 0, 300, 400);
			}
		}); //프레임의 센터에 켄버스 부착
		
		
		bt1= new JButton("버튼1");
		
		setLayout(new FlowLayout());
		
		bt1.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("난 버튼1");
			}
		});
		bt2.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("난 버튼2");
			}
		});
	
		add(bt1);
		add(bt2= new JButton(){
			@Override
			public void paint(Graphics g) {
				g.drawString("나버튼2", 50, 50);
			}
			
		});
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new AnonyTest();

	}

}
