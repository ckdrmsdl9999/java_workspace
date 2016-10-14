package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginTest extends JFrame{
	Button login;
	Button sign;
	
	TextField id;
	TextField password;
	JLabel title;
	JLabel id_label;
	JLabel pwd_label;
	
	
	public LoginTest() {
		title=new JLabel("·Î±×ÀÎ",JLabel.CENTER);
		BorderLayout top = new BorderLayout();
		setLayout(top);
		add(title,top.NORTH);
		title.setPreferredSize(new Dimension(50,50));
		
		
		
		JPanel centerpan =new JPanel();
		id_label = new JLabel("ID", JLabel.CENTER);
		pwd_label = new JLabel("PassWord", JLabel.CENTER);
		id=new TextField(15);
		id.setBackground(new Color(100, 200, 100));
		password=new TextField(15);
		password.setBackground(new Color(100, 200, 100));
		
		GridLayout grid= new GridLayout(2,2);
		
		centerpan.setLayout(grid);
		centerpan.add(id_label);
		centerpan.add(id);
		centerpan.add(pwd_label);
		centerpan.add(password);
		
		add(centerpan,top.CENTER);
		
		JPanel buttonpan =new JPanel();
		login = new Button("Login");
		sign = new Button("Sign");
		buttonpan.add(login);
		buttonpan.add(sign);
		add(buttonpan,top.SOUTH);
		buttonpan.setPreferredSize(new Dimension(50,50));
		
		JPanel west =new JPanel();
		add(west,top.WEST);
		
		JPanel east=new JPanel();
		add(east,top.EAST);
		
		setSize(300, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	public static void main(String[] args) {
		new LoginTest();
	}

}
