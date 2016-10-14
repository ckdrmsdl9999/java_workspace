/*
 * Hero이건 ,Enemy이건 앞으로 게임에 등장하는 모든 사물이
 * 공통적인 특성과 동작을 보유하고 있으므로,각 객체마다
 * 일일이 특성 및 동작을 정의하여 코드를 중복하지 말고 
 * 최상위의 공통적 부모 클래스를 두어서 코드의 재사용을
 * 시도해 보자!!!
 * */
package com.sds.game;

import java.awt.Graphics;

abstract public class GameObject {
	String name;
	int x;
	int y;
	int velX;
	int velY;
	int width;
	int height;

	public GameObject(int x,int y,int width,int height,String name){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.name=name;
		
	} 

	abstract public void tick();

	abstract public void render(Graphics g);

}
