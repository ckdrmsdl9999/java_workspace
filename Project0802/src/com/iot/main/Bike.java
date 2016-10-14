package com.iot.main;

/*패키지에 넣은 클래스는 공개하지 않으면 절대로 접근할 수 없다.!!
public 을 붙여 공개 한다.
자바의 보안 4단계 등급(접근 제한자)
public -보안 최하 ,보안을 처리 하지 않고 공개하는 접근제한자 모든 객체의 접근을 허용

protected - 상속관계의 객체와 같은 패키지 내에 있는 객체에게 접근을 허용해줌

default - 명시 안한경우 적용, 오직 같은 패키지내에 있는 객체에게만 접근을 허용함

private-보안 최고등급, 어떤 누구에게도 접근을 허용하지 않음.

*/
public class  Bike{
	String name="시티100";
	public int price=2000;
	private int cc=100;

	public void run(){
		System.out.println(cc+"바이크 출발!!");
	}

}
