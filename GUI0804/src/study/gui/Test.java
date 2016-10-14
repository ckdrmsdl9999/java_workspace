package study.gui;

import java.awt.Frame;

public class Test {
	private String name;
	
/*이 클래스를 공부중*/

	public Test() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		
		
		Frame f=new Frame("나 제목");
		
		f.setVisible(true);
		f.setSize(700, 700);
	}

}
