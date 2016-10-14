/*
 * GUI0810 MemberApp 정답 확인 하기
 * */


package com.sds.collection;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TableTest3 extends JFrame implements ActionListener{
	JLabel id;
	JLabel name;
	JLabel pass;
	JButton insert;
	JButton	list;
	JTable table;
	JScrollPane scrol;
	JPanel pan;
	JTextField id_txt;
	JTextField name_txt;
	JTextField pass_txt;
	String[] column={"ID","이름","비번"};
	ArrayList<String[]> arr;
	
	public TableTest3() {
		id= new JLabel("I    D");
		name=new JLabel("이름");
		pass=new JLabel("비번");
		insert = new JButton("등록");
		list = new JButton("목록");
		pan= new JPanel();
		id_txt=new JTextField(15);
		name_txt=new JTextField(15);
		pass_txt=new JTextField(15);
		arr = new ArrayList<String[]>();
		table = new JTable(arr,column);
		scrol= new JScrollPane(table);
		
		pan.add(id);
		pan.add(id_txt);
		pan.add(name);
		pan.add(name_txt);
		pan.add(pass);
		pan.add(pass_txt);
		pan.add(insert);
		pan.add(list);
		
		insert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println(e);
				for(int i=0;i<arr.size();i++){
				String idinput=id_txt.getText();
				String nameinput=name_txt.getText();
				String passinput=pass_txt.getText();
				String[] arr1={idinput,nameinput,passinput};
				arr.add(arr.get(i), arr1);
				}
				
//				System.out.println(arr.get(1));
				//System.out.println(idinput);
			}
		});
		list.addActionListener(this);
		
		pan.setPreferredSize(new Dimension(210,700));
		
		add(pan,BorderLayout.WEST);
		add(scrol);
		
		
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 700);
	}



	
	public static void main(String[] args) {
		new TableTest3();
	}
}
