/*
 * JTable�� �����⿡ �Ұ��ϱ� ������ ���� �����͸� ������ ��ü��
 * TableModel�� �����Ͽ� , ȸ�� ���� ���α׷��� ����� JTable�� 
 * ������ �����͸� ó���غ���!!
 * */

package com.sds.collection;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MemberModel extends AbstractTableModel{
	String[] column={"ID","Password","Name"};
	//String[][] data=new String[0][0];//ũ�Ⱑ �����Ǿ� ���� �ʾұ� ������ 0���� �ֱ�
	//�迭�� �� ũ�Ⱑ �����Ǿ� �����Ƿ�, �÷��� �����ӿ��� �̿��Ͽ� ����� ��������!!!
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
		//System.out.println(row+","+col+"�����ұ��?");
		if(col==1){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public void setValueAt(Object val, int row, int col) {
		System.out.println(val+","+row+","+col+"�����ұ��?");
		String[]record=list.get(row);
		record[col]=(String)val;
		
	}
}
