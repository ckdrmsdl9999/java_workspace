/*
변수에 데이터를 담을때는 반드시 변수가 담게될 데이터의 용량을
표시해야 한다. 이 용량 표시를 자료형이라 한다.
풀편하기는 하지만, 개발자가 메모리 용량을 결정 지을 수 있다!!
자료형에는
1.문자 = char (2byte)
2.숫자 = byte(1byte)< short(2byte) <int(4byte) <long (8byte)
3.논리값 = boolean(1byte)


*/

class DataType{
	public static void main(String[] args){
		//문자 자료형!!(케릭터형)
		char c='대';//문자열이 아닌 하나의 문자는 홑따옴표를 쓴다
		
		//2byte짜리 데이터가 총 4개 확보됨 = 8byte 확보 됨
		char[] arr=new char[4];
		arr[0]='대';
		arr[1]='한';
		arr[2]='민';
		arr[3]='국';


		for(int i=0;i<arr.length;i++){
			System.out.println("c의 값은"+arr[i]);
		}
		
		char[] arr2={'아','메','리','카'};

		for(int a=0;a<arr2.length;a++){
			System.out.println(arr2[a]);
		
		}
		byte a=77;
		long k=3434;

		//a+k;
		boolean m=true;
	}
}