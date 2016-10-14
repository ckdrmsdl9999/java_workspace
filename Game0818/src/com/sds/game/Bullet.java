package com.sds.game;

import java.awt.Graphics;
import java.awt.Image;

public class Bullet extends GameObject{
	Image img;
	public Bullet(int x, int y, int width, int height, String name,ObjectManager objectManager) {
		super(x, y, width, height, name,objectManager);
		img=getImage("Laser_Bullet.png");
		velX=5;
	}

	public void tick() {
		x+=velX;
		rect.setBounds(x, y, width, height);
		
		//총알과 적군들과 충돌 검사
		hitTest();
	}
	
	public boolean hitTest(){
		
		for(int i=0;i<objectManager.objectlist.size();i++){
			GameObject obj=objectManager.objectlist.get(i);
			
			if(obj.name.equals("Enemy")){
				//적군과 총알의 충돌검사!!!!!!
				boolean result=rect.intersects(obj.rect);
				if(result){
					//System.out.println("맞았어");
					objectManager.removeObject(this);//총알죽고
					objectManager.removeObject(obj);//적군 죽고
				}
			}
		}
		return false;
	}
	public void render(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
		showRect(g);
	}
}
