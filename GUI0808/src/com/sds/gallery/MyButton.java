/*
 * ���� ���� �ƴ����� JButton�� ��ӹ޾� ����!!
 * �� �׷���?? 
 * ��� ������Ʈ�� �����ϰ� �ִ� paint �޼����� ���縦 Ȯ�� �ϱ� ����
 * */

package com.sds.gallery;

import java.awt.Graphics;

import javax.swing.JButton;

public class MyButton extends JButton{
	public MyButton(String title){
		super(title);
		
	}
/*
 * ��ư�� �׷����� ȣ��Ǵ� paint �޼��带 ������!!!!
 * */
	@Override
	public void paint(Graphics g) {
		System.out.println("paint ȣ��!!!");
	}
}
