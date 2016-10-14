package com.iot.main;

class UseString2{

	public static void main(String[] args){
		/* String s=""  고정된 데이터로 일단 생성된 후 변경 불가
			String 은 immutable(불변=편집 불가) 성질을 가지고 있다.
			스트링객체의 불변의 특징으로 인하여, 자바의 String에는
			누적이라는 개념은 존재하지 않는다.
			해결책?? 
			편집 가능한 객체를 이용하는 것!!!!
		*/
		String s1=new String("apple");//명시적 방법으로 정의 heap 영역에 들어감.
		String s2 = "apple"; //묵시적,암시적 방법으로 인한 정의는 heap 영역에 상수풀(Constant Pool)에 올라가게 된다.
		String s3="apple"; // 상수풀에 중복된 내용이 있는지 확인 하고 기존에 있으면 기존 apple을 가리킨다.
		String s4=new String("apple");

		//String 은 객체 이므로 s1~~s4는 레퍼런스 변수다
		//따라서 아래의 코드는 내용비교가 아니라 주소 비교이다.
		System.out.println(s2==s3);// true가 나오는 이유는 상수풀이라는 영역에 생성되기 때문
												//상수풀의 특징 - 중복된 상수가 있다면, 재 생성하지 않는다.
		System.out.println(s1==s2);
		System.out.println(s1==s4);

		//주의)String 클래스 아님
		//편집 가능한 객체
		StringBuffer b=new StringBuffer();
		b.append("korea");
		b.append(" fighting");// 새롭게 생성되는 것이 아니라 기존 b가 변경됨
		
		//String이 아니기때문에 toString()메서드로 변환 시킨후 
		//출력하자!!!!!!!!!!!!!!]
		System.out.println(b.toString());
	}
}
