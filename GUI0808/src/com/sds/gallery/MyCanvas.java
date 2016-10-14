/*
 * 비주얼 컴포넌트들은 대부분 sun에서 적절히 디자인해 놓은 것이기
 * 때문에 개발자가 주도하여, 그래픽 처리를 할 필요가 없다.
 * 하지만, 컴포넌트중 개발자에게 그래픽 처리를 주도하여 개발할 수 
 * 있도록 지원하는 컴퍼넌트가 있다...
 * 속이 텅 빈 유형 =====> Canvas,JPanel,JFrame 등등
 *								 즉 켄버스와 컨테이너류는 모두 가능!!!!!!! 
 *
 * 	java.awt에서 Image 클래스가 일반이 아닌 추상클래스이다.
 * 따라서 new 할 수 없다.
 *해결책) 원칙상 추상 클래스는 자식에 의해 완성되므로, Image를
 *			상속받아 메서드를 재정의한 후 자식 클래스의 인스턴스를
 *			올릴때, 부모도 올라오지만 주로 sun에서 제공하는 객체가
 *			추상일 경우 반드시 이 원칙대로 개발할 필요는 없게 
 *			해 놓은 경우가 많다.
 *			주로 ,이미 추상클래스의 인스턴스를 간접적으로 편하게 얻을 
 *			수 있는 방법을 제공해준다.
 *			awt에서는 Toolkit을 이용하여 이미지를 얻게 해준다.
 * */

package com.sds.gallery;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class MyCanvas extends Canvas{
	Toolkit kit=Toolkit.getDefaultToolkit();
	
	//위에서 얻은 Toolkit을 이용하여 이미지를 얻어올 수 있음!!!
	Image img;
	
	public MyCanvas() {
		setBackground(Color.YELLOW);
		//img=kit.getImage(http://icon.daumcdn.net/w/icon/1606/30/105915014.png);
		img=kit.getImage("C:/java_workspace/GUI0808/res/zoro.jpg");
		
	}
	@Override
	public void paint(Graphics g) {
		g.drawRect(100, 50, 200, 200);
		g.drawString("난 그림 스트링", 50, 50);
		g.drawOval(200, 200, 300, 500);
		g.drawImage(img, 0, 100, this);
	}
}
