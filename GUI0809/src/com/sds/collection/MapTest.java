/*
 * 컬렉션 프레임웍 중 key와 value의 쌍으로 데이터를 관리하는
 * Map을 알아보자!!
 * */

package com.sds.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		HashMap<String,String> map=new HashMap<String,String>();
		
		map.put("m1", "사과");
		map.put("m2", "딸기");
		map.put("m3", "오렌지");
		map.put("m4", "멜론");
		
		String name=map.get("m3");
		System.out.println(name);
	
		Set<String> i= map.keySet();
		Iterator<String>it=i.iterator();
		
		while(it.hasNext()){
			String key=it.next();
			String fruit=map.get(key);
			
			System.out.println(fruit);
		}
	}

}
