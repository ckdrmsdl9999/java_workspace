package com.sds.file;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CopyTest extends JFrame implements ActionListener{
	JButton open_bt;
	JButton	save_bt;
	JButton	play;
	JTextField open_txt;
	JTextField save_txt;
	JLabel open_label;
	JLabel save_label;
	JPanel west_pan;
	JPanel center_pan;
	JFileChooser chooser;
	FileCopy2 file_copy;
	
	
	public CopyTest() {
		open_bt=new JButton("열기");
		save_bt= new JButton("Save");
		play=new JButton("실행");
		open_txt = new JTextField(50);
		save_txt = new JTextField(50);
		open_label=new JLabel("원        본");
		save_label=new JLabel("저장경로");
		west_pan=new JPanel();
		center_pan=new JPanel();
		chooser=new JFileChooser();
		
		
		
		//west_pan.add(open_label);
		//west_pan.add(save_label);
		
		
		open_bt.addActionListener(this);
		save_bt.addActionListener(this);
		play.addActionListener(this);
		
		center_pan.add(open_label);
		center_pan.add(open_txt);
		center_pan.add(open_bt);
		center_pan.add(save_label);
		center_pan.add(save_txt);
		center_pan.add(save_bt);
		center_pan.add(play);
		//add(west_pan,BorderLayout.WEST);
		add(center_pan);
		
		
		setSize(700, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void openFile(){
		int result=chooser.showOpenDialog(this);
		if(result==JFileChooser.APPROVE_OPTION){
			File file=chooser.getSelectedFile();
			String path=file.getAbsolutePath();
			open_txt.setText(path);
		}
	}
	
	public void saveFile(){
		int result=chooser.showSaveDialog(this);
		if(result==JFileChooser.APPROVE_OPTION){
			File file=chooser.getSelectedFile();
			String path=file.getAbsolutePath();
			save_txt.setText(path);
		}
	}
	
	public void copy(){
		file_copy = new FileCopy2(open_txt.getText(),save_txt.getText());
		
		//System.out.println("name 은"+file_copy.name);
		if(open_txt.getText().equals("")){
			JOptionPane.showMessageDialog(this, "원본 파일을 선택해 주세요.");
		}else if(save_txt.getText().equals("")){
			JOptionPane.showMessageDialog(this, "카피 파일명을 선택해 주세요.");
		}else{
			JOptionPane.showMessageDialog(this, "카피성공 했습니다.");
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		//System.out.println(obj);
		if(obj.equals(open_bt)){
			openFile();
		}else if(obj.equals(save_bt)){
			saveFile();
		}else if(obj.equals(play)){
			copy();
		}
		
	}
	public static void main(String[] args) {
		new CopyTest();
	}

}
