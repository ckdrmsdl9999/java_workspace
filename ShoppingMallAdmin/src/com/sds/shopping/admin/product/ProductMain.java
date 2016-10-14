package com.sds.shopping.admin.product;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.sds.shopping.admin.main.AppMain;

public class ProductMain extends JPanel implements ItemListener, ActionListener, TableModelListener {
	// ���� ����
	JPanel p_west;
	Choice ch_top, ch_sub;
	ImageIcon icon;
	JLabel la_img;
	JTextField t_productname, t_price, t_stock;
	JTextArea detail;
	JScrollPane scroll;
	JButton bt_regist;

	// ���� ����
	JPanel p_center; // BorderLayout �� �Ǿ�� ��.
	JPanel p_north; // ���θ�� ���̽� ������Ʈ�� ��ư�� �߰��� ����
	Choice ch_promotion;
	JButton bt_send;

	JTable table;
	JScrollPane scroll2;
	ProductModel model;
	HashMap<String, Integer> topMap;// ���� ī�װ� ������ ��Ƶ� ��
	HashMap<String, Integer> subMap;// sub ī�װ� ������ ��Ƶ� ��
	HashMap<String, Integer> promotionMap;
	
	JFileChooser chooser;

	String savePath = "C:/product_img"; // �̹��� ������ ����� ���
	FileInputStream fis;
	FileOutputStream fos;
	File file;

	public ProductMain() {
		p_west = new JPanel();
		ch_top = new Choice();
		ch_sub = new Choice();
		icon = new ImageIcon(this.getClass().getClassLoader().getResource("product_default.png"));
		la_img = new JLabel(icon);
		t_productname = new JTextField(10);
		t_price = new JTextField("0", 10);
		t_stock = new JTextField("0", 10);
		detail = new JTextArea(8, 12);
		scroll = new JScrollPane(detail);
		bt_regist = new JButton("���");

		la_img.setPreferredSize(new Dimension(128, 128));
		// ������ �ؽ�Ʈ �ʵ��� ������ ��������..
		t_price.setHorizontalAlignment(SwingConstants.RIGHT);
		t_stock.setHorizontalAlignment(SwingConstants.RIGHT);

		ch_top.add("ī�װ� ���� ��");
		ch_sub.add("ī�װ� ���� ��");

		this.setLayout(new BorderLayout());

		p_west.add(ch_top);
		p_west.add(ch_sub);
		p_west.add(la_img);
		p_west.add(t_productname);
		p_west.add(t_price);
		p_west.add(t_stock);
		p_west.add(scroll);
		p_west.add(bt_regist);
		p_west.setPreferredSize(new Dimension(150, 600));

		add(p_west, BorderLayout.WEST);
		// add(scroll2);

		// ���� ���� ����
		p_center = new JPanel();
		p_north = new JPanel();
		ch_promotion = new Choice();
		bt_send = new JButton("���θ������ ����ϱ�");

		p_center.setLayout(new BorderLayout());
		p_north.add(ch_promotion);
		p_north.add(bt_send);
		p_center.add(p_north, BorderLayout.NORTH);

		model = new ProductModel();
		table = new JTable(model); // ���̺� ���� �;���!!
		scroll2 = new JScrollPane(table);
		p_center.add(scroll2);

		chooser = new JFileChooser("C:/Users/student/Downloads");
		add(p_center);

		this.setPreferredSize(new Dimension(AppMain.CONTENT_WIDTH, AppMain.CONTENT_HEIGHT));

		// ���̽� ������Ʈ�� ������ ����!!
		ch_top.addItemListener(this);

		// ��� ��ư�� ������ ����!!
		bt_regist.addActionListener(this);
		bt_send.addActionListener(this);

		// �̹����� ������ ����!!!
		la_img.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// System.out.println("�̹��� ������?");
				int result = chooser.showOpenDialog(getParent());
				if (result == JFileChooser.APPROVE_OPTION) {
					// c����̺��� ���������� �̹����� ��������!!!

					// �Է� ��Ʈ�� ����
					try {

						fis = new FileInputStream(file = chooser.getSelectedFile());

						// ��(empty) ���� ����
						fos = new FileOutputStream(savePath + "/" + file.getName());

						byte[] b = new byte[1024];
					//	int data;
						while (fis.read(b) != -1) {
							fos.write(b);
							fos.flush();
						}
						JOptionPane.showMessageDialog(getParent(), "��� �Ϸ�");
						BufferedImage img = ImageIO.read(file);
						icon.setImage(img);
						la_img.updateUI();// ������Ʈ ����!!

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						if (fos != null) {
							try {
								fos.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						if (fis != null) {
							try {
								fis.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});

		// ���̺�� ���콺 ������ ����
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				System.out.println(row + "," + col);
			}
		});
		// ���̺�� �� ������ ����
		table.getModel().addTableModelListener(this);

		topMap = new HashMap<String, Integer>();
		subMap = new HashMap<String, Integer>();
		promotionMap = new HashMap<String, Integer>();
		
		getTopCategory();
		getPromotionList();
	}

	// �ֻ��� ī�װ� ��� ��������!!!
	public void getTopCategory() {
		Connection con = AppMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from topcategory";
		// ���̽� ���۳�Ʈ�� ������ ä���!!!!
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ch_top.add(rs.getString("title"));
				topMap.put(rs.getString("title"), rs.getInt("topcategory_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

	}

	// ���� ī�װ� ��� ��������!!!
	public void getSubCategory(int subcategory_id) {
		Connection con = AppMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from subcategory where topcategory_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, subcategory_id);
			rs = pstmt.executeQuery();
			// ������ ���̽��� ������ �����, subMap ���뵵 �����!!!!
			ch_sub.removeAll();
			subMap.clear();

			// ���� ī�װ� ���̽� ������Ʈ ä���!!
			ch_sub.add("ī�װ� ���� ��");
			while (rs.next()) {
				ch_sub.add(rs.getString("title"));
				subMap.put(rs.getString("title"), rs.getInt("subcategory_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// ��ǰ ���!!!
	public void regist() {
		Connection con = AppMain.getConnection();
		PreparedStatement pstmt = null;

		// �Է��� ����� �ߴ��� ���θ� �˻�����!!(=��ȿ�� üũ)
		// ��? ������ ���̽��� �� �������� ���Ἲ ������
		if (ch_top.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "���� ī�װ��� ������ �ּ���");
		} else if (ch_sub.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "���� ī�װ��� ������ �ּ���");
		} else if (t_productname.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "��ǰ���� �Է����ּ���");
			t_productname.requestFocus();
		} else if (t_price.getText().equals("0")) {
			JOptionPane.showMessageDialog(this, "������ �Է��ϼ���");
			t_price.requestFocus();
		} else if (t_stock.getText().equals("0")) {
			JOptionPane.showMessageDialog(this, "����� �Է��ϼ���");
			t_stock.requestFocus();
		} else if (detail.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "�� ������ �Է��ϼ���");
			detail.requestFocus();
		} else {
			String sql = "insert into product(product_id,subcategory_id,product_name,";
			sql = sql + "price,stock,img,detail)values(seq_product.nextval,?,?,?,?,?,?) ";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, subMap.get(ch_sub.getSelectedItem()));
				pstmt.setString(2, t_productname.getText());
				pstmt.setInt(3, Integer.parseInt(t_price.getText()));
				pstmt.setInt(4, Integer.parseInt(t_stock.getText()));
				pstmt.setString(5, file.getName());
				pstmt.setString(6, detail.getText());

				int result = pstmt.executeUpdate();
				if (result != 0) {
					JOptionPane.showMessageDialog(this, "DB�� �߰��Ϸ�");

					// �����ͺ��̽� ��ȸ�ϰ� , ���̺� ����!!!!!!!
					model.selectAll();
					model.fireTableDataChanged();
					table.updateUI();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	// ���ڵ� 1�� ����
	public void update() {
		int row = table.getSelectedRow();
		int col = 0;
		//String product_id = (String) table.getValueAt(row, col);
		Connection con = AppMain.getConnection();
		PreparedStatement pstmt = null;

		String sql = "update product set product_name=?,price=?,stock=?,detail=?";
		sql = sql + "where product_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, (String) table.getValueAt(row, 2));
			pstmt.setInt(2, Integer.parseInt((String) table.getValueAt(row, 3)));
			pstmt.setInt(3, Integer.parseInt((String) table.getValueAt(row, 4)));
			pstmt.setString(4, (String) table.getValueAt(row, 5));
			pstmt.setInt(5, Integer.parseInt((String) table.getValueAt(row, col)));

			int result = pstmt.executeUpdate();
			if (result != 0) {
				JOptionPane.showMessageDialog(this, "�����Ϸ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}

	// ���θ�� ��� ��������
	public void getPromotionList() {
		Connection con = AppMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from promotion";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// ch_promotion�� ä���
			while (rs.next()) {
				ch_promotion.add(rs.getString("title"));
				promotionMap.put(rs.getString("title"), rs.getInt("promotion_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			}
		}
	}

	// ���� ��� ��ǰ ��� �ϱ�!!
	public void registPromotionProduct() {

		Connection con = AppMain.getConnection();
		PreparedStatement pstmt = null;

		String sql = "insert into promotion_product(promotion_product_id,product_id,promotion_id)";
		sql = sql + "values(seq_promotion_product.nextval,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			int product_id=Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), 0));
			//� ��ǰ��...
			pstmt.setInt(1, product_id);

			//� ���θ�ǿ�
			pstmt.setInt(2, promotionMap.get(ch_promotion.getSelectedItem()));
			
			int result=pstmt.executeUpdate();
			if(result != 0){
				JOptionPane.showMessageDialog(this, "��ǰ�ڵ�"+product_id+"��"+ch_promotion.getSelectedItem()+"�� ��� �Ϸ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt !=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void itemStateChanged(ItemEvent e) {
		// ���̽��� �������� �ٲܶ�, topcategory_id ���!!
		getSubCategory(topMap.get(ch_top.getSelectedItem()));
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == bt_regist) {
			regist();
		} else if (obj == bt_send) {
			// ���θ�� ��� �޼��� ȣ��!!!
			registPromotionProduct(); 
		}

	}

	// ���̺��� �����Ͱ� �ٲ�� ��Ƴ��� ������
	public void tableChanged(TableModelEvent e) {
		System.out.println("���̺� ���� �����߾�??");
		update();
	}
}
