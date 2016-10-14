package com.sds.gallery;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gallery extends JFrame implements ActionListener{
	GalleryCanvas can;
	JButton left;
	JButton right;
	JTextField txtfield;
	JPanel pan;

	public Gallery() {
		
		can= new GalleryCanvas();
		pan = new JPanel();
		left = new JButton("<");
		right = new JButton(">");
		txtfield = new JTextField(15);
		
		
		pan.add(left);
		pan.add(txtfield);
		pan.add(right);

		add(can);
		add(pan, BorderLayout.SOUTH);

		//리스너와 버튼 연결
		left.addActionListener(this);
		right.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(600, 650);

	}

	//이전 이미지 보여주기
	public void prev(){
		//자바 버전 경고창
		//JOptionPane.showMessageDialog(this, "이전이미지");
		//GalleryCanvas가 보유한 count 변수를 1증가
		
		can.count--;
		//다시 그려지기 요청 repaint()  -->update() 화면지움--> paint()다시 그림
		can.repaint();
		txtfield.setText(can.path[can.count].substring(30 , can.path[can.count].indexOf(".")));
		
	}
	
	
	//다음 이미지 보여주기
	public void next(){
		//JOptionPane.showMessageDialog(this, "다음이미지");
		
		can.count++;
		can.repaint();
		txtfield.setText(can.path[can.count].substring(30 , can.path[can.count].indexOf(".")));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();//이벤트를 일으킨 컴포넌트 반환!!!!
		
		if(obj.equals(left)){// 이전 버튼이라면
			prev();			
		}else if(obj==right){//다음 버튼이라면
			next();
		}
	}
	
	public static void main(String[] args) {
		new Gallery();
	}

}
