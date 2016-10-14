package com.sds.thread;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class ProgressTest extends JFrame{
	JButton	bt;
	JProgressBar bar;
	int n;
	Thread thread;
	
	public ProgressTest() {
	
		bt=new JButton("다운로드");
		bar =new JProgressBar();
		bar.setPreferredSize(new Dimension(380, 50));
		bar.setBackground(Color.YELLOW);
		
		add(bt);
		add(bar);
		
		thread =new Thread(){
			public void run() {
				while(true){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					fill();
				}
			}
		};
		bt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				thread.start();
			}
		});
		
		setLayout(new FlowLayout());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 150);
	}
	
	public void fill(){
		n++;
		bar.setValue(n);
	}
	public static void main(String[] args) {
		new ProgressTest();
	}

}
