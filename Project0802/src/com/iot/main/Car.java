/*
	현실의 자동차를 만들되 제대로 만들자
	클래스가 보유한 변수가 객체자료형일 경우
	Car has a Wheel : has a 관계
*/
package com.iot.main;

public class  Car{
	
	private Wheel w; //휠 형 객체형 변수 아무것도 주지 않으면 null로 초기화 한다.
	private Engine e; //객체형 변수
	private Handle h;//객체형 변수
	private int price;//일반 변수

	//생성자를 정의하여 초기화 작업을 진행해보자!!
	public Car(){
		w= new Wheel();
		e= new Engine();
		h= new Handle();
		price=200;
	}
	public void go(){
		w.roll();
	}
}
