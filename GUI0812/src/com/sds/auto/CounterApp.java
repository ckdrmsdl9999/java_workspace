/**
 윈도우의 멀티태스킹
 -하나의 윈도우내에 동시에 여러 프로세스를 실행 시키는 방법
 
 멀티쓰래딩
 -하나의 프로세스내에서 독립적으로 실행될 수 있는 세부 실행 단위를 
 쓰래드라 하며 이러한 쓰레드를 여러개 수행 시키는 방법
 을 멀티 쓰래딩이라 한다. 
 */

package com.sds.auto;

public class CounterApp {
	
	//우리가 지금까지 알고 있었던 실행부의 정확한 명칭은 
	//메인쓰레드 였다!! 즉 자바는 쓰레드 단위이다.
	public static void main(String[] args) {
		//분신 생성
		MyThread mt=new MyThread();
		//mt.run(); 일반 메서드로 실행 되기 때문에 멀티 쓰래딩 할때는 직접 실행하면 안됨 
		//Runnable 영역에서 수행되어야 동시 수행이 가능하기 때문에
		//jvm에게 맡긴다.
		
		//너무 빠른 실행 속도를 조정 하고 싶을때는 싶을때는 Runnalbe 영역에서
		//non-Runnable영역으로 쓰레드를 잠시 옮겨 두자
		StarThread st=new StarThread();
		mt.start();
	}
}
