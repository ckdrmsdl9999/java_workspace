/*
1.Cat�̶�� �̸��� ����� Ŭ������ �����ϵ�
	 �̸� ,���� , ������ ����� �����Ͻÿ�..
2.Ư�� ������ ��쿣 static���� �����Ͻÿ�!!

3.���� Ŭ������ main����θ� �ε�, ������� �̸�,����
	������ ����Ͻÿ�.
*/

class  Cat{
	int age=8;
	String name="����";
	static String type="����̰�";

	public static void main(String[] args){
		Cat c= new Cat();
		
		System.out.println(c.age);
		System.out.println(c.name);
		System.out.println(c.type);

	}
}
