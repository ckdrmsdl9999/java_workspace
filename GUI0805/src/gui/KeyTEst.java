/*
  �ڹ� ����� ���ø����̼��� �̺�Ʈ ���� ����!!!!
  1�ܰ� - �˸´� �����ʸ� ���� �ϱ�!!!!
  
  2�ܰ� - �������� �޼��� ������ �ϱ�!!!!!(�ҿ����� �޼��� �ϼ�)
  
  3�ܰ�-�̺�Ʈ �ҽ�(�̺�Ʈ�� ����Ų ������Ʈ)�� �����ʿ��� ���� !!!!!
  
  
  */

package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class KeyTEst extends JFrame implements KeyListener,ActionListener{
	JButton bt;
	JTextField txt;
	
	public KeyTEst(){
		bt= new JButton("�� ��ư");
		txt= new JTextField(15);
		FlowLayout flow =new FlowLayout();
		
		this.setLayout(flow);
		add(txt);
		add(bt);
		
		//���� Ŭ������ ����������, �� key������ �̹Ƿ�....
		txt.addKeyListener(this);
		
		this.setSize(300,400);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("������?");
	}

	@Override
	public void keyReleased(KeyEvent e) {
			System.out.println("������ ������?");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new KeyTEst();
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
