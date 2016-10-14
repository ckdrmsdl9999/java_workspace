package com.iot.main;

class UseAccount{
	public static void main(String[] args){
	//계좌의 잔고 출력하기!!
	Account a= new Account();
	System.out.println(a.getTotal());
	a.setTotal(780000);
	System.out.println(a.getTotal());
	}
}
