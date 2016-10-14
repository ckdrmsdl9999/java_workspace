package com.iot.main;
class  MainTest{
	public void test(String[] k){
		System.out.println(k.length);
		
		for(int i=0;i<k.length;i++){
			System.out.println(k[i]);
		}
	}
	public static void main(String[] args){
		MainTest mt=new MainTest();

		//자바에서는 배열 생성시 반드시 그 크기를 명시해야 한다.
		//자바에서는 배열을 객체 취급하므로, 배열을 가리키는 변수는 레퍼런스변수와 같다.
		String[] arr= new String[3];//배열선언 String[] arr = new String[배열크기]
		arr[0]="사과";
		arr[1]="딸기";
		arr[2]="오렌지";

		mt.test(arr);

		//System.out.println("실행시 배열의 크기는" +args.length);
	}
}
