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




public class LoginForm extends JFrame{
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
	
	StateThread stateThread;
	
	public LoginForm() {
		txt_id=new JTextField(/*"아이디 (이메일 주소)"*/"a@gmail.com", 25);
		txt_id.setBorder(new EmptyBorder(0, 20, 0, 10));
		txt_pass= new JPasswordField(/*"비밀 번호"*/"0000",25);
		txt_pass.setBorder(new EmptyBorder(0, 20, 0, 10));
		txt_pass.setToolTipText("비밀번호");
	
		p_center= new JPanel();
		p_north= new JPanel();
		bt_login= new JButton("로그인");
		bt_login.setBackground(new Color(102, 153, 0));
		bt_login.setForeground(Color.white);
		bt_join =new JButton("회원가입");
		bt_join.setBackground(new Color(102, 153, 0));
		bt_join.setForeground(Color.white);
		bt_pass= new JButton("패스워드 찾기");
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
				//로그인 창으로 이동
				login();
			}
		});
		 bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//회원가입 창으로 이동
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
			setTitle("서버에 접속 됨");
			stateThread  = new StateThread(this, client);
			stateThread.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//로그인 기능
	public void login(){
		String id=txt_id.getText();
		String password = new String(txt_pass.getPassword());
		
		StringBuffer sb= new StringBuffer();
		sb.append("{");
		sb.append(	"\"request\" :\"login\",");
		sb.append("\"m_id\" : \""+id+"\",");
		sb.append("\"m_pwd\":\""+password+"\"");
		sb.append("}");
		
		stateThread.send(sb.toString());
	}
	
	//회占쏙옙占쏙옙占쏙옙 창占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占�
	public void join(){
		membership =new Membership2(this, client);
		this.dispose();
	}
	public void showMsg(String msg){
		JOptionPane.showMessageDialog(this , msg);
	}	
	public static void main(String[] args) {
		new LoginForm();
	}

}
