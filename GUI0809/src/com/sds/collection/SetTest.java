/*
 * 순서없이 모여있는 객체를 표현하는
 * Set에 대해 학습한다.!!!!!!
 * 
 * Set의 하위 객체 중 HashSet을 학습하자.
 * */

package com.sds.collection;

import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;

public class SetTest {

	public static void main(String[] args) {
		//Generic Type : 컬렉션 프레임웍에 채워질 데이터형을 결정 지을 수 있다. <>안에 표기
		HashSet<JButton> set =new HashSet<JButton>();
		
		//auto boxing 
		int x=3;
		Integer i = new Integer(3);
		
		//set.add(x);// 원래 에러가 나야 한다.!!
/*						근데 안나는 이유는?컬렉션 프레임웍의 메서드의 인수로 int를 포함한 기본
						자료형을 명시하면 자동으로 Wrapper객체로 변환되어진다.. 왜? 개발
						자의 의도를 파악하여 번거롭지 않게 봐준다.
*/						
		//set.add("사과");//String 객체가 들어감
		set.add(new JButton("나 버튼"));
		set.add(new JButton("너 버튼"));
		set.add(new JButton("요 버튼"));
		
		//모든 객체의 배경색을 바꾸시오!
		
		Iterator it=set.iterator();
		
		while(it.hasNext()){
			JButton bt=(JButton)it.next();
			System.out.println(bt.getText());
		}
	}

}
