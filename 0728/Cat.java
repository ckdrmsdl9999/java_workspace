/*
1.Cat이라는 이름의 고양이 클래스를 정의하되
	 이름 ,나이 , 종류를 멤버로 보유하시오..
2.특히 종류의 경우엔 static으로 선언하시오!!

3.현재 클래스에 main실행부를 두되, 고양이의 이름,나이
	종류를 출력하시오.
*/

class  Cat{
	int age=8;
	String name="나비";
	static String type="고양이과";

	public static void main(String[] args){
		Cat c= new Cat();
		
		System.out.println(c.age);
		System.out.println(c.name);
		System.out.println(c.type);

	}
}
