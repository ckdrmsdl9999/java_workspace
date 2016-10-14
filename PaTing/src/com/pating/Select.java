/*
	이미지 파일 참고 
*/

package com.pating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Select extends JFrame {
	JPanel p, p_me, p_chatting, p_profileMgr, p_status, p_me1, p_chatting1, p_profileMgr1, p_south, p_north;
	JLabel lb_me, lb_chatting, lb_profileMgr, lb_status;

	Toolkit kit = Toolkit.getDefaultToolkit();
	Image img, img2, img3;
	// AMember afriend;

	Font font1 = new Font("", Font.PLAIN, 20);
	Font font2 = new Font("", Font.PLAIN, 14);

	String[] url = { "./res/profile.png", "./res/chatting.png", "./res/settings.png" };
	String savePath = "c:/pating_img/";

	Socket client;
	BufferedReader buffr;
	BufferedWriter buffw;
	File file;
	int member_no;
	

	public Select(Socket client, int member_no) {// AMember friend) {//int member_no
		// this.afriend=friend;
		this.client =client;
	
		this.member_no=member_no;

		lb_me = new JLabel("a");// ""+friend.la_nickname.getText()+"");
		lb_chatting = new JLabel("채팅");
		lb_profileMgr = new JLabel("프로필 관리");
		lb_status = new JLabel("b");// ""+friend.la_statusMessage.getText()+"");

		lb_me.setHorizontalAlignment(SwingConstants.CENTER);
		lb_chatting.setHorizontalAlignment(SwingConstants.CENTER);
		lb_profileMgr.setHorizontalAlignment(SwingConstants.CENTER);

		p = new JPanel();
		p_me = new JPanel();
		p_chatting = new JPanel();
		p_profileMgr = new JPanel();
		p_status = new JPanel();
		p_south = new JPanel();
		p_north = new JPanel();

		lb_me.setFont(font1);
		lb_chatting.setFont(font2);
		lb_profileMgr.setFont(font2);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screen_w = screenSize.getWidth();
		double screen_h = screenSize.getHeight();
		int window_w = 400;
		int window_h = 700;
		int x = (int) screen_w - window_w - 300;
		int y = 300;

		img = kit.getImage(url[0]);
		p_me1 = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 10, 50, 150, 150, Color.ORANGE, getParent());// friend.img,
																				// 0,
																				// 0,180,180,
																				// Color.ORANGE,
																				// getParent());
			}
		};

		img2 = kit.getImage(url[1]);
		p_chatting1 = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img2, 10, 0, 100, 100, Color.ORANGE, getParent());
			}
		};

		img3 = kit.getImage(url[2]);
		p_profileMgr1 = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img3, 10, 0, 100, 100, Color.ORANGE, getParent());
			}
		};

		p_me.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				// System.out.println("클릭했어?");
				JFileChooser chooser = new JFileChooser("c:/");
				int result = chooser.showOpenDialog(getParent());
				byte[] b = new byte[1024];

				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						try {
							FileInputStream f_input = new FileInputStream(file = chooser.getSelectedFile());
							// buffr =new BufferedReader(new
							// InputStreamReader(client.getInputStream()));
							FileOutputStream f_output = new FileOutputStream(file.getName());
							buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
							String a = f_input.toString();

							StringBuffer sb = new StringBuffer();
							sb.append("{\"request\": \"imgchange\",");
							sb.append("\"m_photo\" :\"" + file.getName() + "\"");
							sb.append("\"member_no\" :\"" + member_no + "\"");
							sb.append("}");
							System.out.println(sb);

							/*if (!file.exists()) {
								// 디렉토리 생성 메서드
								file.mkdirs();
								System.out.println("created directory successfully!");
							}*/

							while (f_input.read(b) != -1) {
								// f_output.write(b);
								// f_output.flush();
								buffw.write(a);
							}
							buffw.write(sb.toString() + "\n");
							buffw.flush();

							String msg = buffr.readLine();
							JSONParser parser = new JSONParser();
							JSONObject object = (JSONObject) parser.parse(msg);

							if (object.get("result").equals("ok")) {
								JSONObject obj = (JSONObject) object.get("data");
								String m_photo = (String) obj.get("m_photo");

								JOptionPane.showMessageDialog(getParent(), m_photo + "로 수정 되었습니다..");

								// 채팅 창 띄우기!!!!!

								// setVisible(false);

							} else if (object.get("result").equals("fail")) {
								JOptionPane.showMessageDialog(getParent(), "사진 수정 실패 하였습니다.");
							}
						} catch (HeadlessException | ParseException e) {
							e.printStackTrace();
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		p_chatting1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("채팅 클릭했어?");
			}
		});

		p_profileMgr1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("뭘루 바꿀거야?");
			}
		});

		p.setLayout(new BorderLayout());
		p_me.setLayout(new BorderLayout());
		p_chatting.setLayout(new BorderLayout());
		p_profileMgr.setLayout(new BorderLayout());

		p_me.setPreferredSize(new Dimension(150, 250));
		p_chatting.setPreferredSize(new Dimension(125, 100));
		p_profileMgr.setPreferredSize(new Dimension(125, 100));
		p_south.setPreferredSize(new Dimension(300, 250));

		p_me.add(lb_me, BorderLayout.SOUTH);
		p_chatting.add(lb_chatting, BorderLayout.SOUTH);
		p_profileMgr.add(lb_profileMgr, BorderLayout.SOUTH);

		p_me.add(p_me1, BorderLayout.CENTER);
		p_chatting.add(p_chatting1, BorderLayout.CENTER);
		p_profileMgr.add(p_profileMgr1, BorderLayout.CENTER);

		p_north.add(p_me);
		p_south.add(p_chatting, BorderLayout.WEST);
		p_south.add(p_profileMgr, BorderLayout.EAST);
		p.add(p_north, BorderLayout.NORTH);
		p.add(p_south);

		p_me.setBackground(Color.ORANGE);
		p_chatting.setBackground(Color.ORANGE);
		p_profileMgr.setBackground(Color.ORANGE);
		p_south.setBackground(Color.ORANGE);
		p_north.setBackground(Color.ORANGE);
		add(p);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(300, 500));
		setBounds(x, y, 300, 500);
		setVisible(true);
	}

	/*public static void main(String[] args) {
		new Select();// null);
	}*/

}
