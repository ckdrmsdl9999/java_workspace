package human;

class UsePerson{
	public static void main(String[] args){
		//������ �Ǻλ��� ����Ͻÿ�
		WhitePerson white =new WhitePerson();
		System.out.println("������ �Ǻλ���"+ white.color);
		white.openParty();
		white.walk();

		BlackPerson black=new BlackPerson();
		black.walk();

		YellowPerson yellow = new YellowPerson();
		yellow.walk();
	}
}
