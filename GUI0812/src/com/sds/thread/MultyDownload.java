package com.sds.thread;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;

public class MultyDownload extends JFrame {
	JButton bt;
	ProgresBar bar1,bar2,bar3;
	Thread t1,t2,t3;
	
	public MultyDownload() {
		setLayout(new FlowLayout());
		bt=new JButton("�ٿ�ε�");
		bar1 = new ProgresBar(1000);
		bar2 = new ProgresBar(500);
		bar3 = new ProgresBar(1500);
		
		add(bt);
		add(bar1);
		add(bar2);
		add(bar3);
		
		//Runnable�� run�޼��带 �������� ���
		// Thread ������ Runnable ��ü�� �μ��� �־��
		//Runnable�� run �޼��尡 �����ϰ� �ȴ�!!
		//���� ����? Thread Ŭ������ ��ӹ޾� �ڵ� �ۼ��Ϸ��� �Ͽ�����
		//�̹� �ٸ� Ŭ������ �ڽ� Ŭ�����ΰ�� ���� ����� �Ұ��� �ϹǷ�
		//�̶� �������̽��� Runnable�� �̿��� �� �ִ�.
		t1 =new Thread(bar1);
		t2 =new Thread(bar2);
		t3 =new Thread(bar3);
		
		bt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				t1.start();
				t2.start();
				t3.start();
			}
		});
		
		
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 500);
	}
	
	
	
	public static void main(String[] args) {
		new MultyDownload();
	}

}
