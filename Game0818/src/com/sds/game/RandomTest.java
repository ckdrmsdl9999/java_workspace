/*
 * 자바에서 랜덤을 돌리는 방법을 공부하자!
 * */

package com.sds.game;

import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		Random random=new Random();
		int n=random.nextInt(5);
		System.out.println(n);
	}

}
