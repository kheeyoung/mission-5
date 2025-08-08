package com.ll;

import com.ll.entity.WiseSaying;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class WiseSayingRepository {
    public static Statement stmt;
    private Connection con;

    public int connectDB() {
        con = null;
        String server = "localhost:3306"; //
        String database = "wiseSaying"; //
        String user_name = "root"; //
        String password = "talent0103"; //

        try {
            String url = "jdbc:mysql://" + server + "/" + database +
                    "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            con = DriverManager.getConnection(url, user_name, password);
            if (con == null) {
                return -1;
            }
            stmt = con.createStatement();
            return 0;

        } catch (SQLException e) {
            return -1;
        }
    }

    public int disConnectDB() {
        if (con != null || stmt != null) {
            return -1;
        }
        try {

            con.close();
            stmt.close();
            return 0;

        } catch (Exception e) {
            return -1;
        }
    }

    public int getNum() {
        try {
            String sql = "SELECT MAX(id) FROM wise_saying";
            var rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("DB 연결 실패: " + e.getMessage());
        }
        return 0; // 기본값
    }

    public int insert(WiseSaying ws) {
        try {
            String sql = "INSERT INTO wise_saying (id, quote, writer) VALUES (" + ws.getId() + ", '" + ws.getQuote() + "', '" + ws.getWriter() + "')";
            stmt.executeUpdate(sql);
            return ws.getId();
        } catch (SQLException e) {
           return -1;
        }
    }
}