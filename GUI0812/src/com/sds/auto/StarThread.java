package com.sds.auto;

public class StarThread extends Thread{
	
	@Override
	public void run() {
	
		while(true){
			try {
				Thread.sleep(1000);//1�ʵ��� non-Runnalbe ���·� �־��
											//1���Ŀ� �ٽ� runnable �� ����
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("��");
			
		}
	}	
		
	
}
