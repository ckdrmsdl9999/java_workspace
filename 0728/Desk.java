public class Desk {
	int x; 
	static int y;
	
	{ //A
		for(int i=0;i<10;i++){
			x++;
		}
	}
	
	
	static { //B
		for(int i=0;i<20;i++){
			y++;
		}
	}
	
	public static void main(String[] args){
		//System.out.println(x); //C
		int a=new Desk().x; //D
		System.out.println(a); //E
		System.out.println(y); //F
		
	}
}
