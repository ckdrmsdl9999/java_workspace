/*
 * 배열은 데이터를 메모리에 순서있게 올려놓는 용도로는 아주 훌륭하다.
 * 하지만 대부분의 응용 프로그램에서 배열은 생성시 반드시 그 크기를
 * 명시해야 한다.!!
 * 때로는 이 규칙은 유연하지 못하다...
 * 
 * 자바 언어에서는 객체를 모아서 처리할때 보다 효율적으로 처리하기
 * 위한 패키지가 지원되며 이패키지를 가리켜
 * 컬렉션 프레임웍이라 한다.!!!!!
 * 
 *  컬렉션 프레임웍의 유형!
 * 1.순서가 있는 경우 : List
 *		-특징 : 배열과 거의 같다.
 *		-배열과 차이점:1)배열은 기본 자료형 까지도  지원 하지만, List를 포함한
 *							컬렉션 프레임웍의 대상은 객체만으로 한정 된다.
 *				  	     2)배열은 생성시 그 크기를 명시해야 하지만(정적),컬렉션
 *							프래임웍은 동적으로 늘어난다. 또한 생성시 그 크기를
 *							명시하지 않아도 된다.
 * 2.순서가 없는 경우 : Set
 * 3.Key-Value의 쌍으로 이루어진 모습 :Map
 * 
 * 컬렉션 프레임웍은 java.util 패키지에서 지원!!!
 * 
 * */

package com.sds.collection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CollectionTest extends JFrame{
	ArrayList<JButton> list;
	JPanel p_north,p_center;
	JButton bt_create,bt_color;
	
	public CollectionTest() {
		list= new ArrayList<JButton>();//리스트 생성!! 아직 크기가 없음.
		p_north =new JPanel();
		p_center =new JPanel();
		p_center.setBackground(Color.YELLOW);
		bt_create = new JButton("생성");
		bt_color = new JButton("색상");
		p_north.add(bt_create);
		p_north.add(bt_color);
		
		add(p_north,BorderLayout.NORTH);
		add(p_center);
		
		//생성
		bt_create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//버튼을생성
				JButton bt= new JButton("나버튼");
				
				p_center.add(bt);
				p_center.updateUI();
				
				//생성된 버튼을 List에 담자!!
				list.add(bt);
				
				System.out.println("현재까지 리스트의 크기는"+list.size());
			}
		});
	
		bt_color.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<list.size();i++){
					JButton bt=list.get(i);
					bt.setBackground(Color.YELLOW);
				}
			}
		});
		setVisible(true);
		setSize(300, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new CollectionTest();
	}

}
