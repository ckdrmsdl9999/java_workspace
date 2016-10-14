package product;
public class  BallPen extends Pen{
	

	public void write(){//오버라이딩 pen에 있는 write()를 업그레이드 함
		System.out.println("잉크를 사용하여 글씨를 씁니다.");
	}
	public void write(String color){// 오버로딩 같은 이름을 가진 메서드명을 정의하여 사용
		System.out.println("색잉크를 사용하여 글씨를 씁니다.");
	}
}
