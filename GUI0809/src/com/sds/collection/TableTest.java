/*
 * swing에서 데이터를 출력 및 관리하기 위한 용도로 지원되는
 * 컴포넌트는 JTable 이다.!!
 * 
 * JTable 은 유지 보수성을 높이기 위해 , 디자인과 모델(로직 및 데이터)
 * 을 분리시킨 모델을 추가한다.
 * 따라서 JTable은 단지 데이터를 보여주는 창(Window)역활만 하고
 * 실제 보여질 데이터를 결정하는 객체는 TableModel이다.
 * 
 * 왜 나눌까?????
 * 유지 보수 성이 좋다!
 * 상황에 따라 데이터를 바꾸기 용이함
 * */

package com.sds.collection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TableTest extends JFrame {
	JTable table;
	JScrollPane scroll;
	TableModel model;
	
	
	
	public TableTest() {
		table= new JTable(model=new PetModel());
		scroll=new JScrollPane(table);
		
		//add(table);
		add(scroll);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TableTest();
	}

}
