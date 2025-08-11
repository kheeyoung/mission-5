package com.ll.repository;

import com.ll.entity.WiseSaying;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

public class WiseSayingRepository {
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

    public List<WiseSaying> getList(int i) {
        try {
            String sql = "SELECT * FROM wise_saying ORDER BY id ASC LIMIT " + (i - 1) * 5 + ", 5";
            var rs = stmt.executeQuery(sql);
            List<WiseSaying> list = new java.util.ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String quote = rs.getString("quote");
                String writer = rs.getString("writer");
                list.add(new WiseSaying(id, quote, writer));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("DB 조회 실패: " + e.getMessage());
            return List.of();
        }
    }

    public List<WiseSaying> searchList(HashMap<String, String> keys, int i) {
        try{
            StringBuilder sql = new StringBuilder("SELECT * FROM wise_saying WHERE ");
            for (String key : keys.keySet()) {
                sql.append(key).append(" LIKE '%").append(keys.get(key)).append("%' AND ");
            }
            sql.setLength(sql.length() - 5); // 마지막 AND 제거
            sql.append(" ORDER BY id ASC LIMIT ").append((i - 1) * 5).append(", 5");

            var rs = stmt.executeQuery(sql.toString());
            List<WiseSaying> list = new java.util.ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String quote = rs.getString("quote");
                String writer = rs.getString("writer");
                list.add(new WiseSaying(id, quote, writer));
            }
            return list;
        } catch (SQLException e) {
            return List.of();
        }
    }

    public int delete(int i) {
        try{
            String sql = "DELETE FROM wise_saying WHERE id = " + i;
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            return -1;
        }
    }
}