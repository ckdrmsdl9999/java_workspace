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
	

	
	//생성자도 메서드 이므로 아래의 코드는 충분히 가능하다.
	//이 생성자를 호출하는 자는 ChatA의 인스턴스를 넘겨야 한다.
	public ChatB(ChatA chata) {
		this.chata=chata;
		area = new JTextArea();
		p = new JPanel();
		txt = new JTextField(15);

		// 스크롤을 적용하고자 하는 컴포넌트를 인수로 넘긴다.!
		scroll = new JScrollPane(area);

		// 센터에 area 부착
		add(scroll);
		txt.addKeyListener(this);
		// 패널에 txt와 bt부착후 남쪽에 패널을 부착!!
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
			//나와 chatA dml area dp cnffur!!
			String msg=txt.getText();
			area.append(msg+"\n");//chatB의 area
			chata.area.append(msg+"\n");//chatA의 area
			chata.chatc.area.append(msg+"\n");
			txt.setText("");
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
		
}
