package com.iot.main;
/*
	1.2�� �̻��� �ɹ� ���� ����
	2.2�� �̻��� �ɹ��޼��� ����
	3.��Ű���� com.iot.main�� �ֱ�
	4.main�޼��� ���� �����ϱ�
	5.Ŭ���� public���� �����ϱ�
	6.������ .class�� �Ѱ� �޾� �����غ���!
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
