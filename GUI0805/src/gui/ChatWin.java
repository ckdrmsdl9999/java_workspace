/*
  java.awt는 os 플랫폼마다(Linux,Mac,Window...) 디자인이 틀리다.
  따라서 이를 보완한 api인 Swing을 사용해 본다.
  
  Swing의 특징
  
 -javax.swing패키지에서 지원
 -컴포넌트가 awt와 거의 동일하다. Button-->JButton
 
 주의 !!- awt를 욕해서는 안된다!!
 		왜??? 눈에 보여지는것 이외에는 아직도 사용된다.
 		결론) GUI 프로그램 개발시 swing+awt 조합하여 개발
 		
 		자바의  GUI컴포넌트의 종류 크게 2가지가 있다.
 		1.남을 포함하는 컴포넌트 (Container)  - JFrame (디폴트로 BorderLayout), 
 		
 														JPanel (디폴트로 FlowLayout)-컨테이너이다..하지만 단독으로 
 														존재 할 수 없고 반드시 프레임 안에 소속되어 있다.
 														특징 , 눈에 보이지 않는다. (div와 흡사)
 														주요 용도- Frame 내에서 별도의 개별적 레이아웃을 적용 시키고 
 														  			싶을때 사용 한다.
 														  			
 														 Applet(디폴트로 FlowLayout)
 														 
 		2.포함당하는 컴포넌트 (Visual Component) - JButton,JCheckbox, JTextArea등등
 * */

package gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatWin  extends JFrame{
	
	JTextField tf;
	JTextArea ta;
	JButton bt;
	JPanel panel;

	public ChatWin(){
		//BorderLayout은 디폴드로 잡히기 때문에 따로 설정 안함.
		tf = new JTextField(15);
		ta= new JTextArea();
		bt=new JButton("입력");
		panel=new JPanel();
		//패널에 tf와 bt를 부착!!
		
		panel.add(tf);
		panel.add(bt);
		
		
		this.add(ta,BorderLayout.CENTER);
		add(panel,BorderLayout.SOUTH);
		
		//윈도우 창을 닫으면, 프로세스 죽이기
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setSize(300, 400);
		this.setVisible(true);
				
		
	}
	public static void main(String[] args) {
		ChatWin ct=new ChatWin();
				
	}
}
