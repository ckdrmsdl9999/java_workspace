/*
	메다이어그램모장의 디자인만 따라하자 ...첫날이니까 ㅋㅋ
*/
package gui;
import java.awt.Frame;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.TextArea;


class Memo{
	public static void main(String[] args){
		//메모장 윈도우 선언
		Frame frame = new Frame();

		//메뉴바 선언
		MenuBar mb =new MenuBar();
		
		
		
		//메뉴들	
		String[] menuTitle={"파일","편집","서식","보기","도움말"};
		Menu[] menus =new Menu[5];
		for(int i=0;i<menus.length;i++){
			menus[i]=new Menu(menuTitle[i]);
			mb.add(menus[i]);//메뉴바에 부착
		}
		//윈도우에 메뉴바 부착.
		frame.setMenuBar(mb);

		//메뉴아이템들


		//TextArea부착
		TextArea area= new TextArea(30,40);
		frame.add(area);//frame에 부착
		
		//윈도우 크기,보이게하기

		frame.setVisible(true);
		frame.setSize(700,700);
	}
}
