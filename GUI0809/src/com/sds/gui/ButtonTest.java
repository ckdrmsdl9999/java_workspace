/*
 * com.sds.collection에 collectiontest 참고할 것!!!!
 * */


package com.sds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonTest extends JFrame{
	JButton newbt;
	JButton	colorbt;
	JPanel	pan;
	JPanel	pan1;
	JButton bt[]=new JButton[2];
	JButton bt1;
	ArrayList<JButton> list;
	
	public ButtonTest() {
		newbt= new JButton("생성");
		colorbt= new JButton("색상");
		
		
		bt[0]=newbt;
		bt[1]=colorbt;
		
		pan= new JPanel();
		pan1= new JPanel();
		pan1.setBackground(Color.YELLOW);
		
		pan.add(newbt);
		pan.add(colorbt);
		
		
		add(pan,BorderLayout.NORTH);
		add(pan1);
		

		newbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj=e.getSource();
				JButton b=(JButton)obj;
				switch(b.getText()){
				case "생성":createbt();break;
				case "색상":changecolor();break;
								
				}
				
				System.out.println("눌렀어?");
			}
		});
		
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	public void createbt(){
		bt1=new JButton("1");
		pan1.add(bt1);
		pan1.updateUI();
		
	}
	public void changecolor(){
		bt1.setBackground(Color.BLUE);
	}
	
	public static void main(String[] args) {
		new ButtonTest();
	}

}
