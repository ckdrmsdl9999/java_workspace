/*
현실에서 존재하는 모든 사물은 단독으로 존재할 수 없다!!
이러한 현상을 자바언어에서도 표현할 수 있는데, sun에서는 이
현상을 다시 2가지로 분류했다.

1)has a 관계 : 특정 객체가 다른 객체를 부품으로 보유한 관계
2)is a 관계: 객체들간 부모 자식 관계를 맺은 경우
*/
package human;

public class WhitePerson extends Person{
	                               /*is a*/
	String color="white";
	
	public void openParty(){
		System.out.println("파티를 즐깁니다.");

	}
}
