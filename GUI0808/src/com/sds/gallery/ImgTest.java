/*
 * �ڹ��� ��� gui ������Ʈ�� ������ �׸��� ��ü�� �Ǿ�
 * �����θ� �׸���. �̶� �׸� �׸��� ������ paint �޼����
 * ǥ���Ǹ� �� paint �޼����� �Ű������� �ȷ�Ʈ��Ȱ�� ��ü��
 * Graphics ��ü�� �μ��� ���� �Ǿ� ����.
 * ex) ��ư�� ������ �׸��� ����
 * */


package com.sds.gallery;

import java.awt.Canvas;//�ƹ��͵� �׷��� ���� ���� ��ȭ�� �˹���

import javax.swing.JFrame;

public class ImgTest extends JFrame{
	//MyButton bt;
	MyCanvas can;
	
	public ImgTest() {
		//bt = new MyButton("�� ��ư");
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
