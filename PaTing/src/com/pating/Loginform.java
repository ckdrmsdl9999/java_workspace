package com.pating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


public class Loginform extends JFrame{
	JTextField txt_id;
	JPasswordField txt_pass;
	JLabel lb_id ,lb_pass;
	JPanel p_center ,p_north ,p_center1,p_center2,p_south;
	JButton bt_login ,bt_join,bt_pass; 
	Membership2 membership;
	
	public static Socket  client;
	String host="70.12.112.119";
	int port=9099;
	
	BufferedReader buffr;
	BufferedWriter buffw;
	 
	boolean err=false;
	
	public Loginform() {
		txt_id=new JTextField("���̵� (�̸��� �ּ�)",25);
		txt_pass= new JPasswordField("��� ��ȣ",25);
		txt_pass.setToolTipText("��й�ȣ");
	
		p_center= new JPanel();
		p_north= new JPanel();
		bt_login= new JButton("�α���");
		bt_join =new JButton("ȸ������");
	//	bt_pass= new JButton("�н����� ã��");
		p_center1= new JPanel();
		p_center2= new JPanel();
		p_south= new JPanel();
		
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
				//�α��� â���� �̵�
				login();
			}
		});
		 bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ȸ�� ���� â���� �̵�
				join();
			}
		});
		
		p_north.setPreferredSize(new Dimension(300, 150));
		p_south.setPreferredSize(new Dimension(400, 150));
		
		//p_center.add(lb_id);
		p_center1.add(txt_id);
		//p_center.add(lb_pass);
		p_center1.add(txt_pass);
		
		p_center2.add(bt_join);
		p_center2.add(bt_login);
	//	p_center2.add(bt_pass);
		
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
			setTitle("������ ���� ��");
			
			buffr= new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw= new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//�α��� ���
	public void login(){
		isValidEmail();
		if(err !=true){
			JOptionPane.showMessageDialog(this, "���̵�� �̸��� �ּ��Դϴ�.");
		}
		String id=txt_id.getText();
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
				
				JOptionPane.showMessageDialog(this, name+"�� �ݰ����ϴ�.");
				
				//ä�� â ����!!!!!
				
			//	setVisible(false);
				
			}else if(object.get("result").equals("fail")){
				JOptionPane.showMessageDialog(this, "�ùٸ��� ���� �α��� ���� �Դϴ�.\n�ٽ� �õ��� �ּ���");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//ȸ������ â���� �������� ���
	public void join(){
		membership =new Membership2();
		this.dispose();
	}
	
	public  boolean isValidEmail() {
		  
		  String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";   
		  Pattern p = Pattern.compile(regex);
		  Matcher m = p.matcher(txt_id.getText());
		  if(m.matches()) {
		   err = true; 
		  }
		 return err;
	 }
	public static Socket getclient(){
		return client;
				
	}
		public static void main(String[] args) {
		new Loginform();
	}

}
