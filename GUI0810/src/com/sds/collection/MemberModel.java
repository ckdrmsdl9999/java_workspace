/*
 * JTable은 껍데기에 불과하기 때문에 실제 데이터를 연동한 객체인
 * TableModel을 정의하여 , 회원 관리 프로그램에 출력할 JTable에 
 * 보여질 데이터를 처리해보자!!
 * */

package com.sds.collection;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MemberModel extends AbstractTableModel{
	String[] column={"ID","Password","Name"};
	//String[][] data=new String[0][0];//크기가 결정되어 지지 않았기 때문에 0으로 주기
	//배열은 그 크기가 고정되어 있으므로, 컬렉션 프레임웍을 이용하여 기능을 개선하자!!!
	ArrayList<String[]> list=new ArrayList<String[]>();
	
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return column.length;
	}
	@Override
	public String getColumnName(int col) {
	
		return column[col];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		String[] record=list.get(row);
		return record[col];
	}
	@Override
	public boolean isCellEditable(int row, int col) {
		//System.out.println(row+","+col+"편집할까요?");
		if(col==1){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public void setValueAt(Object val, int row, int col) {
		System.out.println(val+","+row+","+col+"변경할까요?");
		String[]record=list.get(row);
		record[col]=(String)val;
		
	}
}
