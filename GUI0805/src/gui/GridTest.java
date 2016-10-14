/*
 * 5가지 배치 관리자중 GridLayout을 학습한다.!!!!
 * grid : 격자 (모눈종이 형태)
 *  row(가로칸수)=층수,colum(세로칸수)=호수
 * 
 * java.awt 패키지는 os 플랫폼에 따라 디자인이 제각각 이라서
 * 이를 보완한 GUI 패키지를 사용한다.
 * */


package gui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;

public class GridTest {

	public static void main(String[] args) {
		Frame frame=new Frame();
		GridLayout layout=null;
		frame.setLayout(layout=new GridLayout(10,2,5,3));
		
		//버튼 20개를 생성하여 제목을 버튼1,버튼2로 하시오..
		Button[] bt =new Button[20];
		for(int i=0;i<bt.length;i++){
			bt[i]=new Button("버튼"+i);
			frame.add(bt[i]);//frame에 버튼 부착
		}
		
		frame.setVisible(true);
		frame.setSize(400, 400);
	}

}
