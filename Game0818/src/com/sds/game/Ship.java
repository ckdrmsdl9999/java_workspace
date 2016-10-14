package com.sds.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

public class Ship extends GameObject{
	Image img;
	
	public Ship(int x, int y ,int width, int height, String name ,ObjectManager objectManager) {
		super(x,y,width,height,name,objectManager);
		
		//URL�̶� �ڿ��� ��ġ�� �ǹ��Ѵ� !! web-->>http://~~~~������
		//���� :�ý��ۿ����� �ڿ��� ��ġ
		img=getImage("spaceship3.png");
	}
	
	public void tick() {
		x+=velX;
		y+=velY;
		//�ǽð� �簢���� ��ġ�� �ٲ�� �Ѵ�.!!!
		//�׷��� ���ΰ��� ����ٴϴϱ�...�׷��� �浹 �˻� ����!!
		rect.setBounds(x, y, width, height);
		
		for(int i=0;i<objectManager.objectlist.size();i++){
			GameObject obj=objectManager.objectlist.get(i);
			
			if(obj.name.equals("Enemy")){
				Boolean result=rect.intersects(obj.rect);
				if(result){
					objectManager.removeObject(this);//ship �װ�
					objectManager.removeObject(obj);//���� �װ�
					
				}
			}
			
		}
	}
	public void render(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	
		showRect(g);
	}
	
	//�Ѿ� �߻�!!!
	public void fire(){
		Bullet bullet=new Bullet(x+width	, y+(height/2) , 50 , 15, "Bullet", objectManager);
		objectManager.objectlist.add(bullet);
	}
}
