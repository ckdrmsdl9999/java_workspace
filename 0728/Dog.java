class Dog{
	
	String name="�ǹ�";

	/*�ν��Ͻ� �ʱ�ȭ ��!!!
		- �� Ŭ������ ���� �����Ǵ� �ν��Ͻ��� ������ ����
		�� ������ ���� ��!!!
	*/
	static{// Ŭ������ ���ʿ� �ε�Ǿ����� ����!!
		System.out.println("static �ʱ�ȭ �� ����!");
	}
	{// Ŭ������ ���� �ν��Ͻ��� �����ɶ� ���� ����!!!
		System.out.println("�ν��Ͻ� �ʱ�ȭ �� ����!");
	}	
	
	public static void main(String[] args){
		
		for(int i=0;i<=3;i++){
			new Dog();// �̸��� ������ ���� ���ο��� ���� �Ұ���
		}

		Dog d=new Dog();

	}
}
