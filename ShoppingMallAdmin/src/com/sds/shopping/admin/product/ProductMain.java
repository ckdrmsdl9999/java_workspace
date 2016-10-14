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
	// 서쪽 영역
	JPanel p_west;
	Choice ch_top, ch_sub;
	ImageIcon icon;
	JLabel la_img;
	JTextField t_productname, t_price, t_stock;
	JTextArea detail;
	JScrollPane scroll;
	JButton bt_regist;

	// 센터 영역
	JPanel p_center; // BorderLayout 이 되어야 함.
	JPanel p_north; // 프로모션 초이스 컴포넌트와 버튼이 추가할 예정
	Choice ch_promotion;
	JButton bt_send;

	JTable table;
	JScrollPane scroll2;
	ProductModel model;
	HashMap<String, Integer> topMap;// 상위 카테고리 정보를 담아둘 맵
	HashMap<String, Integer> subMap;// sub 카테고리 정보를 담아둘 맵
	HashMap<String, Integer> promotionMap;
	
	JFileChooser chooser;

	String savePath = "C:/product_img"; // 이미지 파일이 저장될 경로
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
		bt_regist = new JButton("등록");

		la_img.setPreferredSize(new Dimension(128, 128));
		// 숫자형 텍스트 필드의 정렬을 우측으로..
		t_price.setHorizontalAlignment(SwingConstants.RIGHT);
		t_stock.setHorizontalAlignment(SwingConstants.RIGHT);

		ch_top.add("카테고리 선택 ▼");
		ch_sub.add("카테고리 선택 ▼");

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

		// 센터 영역 구성
		p_center = new JPanel();
		p_north = new JPanel();
		ch_promotion = new Choice();
		bt_send = new JButton("프로모션으로 등록하기");

		p_center.setLayout(new BorderLayout());
		p_north.add(ch_promotion);
		p_north.add(bt_send);
		p_center.add(p_north, BorderLayout.NORTH);

		model = new ProductModel();
		table = new JTable(model); // 테이블 모델이 와야함!!
		scroll2 = new JScrollPane(table);
		p_center.add(scroll2);

		chooser = new JFileChooser("C:/Users/student/Downloads");
		add(p_center);

		this.setPreferredSize(new Dimension(AppMain.CONTENT_WIDTH, AppMain.CONTENT_HEIGHT));

		// 초이스 컴포넌트와 리스너 연결!!
		ch_top.addItemListener(this);

		// 등록 버튼과 리스너 연결!!
		bt_regist.addActionListener(this);
		bt_send.addActionListener(this);

		// 이미지와 리스너 연결!!!
		la_img.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// System.out.println("이미지 눌렀어?");
				int result = chooser.showOpenDialog(getParent());
				if (result == JFileChooser.APPROVE_OPTION) {
					// c드라이브의 공유폴더에 이미지를 복사하자!!!

					// 입력 스트림 생성
					try {

						fis = new FileInputStream(file = chooser.getSelectedFile());

						// 빈(empty) 파일 생성
						fos = new FileOutputStream(savePath + "/" + file.getName());

						byte[] b = new byte[1024];
					//	int data;
						while (fis.read(b) != -1) {
							fos.write(b);
							fos.flush();
						}
						JOptionPane.showMessageDialog(getParent(), "등록 완료");
						BufferedImage img = ImageIO.read(file);
						icon.setImage(img);
						la_img.updateUI();// 컴포넌트 갱신!!

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

		// 테이블과 마우스 리스너 연결
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				System.out.println(row + "," + col);
			}
		});
		// 테이블과 모델 리스너 연결
		table.getModel().addTableModelListener(this);

		topMap = new HashMap<String, Integer>();
		subMap = new HashMap<String, Integer>();
		promotionMap = new HashMap<String, Integer>();
		
		getTopCategory();
		getPromotionList();
	}

	// 최상위 카테고리 목록 가져오기!!!
	public void getTopCategory() {
		Connection con = AppMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from topcategory";
		// 쵸이스 컴퍼넌트에 데이터 채우기!!!!
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

	// 하위 카테고리 목록 가져오기!!!
	public void getSubCategory(int subcategory_id) {
		Connection con = AppMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from subcategory where topcategory_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, subcategory_id);
			rs = pstmt.executeQuery();
			// 기존거 초이스의 아이템 지우고, subMap 내용도 지우기!!!!
			ch_sub.removeAll();
			subMap.clear();

			// 하위 카테고리 초이스 컴포넌트 채우기!!
			ch_sub.add("카테고리 선택 ▼");
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

	// 상품 등록!!!
	public void regist() {
		Connection con = AppMain.getConnection();
		PreparedStatement pstmt = null;

		// 입력을 제대로 했는지 여부를 검사하자!!(=유효성 체크)
		// 왜? 데이터 베이스에 들어갈 데이터의 무결성 때문에
		if (ch_top.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "상위 카테고리를 선택해 주세요");
		} else if (ch_sub.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "하위 카테고리를 선택해 주세요");
		} else if (t_productname.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "상품명을 입력해주세요");
			t_productname.requestFocus();
		} else if (t_price.getText().equals("0")) {
			JOptionPane.showMessageDialog(this, "가격을 입력하세요");
			t_price.requestFocus();
		} else if (t_stock.getText().equals("0")) {
			JOptionPane.showMessageDialog(this, "재고량을 입력하세요");
			t_stock.requestFocus();
		} else if (detail.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "상세 설명을 입력하세요");
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
					JOptionPane.showMessageDialog(this, "DB에 추가완료");

					// 데이터베이스 조회하고 , 테이블 갱신!!!!!!!
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

	// 레코드 1건 수정
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
				JOptionPane.showMessageDialog(this, "수정완료");
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

	// 프로모션 목록 가져오기
	public void getPromotionList() {
		Connection con = AppMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from promotion";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// ch_promotion에 채우기
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

	// 프로 모션 상품 등록 하기!!
	public void registPromotionProduct() {

		Connection con = AppMain.getConnection();
		PreparedStatement pstmt = null;

		String sql = "insert into promotion_product(promotion_product_id,product_id,promotion_id)";
		sql = sql + "values(seq_promotion_product.nextval,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			int product_id=Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), 0));
			//어떤 상품을...
			pstmt.setInt(1, product_id);

			//어떤 프로모션에
			pstmt.setInt(2, promotionMap.get(ch_promotion.getSelectedItem()));
			
			int result=pstmt.executeUpdate();
			if(result != 0){
				JOptionPane.showMessageDialog(this, "상품코드"+product_id+"을"+ch_promotion.getSelectedItem()+"에 등록 완료");
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
		// 초이스의 아이템을 바꿀때, topcategory_id 출력!!
		getSubCategory(topMap.get(ch_top.getSelectedItem()));
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == bt_regist) {
			regist();
		} else if (obj == bt_send) {
			// 프로모션 등록 메서드 호출!!!
			registPromotionProduct(); 
		}

	}

	// 테이블의 데이터가 바뀌면 잡아내는 리스너
	public void tableChanged(TableModelEvent e) {
		System.out.println("테이블 정보 변경했어??");
		update();
	}
}
