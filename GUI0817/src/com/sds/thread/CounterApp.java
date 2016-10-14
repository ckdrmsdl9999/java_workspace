package com.sds.thread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//쓰레드를 상속 받아 사용 하려 할때 이미 누군가의 자식 클래스라면
//Runnable 인터페이스를 이용 할 수 있다
//Runnable 은 run 메서드를 추상메서드로 보유한 인터페이스이다!!
public class CounterApp extends JFrame{// implements Runnable{
	JButton bt_start;
	JLabel l_no;
	Thread thread;
	int i=0;
	//MyThread thread;
	CounterApp app;
	//Thread thread3; //Runnable은 쓰레드가 아니므로, run 메서드를
						// 재정의 했다 하더라도 쓰레드가 존재해야 한다.!!
	public CounterApp() {
		app=this;
		bt_start =new JButton("시 작");
		l_no =new JLabel("0");
		
		thread=new Thread(){
			public void run(){
				while(true){
					try {
						thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					count();
				}
				
			}
			
		};
		
		
		//Runnable 인터페이스가 보유한 run 동작을 위한 쓰레드
		//thread3=new Thread(this);
		
		l_no.setPreferredSize(new Dimension(250, 300));
		l_no.setFont(new Font("arial black",Font.BOLD,100));
		
		bt_start.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//thread=new MyThread(app);
				//thread3.start();
				thread.start();
				//	count();
			}
		});
		
		add(bt_start,BorderLayout.NORTH);
		add(l_no);
		
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 300);
	}
	
	public void count(){
		i++;
		l_no.setText(Integer.toString(i));
		
	}
	
	//Runnable 인터페이스 용 run 메서드
	/*public void run() {
		while(true){
			try {
				thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count();
		}
		
	}
	}
	*/
	public static void main(String[] args) {
		new CounterApp();
	}

}
