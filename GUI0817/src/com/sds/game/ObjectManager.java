/*
 * ���� �����忡�� ȭ�鿡 ������ ������Ʈ����
 * ������ ó���ϴ� ���� ���� �������� ��������.
 * 
 * ���� �ϰ����̸鼭 ü�輺�ִ� ��ü�� ������ ���簡 �ʿ���
 * 
 * �� ��ü�� �̸��� ������Ʈ �޴������ �ϰڴ�!!!!
 * */

package com.sds.game;

import java.util.ArrayList;

public class ObjectManager {
	
	//���ӿ� �����ϰԵ� ��� ��ü�� ��Ե� �÷���!!
	ArrayList<GameObject> objectlist=new ArrayList<GameObject>();
	
	
	//ȭ�鿡 �����Ű��!! = �÷��� �����ӿ����� �߰�
	public void addObject(GameObject gameObject){
		objectlist.add(gameObject);
		
	}
	
	//ȭ�鿡�� �����ϱ�!! =�÷��� �����ӿ����� ����
	public void removeObject(GameObject gameObject){
		objectlist.remove(gameObject);
		
	}
	
}
