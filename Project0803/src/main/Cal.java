/*
	java.exe����� ������ ������ �� �����͸� �Ѱܼ�
	�μ��� ���� ���ϴ� ���α׷�...

	�Ϲ������� �ڹ� ���� �⺻ �ڷ���(����,����,����)����
	�� ��ȯ�� ��ü���� ����ȯ�� �����ϰ� �ִ�.
	������ �⺻�ڷ����� ��ü �ڷ������� ����ȯ�� �����ұ�????
	�����ϳ�....�� �⺻ �ڷ������� ��ȯ �� �� �ִ� ��� �����ϴ�.
	��) "true"--> true
		 "2"-->2
		 "Dog"-->2(Dog�� �⺻�ڷ������� ������ ���ɼ��� ����. �Ұ�)
		 "3"  --> '3' (String-->char )
	�ڹ� ������ ��� �⺻�ڷ��� ���� 1:1�� �����ϴ� Wrapper Ŭ������ ���� ��ü
	�ڷ����� �⺻�ڷ������� ����ȯ�� �����ϰ� �ִ�.

	���� 
	byte	:Byte
	short :Short
	int		:Integer 
	long	:Long
	
	�Ǽ� 
	float	:Float
	double:Double
	
	����
	boolean : Boolean
*/
package main;
class Cal{
	public static void main(String[] args){
		String n1=args[0];//"1"-->1
		String n2=args[1];

		int a= Integer.parseInt(n1);//���ڿ��� ����ȭ ��Ŵ
		int b= Integer.parseInt(n2);
		System.out.println("�μ��� ����"+(a+b));
	
	}
}
