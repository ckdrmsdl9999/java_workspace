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

		//�����ʿ� ��ư ����
		left.addActionListener(this);
		right.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(600, 650);

	}

	//���� �̹��� �����ֱ�
	public void prev(){
		//�ڹ� ���� ���â
		//JOptionPane.showMessageDialog(this, "�����̹���");
		//GalleryCanvas�� ������ count ������ 1����
		
		can.count--;
		//�ٽ� �׷����� ��û repaint()  -->update() ȭ������--> paint()�ٽ� �׸�
		can.repaint();
		txtfield.setText(can.path[can.count].substring(30 , can.path[can.count].indexOf(".")));
		
	}
	
	
	//���� �̹��� �����ֱ�
	public void next(){
		//JOptionPane.showMessageDialog(this, "�����̹���");
		
		can.count++;
		can.repaint();
		txtfield.setText(can.path[can.count].substring(30 , can.path[can.count].indexOf(".")));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();//�̺�Ʈ�� ����Ų ������Ʈ ��ȯ!!!!
		
		if(obj.equals(left)){// ���� ��ư�̶��
			prev();			
		}else if(obj==right){//���� ��ư�̶��
			next();
		}
	}
	
	public static void main(String[] args) {
		new Gallery();
	}

}
