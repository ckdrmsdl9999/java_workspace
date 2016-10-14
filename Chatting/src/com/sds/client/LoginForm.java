package com.sds.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoginForm extends JFrame implements ActionListener{
	JTextField tf;
	JPasswordField tf2;
	JButton bt;
	JButton bt2;
	JPanel pl;
	JPanel pl_ct;
	JPanel pl_ct2;
	JPanel pl_ct3;
	JLabel lb;
	JLabel lb2;
	JLabel lb3;
	
	//로그인 이전에 이미 소켓 접속을 확보해 두자!!
	Socket client;
	String ip="70.12.112.119";
	int port=7777;
	
	
	BufferedReader buffr;
	BufferedWriter buffw;
	
	public LoginForm() {
		super("LOGIN");
		lb = new JLabel("로그인",0);
		lb2 = new JLabel("ID");
		lb3 = new JLabel("PW");
		bt = new JButton("Login");
		bt2 = new JButton("Sign");
		tf = new JTextField(15);
		tf2 = new JPasswordField(15);
		pl = new JPanel();
		pl_ct = new JPanel();
		pl_ct2 = new JPanel();
		pl_ct3 = new JPanel();
		
		tf.setBackground(Color.yellow);
		tf2.setBackground(Color.yellow);
		
		//버튼과 리스너와 연결
		bt.addActionListener(this);
		
		pl.add(bt);
		pl.add(bt2);		
		
		pl_ct.add(lb2);
		pl_ct.add(tf);
		pl_ct2.add(lb3);
		pl_ct2.add(tf2);
		pl_ct3.add(pl_ct);
		pl_ct3.add(pl_ct2);
		
		lb.setPreferredSize(new Dimension(130, 20));
		lb2.setPreferredSize(new Dimension(70, 20));
		lb3.setPreferredSize(new Dimension(70, 20));
		bt.setPreferredSize(new Dimension(80, 20));
		bt2.setPreferredSize(new Dimension(80, 20));
				
		add(lb,BorderLayout.NORTH);
		add(pl_ct3,BorderLayout.CENTER);
		//add(pl_ct2,BorderLayout.CENTER);
		//add(lb2,BorderLayout.CENTER);
		//add(lb3,BorderLayout.CENTER);
		//add(tf,BorderLayout.CENTER);
		//add(tf2,BorderLayout.CENTER);
		add(pl,BorderLayout.SOUTH);
				
				
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(350,180);
		setVisible(true);
		
		connect();//화면이 보여질때 연결하자!!!
	}
	
	//프로그램이 가동되면, 서버에 소켓 연결을 시도 하자!!!
	
	public void connect(){
		try {
			client =new Socket(ip,port);
			System.out.println(client);
			setTitle("서버에 접속 됨");
			
			buffr= new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw= new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void loginCheck(){
		//소켓 서버에 인증을 요청한다!!!
		String id=tf.getText();// id
		String password=new String(tf2.getPassword());//password
		
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append(	"\"request\" :\"login\",");
		sb.append("\"id\" : \""+id+"\",");
		sb.append("\"password\":\""+password+"\"");
		sb.append("}");
		//아이디와 패스워드 전송!
		try {
			buffw.write(sb.toString()+"\n");
			buffw.flush();
			
			//서버로 부터 전송되어온 요청 처리 결과 제이슨!!!!
			String msg=buffr.readLine();
			System.out.println(msg);
			
			//파싱 시작!!
			JSONParser parser =new JSONParser();
			JSONObject jsonObject=(JSONObject)parser.parse(msg);
			
			if(jsonObject.get("result").equals("ok")){
				JSONObject obj=(JSONObject)jsonObject.get("data");
				String name=(String)obj.get("name");
				long chatmember_id=(Long)obj.get("chatmember_id");
				
				JOptionPane.showMessageDialog(this, name+"님 반갑습니다.");
				
				//채팅 창 띄우기!!!!!
				AppMain app=new AppMain(buffr,buffw,(int)chatmember_id);
				setVisible(false);
				
			}else if(jsonObject.get("result").equals("fail")){
				JOptionPane.showMessageDialog(this, "올바르지 않은 로그인 정보 입니다.\n다시 시도해 주세요");
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		loginCheck();
	}
	
	public static void main(String[] args) {
		new LoginForm();

	}

}
