package music;
class  FinalTest{
	static int a=3;
	
	int c=5;

	public static void main(String[] args){
	 final int b=5;// 이후의 문장에서는 더이상 변수의 값을 
	 //수정 할 수 없다.
	 b=10;
	}
}
