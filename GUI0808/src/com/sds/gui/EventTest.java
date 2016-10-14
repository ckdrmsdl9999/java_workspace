/*
 * �Ʒ��� ��Ģ�� �� �������(=�ȵ���̵� ���߽� ����)
  1.�˸´� ������ ����
  2.�������� �޼��� ������
  3.�̺�Ʈ �ҽ�(=�̺�Ʈ�� ����Ų ������Ʈ)�� �����ʿ� ����
  */


package com.sds.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;



public class EventTest extends JFrame implements ActionListener, KeyListener, WindowListener{
	JButton bt;
	JTextField text;
		
	public EventTest() {
	
		bt =new JButton("�Է�");
		text  = new JTextField(15);
		
		setLayout(new FlowLayout());
		
		add(bt);
		add(text);
		
		//��ư�� �����ʿ� ����
		bt.addActionListener(this);
		
		//�ؽ�Ʈ�ʵ�� �����ʿ� ����
		text.addKeyListener(this);
		
		//������� �����ʿ��� ����
		this.addWindowListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 400);
		setVisible(true);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("Ű ������?");
		
		int key =e.getKeyCode();
		//System.out.println(e);
		if (key==KeyEvent.VK_ENTER){
			System.out.println("�����ƾ�?");
			
		};
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("������?");
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
			
	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("windowActivated ȣ��");
	}
	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("windowClosed ȣ��");
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("windowClosing ȣ��");
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("windowClosing ȣ��");
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("windowDeiconified ȣ��");
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("windowIconified ȣ��");
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("windowOpened ȣ��");
		
	}
	public static void main(String[] args) {
		new EventTest();
	}

}

