package com.pating.chattingList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pating.friendList.TestMain_FriendList;

public class FrameFriendInvitation extends JFrame implements ActionListener {
	String TAG=this.getClass().getName();
	PanelChattingList panelChattingList;
	//����
	JButton bt_invitation;
	
	//����
	JScrollPane scroll;
	JPanel p_center;
	JPanel p_friendList;
	URL url;
	JButton bt_confirm;
	JTextField t_rTitle;
		
	ArrayList<Long> list_friendsNo;							//ģ�����
	ArrayList<Long> list_friendsSelectedNo;			//��ȭ�� �߰��� ���
	ArrayList<PanelItem> list_friends;							//PanelItem
	ArrayList<JCheckBox> list_check;
	int count_f;
	
	long me_no;			//���� ������ member_no;
	boolean access=true;
	long[] memberAccessList;
	
	public FrameFriendInvitation (long me_no,long[] memberAccessList, PanelChattingList panelChattingList){
		this.panelChattingList=panelChattingList;
		
		this.memberAccessList = memberAccessList;
		this.me_no =me_no;
		setLayout(new BorderLayout());
		setBackground(new Color(255, 204, 51));
		bt_invitation=new JButton("    < 대화상대 초대");
		bt_invitation.setHorizontalAlignment(SwingConstants.LEFT);
		bt_invitation.setPreferredSize(new Dimension(400, 30));
		bt_invitation.setOpaque(true);
		bt_invitation.setBackground(new Color(102, 153, 0));
		bt_invitation.setForeground(Color.white);
		bt_confirm=new JButton("확인");
		bt_confirm.addActionListener(this);
		t_rTitle=new JTextField("방 제목을 입력하세요.", 20);
		t_rTitle.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				t_rTitle.setText("");
			}
		});
		
		//center
		p_center=new JPanel(new BorderLayout());
		p_center.setBackground(new Color(255, 204, 51));
		p_center.setBorder(new EmptyBorder(0, 0, 0, 0));
		p_center.setPreferredSize(new Dimension(400, 700));
		p_friendList=new JPanel();
		p_friendList.setLayout(new FlowLayout());
		p_friendList.setBorder(new EmptyBorder(0, 0, 0, 0));
		p_friendList.setBackground(new Color(255, 204, 51));
		scroll = new JScrollPane(p_friendList);
		
		add(bt_invitation, BorderLayout.NORTH);
			
		add(p_center, BorderLayout.CENTER);
		p_center.add(scroll);
		
		//---------------------------------------------------------------------------------------������ ��
		list_friendsNo=new ArrayList<Long>();
		list_friendsSelectedNo=new ArrayList<Long>();
		list_friends=new ArrayList<PanelItem>();
		list_check=new ArrayList<JCheckBox>();
		
		getList();
		
		count_f=(list_friends.size()*56) + 100;
		
		p_friendList.setPreferredSize(new Dimension(350, count_f));
		//---------------------------------------------------------------------------------------������ ����
	
		setBounds(400, 0, 400, 700);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(400, 700));
		setVisible(true);
	}
	
	public void getList(){
		
		try {				
			StringBuffer sb = new StringBuffer();
			sb.delete(0, sb.length());
			
			sb.append("{");
			sb.append("\"request\" : \"friendList\",");
			sb.append(" \"member_id\" : "+me_no+"");
			sb.append("}");
			
			System.out.println(sb.toString());
			TestMain_FriendList.buffw.write(sb.toString()+"\n");
			TestMain_FriendList.buffw.flush();
			
			//�������� ģ����� �ҷ��ͼ� ���ݺ��� �ѷ��ش�.
			String data=TestMain_FriendList.buffr.readLine();
			System.out.println(data);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject=(JSONObject)jsonParser.parse(data);
			String result = (String)jsonObject.get("result");
			JSONArray jsonArray=(JSONArray)jsonObject.get("data");
			System.out.println(jsonArray.size());
			
			if(result.equals("ok")){				
				for(int i=0;i<jsonArray.size();i++){
					JSONObject obj=(JSONObject)jsonArray.get(i);
					System.out.println("여기야여기"+obj);
					long you=(long)obj.get("member_id");
					String m_nickname=(String)obj.get("nickname");
					String m_pic=(String)obj.get("pic");
					
					for(int j=0; j<memberAccessList.length; j++){
						if(you==memberAccessList[j]){		access=true; 	break;	}
						else{		access=false;		}
					}
					
					list_friends.add(new PanelItem(you, m_nickname, m_pic, access));
					p_friendList.add(list_friends.get(i));
					
				}			
				
			}
			
			p_friendList.add(t_rTitle);
			p_friendList.add(bt_confirm);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource() == bt_confirm){
			if(t_rTitle.getText() == null){
				JOptionPane.showMessageDialog(this, "방 이름을 입력하세요!");
			}
			else{
				inputListFriendsSelectedNo(); //여기서 호출했기 떄문에 list_friendsSelectedNo 밑에서 사용 가능.
				//sendSelectedMember();	
				StringBuffer sb=new StringBuffer();
				sb.delete(0, sb.length());
				String r_title=t_rTitle.getText();
				
				sb.append("{");
				sb.append("\"request\" : \"addChattingRoom\",");
				sb.append(" \"member_id\" : "+me_no+",");
				sb.append("\"roommateNo\" : [");
				for(int i=0;i<list_friendsSelectedNo.size();i++){
					sb.append("{");
					sb.append("\"memberNo\" : "+list_friendsSelectedNo.get(i)+"");
					if(i==list_friendsSelectedNo.size()-1){
						sb.append("}");
					}else{
						sb.append("},");						
					}
				}
				sb.append("],");
				sb.append(" \"r_title\" : \""+r_title+"\"");
				sb.append("}");
				//System.out.println(sb.toString());
				
				try {
					TestMain_FriendList.buffw.write(sb.toString()+"\n");
					TestMain_FriendList.buffw.flush();
					
					String msg = TestMain_FriendList.buffr.readLine();
					
					JSONParser parser = new JSONParser();
					try {
						JSONObject obj= (JSONObject)parser.parse(msg);
						if(obj.get("result").equals("ok")){
							panelChattingList.getList();
							panelChattingList.updateUI();
							dispose();	
						}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			
			}
			
			
		}
	}
	
	public void inputListFriendsSelectedNo() {
		JCheckBox cb=null;
		long m_noSelected;
		for(int i=0; i<list_friends.size(); i++){
			cb=list_friends.get(i).getCheckBox();
			if(cb.isSelected()){
				m_noSelected=(long)list_friends.get(i).isChecked();
				System.out.println(m_noSelected+"가 선택되었음.");
				list_friendsSelectedNo.add(m_noSelected);		
				System.out.println(list_friendsSelectedNo.get(i)+"제발 나와주");
			}
		}
	
	}
	
	public ArrayList<Long> sendSelectedMember() {
		return list_friendsSelectedNo;

	}	
}
