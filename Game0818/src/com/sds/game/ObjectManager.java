/*
 *���ӿ� ������ ��ü�� �����ϴ� Ŭ���� ����
 *
 *��ü�� ���� ������ �����ͺ��̽�ȭ ���Ѽ� �����ϵ�,
 *�޸𸮻� �����Ѵ�.
 *��ü�� ��Ƽ� �����ϴµ� ����ȭ �� �÷��� �����ӿ� ���
 *
 * */

package com.sds.game;

import java.util.ArrayList;

public class ObjectManager {
	ArrayList<GameObject> objectlist=new ArrayList<GameObject>();//�޸𸮻� ������ ���̽�;
	
	
	//������Ʈ �߰�
	public void addObject(GameObject obj){
		objectlist.add(obj);
		
	}
	//������Ʈ ����
	public void removeObject(GameObject obj){
		objectlist.remove(obj);
		
	}
	
}
