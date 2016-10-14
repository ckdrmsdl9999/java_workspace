class  Computer{
	int memory=256;

	public void turnOn(){
		 System.out.println("컴퓨터를 켭니다.");
	}
	public static void main(String[] args){
		int k=5;
		Computer c=new Computer();
		System.out.println(c.memory);
		System.out.println("k의 값은" +k);
		//c는 k와는 틀리게 데이터를 직접 담지 않고,
		//객체 데이터의 메모리상의 위치 즉 주소값만을
		//보유하고 있다고 하여, reference (참조) 변수라고 한다!!
		System.out.println("c의 값은"+c);

	}
}
