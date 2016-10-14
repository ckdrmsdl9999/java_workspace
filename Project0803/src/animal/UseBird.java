package animal;
class UseBird{
	public static void main(String[] args){
		Duck d= new Duck();
		
		//현실의 개념과 마찬가지로, 자바에서도 상위객체로
		//하위 객체를 가리키는 것이 가능하다.
		Bird bird = new Duck();
		bird.wing();
		//bird.swim(); //- 실행 안됨
		Bird b = new Sparrow();
		System.out.println(b.name);

		b.wing();
	}
}
