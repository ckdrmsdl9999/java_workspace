package com.sds.auto;

public class StarThread extends Thread{
	
	@Override
	public void run() {
	
		while(true){
			try {
				Thread.sleep(1000);//1초동안 non-Runnalbe 상태로 있어라
											//1초후에 다시 runnable 로 복귀
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("★");
			
		}
	}	
		
	
}
