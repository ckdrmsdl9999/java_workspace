/*
자바의 모든 클래스는 개발자가 정의한 클래스이건
sun이 제공하는 api이건 모두  Object라는 최상위 객체를
상속 받는다.
*/
package main;
class StringTest{
	public static void main(String[] args){
		public void test(){
			boolean b=	this.equals("대한민국");
		}
		
		String a="대한민국";
		String b="대한민국";

		System.out.println(a.equals(b));
	}
}
