package com.iot.main;

class UseTv{
	
	public void getInfo(Tv tv,int k){
		//매개변수 tv의 인스턴스 1개를 넘겨서 해당 tv의 가격을 출력하라!!
		//getInfo를 호출해 보시오
		tv.printPrice();
	}
	public void test(boolean b,int k){
		System.out.println(b+","+k);
	}
	//아래의 메서드에서 tv가격을 출력!!!!
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
