/*
상속
  - 한번 작성한 클래스 코드는 중복하지 않고, 추후
  재사용할 수 있는데, 이러한 객체지향 언어의 개발 기법을
  상속이라 한다.

  oop(객체지향)언어의 특징-은닉화, 상속
*/

package human;

public class Person{
	String color;
	
	public void walk(){
		System.out.println("걷습니다.");
	}
	public void run(){
		System.out.println("뜁니다.");
	}
}
