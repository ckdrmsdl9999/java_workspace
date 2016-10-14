package com.sds.game;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Block extends GameObject{

	public Block(int x, int y, int width, int height, String name, ObjectManager objectManager) {
		super(x, y, width, height, name, objectManager);
	}

	public void tick() {}

	public void render(Graphics g) {
		//벽돌을 이미지로 표현하기
		showRect(g);
	}

}
