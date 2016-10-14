package com.sds.thread;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JProgressBar;

public class ProgresBar extends JProgressBar implements Runnable{
	int interval;//몇초 간격으로 증가 할 지 결정하자
	int n;
	
	public void run() {
		while(true){
			n++;
			this.setValue(n);
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public ProgresBar(int interval) {
		this.interval=interval;
		this.setPreferredSize(new Dimension(380, 50));
		this.setBackground(Color.YELLOW);
	}

}
