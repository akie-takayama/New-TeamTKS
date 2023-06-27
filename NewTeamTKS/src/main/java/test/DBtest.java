package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBtest {

    public static void main(String[] args) throws Exception {

        String url = "jdbc:postgresql://localhost:5432/testdb10";
        String user = "postgres";
        String password = "akie1127";


        // データベースに接続
        try(Connection conn = DriverManager.getConnection(url,user,password)) {

            //SELECT文を準備
            String sql = "SELECT * FROM syain";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            ResultSet rs = pStmt.executeQuery();
            
            System.out.println("【接続開始。データベース名：testdb10　テーブル名：syain】");

            //結果を表示
            while (rs.next()) {
                String id = rs.getString("user_id");
                String email = rs.getString("email");
                String pass = rs.getString("password");

                System.out.println("ID:" + id + "  " + "ML:" + email + "  " +"PW:" +  pass);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }}
   
    };