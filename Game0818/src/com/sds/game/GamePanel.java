package com.sds.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


//jpanel ������� paint �޼��带 ������ �ϱ� ����
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

	//���ΰ� ���� �޼���
	public void createShip(){
		ship=new Ship(50, 50, 100, 100, "Ship",objectManager);
		objectManager.objectlist.add(ship);
	}
	
	//���� ���� �޼���
	public void createEnemy(){
		random =new Random();
		
		for(int i=0;i<8;i++){
			int x=random.nextInt(7);
			enemy=new Enemy(WIDTH-150, x*100 , 100, 100, "Enemy", objectManager);
			objectManager.objectlist.add(enemy);
		}
	}
	//Block ���� �޼���
	public void createBlock(){
		//�� ��
		for(int i=0;i<20;i++){
			Block block =new Block(i*50, 0, 50, 50, "Block", objectManager);
			
			objectManager.addObject(block);
		}
		
		//�Ʒ� ��
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
		
		//��� ���� ������Ʈ�� tick(), render() ȣ��
		//render�� Graphics g �� ���� �ֱ� ������ ���⼭ ȣ��
		for(int i=0; i<objectManager.objectlist.size();i++){
			GameObject obj=objectManager.objectlist.get(i);
			obj.tick();
			obj.render(g);
			
		}
		
	}
	
	//���ӽ��� (������ ���� �� ����)
	public void startGame(){
		if(gameThread !=null){
			return;
		}
		running=true;
		gameThread = new Thread(this);
		gameThread.start();//Runnable �������� ���Խ�Ű��!
	}
	
	//���� �ߴ�(������ ����)
	public void pauseGame(){
		running=false;
		gameThread=null;
	}
	
	//���� ����(���μ��� ����)
	public void exitGame(){
		System.exit(0);//0���� ���� ���� �ָ� ���� ����
	}
	
	
	
	public void run() {
		while(running){
			//���⼭ ������ ��� ��ü�� �۵�!!!! tick() , render()
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("���� ���� �۵���");
			//��� ������Ʈ���� ������� tick()�� render() ȣ��!!
			repaint(); //update() ȣ��---> paint()ȣ��
		}
	}
}
