/*
	main() �޼����� �μ��� args�� ����� ����.
	main�� �����ڰ� ���Ҷ� ������� ȣ���� �� �ִ� �޼��尡 �ƴϴ�.
	���ø����̼��� ���۵ɶ� ���� �ѹ� ȣ��Ǵ� �޼����̴�.
	java.exe���Ͽ� ���� �ڵ� ȣ��...
*/

package main;
class MainTest{
	public static void main(String[] args){
		
	//	System.out.println("���α׷� ����� �Ѱܹ��� �μ��� ������"+ args.length);
		for(int i=0;i<args.length;i++){
			System.out.println(args[i]);
		}
	}
}
