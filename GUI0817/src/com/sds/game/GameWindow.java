/*
 *  게임의 윈도우를 정의한다. 1024 x 768
 *  
 *  구현할 인터페이스가 3개 이상의 재정의 메서드를 보유할 경우
 *  사용하지도 않는 메서드를 코드상으로 존재 시켜야 하는 불합리한
 *  경우가 발생하게 된다.
 *  
 *  해결책!!!!
 *  sun에서는 어댑터라는 클래스를 통해 이 문제를 해결해 줌
 * */

package com.sds.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	String[] itemTitle={"게임시작","일시 중지","게임종료"};
//	KeyAdapter keyAdapter;//우리 대신 KeyListener를 구현한 추상 클래스!!!!
										//우리는 따라서keyReleased,keyPresses,keyTyped에 대한
										//정의 의무가 없다!!
										//KeyBoard 클래스를 따로 만들어서 안씀                                         
	KeyBoard keyboard;
	
	
	public GameWindow() {
		
		
		add(gamePanel=new GamePanel());
		pack();
		
		//메뉴 구성
		bar = new JMenuBar();
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
			
		//윈도우와 키리스너 연결
		this.addKeyListener(keyboard=new KeyBoard(gamePanel.objectManager));
	
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setBackground(Color.BLACK);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		if(obj==item[0]){//게임시작
			gamePanel.startGame();
			
		}else if(obj==item[1]){//게임 일시 중지
			gamePanel.pauseGame();
			
		}else if(obj==item[2]){//게임 종료
			gamePanel.exitGame();
		}
	}
	
	
	public static void main(String[] args) {
		new GameWindow();
	}

}
