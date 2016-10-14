/*
 * 오직 오라클 데이터베이스에 대한 쿼리문 수행 담당객체!!
 * */

package com.sds.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OracleManager {

	Connection con;
	PreparedStatement pstmt;
	
	public OracleManager(Connection con) {
		this.con=con;
	}
	//모든 레코드 조회
	public void selectAll(){
		String sql="select * from member2";
		try {
			pstmt=con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
