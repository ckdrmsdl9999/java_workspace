package com.iot.main;

class UseString2{

	public static void main(String[] args){
		/* String s=""  ������ �����ͷ� �ϴ� ������ �� ���� �Ұ�
			String �� immutable(�Һ�=���� �Ұ�) ������ ������ �ִ�.
			��Ʈ����ü�� �Һ��� Ư¡���� ���Ͽ�, �ڹ��� String����
			�����̶�� ������ �������� �ʴ´�.
			�ذ�å?? 
			���� ������ ��ü�� �̿��ϴ� ��!!!!
		*/
		String s1=new String("apple");//����� ������� ���� heap ������ ��.
		String s2 = "apple"; //������,�Ͻ��� ������� ���� ���Ǵ� heap ������ ���Ǯ(Constant Pool)�� �ö󰡰� �ȴ�.
		String s3="apple"; // ���Ǯ�� �ߺ��� ������ �ִ��� Ȯ�� �ϰ� ������ ������ ���� apple�� ����Ų��.
		String s4=new String("apple");

		//String �� ��ü �̹Ƿ� s1~~s4�� ���۷��� ������
		//���� �Ʒ��� �ڵ�� ����񱳰� �ƴ϶� �ּ� ���̴�.
		System.out.println(s2==s3);// true�� ������ ������ ���Ǯ�̶�� ������ �����Ǳ� ����
												//���Ǯ�� Ư¡ - �ߺ��� ����� �ִٸ�, �� �������� �ʴ´�.
		System.out.println(s1==s2);
		System.out.println(s1==s4);

		//����)String Ŭ���� �ƴ�
		//���� ������ ��ü
		StringBuffer b=new StringBuffer();
		b.append("korea");
		b.append(" fighting");// ���Ӱ� �����Ǵ� ���� �ƴ϶� ���� b�� �����
		
		//String�� �ƴϱ⶧���� toString()�޼���� ��ȯ ��Ų�� 
		//�������!!!!!!!!!!!!!!]
		System.out.println(b.toString());
	}
}
