/*
 * ���ӿ� ���� Ű���� �̺�Ʈ�� �����Ͽ� ó���ϴ� ��ü
 * */

package com.sds.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyBoard extends KeyAdapter {
	ObjectManager objectmanager;

	// ���� ������Ʈ�޴��� ������
	public KeyBoard(ObjectManager objectManager){
		this.objectmanager=objectManager;
		
	}

	public void keyPressed(KeyEvent e) {
		// System.out.println("�� ������?");
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
		System.out.println("�� �þ�?");
	};
}