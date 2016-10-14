package com.pating.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

public class Membership2 extends JFrame{
	JTextField txt_id, txt_name, txt_nickname;
	JLabel lb_id, lb_pass, lb_pass1, lb_name, lb_nickname;
	JPanel p_center, p_north, p_south;
	JButton bt_join, bt_return;
	JPasswordField txt_pass, txt_pass1;
	Loginform login;
	boolean pass_check;
	Socket client;
	 BufferedReader buffr;
	 BufferedWriter buffw;
	
	
	
	public Membership2() {
		txt_id = new JTextField("아이디(이메일 주소)",20);
		txt_pass = new JPasswordField("비밀번호",20);
		txt_pass.setToolTipText("비밀번호를 입력해주세요");
		txt_pass1 = new JPasswordField("비밀번호",20);
		txt_pass1.setToolTipText("비밀번호를 입력해주세요");
		txt_name = new JTextField("이름",20);
		txt_nickname = new JTextField("닉네임",20);
		//lb_id = new JLabel("아이디");
	//	lb_pass = new JLabel("비밀번호");
		//lb_pass1 = new JLabel("비번확인");
		//lb_name = new JLabel("이    름");
		//lb_nickname = new JLabel("닉  네  임");
		bt_join = new JButton("회원 가입");
		bt_return = new JButton("돌아가기");
		
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
		txt_pass1.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				txt_pass1.setText("");
			}
		});
		txt_name.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				txt_name.setText("");
			}
		});
		txt_nickname.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				txt_nickname.setText("");
			}
		});
		
		
		//p_center = new JPanel(new GridLayout(5, 2));
		p_center = new JPanel();
		p_north = new JPanel();
		p_south = new JPanel();
		
		
		p_north.setPreferredSize(new Dimension(300, 200));
		p_south.setPreferredSize(new Dimension(300, 200));
		// p_center에 아이디,비번,이름,취미 추가하기

		// 회원 가입시 비번 확인을 위한 button에 기능 추가
		bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verify();
				if(pass_check==true){
					member_join();
				}
			}
		});

		//돌아가기 버튼 기능 추가
		bt_return.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Login_return();
			}

			
		});
		
	// p_center.add(lb_id);
		p_center.add(txt_id);
	//	p_center.add(lb_pass);
		p_center.add(txt_pass);
	//	p_center.add(lb_pass1);
		p_center.add(txt_pass1);
	//	p_center.add(lb_name);
		p_center.add(txt_name);
	//	p_center.add(lb_nickname);
		p_center.add(txt_nickname);
		p_south.add(bt_join);
		p_south.add(bt_return);
		
		p_north.setBackground(Color.ORANGE);
		p_south.setBackground(Color.ORANGE);
		p_center.setBackground(Color.ORANGE);
		
		

		// 프레임 창에 jpanel추가 하기
		add(p_north, BorderLayout.NORTH);
		add(p_south, BorderLayout.SOUTH);
		add(p_center);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 700);
	}

	// 회원 가입시 비번 확인
	public void verify(){
		
		String a=new String(txt_pass.getPassword());
		String b=new String(txt_pass1.getPassword());
		if(a.length()>b.length()){
			JOptionPane.showMessageDialog(this, "비밀번호를 확인해 주세요");
		}else if(b.length()>a.length()){
			JOptionPane.showMessageDialog(this, "비밀번호를 확인해 주세요");
		}else{
			for (int i =0;i<b.length();i++){	
				if(b.charAt(i) != a.charAt(i)){
					System.out.println("비밀번호 안맞아요");
					pass_check=false;
					JOptionPane.showMessageDialog(this, "비밀번호를 확인해 주세요");
					break;
					
				}else{
					pass_check=true;
				}
				System.out.println(pass_check);
			}
		}
		
	}

	public void Login_return() {
		login=new Loginform();
		this.dispose();
	}
	
	
	public void member_join(){
		String m_id=txt_id.getText();
		String m_pwd=new String(txt_pass.getPassword());
		String m_name=txt_name.getText();
		String m_nickname=txt_nickname.getText();
		URL url=this.getClass().getClassLoader().getResource("./login/friend.png");
		String m_pic=url.getPath();
		//System.out.println(m_pic);
		String m_status="상태메세지를 설정해 주세요";
		
		client=login.client;
		System.out.println(client);
		
		try {
			buffr=new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw= new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuffer sb= new StringBuffer();
		
		sb.append("{");
		sb.append(	"\"request\" :\"member_join\",");
		sb.append("\"m_id\" : \""+m_id+"\",");
		sb.append("\"m_pwd\":\""+m_pwd+"\",");
		sb.append("\"m_name\":\""+m_name+"\",");
		sb.append("\"m_nickname\":\""+m_nickname+"\"");
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
				
				JOptionPane.showMessageDialog(this, name+"님 회원가입 되었습니다.");
				
				//채팅 창 띄우기!!!!!
				
			//	setVisible(false);
				
			}else if(object.get("result").equals("fail")){
				JOptionPane.showMessageDialog(this, "올바르지 않은 회원 정보 입니다.\n다시 시도해 주세요");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
	}
	
}
