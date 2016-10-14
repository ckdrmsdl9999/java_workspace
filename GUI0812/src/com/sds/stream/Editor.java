package com.sds.stream;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Editor extends JFrame implements ActionListener{
	JPanel p_north;
	JPanel p_west;
	JPanel p_center;
	JTextArea txtarea;
	JScrollPane scroll;
	JLabel l_no;
	JMenuBar menubar;
	JMenu file,edit,serch;
	JMenuItem newfile,open,close,save,newsave,close1;
	JFileChooser chooser;
	FileCopy1 file_copy;
	String a;
	String b;
	FileReader reader;
	BufferedReader buffr;
	
	public Editor() {
		p_north=new JPanel();
		p_west= new JPanel();
		p_center=new JPanel();
		txtarea= new JTextArea();
		scroll= new JScrollPane(p_center);
		l_no=new JLabel();
		menubar=new JMenuBar();
		
		file=new JMenu("파일");
		edit=new JMenu("편집");
		serch=new JMenu("검색");
		
		newfile = new JMenuItem("새파일");
		open = new JMenuItem("열기");
		close = new JMenuItem("닫기");
		save = new JMenuItem("저장");
		newsave = new JMenuItem("새이름으로 저장");
		close1 = new JMenuItem("종료");
		
		chooser=new JFileChooser();
		
		
		
		//p_north.setBackground(Color.YELLOW);
		p_north.setPreferredSize(new Dimension(800, 30));
		
		p_west.setBackground(Color.CYAN);
		p_west.setPreferredSize(new Dimension(30, 700));
		
		p_center.setLayout(new BorderLayout());
		p_center.add(p_west,BorderLayout.WEST);
		p_center.add(txtarea);
		
		menubar.setBackground(Color.YELLOW);
		menubar.setPreferredSize(new Dimension(750, 30));
		
		open.addActionListener(this);
		save.addActionListener(this);
		close1.addActionListener(this);
		close.addActionListener(this);
		newsave.addActionListener(this);

		
		file.add(newfile);
		file.add(open);
		file.add(close);
		file.addSeparator();
		file.add(save);
		file.add(newsave);
		file.addSeparator();
		file.add(close1);
		
		
		menubar.add(file);
		menubar.add(edit);
		menubar.add(serch);
	
		
		p_north.add(menubar);
		
		add(p_north,BorderLayout.NORTH);
		add(scroll);
		
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,700);
		setVisible(true);
	}
	
	public void openfile(){
		if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
			File file=chooser.getSelectedFile();
			//파일 오픈후에 txtarea창에 뿌리자
			a=file.getPath();
			
		//	System.out.println(a);
			try {
				reader = new FileReader(a);
				buffr  = new BufferedReader(reader);
				
				String data=null;
				StringBuffer sb = new StringBuffer();
				
				while((data=buffr.readLine()) !=null){
					sb.append(data); //sb 에 누적!!
					String a=sb.toString();
					txtarea.setText(a);;
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public void closefile(){
		txtarea.setText(" ");
		
	}
	
	public void savefile(){
			//파일 저장하자
		
		file_copy=new FileCopy1(a,a);
	}
	public void saveas(){
		if(chooser.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
			//파일 저장하자
			File file=chooser.getSelectedFile();
			
			b=file.getPath();
			file_copy=new FileCopy1(a,b);
			}
		
	}

	public void closefile1(){
		this.dispose();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		//System.out.println(obj);
		if(obj.equals(open)){
			openfile();
			
		}else if(obj.equals(save)){
			savefile();
			System.out.println(obj);
		}else if(obj.equals(close1)){
			closefile1();
		}else if(obj.equals(close)){
			closefile();
		}else if(obj.equals(newsave)){
			saveas();
			
		};
	}
	
	
	public static void main(String[] args) {
		new Editor();
	}


}
