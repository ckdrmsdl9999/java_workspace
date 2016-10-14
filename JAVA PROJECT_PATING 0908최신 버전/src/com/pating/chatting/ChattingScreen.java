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
	//ū Ʋ �����
	JPanel p_north,p_center,p_south,p_south_north,p_south_south;
	JTextField txt_chatting;
	JScrollPane scroll;
	GridBagLayout gbl_wrap,gbl_north;
	GridBagConstraints gbc_wrap,gbc_north;
	
	//���� ������
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
	
	//�̺�Ʈ ����
	JFileChooser chooser;
	
	//�޼��� �г� �׸��� ���̾ƿ� ���� ��
	int count;
	
	//��Ʈ��ũ �����Ͽ� ���
	Socket client;
	//������ �����ϰ� �ʹٸ� ��� �� ������ ip�� port��ȣ�� ����.
	String serverIp ="70.12.112.100";
	int port =7676;
	/*//������ �޼����� �����ϰ� �� �޼����� �ٸ� Ŭ���̾�Ʈ���� �ѷ��ֱ� ���� ��Ʈ���� �ʿ��ϴ�.! ���� �̰� ������� �����ҵ�
	BufferedReader buffr;
	BufferedWriter buffw;*/
	
	//�����ϴ� �޼���~~
	String sendMsg;
	String receiveMsg;
	
	//Ŭ���̾�Ʈ �����带 �����Ͽ� ����� ���� ���������� �� �� �ֵ��� ����.
	ChatThread ct;
	
	public ChattingScreen(){
		
		setSize(400,700);
		setTitle("채팅방");
		//ū Ʋ ����
		p_north = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		p_south_south = new JPanel();
		txt_chatting = new JTextField();
		scroll = new JScrollPane(p_center);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
		
		
		//ū Ʋ ���� ������
		p_north.setBackground(new Color(51,102,0));
		p_center.setBackground(new Color(255,165,0));
		p_south.setBackground(new Color(255,165,0));
		p_south_south.setBackground(new Color(255,165,0));
		
		
		//ū Ʋ ũ�� ���ϱ�
		p_north.setPreferredSize(new Dimension(getWidth(),30));
		//p_center.setPreferredSize(new Dimension(getWidth(),1000 ));
		p_south.setPreferredSize(new Dimension(getWidth(),140));
	
		//���� ������ ���� ����
		p_northProfile = new JPanel();
		l_profile = new JLabel("4��");
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
		
		//���� ������ ���� ����
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
		
		
		//���� ������ ���� �������� ����
		p_northProfile.setBackground(new Color(51,102,0));
		p_northCenter.setBackground(new Color(51,102,0));
		p_northEast.setBackground(new Color(51,102,0));
		img[0] = kit.getImage(imgPath[0]);
		c_bellEnabled.setBackground(new Color(51,102,0));
		img[0] = kit.getImage(imgPath[0]);
		c_threeLine.setBackground(new Color(51,102,0));
		
		//���� ������ ���� �������� ����
		p_southEast.setBackground(new Color(255,165,0));
		c_smile.setBackground(new Color(255,165,0));
		c_clip.setBackground(new Color(255,165,0));
		c_mountain.setBackground(new Color(255,165,0));
		
		//���� ������ ũ�� ���ϱ� ����
		p_northProfile.setPreferredSize(new Dimension(65,30));
		c_profile.setPreferredSize(new Dimension(30, 30));
		l_profile.setFont(new Font("arialWhite", 10, 15));
		p_northEast.setPreferredSize(new Dimension(70,35));
		c_bellEnabled.setPreferredSize(new Dimension(40,30));
		c_threeLine.setPreferredSize(new Dimension(30,30));
		
		//���� ������ ũ�� ���ϱ� ����
		p_southEast.setPreferredSize(new Dimension(100,30));
		c_smile.setPreferredSize(new Dimension(30, 30));
		c_clip.setPreferredSize(new Dimension(30, 30));
		c_mountain.setPreferredSize(new Dimension(30, 30));
		
		
		p_north.setLayout(new BorderLayout());
		//�̹��� ���̱� ����
		p_northEast.setLayout(new GridLayout(1,2));
		p_northEast.add(c_bellEnabled);
		p_northEast.add(c_threeLine);
		
		p_northProfile.add(c_profile,BorderLayout.WEST);
		p_northProfile.add(l_profile,BorderLayout.EAST);
		
		p_north.add(p_northProfile,BorderLayout.WEST);
		p_north.add(p_northCenter,BorderLayout.CENTER);
		p_north.add(p_northEast,BorderLayout.EAST);

		
		
		p_south_south.setLayout(new BorderLayout());
		//�̹��� ���̱� ����
		p_southEast.setLayout(new GridLayout(1,3));
		p_southEast.add(c_smile);
		p_southEast.add(c_clip);
		p_southEast.add(c_mountain);
		
		p_south_south.add(p_southEast,BorderLayout.WEST);
		
		
		
		//ūƲ ���̱�
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
		//�̺�Ʈ �����ϱ�
		
		chooser = new JFileChooser();
		
	/*	//1.�ؽ�Ʈ �Է½� �ؽ�Ʈ area�� �۾� �������� �ϱ�
		txt_chatting.addKeyListener(new KeyListener() {
		
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER){
					
					JPanel p_message = new JPanel(); //�޼����� �ø���, ���ܳ��� ��ü �г�
					JPanel p_binLeft = new JPanel(); //�޼��� �г� �ȿ� �� ������ �ǹ��ϴ� �г�
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
					txt_chatting.setText(""); //textfield�� ���������.
				}
			}
			
		});*/
		
		//2.���� ���ε� Ŭ���� ���� ���� Ž���� ����
		c_clip.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int result = chooser.showOpenDialog(getParent());
			}
		});
		
		//3.���� ���ε� Ŭ���� ���� Ž���� ����
		c_mountain.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = chooser.showOpenDialog(getParent());
			}
		});
		
		//4.�̸�Ƽ�� Ŭ���� ������ ������ ����
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
		
		//5.�Ҹ� �̹��� Ŭ���� �Ҹ� �ȳ��� �̹����� �ٲ�� �Ҹ��� �ȳ��;� �Ѵ�.
		c_bellEnabled.addMouseListener(new MouseAdapter(){
			
		});
		
		
		//***************************************************************************
		
	
		//������
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		//****************************************************************************
		//���ݺ��� ������ ��Ʈ��ũ ����� �ϵ��� ����.
		
		try {
			client = new Socket(serverIp, port);
			//������ �Ϸ� ������ ���� ����ڰ� �Է��� �ؽ�Ʈ�� ������ ����.
			//��ȭ â�� ������ ���������� ��ȭ ������ ��ȭ�⸦ �����ϴ°� ���� �� ����.
			ct = new ChatThread(this,client);
			ct.start();
			
			
			txt_chatting.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					sendMsg = txt_chatting.getText();
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						System.out.println(sendMsg+"����");
					
						ct.sendMsg(sendMsg);
						txt_chatting.setText("");
						
						if(ct.receiveMsg==null){
							MessagePanelMe mpm = new MessagePanelMe(ct.receiveMsg);
						}//data ó���� null ���� ���� ������ ��ü�� �ѹ� �����ؼ� ������...
						
						
							MessagePanelMe mpm = new MessagePanelMe(sendMsg);
							JPanel p_message = new JPanel(); //�޼����� �ø���, ���ܳ��� ��ü �г�
							JPanel p_binLeft = new JPanel(); //�޼��� �г� �ȿ� �� ������ �ǹ��ϴ� �г�
							
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
	
	
	
	//p_north �׸��� ���̾ƿ��� ũ�⺰�� �����ϱ� ����.
	 private void gbAdd_north(Component c, int x, int y, int w, int h) {

	      gbc_north.gridx = x;//��
	      gbc_north.gridy = y; //��
	   
	      gbc_north.gridwidth  = w;//����
	      gbc_north.gridheight = h;//����

	      gbl_north.setConstraints(c, gbc_north); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ
	      add(c);
	   }
	
	//p_south �׸��� ���̾ƿ��� �����̸� �����ϱ� ����.
	 private void gbAdd_wrap(Component c, int x, int y, int w, int h) {

	      gbc_wrap.gridx = x;//��
	      gbc_wrap.gridy = y; //��
	   
	      gbc_wrap.gridwidth  = w;//����
	      gbc_wrap.gridheight = h;	//����

	      gbl_wrap.setConstraints(c, gbc_wrap); //������Ʈ�� ������Ʈ ��ġ+ũ�� ������ ���� GridBagLayout�� ��ġ
	      add(c);
	   }
	 
	
	/*public static void main(String args[]){
		new ChattingScreen();
	}*/
}
