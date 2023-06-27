package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.SyainBean;


public class SyainDao {
	private Connection conn;
	
	// コンストラクター　DB接続を行う
	public SyainDao() throws ClassNotFoundException, SQLException {
		// DB接続情報
		String url = "jdbc:postgresql://localhost:5432/testdb10";
		String user = "postgres";
		String password = "●●●";	//PW要変更
		
		// DBに接続
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url, user, password);
	}
	
	// SQL操作 (ユーザーIDとPWを用いて社員が存在するかチェック)
	public SyainBean getByIdAndPassword(String user_id, String password ) throws SQLException {
		
		PreparedStatement pStmt = null;
		
		// SELECT文を準備
		String sql = "select * from syain where user_id = ? and password = ? and delete_flg = '0'";
		
		pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, user_id);
		pStmt.setString(2, password);

		ResultSet rs = pStmt.executeQuery();

		
		// 結果をBeanに置き換える
		SyainBean sb = new SyainBean();
		

		// 結果を表示
		while (rs.next()) {
			sb.setUser_id(rs.getString("user_id"));	
			sb.setPassword(rs.getString("password"));
			sb.setCreated_at(rs.getString("created_at"));
			sb.setUpdated_at(rs.getString("updated_at"));
			sb.setEmail(rs.getString("email"));
			sb.setDelete_flg(rs.getString("delete_flg"));
		}
		rs.close();
		pStmt.close();
		
		return sb;
	}
	public void close() {
		try {
			conn.close();
			
		} catch (SQLException e) {
			// TODO エラー処理を行うこと
			e.printStackTrace();
		}
	}

	public int insertSyainData(SyainBean sb) {
		return 0;	//insert文は登録されたデータ件数が結果となる
	}
	

}
