package animal;
class UseBird{
	public static void main(String[] args){
		Duck d= new Duck();
		
		//������ ����� ����������, �ڹٿ����� ������ü��
		//���� ��ü�� ����Ű�� ���� �����ϴ�.
		Bird bird = new Duck();
		bird.wing();
		//bird.swim(); //- ���� �ȵ�
		Bird b = new Sparrow();
		System.out.println(b.name);

		b.wing();
	}
}
