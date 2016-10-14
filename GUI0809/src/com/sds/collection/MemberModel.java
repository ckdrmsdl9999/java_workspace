/*
 * JTable에 보여질 데이터중, 회원 관련
 * 데이터를 처리할 TableModel
 * 
 * 주의 ) TableModel이 보유한 모든 메서드는 개발자가
 * 호출하는 것이 아니라, JTable이 화면에 보여질때 스스로
 * 호출한다!! 즉 시스템이 호출한다!!
 * */

package com.sds.collection;

import javax.swing.table.AbstractTableModel;

public class MemberModel extends AbstractTableModel{
	String[] column={"번호","이름","비밀번호","이메일"};
	String[][] data={
			{"1","김길동","0000","kim@naver.com"},
			{"2","홍길동","1111","hong@naver.com"},
			{"3","이길동","2222","lee@naver.com"}
			
	};
	
	
	@Override
	public String getColumnName(int col) {
		System.out.println(col+"번째 제목은"+column[col]);
		return column[col];
	}
	//총 컬럼의 수
	public int getColumnCount() {
		return column.length;
	}

	//총 레코드 수
	public int getRowCount() {
		return data.length;
	}

	//각 항목의 보여질 데이터
	public Object getValueAt(int row, int col) {
		//System.out.println(row+","+col+"호출!!");
		return data[row][col];
	}
	
}
