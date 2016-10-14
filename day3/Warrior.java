/*
	이름,체력
	싸우다,움직이다
*/	
class Warrior{
	//자바와 자바 스크립트는 모두 문자를 모아놓은 
	//문자열을 객체로 간주한다!!! 단 객체 이지만 반드시
	//new 연산자를 사용해야 한다는 원칙을 지키지 않는다.
	//(암시적 생성법)

	String name="듀로탄";
	int hp=0;
	
	
	public void fight(){
		System.out.println("싸웁니다.");
	}// 세미콜론은 필수 아니다.
	  //특히 호출이 아닌 정의시엔 붙이지 말자!!!

	public void move(){
	  System.out.println("움직입니다.");
	}

}