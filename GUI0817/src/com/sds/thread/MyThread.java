package com.sds.thread;

public class MyThread extends Thread {
	/*
	 * 개발자는 독립적으로 수행하고 싶은 코드를 run메서드에 작성해야 한다.
	 */
	int count = 0;
	CounterApp app;// 현재는 null
	public MyThread(CounterApp app) {
		this.app=app;
	}
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count();
		}
	}

	public void count() {
		
		count++;
		// 윈도우의 라벨에 출력
		app.l_no.setText(Integer.toString(count));
	}
}
