package com.pating.friendList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AMember extends JPanel{
	JPanel p_west,p_center,p_east,p_statusMessage;///
	JLabel la_nickname, la_statusMessage,la_vacancy;
	JButton bt;
	Toolkit kit= Toolkit.getDefaultToolkit();
	Image img;	
	
	static Color orange = new Color(255,165,0);
	static Color orange_deep = new Color(255,88,0);
	static Color green= new Color(51,102,0);
	
	static Font font1 =new Font("",Font.BOLD,16);
	static Font font2 =new Font("",Font.BOLD,20);
	
	public static int h = 80;
	
	TestMain_FriendList tf;
	long you;
	
	String[] friendInfo;
	
	public AMember(String[] friendInfo) {	
		this.friendInfo =friendInfo;
		
		this.you=you;
		p_center = new JPanel();
		p_east = new JPanel();///
		bt = new JButton("ģ���α�");		
		p_statusMessage= new JPanel();
		la_nickname= new JLabel(friendInfo[2]);//nickname을 받아옴.
		la_statusMessage= new JLabel(friendInfo[4]);//status를 받아옴.
		la_vacancy=new JLabel("");
		
		la_nickname.setFont(font2);
		la_statusMessage.setFont(font1);
		
		la_nickname.setBackground(Color.WHITE);			
		la_nickname.setBackground(Color.WHITE);
		p_statusMessage.setBackground(Color.WHITE);
		
		la_statusMessage.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));		
		p_statusMessage.add(la_statusMessage);
		p_statusMessage.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		URL url = this.getClass().getClassLoader().getResource(friendInfo[3]); //path를 담고 있음.
		//System.out.println(url);
		img=kit.getImage(url);		
		p_west = new JPanel(){
			@Override
			public void paint(Graphics g) {
				g.drawImage(img,5, 5, 50, 50,getParent());
			}
		};
		p_west.setPreferredSize(new Dimension(80, 80));
		p_west.setBackground(Color.WHITE);
		
		p_center.setLayout(new GridLayout(2,1));//		
		p_center.setPreferredSize(new Dimension(160, 65));
		p_center.setBackground(Color.WHITE);		
		
		p_east.setBackground(Color.WHITE);
		
		p_east.add(bt);///
		p_center.add(la_nickname);
		//p_center.add(la_vacancy);
		p_center.add(p_statusMessage);
		//add(p_east,BorderLayout.EAST);///
		add(p_west,BorderLayout.WEST);
		add(p_center,BorderLayout.CENTER);
				
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		setLayout(new FlowLayout(FlowLayout.LEFT));		
		setPreferredSize(new Dimension(350, h));
		
		bt.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent arg0) {
				//잠시 호출 중단makefriend();				
			}			
		});		
		
		//setVisible(true);
		//setSize(500, 500);		
	}
	
	public void attachButton(){
		add(p_east,BorderLayout.EAST);
	}
	
	//친구만들기도 잠시 멈추자 
	/*public void makefriend(){
		//friend���̺� �ش��ο��� �߰��ϰ�
		StringBuffer sb = new StringBuffer();	
		
		sb.append("{");
		sb.append("\"request\" : \"makefriend\",");
		sb.append(" \"member_id\" : "+you+"");
		sb.append("}");
		
		try {
			TestMain_FriendList.buffw.write(sb.toString()+"\n");
			TestMain_FriendList.buffw.flush();
			
			String data = TestMain_FriendList.buffr.readLine();
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(data);
			String result =(String)jsonObject.get("result");
						
			if(result.equals("ok")){
				String name=(String)jsonObject.get("name");
				JOptionPane.showMessageDialog(this, name+"���� ģ���� �߰��Ǿ����ϴ�.");
			}
			
		} catch (ParseException e) {			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		*/
		

	/*
	public static void main(String[] args) {
		new AMember("ball1.png","g","g",1);
	}*/
	
}
