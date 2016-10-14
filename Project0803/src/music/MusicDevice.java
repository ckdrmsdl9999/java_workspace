/*
PM 입장에서 사원들을 구속하기 위한 클래스 정의

추상 클래스란?
-미완성 클래스를 말함.
-미완성이란?
	몸체 없는 추상 메서드를 단 1개라도 보유하고 있다면
	그 클래스는 완전하지 못하므로 인스턴스화 될 수 없다.
	따라서 해당 추상클래스를 상속 받는 자식에게 구현의
	의무를 부여하고, 자식이 구현을 완료했을때 비로서
	부모 클래스도 인스턴스화 될 수 있다.
*/
package music;
abstract public class MusicDevice{ 
	abstract public void setVolume(); // 의도적으로 미완성이라고 알려주는 abstract
	abstract public void playMP3(); //미완성으로 남겨 놓아야 자식 클래스에게 구현 강제를 가할 수
												//있다.
}
