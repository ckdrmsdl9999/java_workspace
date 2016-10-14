/*클래스명 : Flower
클래스의 속성 : 색상,이름,가시유무
클래스의 메서드: 피는 행위
메모리에 올려서 해당 꽃의 속성 및 ,메서드를 
UseFlower에서 호출하여 출력 하라*/
class Flower{
	String color="red";
	String name="Rose";
	boolean thorn=true;

	public void bloom(){
		System.out.println("꽃이 피었습니다.");
	}

}