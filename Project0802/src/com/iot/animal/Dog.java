package com.iot.animal;

public class  Dog{
	String name="��Ƽ��";
	int age;
	String gender="����";
	
	/**
		��ü �ν��Ͻ� ������ �� �޼���� ������ ȣ��ǹǷ�
		�ʱ�ȭ �������� �������!!
	*/
	public Dog(int age,String gender){
		this.age=age;
		this.gender=gender;
	}
	
	public void bark(){
		System.out.print("�۸�");
	}

public static void main(String[] args){
		Dog d = new Dog(10, "�۸���"); 
		System.out.println(d.age);
	}
}
