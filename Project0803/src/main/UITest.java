/*
자바 언어로 GUI(Graphic User Interface)
사용자가 그림으로 어플리케이션을 조작할 수 있는 방법

*/

package main;
import java.awt.Frame;

class  UITest{
	public static void main(String[] args){
		//윈도우 생성하기
		Frame frame=new Frame();
		//기본적으로 않보이게 속성이 설정됨.
		frame.setVisible(true);
	}
}
