/*
	java.exe실행시 연산을 수행할 두 데이터를 넘겨서
	두수의 합을 구하는 프로그램...

	일반적으로 자바 언어는 기본 자료형(문자,숫자,논리값)간에
	형 변환과 객체간의 형변환은 지원하고 있다.
	하지만 기본자료형과 객체 자료형간의 형변환도 가능할까????
	가능하나....단 기본 자료형으로 변환 할 수 있는 대상만 가능하다.
	예) "true"--> true
		 "2"-->2
		 "Dog"-->2(Dog은 기본자료형으로 변형될 가능성이 없다. 불가)
		 "3"  --> '3' (String-->char )
	자바 언어에서는 모든 기본자료형 마다 1:1로 대응하는 Wrapper 클래스를 통해 객체
	자료형과 기본자료형간의 형변환을 지원하고 있다.

	정수 
	byte	:Byte
	short :Short
	int		:Integer 
	long	:Long
	
	실수 
	float	:Float
	double:Double
	
	논리값
	boolean : Boolean
*/
package main;
class Cal{
	public static void main(String[] args){
		String n1=args[0];//"1"-->1
		String n2=args[1];

		int a= Integer.parseInt(n1);//문자열을 정수화 시킴
		int b= Integer.parseInt(n2);
		System.out.println("두수의 합은"+(a+b));
	
	}
}
