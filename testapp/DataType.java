/*
������ �����͸� �������� �ݵ�� ������ ��Ե� �������� �뷮��
ǥ���ؾ� �Ѵ�. �� �뷮 ǥ�ø� �ڷ����̶� �Ѵ�.
Ǯ���ϱ�� ������, �����ڰ� �޸� �뷮�� ���� ���� �� �ִ�!!
�ڷ�������
1.���� = char (2byte)
2.���� = byte(1byte)< short(2byte) <int(4byte) <long (8byte)
3.���� = boolean(1byte)


*/

class DataType{
	public static void main(String[] args){
		//���� �ڷ���!!(�ɸ�����)
		char c='��';//���ڿ��� �ƴ� �ϳ��� ���ڴ� Ȭ����ǥ�� ����
		
		//2byte¥�� �����Ͱ� �� 4�� Ȯ���� = 8byte Ȯ�� ��
		char[] arr=new char[4];
		arr[0]='��';
		arr[1]='��';
		arr[2]='��';
		arr[3]='��';


		for(int i=0;i<arr.length;i++){
			System.out.println("c�� ����"+arr[i]);
		}
		
		char[] arr2={'��','��','��','ī'};

		for(int a=0;a<arr2.length;a++){
			System.out.println(arr2[a]);
		
		}
		byte a=77;
		long k=3434;

		//a+k;
		boolean m=true;
	}
}