/*
	�޴��̾�׷������� �����θ� �������� ...ù���̴ϱ� ����
*/
package gui;
import java.awt.Frame;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.TextArea;


class Memo{
	public static void main(String[] args){
		//�޸��� ������ ����
		Frame frame = new Frame();

		//�޴��� ����
		MenuBar mb =new MenuBar();
		
		
		
		//�޴���	
		String[] menuTitle={"����","����","����","����","����"};
		Menu[] menus =new Menu[5];
		for(int i=0;i<menus.length;i++){
			menus[i]=new Menu(menuTitle[i]);
			mb.add(menus[i]);//�޴��ٿ� ����
		}
		//�����쿡 �޴��� ����.
		frame.setMenuBar(mb);

		//�޴������۵�


		//TextArea����
		TextArea area= new TextArea(30,40);
		frame.add(area);//frame�� ����
		
		//������ ũ��,���̰��ϱ�

		frame.setVisible(true);
		frame.setSize(700,700);
	}
}
