package use;
import com.iot.main.Bike;

class  UseBike{
	public static void main(String[] args){
		Bike bike=new Bike();
		bike.run();
		System.out.println(bike.price);
		bike.cc=1000;
	}
}
