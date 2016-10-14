package com.sds.thread;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ThreadPackage extends JFrame{
	JButton b;
	JLabel la;
	JProgressBar bar1, bar2;
	JPanel p;
	Thread thread, thread2;
	int x = 0;
	int y = 0;
		
	public ThreadPackage() {
		b = new JButton("Ω√¿€");
		la = new JLabel("0");
		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		p = new JPanel();
		
		setLayout(new BorderLayout());
		
		b.setPreferredSize(new Dimension(50, 50));
		la.setFont(new Font("arial black", Font.BOLD, 50));
		bar1.setPreferredSize(new Dimension(400, 30));
		bar2.setPreferredSize(new Dimension(400, 30));
		
		p.add(la);
		p.add(bar1);
		p.add(bar2);
		
		thread = new Thread(){
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					x++;
					la.setText(Integer.toString(x));
					bar1.setValue(x);
				}
			}
		};
		
		thread2 = new Thread(){
			public void run() {
				while(true){
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					y++;
					bar2.setValue(y);
				}
			}
		};
		
		add(b, BorderLayout.NORTH);
		add(p, BorderLayout.CENTER);
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread.start();
				thread2.start();
				b.setEnabled(false);
			}
		});
				
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(450, 300);
		setVisible(true);		
	}
	
	public static void main(String[] args) {
		new ThreadPackage();
	}

}
