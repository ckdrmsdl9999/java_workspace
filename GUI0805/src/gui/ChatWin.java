/*
  java.awt�� os �÷�������(Linux,Mac,Window...) �������� Ʋ����.
  ���� �̸� ������ api�� Swing�� ����� ����.
  
  Swing�� Ư¡
  
 -javax.swing��Ű������ ����
 -������Ʈ�� awt�� ���� �����ϴ�. Button-->JButton
 
 ���� !!- awt�� ���ؼ��� �ȵȴ�!!
 		��??? ���� �������°� �̿ܿ��� ������ ���ȴ�.
 		���) GUI ���α׷� ���߽� swing+awt �����Ͽ� ����
 		
 		�ڹ���  GUI������Ʈ�� ���� ũ�� 2������ �ִ�.
 		1.���� �����ϴ� ������Ʈ (Container)  - JFrame (����Ʈ�� BorderLayout), 
 		
 														JPanel (����Ʈ�� FlowLayout)-�����̳��̴�..������ �ܵ����� 
 														���� �� �� ���� �ݵ�� ������ �ȿ� �ҼӵǾ� �ִ�.
 														Ư¡ , ���� ������ �ʴ´�. (div�� ���)
 														�ֿ� �뵵- Frame ������ ������ ������ ���̾ƿ��� ���� ��Ű�� 
 														  			������ ��� �Ѵ�.
 														  			
 														 Applet(����Ʈ�� FlowLayout)
 														 
 		2.���Դ��ϴ� ������Ʈ (Visual Component) - JButton,JCheckbox, JTextArea���
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
		//BorderLayout�� ������� ������ ������ ���� ���� ����.
		tf = new JTextField(15);
		ta= new JTextArea();
		bt=new JButton("�Է�");
		panel=new JPanel();
		//�гο� tf�� bt�� ����!!
		
		panel.add(tf);
		panel.add(bt);
		
		
		this.add(ta,BorderLayout.CENTER);
		add(panel,BorderLayout.SOUTH);
		
		//������ â�� ������, ���μ��� ���̱�
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setSize(300, 400);
		this.setVisible(true);
				
		
	}
	public static void main(String[] args) {
		ChatWin ct=new ChatWin();
				
	}
}
