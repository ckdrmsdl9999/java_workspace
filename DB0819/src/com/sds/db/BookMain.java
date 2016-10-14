package com.sds.db;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class BookMain extends JFrame implements ItemListener,ActionListener{
	JPanel p_west; // ���� �Է����� ����� �г�
	Choice ch_top, ch_sub; // �� ���� ī�װ� ���
	JTextField t_bookname, t_publisher, t_author, t_price;
	JButton bt_regist;
	
	
	// ���� ����
	JPanel p_north;
	Choice ch_category;
	JTextField t_keyword;
	JButton bt_search;
	JButton bt_delete;

	// ���� ����
	MyModel model;
	JScrollPane scroll;
	JTable table;

	// �������� ���� (jdbc=java database connectivity)
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "java0819";
	String password = "java0819";
	
	//jdbc���� ��ü �� ���� ��, �� ������ ���� ��ü
	//���� ������ �������� �� ��ü�� �̿��ؾ� �Ѵ�.!!!
	Connection con;
	
	//������ ���� ��ü!! �������̽� �̹Ƿ� new �Ұ�!!
	//������ �Ǿ�� �������� ���� �� �� �����Ƿ� con���� ���� ���;� ��.
	PreparedStatement pstmt;
	
	//������ ���� �� ���ڵ尡 ��ȯ�Ǵ� ���, �� select���� ����� 
	//��� ������ ��� ��Ȱ�� �����ϴ� �������̽�!!
	ResultSet rs;
	
	//���� ī�װ��� id ���� �����س��� �÷���!!
	ArrayList<Integer> subcategory_id =new ArrayList<Integer>();
	int book_id;//������ ������ ���̺��� book_id
	
	public BookMain() {
		p_west = new JPanel();
		ch_top = new Choice();
		ch_sub = new Choice();
		t_bookname = new JTextField(10);
		t_publisher = new JTextField(10);
		t_author = new JTextField(10);
		t_price = new JTextField("0",10);
		bt_regist = new JButton("���");
		bt_delete= new JButton("����");
		
		p_north = new JPanel();
		ch_category = new Choice();
		t_keyword = new JTextField(30);
		bt_search = new JButton("�˻�");
		
		//���� �õ��ϱ� 
		connect();
		table = new JTable(model= new MyModel(con));
		scroll = new JScrollPane(table);
		
		//���̺�� ���콺 �����ʿ��� ����
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row=table.getSelectedRow();
				//int col=table.getSelectedColumn();
				String value=(String)table.getValueAt(row, 0);
				
				book_id=Integer.parseInt(value);
				
				for(int col=0;col<model.columTitle.length;col++){
					String value1=(String)table.getValueAt(row, col);
					switch(col){
					case 2 : t_bookname.setText(value1);;break;
					case 3 : t_publisher.setText(value1);break;
					case 4 : t_author.setText(value1);break;
					case 5 : t_price.setText(value1);break;
					
					}
				}
						
				System.out.println(value+"Ŭ���߾�?");
				
			}
		});

		ch_top.setPreferredSize(new Dimension(100, 30));
		ch_sub.setPreferredSize(new Dimension(100, 30));

		ch_top.add("���á�");
		ch_sub.add("���á�");

		p_west.add(ch_top);
		p_west.add(ch_sub);
		p_west.add(t_bookname);
		p_west.add(t_publisher);
		p_west.add(t_author);
		p_west.add(t_price);
		p_west.add(bt_regist);
		p_west.add(bt_delete);
		p_west.setBackground(Color.GREEN);
		p_west.setPreferredSize(new Dimension(120, 600));
		add(p_west, BorderLayout.WEST);

		p_north.add(ch_category);
		p_north.add(t_keyword);
		p_north.add(bt_search);
		p_north.setBackground(Color.CYAN);

		ch_category.add("������");
		ch_category.add("���ǻ�");
		ch_category.add("��   ��");
		ch_category.setPreferredSize(new Dimension(100, 30));

		add(p_north, BorderLayout.NORTH);

		add(scroll);
		
		//���� ī�װ� ���̽��� ������ ����
		ch_top.addItemListener(this);
		bt_delete.addActionListener(this);
		
		//������� ������ ������ ����
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				//db�ݱ�!!!!!!
				if(con != null){
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				//���μ��� ���̱�!!!!
				System.exit(-1);
			}
		});
		
		//��ư�� ������ ����
		bt_regist.addActionListener(this);

		//setDefaultCloseOperation(EXIT_ON_CLOSE);//���μ����� ���̰� db�� ������ �ʱ� ������
																		//���� �ȵ�
		setVisible(true);
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		
		// ���� ��� ��������
		getTopCategory();
	}

	//�����ͺ��̽� �����ϱ�!!(���α׷��� �����ɶ� ������ �̷������ �ϱ⶧��)
	public void connect(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// JOptionPane.showMessageDialog(this, "����̹��ε� ����");
			this.setTitle("����̹��ε� ����");
			// ����
			con=DriverManager.getConnection(url, user, password);
			if(con == null){
				JOptionPane.showMessageDialog(this, "���ӽ���");
				return;
			}
			//JOptionPane.showMessageDialog(this, "���� ����");
			this.setTitle("���Ӽ���");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ���� ī�װ� ��������!!!!
	public void getTopCategory() {
		// ����̹� �ε�!!
		try {
			//���� ī�װ� ��������
			String sql = "select * from topcategory";
			pstmt=con.prepareStatement(sql);
			
			//���� �� ����!!!
			rs=pstmt.executeQuery();
			
			//���̽� ������Ʈ�� �˸´� ������ ä���!!
			while(rs.next()){
				ch_top.add(rs.getString("title"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	//���� ī�װ� ��� ��������
	public void getSubCategory(String title){
		//JOptionPane.showMessageDialog(this, "�ٲ��?");
		StringBuffer sql=new StringBuffer();
		
		sql.append("select * from subcategory");
		sql.append(" where topcategory_id=(");
		sql.append("select topcategory_id from");
		sql.append(" topcategory where title='"+title+"'");
		sql.append(")");
		//System.out.println(sql.toString());
		
		//���� ����
		try {
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();

			//������ ����� �ֱ�!!
			ch_sub.removeAll();
			subcategory_id.removeAll(subcategory_id);
			ch_sub.add("���á�");
			while(rs.next()){
				ch_sub.add(rs.getString("title"));
				subcategory_id.add(rs.getInt("subcategory_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	public void itemStateChanged(ItemEvent e) {
		Choice ch=(Choice)e.getSource();
		getSubCategory(ch.getSelectedItem());
	}

	//book ���̺� ���ڵ� �Ѱ� �߰� �ϱ�
	public void registBook(){
		
		//���� ������ ���� ī�װ��� id ���� �˾ƺ���
		int selectedIndex=ch_sub.getSelectedIndex();
		int sub_id=subcategory_id.get(selectedIndex-1);
		
		JOptionPane.showMessageDialog(this, sub_id);
		
		String book_name=t_bookname.getText();
		String publisher=t_publisher.getText();
		String author=t_author.getText();
		int price=Integer.parseInt(t_price.getText());
		
		//String sql="insert into book(book_id,subcategory_id,bookname,publisher,author,price)";
		//sql=sql+"values(seq_book.nextval,"+sub_id+", '"+book_name+"' , '"+publisher+"' , '"+author+"' ,"+ price+")";
		
		String sql="insert into book(book_id,subcategory_id,bookname,publisher,author,price)";
		sql=sql+"values(seq_book.nextval,?,?,?,?,?)";
		
		System.out.println(sql);
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, sub_id);
			pstmt.setString(2, book_name);
			pstmt.setString(3, publisher);
			pstmt.setString(4, author);
			pstmt.setInt(5, price);
			
			//��ȯ���� ������ ����???
			//�� �������� ���� ������� ���ڵ� ���� ��ȯ�ȴ�!!!
			//����  insert ������ ������ 1�� ��ȯ update: 1�̻�
			//delete : 1�̻�,  ���н� 0�� ��ȯ
			int result=pstmt.executeUpdate();
			if(result !=0 ){
				JOptionPane.showMessageDialog(this, "��� ����");
				
				//JTable ����!!
				model.selectAll();
				table.updateUI();
				model.fireTableDataChanged();
			}else{
				JOptionPane.showMessageDialog(this,"��� ����");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();

		if(obj==bt_regist){
			registBook();
		}else if(obj==bt_search){
			
		}else if(obj==bt_delete){
			int result=JOptionPane.showConfirmDialog(this, "���� ����ž�?");
			if(result==JOptionPane.OK_OPTION){
				if(book_id==0){
					JOptionPane.showInternalMessageDialog(this, "������ å�� �����ϴ�.");
					return;
				}
				if(model.deleteBook(book_id)!=0){
					JOptionPane.showMessageDialog(this, "��������");
					model.selectAll();
					model.fireTableDataChanged();
					table.updateUI();
				}else{
					JOptionPane.showMessageDialog(this, "��������");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new BookMain();
	}

}
