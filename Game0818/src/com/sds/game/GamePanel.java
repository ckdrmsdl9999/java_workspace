package com.sds.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


//jpanel 상속이유 paint 메서드를 재정의 하기 위해
public class GamePanel extends JPanel implements Runnable {
	public static final int WIDTH=1024;
	public static final int HEIGHT=768;
	Thread gameThread;
	boolean running=true;
	
	ObjectManager objectManager;
	Ship ship;
	Enemy enemy;
	Random random;
	Image img;
	
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		objectManager =new ObjectManager();

		createShip();
		createEnemy();
		createBlock();
	}

	//주인공 등장 메서드
	public void createShip(){
		ship=new Ship(50, 50, 100, 100, "Ship",objectManager);
		objectManager.objectlist.add(ship);
	}
	
	//적군 등장 메서드
	public void createEnemy(){
		random =new Random();
		
		for(int i=0;i<8;i++){
			int x=random.nextInt(7);
			enemy=new Enemy(WIDTH-150, x*100 , 100, 100, "Enemy", objectManager);
			objectManager.objectlist.add(enemy);
		}
	}
	//Block 생성 메서드
	public void createBlock(){
		//윗 블럭
		for(int i=0;i<20;i++){
			Block block =new Block(i*50, 0, 50, 50, "Block", objectManager);
			
			objectManager.addObject(block);
		}
		
		//아래 블럭
		for(int i=0;i<20;i++){
			Block block =new Block(i*50, HEIGHT-50, 50, 50, "Block", objectManager);
			objectManager.addObject(block);
		}
	}
	
	protected void paintComponent(Graphics g) {
		/*try {
			img = ImageIO.read(this.getClass().getClassLoader().getResource("space4.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//모든 게임 오브젝트의 tick(), render() 호출
		//render의 Graphics g 가 여기 있기 때문에 여기서 호출
		for(int i=0; i<objectManager.objectlist.size();i++){
			GameObject obj=objectManager.objectlist.get(i);
			obj.tick();
			obj.render(g);
			
		}
		
	}
	
	//게임시작 (쓰레드 생성 및 시작)
	public void startGame(){
		if(gameThread !=null){
			return;
		}
		running=true;
		gameThread = new Thread(this);
		gameThread.start();//Runnable 영역으로 진입시키기!
	}
	
	//게임 중단(쓰레드 종료)
	public void pauseGame(){
		running=false;
		gameThread=null;
	}
	
	//게임 종료(프로세스 종료)
	public void exitGame(){
		System.exit(0);//0보다 작은 수를 주면 빠져 나감
	}
	
	
	
	public void run() {
		while(running){
			//여기서 게임의 모든 객체를 작동!!!! tick() , render()
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("게임 엔진 작동중");
			//모든 오브젝트들을 대상으로 tick()과 render() 호출!!
			repaint(); //update() 호출---> paint()호출
		}
	}
}
