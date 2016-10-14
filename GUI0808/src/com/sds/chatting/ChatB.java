package com.sds.chatting;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatB extends JFrame implements KeyListener{
	JTextArea area;
	JPanel p;
	JTextField txt;
	JScrollPane scroll;
	ChatA chata;
	

	
	//�����ڵ� �޼��� �̹Ƿ� �Ʒ��� �ڵ�� ����� �����ϴ�.
	//�� �����ڸ� ȣ���ϴ� �ڴ� ChatA�� �ν��Ͻ��� �Ѱܾ� �Ѵ�.
	public ChatB(ChatA chata) {
		this.chata=chata;
		area = new JTextArea();
		p = new JPanel();
		txt = new JTextField(15);

		// ��ũ���� �����ϰ��� �ϴ� ������Ʈ�� �μ��� �ѱ��.!
		scroll = new JScrollPane(area);

		// ���Ϳ� area ����
		add(scroll);
		txt.addKeyListener(this);
		// �гο� txt�� bt������ ���ʿ� �г��� ����!!
		p.add(txt);
		add(p, BorderLayout.SOUTH);
		setBounds(400, 200, 300, 400);
		setSize(300, 400);
		setVisible(true);
		
	
	}
	


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			//���� chatA dml area dp cnffur!!
			String msg=txt.getText();
			area.append(msg+"\n");//chatB�� area
			chata.area.append(msg+"\n");//chatA�� area
			chata.chatc.area.append(msg+"\n");
			txt.setText("");
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
		
}
