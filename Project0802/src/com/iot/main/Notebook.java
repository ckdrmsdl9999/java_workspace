package com.iot.main;
/*
	1.2개 이상의 맴버 변수 정의
	2.2개 이상의 맴버메서드 정의
	3.패키지는 com.iot.main에 넣기
	4.main메서드 없이 정의하기
	5.클래스 public으로 공개하기
	6.상대방의 .class를 넘겨 받아 실행해보기!
*/
public class Notebook{
	private int price=100;
	private String name="samsung";
	private String cpu="intel7";
	
	public int getPrice(){
		return price;
	}
	public void setPrice(int price){
		this.price=price;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getCpu(){
		return cpu;
	}
	public void setCpu(String cpu){
		this.cpu=cpu;
	}
}
