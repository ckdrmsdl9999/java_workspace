/*
 * 자바의 모든 gui 컴포넌트는 스스로 그림의 주체가 되어
 * 스스로를 그린다. 이때 그림 그리는 행위는 paint 메서드로
 * 표현되며 이 paint 메서드의 매개변수로 팔레트역활의 객체인
 * Graphics 객체가 인수로 전달 되어 진다.
 * ex) 버튼이 스스로 그림을 증명
 * */


package com.sds.gallery;

import java.awt.Canvas;//아무것도 그려져 있지 않은 도화지 켄버스

import javax.swing.JFrame;

public class ImgTest extends JFrame{
	//MyButton bt;
	MyCanvas can;
	
	public ImgTest() {
		//bt = new MyButton("나 버튼");
		can = new MyCanvas();
		add(can);
		//add(bt);
		
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 400);
		setVisible(true);
	
	}
	public static void main(String[] args) {
		new ImgTest();
	}

}
