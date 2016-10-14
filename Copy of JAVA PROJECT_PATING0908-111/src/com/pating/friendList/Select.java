package com.pating.friendList;

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
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pating.login.Loginform;

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
		
	File file;	

	public Select(AMember amember) {// AMember friend) {//int member_no
		// this.afriend=friend;

		lb_me = new JLabel("a");// ""+friend.la_nickname.getText()+"");
		lb_chatting = new JLabel("√§ÔøΩÔøΩ");
		lb_profileMgr = new JLabel("ÔøΩÔøΩÔøΩÔøΩÔøΩÔøΩ ÔøΩÔøΩÔøΩÔøΩ");
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
		int y = amember.getLocation().y+185;	

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
				profileImg();
			}
		});
/*		this.client=Loginform.getconnect();
		System.out.println(client+"123");*/
		p_chatting1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("√§ÔøΩÔøΩ ≈¨ÔøΩÔøΩÔøΩﬂæÔøΩ?");
				
			}
		});

		p_profileMgr1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("ÔøΩÔøΩÔøΩÔøΩ ÔøΩŸ≤‹∞≈æÔøΩ?");
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

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(300, 500));
		setBounds(x, y, 300, 500);
		setVisible(true);
	}

	public void profileImg(){
		JFileChooser chooser = new JFileChooser("c:/");
		int result = chooser.showOpenDialog(getParent());
		
		if (result == JFileChooser.APPROVE_OPTION) {
			file=chooser.getSelectedFile();
			StringBuffer sb = new StringBuffer();
			sb.append("{\"request\": \"imgchange\",");
			sb.append("\"m_pic\" :\"" + file.getName() + "\",");
			sb.append("\"m_id\" :\"" + Loginform.id + "\"");
			sb.append("}");
			System.out.println(sb);
			try {
				TestMain_FriendList.buffw.write(sb.toString() + "\n");
				TestMain_FriendList.buffw.flush();

				String msg = TestMain_FriendList.buffr.readLine();
				JSONParser parser = new JSONParser();
				JSONObject object = (JSONObject) parser.parse(msg);

				if (object.get("result").equals("ok")) {
					JSONObject obj = (JSONObject) object.get("data");
					String m_photo = (String) obj.get("m_pic");

					JOptionPane.showMessageDialog(getParent(), m_photo + "Î°? ?àò?†ï ?êò?óà?äµ?ãà?ã§..");
				}
			
				FileThread fileThread =new FileThread(file);
				fileThread.start();
				
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	}
	/*public static void main(String[] args) {
		new Select();// null);
	}*/

}
