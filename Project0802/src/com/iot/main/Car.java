/*
	������ �ڵ����� ����� ����� ������
	Ŭ������ ������ ������ ��ü�ڷ����� ���
	Car has a Wheel : has a ����
*/
package com.iot.main;

public class  Car{
	
	private Wheel w; //�� �� ��ü�� ���� �ƹ��͵� ���� ������ null�� �ʱ�ȭ �Ѵ�.
	private Engine e; //��ü�� ����
	private Handle h;//��ü�� ����
	private int price;//�Ϲ� ����

	//�����ڸ� �����Ͽ� �ʱ�ȭ �۾��� �����غ���!!
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
