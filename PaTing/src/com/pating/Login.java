package com.pating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{
	JTextField txt_id;
	JPasswordField txt_pass;
	JLabel lb_id ,lb_pass;
	JPanel p_center ,p_north ,p_center1,p_center2,p_south;
	JButton bt_login ,bt_join,bt_pass; 
	Membership membership;
	
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="pating1234";
	String password="pating1234";
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public Login() {
		txt_id=new JTextField("���̵� (�̸��� �ּ�)",25);
		txt_pass= new JPasswordField("��� ��ȣ",25);
		//lb_id=new JLabel("I      D");
		//lb_pass= new JLabel("P w d");
		p_center= new JPanel();
		p_north= new JPanel();
		bt_login= new JButton("�α���");
		bt_join =new JButton("ȸ������");
		bt_pass= new JButton("�н����� ã��");
		p_center1= new JPanel();
		p_center2= new JPanel();
		p_south= new JPanel();
		
		txt_id.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				txt_id.setText("");
			}
		});
			
		txt_pass.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
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
		p_center2.add(bt_pass);
		
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
	}
	
	//�α��� ���
	public void login(){
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����");
			con=DriverManager.getConnection(url, user, password);
			
			
			if(con !=null){
				System.out.println("����");
				
				StringBuffer sql=new StringBuffer("select * from member");
				sql.append(" where m_id=? and m_pwd=?");
				
				String pwd=new String(txt_pass.getPassword());
				
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, txt_id.getText());
				pstmt.setString(2,pwd);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					JOptionPane.showMessageDialog(this, "������ ȯ���մϴ�.");
				}else {
					JOptionPane.showMessageDialog(this, "ȸ�� ������ �����ϴ�..");
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null ){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//ȸ������ â���� �������� ���
	public void join(){
		membership =new Membership();
		this.dispose();
	}
	public static void main(String[] args) {
		new Login();
	}

}
