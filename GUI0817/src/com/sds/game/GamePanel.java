/*
 * 게임의 모든 stage로서 실제적으로 모든 그래픽이 처리되는 영역
 * */

package com.sds.game;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	public static final int WIDTH=1024;
	public static final int HEIGHT=768;
	Thread gameThread;//게임의 심장부!!!!
	boolean flag=true;
	
	//오브젝트 메니져
	ObjectManager objectManager;
	
	Hero hero;//주인공 선언
	
	Enemy[] enemy=new Enemy[5];//적군들 선언
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.BLACK);
		
		objectManager=new ObjectManager();
		
		createHero();//주인공 등장
		createEnemy();//적군 등장
	}
	
	// 주인공 등장 메서드 정의
	public void createHero(){
		hero=new Hero(100,200,50,50,"Hero");
		
		//오브젝트 메니져 에 있는 리스트에 등록(데이터 베이스에 등록)
		objectManager.addObject(hero);
	}
	
	@Override
	public void paint(Graphics g) {
		for(int i=0;i<objectManager.objectlist.size();i++){
			GameObject obj=objectManager.objectlist.get(i);
			obj.tick();
			obj.render(g);
		}
	}
	
	//적군 등장 메서드!!
	public void createEnemy(){
			enemy[0]=new Enemy(200,200,30,30,"Enemy");
			enemy[1]=new Enemy(200,200,30,30,"Enemy");
			enemy[2]=new Enemy(700,250,30,30,"Enemy");
			enemy[3]=new Enemy(800,400,30,30,"Enemy");
			enemy[4]=new Enemy(300,500,30,30,"Enemy");
			
			objectManager.addObject(enemy[0]);
			objectManager.addObject(enemy[1]);
			objectManager.addObject(enemy[2]);
			objectManager.addObject(enemy[3]);
			objectManager.addObject(enemy[4]);
	}
	
	//게임 시작 메서드
	public void startGame(){
		
		if(gameThread !=null){//이미 가동 중이라면
			return;
		}
		gameThread=new Thread(this);
		gameThread.start();
		flag=true;
	}
	
	//일시 정지 메서드
	public void pauseGame(){
		flag=false;
		gameThread=null;
		
	}
	//게임 종료 메서드
	public void exitGame(){
		System.exit(-1);
		
	}
	
	
	@Override
	public void run() {
		while(flag){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//	System.out.println("게임 엔진 작동 중.....");
			//모든 게임 오브젝트들이 들어있는 명단을 이용하여 모든 게임 오브젝트에
			//tick, render를 호출하게 하자
			repaint();
		}
	}
}
