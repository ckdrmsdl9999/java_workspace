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
		
		cho.add("선택");
		cho.add("애완동물");
		cho.add("회원정보");
		
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
		if(obj.equals("선택")){
			JOptionPane.showMessageDialog(this,	 "선택하세요");
		}else if(obj.equals("애완동물")){
			table.setModel(model=new PetModel());
			
		}else if (obj.equals("회원정보")){
			table.setModel(model=new MemberModel());
		}
		table.updateUI();
	}
	
	public static void main(String[] args) {
		new TableTest2();
	}
}
