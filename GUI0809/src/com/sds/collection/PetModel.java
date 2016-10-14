/*
 * JTable에 보여질 데이터중 , 애견 관련
 * 데이터를 처리하는 TableModel
 * */

package com.sds.collection;

import javax.swing.table.AbstractTableModel;

public class PetModel extends AbstractTableModel{
	String[] column={"이름","종류","성별"};
	String[][] data={
			{"나나","말티즈","남"},
			{"나비","진돗개","여"},
			{"바나나","진돗개","남"},
			
			
	};
	
	public String getColumnName(int col) {
		return column[col];
	}
	
	//아래의 모든 메서드는 개발자를 위한게 아닌 JTable이 호출하는 메서드이다.!!!!
	public int getColumnCount() {
		return column.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	
	
}
