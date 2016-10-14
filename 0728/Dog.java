class Dog{
	
	String name="뽀미";

	/*인스턴스 초기화 블럭!!!
		- 이 클래스로 부터 생성되는 인스턴스가 있을때 마다
		이 영역이 수행 됨!!!
	*/
	static{// 클래스가 최초에 로드되어질때 수행!!
		System.out.println("static 초기화 블럭 수행!");
	}
	{// 클래스로 부터 인스턴스가 생성될때 마다 수행!!!
		System.out.println("인스턴스 초기화 블럭 수행!");
	}	
	
	public static void main(String[] args){
		
		for(int i=0;i<=3;i++){
			new Dog();// 이름이 없으면 이후 라인에서 제어 불가능
		}

		Dog d=new Dog();

	}
}
