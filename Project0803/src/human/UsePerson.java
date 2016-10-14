package human;

class UsePerson{
	public static void main(String[] args){
		//백인의 피부색을 출력하시오
		WhitePerson white =new WhitePerson();
		System.out.println("백인의 피부색은"+ white.color);
		white.openParty();
		white.walk();

		BlackPerson black=new BlackPerson();
		black.walk();

		YellowPerson yellow = new YellowPerson();
		yellow.walk();
	}
}
