/*
	클래스 작성시, 변수는 private로 접근을 제한하고
	이 변수에 대한 조작은 메서드를 통해 제공하는 객체지향언어의
	개발 기법을 가리켜 은닉화(=encapsulation)라 한다.
	고객 계좌를 정의한다.
*/
package com.iot.main;

public class Account{
	//실제적으로 클래스가 보유한 데이터는 보호 대상이다.
	//따라서 private으로 보안 처리 하는게 대부분이다.
	private String num="110-58789-232";
	private int total=3000;

	//고객의 계좌를 접근 할 수 있도록 메서드를 제공...
	public int getTotal(){
		return total;
	}
	// 멤버 변수의 값을 변경할 용도로 정의되는 메서드를 가리켜 setter라 한다.
	//setter 메서드 정의 시 규칙
	//set+멤버변수명 , 주의 할 점  set 뒤의 단어는 첫 철자를
	//대문자로 한다.!!
	public void setTotal(int total){
		this.total=total;
	}
}
