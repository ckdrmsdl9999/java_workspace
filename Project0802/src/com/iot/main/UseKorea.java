package com.iot.main;

class UseKorea{
	public static void main(String[] args){
		String str="korea java";  
		char ab= str.charAt(3);
		System.out.println(ab);//���ڿ����� 4��° ��ġ�� 'e' ���ڸ� �����Ͽ� ����ϼ���. 
		System.out.println(str.indexOf('a'));// ���� �� ù��° a�� index (����)�� ����ϼ���.
		System.out.println("��Ʈ��"+str.lastIndexOf('a'));//���� �� ������ a�� index �� ����ϼ���
		System.out.println(str.codePointCount(0, str.length()));//���ڿ��� �� ���̸� ����ϼ���.
		//System.out.println(str.replace("korea", "�ڸ���"));//) korea �� "�ڸ���"�� ��ü�Ͽ� "�ڸ��� java"�� ����ϼ���
		
		System.out.println(str.substring(3 , 8));
		System.out.println(str.replaceAll("korea java","KOREA JAVA"));
	}
}
