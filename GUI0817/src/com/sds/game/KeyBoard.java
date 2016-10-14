/*
 * 게임에 사용될 키보드 이벤트를 전담하여 처리하는 객체
 * */

package com.sds.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyBoard extends KeyAdapter {
	ObjectManager objectmanager;

	// 기존 오브젝트메니져 얻어오기
	public KeyBoard(ObjectManager objectManager){
		this.objectmanager=objectManager;
		
	}

	public void keyPressed(KeyEvent e) {
		// System.out.println("나 눌렀어?");
		int key = e.getKeyCode();
		

		for (int i = 0; i < objectmanager.objectlist.size(); i++) {
			GameObject obj = objectmanager.objectlist.get(i);

			if (obj.name.equals("Hero")) {
				switch (key) {
				case KeyEvent.VK_LEFT:obj.velX=-10;	break;
				case KeyEvent.VK_UP:obj.velY=-10;break;
				case KeyEvent.VK_DOWN:obj.velY=10	;break;
				case KeyEvent.VK_RIGHT:obj.velX=10;break;
				case KeyEvent.VK_SPACE: ;break;
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		System.out.println("나 뗐어?");
	};
}