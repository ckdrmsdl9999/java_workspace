/*
 * 흔한 일은 아니지만 JButton을 상속받아 보자!!
 * 왜 그러나?? 
 * 모든 컴포넌트가 보유하고 있는 paint 메서드의 존재를 확인 하기 위해
 * */

package com.sds.gallery;

import java.awt.Graphics;

import javax.swing.JButton;

public class MyButton extends JButton{
	public MyButton(String title){
		super(title);
		
	}
/*
 * 버튼이 그려질때 호출되는 paint 메서드를 재정의!!!!
 * */
	@Override
	public void paint(Graphics g) {
		System.out.println("paint 호출!!!");
	}
}
