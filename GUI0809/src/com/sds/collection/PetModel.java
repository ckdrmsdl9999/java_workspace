/*
 * JTable�� ������ �������� , �ְ� ����
 * �����͸� ó���ϴ� TableModel
 * */

package com.sds.collection;

import javax.swing.table.AbstractTableModel;

public class PetModel extends AbstractTableModel{
	String[] column={"�̸�","����","����"};
	String[][] data={
			{"����","��Ƽ��","��"},
			{"����","������","��"},
			{"�ٳ���","������","��"},
			
			
	};
	
	public String getColumnName(int col) {
		return column[col];
	}
	
	//�Ʒ��� ��� �޼���� �����ڸ� ���Ѱ� �ƴ� JTable�� ȣ���ϴ� �޼����̴�.!!!!
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
