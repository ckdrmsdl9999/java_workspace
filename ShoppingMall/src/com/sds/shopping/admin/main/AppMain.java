/*
 * 쇼핑몰의 관리자측 메인 프레임!!
 * 
 * 따라서 공유할 데이터가 있다면 이 클래스에 선언하면 좋다.
 * */

package com.sds.shopping.admin.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sds.shopping.admin.member.MemberMain;
import com.sds.shopping.admin.order.OrderMain;
import com.sds.shopping.admin.partner.PartnerMain;
import com.sds.shopping.admin.product.ProductMain;
import com.sds.shopping.admin.sales.SalesMain;
import com.sds.shopping.admin.staff.StaffMain;

public class AppMain extends JFrame{
	private static Connection con;
	public static final int CONTENT_WIDTH=700;
	public static final int CONTENT_HEIGHT=550;
	
	JPanel p_north;
	JButton[] menu;
	String[] menuTitle={
			"상품관리","주문관리","회원관리","매출관리","업체관리","관리자정보"
	};
	
	JPanel p_container; //모든 패널들을 위치 시킬 중앙 패널
	JPanel[] p_contents;// 각 화면들을 가리킬 상위 자료형!!
	
	public AppMain() {
		connect();
		
		p_north = new JPanel();
		menu= new JButton[menuTitle.length];
		
		for(int i=0;i<menuTitle.length;i++){
			menu[i]= new JButton(menuTitle[i]);
			p_north.add(menu[i]);
		}
		
		add(p_north,BorderLayout.NORTH);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//데이터베이스 접속 끊기
				if(con != null){
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
				//프로세스 종료
				System.exit(0);

			}
		});
		
		p_container= new JPanel();
		p_container.setBackground(Color.YELLOW);
		
		p_contents = new JPanel[menuTitle.length];
		p_contents[0]= new ProductMain();
		p_contents[1]= new OrderMain();
		p_contents[2]= new MemberMain();
		p_contents[3]= new SalesMain();
		p_contents[4]= new PartnerMain();
		p_contents[5]= new StaffMain();
		
		for(int i=0; i<p_contents.length;i++){
			p_container.add(p_contents[i]);
			
		}
		add(p_container);
		setVisible(true);
		setSize(700,600);
		connect();
	}
	
	public void connect(){
		//자바 소스 코드 외부에 둔 특정 파일의 내용을 읽어와서 데이터 베이스 정보를 구성하면,
		//유지 보수성이 높아진다. 
		//왜? db정보가 변경되어도 자바 소스를 바꾸지 않아도 된다.....
		//static 영역에서는 this 를 사용 할 수 없다
		
		//파일이 class 일 경우는 . 으로 지정 할 수 있지만 일반 파일인 경우이는 반드시
		//   /를 사용 하여 경로를 인식 시켜 줘야 한다.
		URL url=this.getClass().getClassLoader().getResource("com/sds/shopping/db/db.data");
		url.toString();
		
		
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(url.getPath());
			//System.out.println("스트림 생성~!!!!");
			
			//key-value로 이루어진 데이터를 해석해주는 객체
			Properties props= new Properties();
			props.load(fis);//파일을 인식한 시점
			
			String driver=props.getProperty("driver");
			String host=props.getProperty("url");
			//System.out.println(host);
			String user=props.getProperty("user");
			//System.out.println(user);
			String password=props.getProperty("password");
			//System.out.println(password);
			Class.forName(driver);
			con=DriverManager.getConnection(host, user, password);
			
			if(con != null){
				setTitle("접속 성공");
			}else{
				setTitle("접속 실패!!");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	//외부의 객체들은 이 메서드를 통해서만 공유된 Connection 을 가져 가제 하자!!
	public static Connection getConnection(){
		return con;
	}
	
	public static void main(String[] args) {
		new AppMain();
	}

}
