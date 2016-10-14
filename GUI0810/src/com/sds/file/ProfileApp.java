package com.sds.file;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfileApp extends JFrame implements ActionListener{
	Canvas can;
	JPanel p_center;
	JButton bt;
	JLabel la_path;
	Toolkit kit;
	Image img;
	JFileChooser chooser;
	
	public ProfileApp() {
		kit= Toolkit.getDefaultToolkit();
		img=kit.getImage("C:/java_workspace/GUI0810/res/profile.png");
		
		can=new Canvas(){
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, 300, 280, this);
			}			
		};
		can.setPreferredSize(new Dimension(300,280));
		
		p_center = new JPanel();
		p_center.setBackground(Color.WHITE);
		bt = new JButton("파일탐색");
		la_path = new JLabel();
		la_path.setPreferredSize(new Dimension(250, 40));
		
		p_center.add(bt);
		p_center.add(la_path);
		add(can, BorderLayout.WEST);
		add(p_center);
		
		//파일추저 생성
		chooser =new JFileChooser();
		
		//버튼과 리스너 연결
		bt.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,300);
		setVisible(true);
	}
	
	//스윙에서도 파일 탐색기가 지원된다.
	//모든 탐색기에는 JFileChooser를 사용한다.
	public void openFile(){
		int result=chooser.showOpenDialog(this);
		//System.out.println("당신의 선택은"+result);
		if(result==JFileChooser.APPROVE_OPTION){
			//지금 선택한 이미지를 프로필 사진에 반영하자!!!
			File file=chooser.getSelectedFile();
			//System.out.println(file);
			String path=file.getAbsolutePath();//방금 선택한 파일의 물리적 경로 반환
			System.out.println(path);
			img=kit.getImage(path);
			la_path.setText(path);
			can.repaint();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		openFile();
	}

	public static void main(String[] args) {
		new ProfileApp();
	}
}
