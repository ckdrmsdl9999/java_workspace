package com.sds.movie.list;

import javax.swing.table.AbstractTableModel;

public class MovieModel extends AbstractTableModel{
	String[] columnName={
			"movie_id",
			"영화제목",
			"genre_id",
			"개봉일",
			"상영시간"
	};
	
	public int getColumnCount() {
		return columnName.length;
	}
	public String getColumnName(int col) {
		return columnName[col];
	}
	public int getRowCount(){
		return 0;
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
	 
	//영화목록 가져오기 
	public void selectAll(){
		String sql="select m.genre_id, title, movie_id, openday,showtime from movie m, genre g";
		sql=sql+" where m.genre_id=g.genre_id";
	}
	
}












