package com.sds.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameWindow extends JFrame implements ActionListener{
	GamePanel gamePanel;
	JMenuBar bar;
	JMenu[] menu=new JMenu[2];
	JMenuItem[] item=new JMenuItem[3];
	String[] itemTitle={"���ӽ���","����","����"};
	KeyBoard keyBoard;
	
	
	//���� �ɼ�-���ӽ���,����,���� 
	//���� 
	public GameWindow() {
		bar= new JMenuBar();
		menu[0]=new JMenu("���ӿɼ�");
		menu[1]=new JMenu("����");
		
		for(int i=0;i<item.length;i++){
			item[i]= new JMenuItem(itemTitle[i]);
			menu[0].add(item[i]);
			item[i].addActionListener(this);// �����ʿ� ����!!
		}
		
		bar.add(menu[0]);
		bar.add(menu[1]);
		
		setJMenuBar(bar);
		
		
		gamePanel=new GamePanel();
		keyBoard =new KeyBoard(gamePanel.objectManager);
		
		//�����ʿ� ������� ����
		addKeyListener(keyBoard);
		
		add(gamePanel);
		setBackground(Color.BLACK);
		
		pack();//���빰 ��ŭ �ڽ��� ����� �����ִ� �޼���
		setLocationRelativeTo(null);// ȭ�� ����� â�� ��ġ�ϰ� ���� 
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		
		if(obj==item[0]){
			gamePanel.startGame();
			
		}else if(obj==item[1]){
			gamePanel.pauseGame();
			
		}else if(obj==item[2]){
			gamePanel.exitGame();
			
		}
		
	}
	
	public static void main(String[] args) {
		new GameWindow();
	}

}
