package com.sds.json;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GUIProfile extends JFrame{
	JPanel p_west;
	JButton bt;
	JPanel p_center;
	JScrollPane scroll;
	JFileChooser chooser;
	FileReader reader;
	
	
	public GUIProfile() {
		p_west =  new JPanel();
		bt = new JButton("열기");
		p_center = new JPanel();
		scroll = new JScrollPane(p_center);
		
		p_west.setPreferredSize(new Dimension(100, 600));
		p_west.add(bt);
		
		//p_center.setBackground(Color.YELLOW);
		p_center.setPreferredSize(new Dimension(700, 120*10));
				
		add(p_west, BorderLayout.WEST);
		add(scroll);
		
		chooser  = new JFileChooser("C:/java_workspace/GUI0811/res");
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
	}
	public void openFile(){
		int result=chooser.showOpenDialog(this);
		
		if(result == JFileChooser.APPROVE_OPTION){
			File file=chooser.getSelectedFile();
			try {
				reader  = new FileReader(file);
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject=(JSONObject)jsonParser.parse(reader);
				JSONArray jsonArray=(JSONArray)jsonObject.get("members");
				
				for(int i=0;i<jsonArray.size();i++){
					JSONObject member=(JSONObject)jsonArray.get(i);
					
					Profile profile = new Profile((String)member.get("photo"));
					profile.la_name.setText("이름:"+member.get("name"));
					profile.la_gender.setText("성별:"+member.get("gender"));
					profile.la_age.setText("나이:"+member.get("age"));
					
					p_center.add(profile);
				}
				p_center.updateUI();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public static void main(String[] args) {
		new GUIProfile();
	}

}







