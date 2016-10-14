/*
 * 적군을 정의한다!!!
 * */

package com.sds.game;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject {
	//적군이 태어날때 결정될 데이터!!
	public Enemy(int x,int y,int width,int height,String name) {
		super(x, y, width, height, name);
	}
	public void tick() {
		x+=velX;
		y+=velY;
	}

	//적군이 어떻게 그려질지 정의 하자!
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.drawOval(x, y, width	, height);
	}
	
	
}
