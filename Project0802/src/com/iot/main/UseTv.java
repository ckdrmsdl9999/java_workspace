package com.iot.main;

class UseTv{
	
	public void getInfo(Tv tv,int k){
		//�Ű����� tv�� �ν��Ͻ� 1���� �Ѱܼ� �ش� tv�� ������ ����϶�!!
		//getInfo�� ȣ���� ���ÿ�
		tv.printPrice();
	}
	public void test(boolean b,int k){
		System.out.println(b+","+k);
	}
	//�Ʒ��� �޼��忡�� tv������ ���!!!!
	public void showPrice(){
		Tv tv= new Tv();
		tv.printPrice();
	}
	
	public static void main(String[] args){
		UseTv ut= new UseTv();
		ut.showPrice();
		ut.test(true, 5);
		
		Tv t=new Tv();
		ut.getInfo(t,5);
	}
}
