/*
  자바의 GUI 관련 API 들은 java.awt 패키지에서 지원한다.
  특히 윈도우와 같이 다른 컴포넌트들을 포함할 수 있는
  객체는 배치의 문제를 고민해야 하면, 이 배치의 종류엔 5가지가
  있다.
  그 중 BorderLayout을 학습한다.
  **/

package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;

public class BorderTest {

	public static void main(String[] args) {
		Frame frame=new Frame();
		Button bt_north=new Button("북쪽");
		Button bt_west=new Button("서쪽");
		Button bt_center=new Button("센터");
		Button bt_east=new Button("동쪽");
		Button bt_south=new Button("남쪽");
		
		
		//BorderLayout을 적용하자!!
		BorderLayout layout=new BorderLayout();
		
		//frame에 레이아웃을 적용하자!!
		//개발자가 레이아웃을 지정해주지 않으면 해당 객체의 디폴트
		//배치관리자가 적용되고 Frame의 경우 디폴트 배치관리자는
		//BorderLayout 이다. 또한 BorderLayout의 경우
		//개발자가 방위를 지정하지 않으면 Center이다.!!
		//frame.setLayout(layout);
		bt_east.setPreferredSize(new Dimension(200,800));//적용된 layout size 변경
		
		frame.add(bt_north, BorderLayout.NORTH);
		frame.add(bt_east, BorderLayout.EAST);
		frame.add(bt_center, BorderLayout.CENTER);
		frame.add(bt_west, BorderLayout.WEST);
		frame.add(bt_south, layout.SOUTH);
		
		
		frame.setVisible(true);
		frame.setSize(500, 400);
		
		
	}

}
