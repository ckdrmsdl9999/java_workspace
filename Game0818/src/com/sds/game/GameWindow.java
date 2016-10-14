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
	String[] itemTitle={"게임시작","멈춤","종료"};
	KeyBoard keyBoard;
	
	
	//게임 옵션-게임시작,멈춤,종료 
	//도움말 
	public GameWindow() {
		bar= new JMenuBar();
		menu[0]=new JMenu("게임옵션");
		menu[1]=new JMenu("도움말");
		
		for(int i=0;i<item.length;i++){
			item[i]= new JMenuItem(itemTitle[i]);
			menu[0].add(item[i]);
			item[i].addActionListener(this);// 리스너와 연결!!
		}
		
		bar.add(menu[0]);
		bar.add(menu[1]);
		
		setJMenuBar(bar);
		
		
		gamePanel=new GamePanel();
		keyBoard =new KeyBoard(gamePanel.objectManager);
		
		//리스너와 윈도우와 연결
		addKeyListener(keyBoard);
		
		add(gamePanel);
		setBackground(Color.BLACK);
		
		pack();//내용물 만큼 자신의 사이즈를 맞춰주는 메서드
		setLocationRelativeTo(null);// 화면 가운데로 창을 위치하게 해줌 
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
