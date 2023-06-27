package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.SyainBean;


public class SyainDao {
	private Connection conn;
	
	// �R���X�g���N�^�[�@DB�ڑ����s��
	public SyainDao() throws ClassNotFoundException, SQLException {
		// DB�ڑ����
		String url = "jdbc:postgresql://localhost:5432/testdb10";
		String user = "postgres";
		String password = "������";	//PW�v�ύX
		
		// DB�ɐڑ�
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url, user, password);
	}
	
	// SQL���� (���[�U�[ID��PW��p���ĎЈ������݂��邩�`�F�b�N)
	public SyainBean getByIdAndPassword(String user_id, String password ) throws SQLException {
		
		PreparedStatement pStmt = null;
		
		// SELECT��������
		String sql = "select * from syain where user_id = ? and password = ? and delete_flg = '0'";
		
		pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, user_id);
		pStmt.setString(2, password);

		ResultSet rs = pStmt.executeQuery();

		
		// ���ʂ�Bean�ɒu��������
		SyainBean sb = new SyainBean();
		

		// ���ʂ�\��
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
			// TODO �G���[�������s������
			e.printStackTrace();
		}
	}

	public int insertSyainData(SyainBean sb) {
		return 0;	//insert���͓o�^���ꂽ�f�[�^���������ʂƂȂ�
	}
	

}
