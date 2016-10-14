/*
 * 게임 쓰레드에서 화면에 등장할 오브젝트들을
 * 일일이 처리하다 보면 유지 보수성이 떨어진다.
 * 
 * 무언가 일괄적이면서 체계성있는 객체를 관리할 존재가 필요함
 * 
 * 이 객체의 이름을 오브젝트 메니져라고 하겠다!!!!
 * */

package com.sds.game;

import java.util.ArrayList;

public class ObjectManager {
	
	//게임에 등장하게될 모든 객체를 담게될 컬렉션!!
	ArrayList<GameObject> objectlist=new ArrayList<GameObject>();
	
	
	//화면에 등장시키기!! = 컬렉션 프레임웍에서 추가
	public void addObject(GameObject gameObject){
		objectlist.add(gameObject);
		
	}
	
	//화면에서 제거하기!! =컬렉션 프레임웍에서 제거
	public void removeObject(GameObject gameObject){
		objectlist.remove(gameObject);
		
	}
	
}
