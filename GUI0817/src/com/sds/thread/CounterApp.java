package com.sds.thread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//�����带 ��� �޾� ��� �Ϸ� �Ҷ� �̹� �������� �ڽ� Ŭ�������
//Runnable �������̽��� �̿� �� �� �ִ�
//Runnable �� run �޼��带 �߻�޼���� ������ �������̽��̴�!!
public class CounterApp extends JFrame{// implements Runnable{
	JButton bt_start;
	JLabel l_no;
	Thread thread;
	int i=0;
	//MyThread thread;
	CounterApp app;
	//Thread thread3; //Runnable�� �����尡 �ƴϹǷ�, run �޼��带
						// ������ �ߴ� �ϴ��� �����尡 �����ؾ� �Ѵ�.!!
	public CounterApp() {
		app=this;
		bt_start =new JButton("�� ��");
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
		
		
		//Runnable �������̽��� ������ run ������ ���� ������
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
	
	//Runnable �������̽� �� run �޼���
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
