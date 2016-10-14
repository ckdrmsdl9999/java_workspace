package com.pating.chatting;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
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

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;

public class ChattingScreen extends JFrame{
	//큰 틀 만들기
	JPanel p_north,p_center,p_south,p_south_north,p_south_south;
	JTextField txt_chatting;
	JScrollPane scroll;
	GridBagLayout gbl_wrap,gbl_north;
	GridBagConstraints gbc_wrap,gbc_north;
	
	//세부 아이콘
	JPanel p_northProfile,p_northCenter,p_northEast;
	JLabel l_profile;
	Canvas c_profile,c_bellEnabled,c_threeLine;
	
	JPanel p_southEast;
	Canvas c_smile,c_clip,c_mountain;
	
	Toolkit kit;
	Image[] img = new Image[7];
	String[] imgPath = {"./res/profile.png",
			"./res/bell_enabled.png",
			"./res/three_line.png",
			"./res/smile.png",
			"./res/clip.png",
			"./res/mountain.png",
			"./res/smile.png",
			"./res/smile.png"};
	
	//이벤트 관련
	JFileChooser chooser;
	
	//메세지 패널 그리드 레이아웃 관련 수
	int count;
	
	//네트워크 연결하여 통신
	Socket client;
	//서버에 접속하고 싶다면 적어도 그 서버의 ip와 port번호는 알자.
	String serverIp ="70.12.112.93";
	int port =7878;
	/*//서버에 메세지를 전송하고 그 메세지를 다른 클라이언트에게 뿌려주기 위해 스트림이 필요하다.! 이젠 이건 스레드로 뺴야할듯
	BufferedReader buffr;
	BufferedWriter buffw;*/
	
	//전송하는 메세지~~
	String sendMsg;
	String receiveMsg;
	
	//클라이언트 스레드를 선언하여 통신을 각각 독립적으로 할 수 있도록 하자.
	ChatThread ct;
	
	public ChattingScreen(){
		setSize(400,700);
		setTitle("파팅");
		//큰 틀 생성
		p_north = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		p_south_south = new JPanel();
		txt_chatting = new JTextField();
		scroll = new JScrollPane(p_center);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
		
		
		//큰 틀 샐깔 입히기
		p_north.setBackground(new Color(51,102,0));
		p_center.setBackground(new Color(255,165,0));
		p_south.setBackground(new Color(255,165,0));
		p_south_south.setBackground(new Color(255,165,0));
		
		
		//큰 틀 크기 정하기
		p_north.setPreferredSize(new Dimension(getWidth(),30));
		//p_center.setPreferredSize(new Dimension(getWidth(),1000 ));
		p_south.setPreferredSize(new Dimension(getWidth(),140));
	
		//세부 아이콘 생성 북쪽
		p_northProfile = new JPanel();
		l_profile = new JLabel("4명");
		p_northCenter = new JPanel();
		p_northEast = new JPanel();
		
		kit = Toolkit.getDefaultToolkit();
		img[0] = kit.getImage(imgPath[0]);
		c_profile = new Canvas(){
			public void paint(Graphics g) {
				g.drawImage(img[0], 0, 0, 20, 20, this);
			}
		};
		img[1] = kit.getImage(imgPath[1]);
		c_bellEnabled = new Canvas(){
			public void paint(Graphics g) {
				g.drawImage(img[1], 0, 0, 25, 25, this);
			}
		};
		img[2] = kit.getImage(imgPath[2]);
		c_threeLine = new Canvas(){
			public void paint(Graphics g) {
				g.drawImage(img[2], 0, 0, 25, 25, this);
			}
		};
		
		//세부 아이콘 생성 남쪽
		p_southEast = new JPanel();
		img[3] = kit.getImage(imgPath[3]);
		c_smile = new Canvas(){
			public void paint(Graphics g) {
				g.drawImage(img[3], 0, 0, 25, 25, this);
			}
		};
		img[4] = kit.getImage(imgPath[4]);
		c_clip = new Canvas(){
			public void paint(Graphics g) {
				g.drawImage(img[4], 0, 0, 25, 25, this);
			}
		};
		img[5] = kit.getImage(imgPath[5]);
		c_mountain = new Canvas(){
			public void paint(Graphics g) {
				g.drawImage(img[5], 0, 0, 25, 25, this);
			}
		};
		
		
		//세부 아이콘 색깔 입혀보기 북쪽
		p_northProfile.setBackground(new Color(51,102,0));
		p_northCenter.setBackground(new Color(51,102,0));
		p_northEast.setBackground(new Color(51,102,0));
		img[0] = kit.getImage(imgPath[0]);
		c_bellEnabled.setBackground(new Color(51,102,0));
		img[0] = kit.getImage(imgPath[0]);
		c_threeLine.setBackground(new Color(51,102,0));
		
		//세부 아이콘 색깔 입혀보기 남쪽
		p_southEast.setBackground(new Color(255,165,0));
		c_smile.setBackground(new Color(255,165,0));
		c_clip.setBackground(new Color(255,165,0));
		c_mountain.setBackground(new Color(255,165,0));
		
		//세부 아이콘 크기 정하기 북쪽
		p_northProfile.setPreferredSize(new Dimension(65,30));
		c_profile.setPreferredSize(new Dimension(30, 30));
		l_profile.setFont(new Font("arialWhite", 10, 15));
		p_northEast.setPreferredSize(new Dimension(70,35));
		c_bellEnabled.setPreferredSize(new Dimension(40,30));
		c_threeLine.setPreferredSize(new Dimension(30,30));
		
		//세부 아이콘 크기 정하기 남쪽
		p_southEast.setPreferredSize(new Dimension(100,30));
		c_smile.setPreferredSize(new Dimension(30, 30));
		c_clip.setPreferredSize(new Dimension(30, 30));
		c_mountain.setPreferredSize(new Dimension(30, 30));
		
		
		p_north.setLayout(new BorderLayout());
		//이미지 붙이기 북쪽
		p_northEast.setLayout(new GridLayout(1,2));
		p_northEast.add(c_bellEnabled);
		p_northEast.add(c_threeLine);
		
		p_northProfile.add(c_profile,BorderLayout.WEST);
		p_northProfile.add(l_profile,BorderLayout.EAST);
		
		p_north.add(p_northProfile,BorderLayout.WEST);
		p_north.add(p_northCenter,BorderLayout.CENTER);
		p_north.add(p_northEast,BorderLayout.EAST);

		
		
		p_south_south.setLayout(new BorderLayout());
		//이미지 붙이기 남쪽
		p_southEast.setLayout(new GridLayout(1,3));
		p_southEast.add(c_smile);
		p_southEast.add(c_clip);
		p_southEast.add(c_mountain);
		
		p_south_south.add(p_southEast,BorderLayout.WEST);
		
		
		
		//큰틀 붙이기
		txt_chatting.setPreferredSize(new Dimension(getWidth(),100));
		p_south_south.setPreferredSize(new Dimension(getWidth(),40));
	
		p_south.setLayout(gbl_wrap = new GridBagLayout());
		gbc_wrap = new GridBagConstraints();
		gbc_wrap.fill = GridBagConstraints.BOTH;
		gbc_wrap.weightx = 1.0;
		gbc_wrap.weighty = 1.0;
		gbAdd_wrap(txt_chatting,0,0,1,1);
		gbAdd_wrap(p_south_south,0,1,1,1);
	
		p_south.add(txt_chatting);
		p_south.add(p_south_south);
		p_center.setLayout(new MigLayout());
		
		add(p_north,BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER);
		add(p_south,BorderLayout.SOUTH);
		
		//**************************************************************************
		//이벤트 생성하기
		
		chooser = new JFileChooser();
		
	/*	//1.텍스트 입력시 텍스트 area로 글씨 보여지게 하기
		txt_chatting.addKeyListener(new KeyListener() {
		
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER){
					
					JPanel p_message = new JPanel(); //메세지를 올릴때, 생겨나는 전체 패널
					JPanel p_binLeft = new JPanel(); //메세지 패널 안에 빈 공간을 의미하는 패널
					MessagePanelMe mpm = new MessagePanelMe(txt_chatting.getText());
					
					p_binLeft.setPreferredSize(new Dimension(170, 30));
					p_binLeft.setBackground(new Color(255,165,0));
					p_message.add(p_binLeft);
					p_message.add(mpm);
					p_message.setBackground(new Color(255,165,0));
					
					p_center.add(p_message,"wrap");
					p_center.updateUI();
					p_center.repaint();
					scroll.revalidate();
					scroll.repaint();
					txt_chatting.setText(""); //textfield는 비어져야함.
				}
			}
			
		});*/
		
		//2.사진 업로드 클릭시 사진 파일 탐색기 열기
		c_clip.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int result = chooser.showOpenDialog(getParent());
			}
		});
		
		//3.파일 업로드 클릭시 파일 탐색기 열기
		c_mountain.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = chooser.showOpenDialog(getParent());
			}
		});
		
		//4.이모티콘 클릭시 윈도우 프레임 열기
		c_smile.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JFrame frame = new JFrame();
				Canvas can = new Canvas();
				
			
				frame.setBackground(new Color(255,165,0));
				//frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame.setSize(300,300);
				frame.setVisible(true);
			}
		});
		
		//5.소리 이미지 클릭시 소리 안나는 이미지로 바뀌며 소리도 안나와야 한다.
		c_bellEnabled.addMouseListener(new MouseAdapter(){
			
		});
		
		
		//***************************************************************************
		
	
		//마무리
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		//****************************************************************************
		//지금부턴 서버와 네트워크 통신을 하도록 하자.
		
		try {
			client = new Socket(serverIp, port);
			//연결을 완료 했으니 이제 사용자가 입력한 텍스트를 전송해 보자.
			//대화 창이 열리면 독립적으로 대화 가능한 전화기를 선언하는게 좋을 것 같다.
			ct = new ChatThread(this,client);
			ct.start();
			
			
			txt_chatting.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					sendMsg = txt_chatting.getText();
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						System.out.println(sendMsg+"오잉");
					
						ct.sendMsg(sendMsg);
						txt_chatting.setText("");
						
						if(ct.receiveMsg==null){
							MessagePanelMe mpm = new MessagePanelMe(ct.receiveMsg);
						}//data 처음에 null 값이 들어가기 떄문에 객체를 한번 선언해서 죽이자...
						
						
							MessagePanelMe mpm = new MessagePanelMe(sendMsg);
							JPanel p_message = new JPanel(); //메세지를 올릴때, 생겨나는 전체 패널
							JPanel p_binLeft = new JPanel(); //메세지 패널 안에 빈 공간을 의미하는 패널
							
							p_binLeft.setPreferredSize(new Dimension(170, 30));
							p_binLeft.setBackground(new Color(255,165,0));
							p_message.add(p_binLeft);
							p_message.add(mpm);
							p_message.setBackground(new Color(255,165,0));
							
							p_center.add(p_message,"wrap");
							p_center.updateUI();
							//p_center.repaint();
							scroll.revalidate();
							//scroll.repaint();
						
					}
				}
			});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	//p_north 그리드 레이아웃을 크기별로 지정하기 위함.
	 private void gbAdd_north(Component c, int x, int y, int w, int h) {

	      gbc_north.gridx = x;//열
	      gbc_north.gridy = y; //행
	   
	      gbc_north.gridwidth  = w;//넓이
	      gbc_north.gridheight = h;//높이

	      gbl_north.setConstraints(c, gbc_north); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치
	      add(c);
	   }
	
	//p_south 그리드 레이아웃의 높낮이를 설정하기 위함.
	 private void gbAdd_wrap(Component c, int x, int y, int w, int h) {

	      gbc_wrap.gridx = x;//열
	      gbc_wrap.gridy = y; //행
	   
	      gbc_wrap.gridwidth  = w;//넓이
	      gbc_wrap.gridheight = h;	//높이

	      gbl_wrap.setConstraints(c, gbc_wrap); //컴포넌트를 컴포넌트 위치+크기 정보에 따라 GridBagLayout에 배치
	      add(c);
	   }
	 
	
	public static void main(String args[]){
		new ChattingScreen();
	}
}
