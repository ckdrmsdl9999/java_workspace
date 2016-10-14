package com.sds.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		// ������ ��ģ��!!(�������� ����Ŭ ���ӽ� ��Ʈ�� ������ �ʼ���.)
		// java.sql ��Ű������ ������ ���̽� ���� Ŭ������ �����Ѵ�.

		// ������ ���̽��� ����
		String url = "jdbc:oracle:thin:@localhost:1521:XE";// �ϱ� �ϱ� ip�ּ� �����ش�.
		String user = "java0819";
		String password = "java0819";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// � �����ͺ��̽� ��ǰ�� ������� �� ����̹���
		// ���� �ε��ؾ� �Ѵ�!!
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����");

			// ���ӽõ�
			// Connection �������̽��� ���� �õ���, ������ ��츸
			// �޸𸮿� �ö�´�.

			con = DriverManager.getConnection(url, user, password);
			if (con != null) {
				System.out.println("���� ����");

				// ���ϴ� ������ ����
				// ���� ���� �������̽� ����Ͽ� �������� ��Ʈ������ �����Ѵ�.
				String sql = "select * from topcategory";
				pstmt = con.prepareStatement(sql);

				// ���� ���� �޼���!!
				rs = pstmt.executeQuery();

				// ResultSet�� Ŀ����� �����͸� �����ϹǷ�, ���ϴ� ���ڵ忡 �����Ϸ���
				// Ŀ���� �Űܰ��� ����ؾ� �Ѵ�.
				rs.next();// 1row ����!!
				//String title = rs.getString("title");
				//System.out.println(title);
				
				  rs.next();
				  rs.next();
				  rs.next();
				  String title=rs.getString("title"); int
				  id=rs.getInt("topcategory_id");
				  System.out.println(id+","+title);
				 

			} else {
				System.out.println("���� ����");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("����̹� �ε� ����");

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
					if (con != null) {
						try {
							con.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

	}
}
