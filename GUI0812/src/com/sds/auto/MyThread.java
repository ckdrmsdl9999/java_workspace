/*
 * �ϳ��� ���μ��������� ���������� ����Ǵ� ���ν��� ������
 * ������� �ϸ� ThreadŬ������ �����Ѵ�.
 * 
 * ����) ������� �ڹ��� ������ �ƴϴ�.!! �����ϴ� ��κ��� ����
 * ���α׷��� �����尡 �����ȴ�.
 * c, c++, c#, javascript(=setRimeout() ���� ����ϰ� ����)
 * */

package com.sds.auto;

public class MyThread extends Thread {
	int i=0;
	/*�����ڴ� ���������� �����ϰ� ���� �ڵ尡 �ִٸ�
		run()�޼��带 ������ �ϸ� �ȴ�!!!
	*/
	public void run() {
		
		while(true){
			try {
				Thread.sleep(1000);//1�ʵ��� non-Runnalbe ���·� �־��
											//1���Ŀ� �ٽ� runnable �� ����
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
			System.out.println(i);
		}
	}
}
