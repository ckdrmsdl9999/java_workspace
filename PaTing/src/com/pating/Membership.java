package com.pating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Membership extends JFrame{
	JTextField txt_id, txt_name, txt_hobby;
	JLabel lb_id, lb_pass, lb_pass1, lb_name, lb_nickname;
	JPanel p_center, p_north, p_south;
	JButton bt_join, bt_return;
	JPasswordField txt_pass, txt_pass1;
	Loginform login;
	boolean pass_check;
	
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="pating1234";
	String password="pating1234";
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	
	public Membership() {
		txt_id = new JTextField(20);
		txt_pass = new JPasswordField(20);
		txt_pass1 = new JPasswordField(20);
		txt_name = new JTextField(20);
		txt_hobby = new JTextField(20);
		lb_id = new JLabel("���̵�");
		lb_pass = new JLabel("��й�ȣ");
		lb_pass1 = new JLabel("���Ȯ��");
		lb_name = new JLabel("��    ��");
		lb_nickname = new JLabel("��  ��  ��");
		bt_join = new JButton("ȸ�� ����");
		bt_return = new JButton("���ư���");
		

		p_center = new JPanel(new GridLayout(5, 2));
		p_north = new JPanel();
		p_south = new JPanel();

		p_north.setPreferredSize(new Dimension(300, 200));
		p_south.setPreferredSize(new Dimension(300, 200));
		// p_center�� ���̵�,���,�̸�,��� �߰��ϱ�

		// ȸ�� ���Խ� ��� Ȯ���� ���� button�� ��� �߰�
		bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verify();
				if(pass_check==true){
					member_join();
				}
			}
		});

		//���ư��� ��ư ��� �߰�
		bt_return.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Login_return();
			}

			
		});
		
		p_center.add(lb_id);
		p_center.add(txt_id);
		p_center.add(lb_pass);
		p_center.add(txt_pass);
		p_center.add(lb_pass1);
		p_center.add(txt_pass1);
		p_center.add(lb_name);
		p_center.add(txt_name);
		p_center.add(lb_nickname);
		p_center.add(txt_hobby);
		p_south.add(bt_join);
		p_south.add(bt_return);
		
		p_north.setBackground(Color.ORANGE);
		p_south.setBackground(Color.ORANGE);
		p_center.setBackground(Color.ORANGE);

		// ������ â�� jpanel�߰� �ϱ�
		add(p_north, BorderLayout.NORTH);
		add(p_south, BorderLayout.SOUTH);
		add(p_center);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 700);
	}

	// ȸ�� ���Խ� ��� Ȯ��
	public void verify(){
		
		String a=new String(txt_pass.getPassword());
		String b=new String(txt_pass1.getPassword());
		if(a.length()>b.length()){
			JOptionPane.showMessageDialog(this, "��й�ȣ�� Ȯ���� �ּ���");
		}else if(b.length()>a.length()){
			JOptionPane.showMessageDialog(this, "��й�ȣ�� Ȯ���� �ּ���");
		}else{
			for (int i =0;i<b.length();i++){	
				if(b.charAt(i) != a.charAt(i)){
					System.out.println("��й�ȣ �ȸ¾ƿ�");
					pass_check=false;
					JOptionPane.showMessageDialog(this, "��й�ȣ�� Ȯ���� �ּ���");
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
		String m_nickname=txt_hobby.getText();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url, user, password);
			
			if(con != null){
				System.out.println("����");
				
				StringBuffer sql=new StringBuffer();
				sql.append("insert into member(");
				sql.append("member_id,m_id,m_pwd,m_name,m_nickname");
				sql.append(")values(seq_member.nextval,?,?,?,?)");
				
				pstmt=con.prepareStatement(sql.toString());
				
				pstmt.setString(1, m_id);
				pstmt.setString(2, m_pwd);
				pstmt.setString(3, m_name);
				pstmt.setString(4, m_nickname);
				
				rs=pstmt.executeQuery();
			}
		}catch (ClassNotFoundException e) {
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
}
