/*
 * ������ ��� stage�μ� ���������� ��� �׷����� ó���Ǵ� ����
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
	Thread gameThread;//������ �����!!!!
	boolean flag=true;
	
	//������Ʈ �޴���
	ObjectManager objectManager;
	
	Hero hero;//���ΰ� ����
	
	Enemy[] enemy=new Enemy[5];//������ ����
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.BLACK);
		
		objectManager=new ObjectManager();
		
		createHero();//���ΰ� ����
		createEnemy();//���� ����
	}
	
	// ���ΰ� ���� �޼��� ����
	public void createHero(){
		hero=new Hero(100,200,50,50,"Hero");
		
		//������Ʈ �޴��� �� �ִ� ����Ʈ�� ���(������ ���̽��� ���)
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
	
	//���� ���� �޼���!!
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
	
	//���� ���� �޼���
	public void startGame(){
		
		if(gameThread !=null){//�̹� ���� ���̶��
			return;
		}
		gameThread=new Thread(this);
		gameThread.start();
		flag=true;
	}
	
	//�Ͻ� ���� �޼���
	public void pauseGame(){
		flag=false;
		gameThread=null;
		
	}
	//���� ���� �޼���
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
		//	System.out.println("���� ���� �۵� ��.....");
			//��� ���� ������Ʈ���� ����ִ� ����� �̿��Ͽ� ��� ���� ������Ʈ��
			//tick, render�� ȣ���ϰ� ����
			repaint();
		}
	}
}
