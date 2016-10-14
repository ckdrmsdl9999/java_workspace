package com.sds.stream;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Editor extends JFrame{
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	JFileChooser chooser;
	FileReader reader; //문자가 깨지지 않기 위한 스트림객체
	BufferedReader buffr;//읽어들일때 한 줄씩 읽어들이기 위한
	//스트림
	
	public Editor() {
		bt= new JButton("파일열기");
		area = new JTextArea();
		scroll  =new JScrollPane(area);
		
		add(bt, BorderLayout.NORTH);
		add(scroll);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		
		chooser = new JFileChooser();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 500);
		setVisible(true);
	}
	
	//문서 파일의 내용을 한줄씩 읽어와  area에 출력하자!!
	//= 편집기의 파일 열기 기능!!
	public void openFile(){
		int result=chooser.showOpenDialog(this);

		if(result == JFileChooser.APPROVE_OPTION){
			File file=chooser.getSelectedFile();
			try {
				reader = new FileReader(file);
				buffr  = new BufferedReader(reader);
				
				String data=null;
				
				while((data=buffr.readLine())!=null ){
					area.append(data+"\n");
				}			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(buffr!=null){
					try {
						buffr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Editor();
	}
}










