package com.sds.collection;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TableTest2 extends JFrame implements ItemListener{
	Choice cho;
	JTable table;
	JScrollPane scrol;
	TableModel model;
	JPanel pan;
	
	
	
	public TableTest2() {
		pan =new JPanel();
		cho =new Choice();
		
		cho.add("����");
		cho.add("�ֿϵ���");
		cho.add("ȸ������");
		
		cho.addItemListener(this);
		pan.add(cho);
		
		table = new JTable();
		scrol= new JScrollPane(table);
		
		add(pan,BorderLayout.WEST);
		add(scrol);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 700);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj=e.getItem();
		System.out.println(obj);
		if(obj.equals("����")){
			JOptionPane.showMessageDialog(this,	 "�����ϼ���");
		}else if(obj.equals("�ֿϵ���")){
			table.setModel(model=new PetModel());
			
		}else if (obj.equals("ȸ������")){
			table.setModel(model=new MemberModel());
		}
		table.updateUI();
	}
	
	public static void main(String[] args) {
		new TableTest2();
	}
}
