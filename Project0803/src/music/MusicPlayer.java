/*
28명의 사원중 이상신 사원이 제작할 어플리케이션을
정의한다.

	자바 언어는 다중상속을 허용하지 않는다. 
	이 원칙은 현실을 반영하고 있다는 측면에서는 올바른 제한
	사항 이지만 요즘 현실은 상당히 다중상속적 특징을 띄는
	객체가 많기 때문에 다중상속적  현실을 반영하기엔 무리가 있다.
	이러한 문제를 해결하기 위한 방법이 바로 인터페이스이다.
	인터페이스는 클래스는 아니면서 오직 기능만을 보유한 집합이므로
	하나의 클래스가 여러개의 인터페이스를 구현하더라도 다중
	상속의 제한을 받지 않는다....
*/

package music;

public class  MusicPlayer extends MusicDevice implements JetEngine{

	
	public void stream(){
	}
	
	public void setVolume(){
	}
	
	public void playMP3(){
	}
	public void fire(){
	}
}
