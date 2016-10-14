/*
	실행을 위한 클래스이며 사물을 표현하지 않음...
	String 클래스에 대해 알아보자
	참고) String 클래스는 우리가 정의하지 않은 외부 클래스이며
		   특히 sun에서 제공하는 javaSE  의 기본 객체이다.
			하지만 ,현재 클래스와는 별도의 패키지에 존재하므로 
			원칙상으로는 import 하여 그 위치를 명시해야 한다..
			but 명시하지 않아도 에러 안나는 이유는???
			java.lang패키지는 개발자가 명시하지 않더라도 이미 시스템적으로
			경로가 인식되어져 있다...따라서 import 의 대상이 되지 않는다.
			java.lang 패키지는 프로그램 언어 작성시 상당히 비중이 높고 
			자주 사용되는 주요 객체들을 담아놓은 패키지이기 때문이다...	

*/

package com.iot.main;

class UseString{
	public static void main(String[] args){
		//암시적, 묵시적 (implicit)생성법
		String s= "korea";
			
		//명시적 (explicit) 생성법
		String s1=new String("Korea");
		
		for(int i=0;i<s.length();i++){
			char c=s.charAt(i);
			System.out.print(c);
		}
	}
}
