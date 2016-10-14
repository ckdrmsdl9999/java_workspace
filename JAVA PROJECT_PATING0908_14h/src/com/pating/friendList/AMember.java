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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
	
	int count;
	
	long you;
	long me;
	String nickname;
	String status;
	
	AMember moi;
	
	public AMember(FriendList fr, String path,String nickname,String status,long me, long you) {		
		this.you=you;
		this.me=me;
		this.nickname=nickname;
		moi=this;
		this.status=status;
		
		p_center = new JPanel();
		p_east = new JPanel();//
		bt = new JButton("친구맺기");		
		p_statusMessage= new JPanel();
		la_nickname= new JLabel(nickname);
		la_statusMessage= new JLabel(status);
		la_vacancy=new JLabel("");
		
		la_nickname.setFont(font2);
		la_statusMessage.setFont(font1);
		
		la_nickname.setBackground(Color.WHITE);			
		la_nickname.setBackground(Color.WHITE);
		p_statusMessage.setBackground(Color.WHITE);
		
		la_statusMessage.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));		
		p_statusMessage.add(la_statusMessage);
		p_statusMessage.setLayout(new FlowLayout(FlowLayout.LEFT));	
		
		
		try {
			System.out.println(path);
			URL url = new URL("http://70.12.112.94:9091/"+path+"");
			System.out.println(url);
			img=kit.getImage(url);		
			p_west = new JPanel(){
				@Override
				public void paint(Graphics g) {
					g.drawImage(img,5, 5, 50, 50,getParent());
				}
			};
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p_west.setPreferredSize(new Dimension(80, 80));
		p_west.setBackground(Color.WHITE);
		
		p_center.setLayout(new GridLayout(2,1));//		
		p_center.setPreferredSize(new Dimension(140, 65));
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
		
		addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e) {
				Select select = new Select(moi);
			
			}
		});
		
		bt.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent arg0) {
				makefriend();
				fr.p_list2.removeAll();
				fr.select_member();				
			}			
		});		
		
		//setVisible(true);
		//setSize(500, 500);		
	}
	
	
	
	public void attachButton(){
		add(p_east,BorderLayout.EAST);
	}
	
	public void makefriend(){
		//friend테이블에 해당인원을 추가하고
		StringBuffer sb = new StringBuffer();	
		
		sb.append("{");
		sb.append("\"request\" : \"makefriend\",");
		sb.append(" \"f_you\" : "+you+",");
		sb.append("\"f_me\" : "+me+",");
		sb.append("\"nickname\" : \""+nickname+"\"");
		sb.append("}");
		
		try {
			TestMain_FriendList.buffw.write(sb.toString()+"\n");
			TestMain_FriendList.buffw.flush();
			
			String data = TestMain_FriendList.buffr.readLine();
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(data);
			String result =(String)jsonObject.get("result");
						
			if(result.equals("ok")){
				JSONObject obj=(JSONObject)jsonObject.get("data");				
				String nick =(String)obj.get("nickname");
				JOptionPane.showMessageDialog(getParent(), nick+"님이 친구로 추가되었습니다.");				
			}
			
		} catch (ParseException e) {			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	public static void main(String[] args) {
		new AMember("ball1.png","g","g",1);
	}*/
	
}
