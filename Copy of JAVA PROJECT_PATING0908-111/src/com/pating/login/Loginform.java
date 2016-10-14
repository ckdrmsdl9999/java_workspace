package com.pating.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pating.friendList.TestMain_FriendList;


public class Loginform extends JFrame{
	JTextField txt_id;
	JPasswordField txt_pass;
	JLabel lb_id ,lb_pass;
	JPanel p_center ,p_north ,p_center1,p_center2,p_south;
	JButton bt_login ,bt_join,bt_pass; 
	Membership2 membership;
	JLabel l_logo;
	Image img_logo;
	ImageIcon icon_logo;
	URL url=this.getClass().getClassLoader().getResource("PATING_logo.png");
	
	public static Socket client;
	String host="70.12.112.119";
	int port=7676;
	
	BufferedReader buffr;
	BufferedWriter buffw;
	
	public static String id;
	
	public Loginform() {
		txt_id=new JTextField(/*"�븘�씠�뵒 (�씠硫붿씪 二쇱냼)"*/"test@papaya.com", 25);
		txt_id.setBorder(new EmptyBorder(0, 20, 0, 10));
		txt_pass= new JPasswordField(/*"鍮꾨� 踰덊샇"*/"1111",25);
		txt_pass.setBorder(new EmptyBorder(0, 20, 0, 10));
		txt_pass.setToolTipText("鍮꾨�踰덊샇");
	
		p_center= new JPanel();
		p_north= new JPanel();
		bt_login= new JButton("濡쒓렇�씤");
		bt_login.setBackground(new Color(102, 153, 0));
		bt_login.setForeground(Color.white);
		bt_join =new JButton("�쉶�썝媛��엯");
		bt_join.setBackground(new Color(102, 153, 0));
		bt_join.setForeground(Color.white);
		bt_pass= new JButton("�뙣�뒪�썙�뱶 李얘린");
		p_center1= new JPanel();
		p_center2= new JPanel();
		p_south= new JPanel();
		icon_logo=new ImageIcon(url);
		img_logo=icon_logo.getImage().getScaledInstance(120, 120, 0);
		icon_logo.setImage(img_logo);
		l_logo=new JLabel(icon_logo);
		l_logo.setPreferredSize(new Dimension(350, 350));
		
		txt_id.addFocusListener(new FocusAdapter() {
			
			public void focusGained(FocusEvent e) {
				txt_id.setText("");
			}
		});
		
		txt_pass.addFocusListener(new FocusAdapter() {
			
			public void focusGained(FocusEvent e) {
				txt_pass.setText("");
			}
		});
		
		 bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//濡쒓렇�씤 李쎌쑝濡� �씠�룞
				login();
			}
		});
		 bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�쉶�썝媛��엯 李쎌쑝濡� �씠�룞
				join();
			}
		});
		
		p_north.setPreferredSize(new Dimension(400, 300));
		p_south.setPreferredSize(new Dimension(400, 150));
		
		//p_center.add(lb_id);
		p_center1.add(txt_id);
		//p_center.add(lb_pass);
		p_center1.add(txt_pass);
		
		p_center2.setPreferredSize(new Dimension(400, 100));
		p_center2.add(bt_join);
		p_center2.add(bt_login);
		//p_center2.add(bt_pass);
		
		p_center.setLayout(new GridLayout(2, 1));
		p_center.add(p_center1);
		p_center.add(p_center2);
		
		p_north.setBackground(Color.ORANGE);
		p_center1.setBackground(Color.ORANGE);
		p_center2.setBackground(Color.ORANGE);
		p_center.setBackground(Color.ORANGE);
		p_south.setBackground(Color.ORANGE);

		add(p_center);
		add(p_north, BorderLayout.NORTH);
		p_north.add(l_logo);
		add(p_south, BorderLayout.SOUTH);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 700);
		connect();
	}
	
	public void connect(){
		try {
			client= new Socket(host, port);
			System.out.println(client);
			setTitle("�꽌踰꾩뿉 �젒�냽 �맖");
			
			buffr= new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw= new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//濡쒓렇�씤 湲곕뒫
	public void login(){
		id=txt_id.getText();
		String password = new String(txt_pass.getPassword());
		
		StringBuffer sb= new StringBuffer();
		sb.append("{");
		sb.append(	"\"request\" :\"login\",");
		sb.append("\"m_id\" : \""+id+"\",");
		sb.append("\"m_pwd\":\""+password+"\"");
		sb.append("}");
		
		try {
			System.out.println(sb.toString());
			buffw.write(sb.toString() +"\n");
			buffw.flush();
			
			String msg=buffr.readLine();
			JSONParser parser=new JSONParser();
			JSONObject object= (JSONObject)parser.parse(msg);
			
			if(object.get("result").equals("ok")){
				JSONObject obj=(JSONObject)object.get("data");
				String name=(String)obj.get("m_name");
				String member_id = (String)obj.get("member_id");
				int me = Integer.parseInt(member_id) ;
				
				JOptionPane.showMessageDialog(this, name+"님 반갑습니다.");
				
				String path=(String)obj.get("pic");//Yt
				String nick = (String)obj.get("nickname");//Yt
				String status  = (String)obj.get("status"); 	//Yt
				//梨꾪똿 李� �쓣�슦湲�!!!
				new TestMain_FriendList(buffr,buffw,me ,path,nick,status);
				
				setVisible(true);
				
			}else if(object.get("result").equals("fail")){
				JOptionPane.showMessageDialog(this, "�삱諛붾Ⅴ吏� �븡�� 濡쒓렇�씤 �젙蹂� �엯�땲�떎.\n�떎�떆 �떆�룄�빐 二쇱꽭�슂");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//�쉶�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 李썲뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝占�
	public void join(){
		membership =new Membership2();
		this.dispose();
	}
	public static void main(String[] args) {
		new Loginform();
	}

}
