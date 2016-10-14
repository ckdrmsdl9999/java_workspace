package com.sds.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

public class Ship extends GameObject{
	Image img;
	
	public Ship(int x, int y ,int width, int height, String name ,ObjectManager objectManager) {
		super(x,y,width,height,name,objectManager);
		
		//URL이란 자원의 위치를 의미한다 !! web-->>http://~~~~이지만
		//응용 :시스템에서의 자원의 위치
		img=getImage("spaceship3.png");
	}
	
	public void tick() {
		x+=velX;
		y+=velY;
		//실시간 사각형의 위치를 바꿔야 한다.!!!
		//그래야 주인공을 따라다니니까...그래야 충돌 검사 가능!!
		rect.setBounds(x, y, width, height);
		
		for(int i=0;i<objectManager.objectlist.size();i++){
			GameObject obj=objectManager.objectlist.get(i);
			
			if(obj.name.equals("Enemy")){
				Boolean result=rect.intersects(obj.rect);
				if(result){
					objectManager.removeObject(this);//ship 죽고
					objectManager.removeObject(obj);//적군 죽고
					
				}
			}
			
		}
	}
	public void render(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	
		showRect(g);
	}
	
	//총알 발사!!!
	public void fire(){
		Bullet bullet=new Bullet(x+width	, y+(height/2) , 50 , 15, "Bullet", objectManager);
		objectManager.objectlist.add(bullet);
	}
}
