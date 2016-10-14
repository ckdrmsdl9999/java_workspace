package com.pating.chattingList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelItem extends JPanel{
	URL url;
	JLabel l_profile;
	ImageIcon icon;
	JPanel p_info;
	JLabel l_name;
	JLabel l_stateMsg;
	private JCheckBox cb;
	JLabel l_access,l_unaccess;
	boolean access;
	
	//------------------------------------------------------------	�� ����� member ����
	long member_no;
	
	public PanelItem(long room_no, String room_title, String msg){
		this.setBorder(new EmptyBorder(0, 20, 0, 10));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(350, 50));
		//----------------------------------------------------------------------
		this.url = this.getClass().getClassLoader().getResource("friend.png");
		icon=new ImageIcon(url);
		l_profile=new JLabel(icon);
		Image img=icon.getImage();
		img=img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		icon.setImage(img);
		
		p_info=new JPanel(new GridLayout(2, 1));
		p_info.setBorder(new EmptyBorder(0, 30, 0, 0));
		this.l_name=new JLabel(room_title);
		this.l_stateMsg=new JLabel(msg);
		
		add(l_profile, BorderLayout.WEST);
		add(p_info, BorderLayout.CENTER);
		p_info.add(l_name);
		p_info.add(l_stateMsg);

	
	}
	
	public PanelItem(long friend_no, String name, String url_pic, boolean access) {
		this.setBorder(new EmptyBorder(0, 20, 0, 10));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(350, 50));
		//------------------------------------------------------------------------
		this.member_no=friend_no;
		this.url = this.getClass().getClassLoader().getResource(url_pic);
		icon=new ImageIcon(this.url);
		l_profile=new JLabel(icon);
		Image img=icon.getImage();
		img=img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		icon.setImage(img);
		
		p_info=new JPanel(new GridLayout(2, 1));
		p_info.setBorder(new EmptyBorder(0, 30, 0, 0));
		this.l_name=new JLabel(name);
		
		cb=new JCheckBox();
		
		l_access=new JLabel("접속중");
		l_unaccess = new JLabel("비접속");
		l_access.setForeground(new Color(102, 153, 0));
		l_unaccess.setForeground(Color.red);
	
		add(l_profile, BorderLayout.WEST);
		add(p_info, BorderLayout.CENTER);
		p_info.add(l_name);
		add(cb, BorderLayout.EAST);
		
		this.access=access;
		
		if(this.access){
			p_info.add(l_access);
		}else{
			p_info.add(l_unaccess);
			cb.setEnabled(false);
		}
	}
	
	public JCheckBox getCheckBox(){
		return this.cb;
	}
	
	public long isChecked(){
		return member_no;
	}

}
