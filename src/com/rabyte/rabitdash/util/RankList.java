package com.rabyte.rabitdash.util;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

//为了凑课设的数据库要求强行弄出的排行榜
public class RankList {
    //字符串数组，存储信息
    Connection con;

    RankList(String url) {
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Failed to connect the database");
            JOptionPane.showMessageDialog(null,
                    "Failed to connect the database, cannot show the rank list");
        }
    }

    private static Connection getConn() {
        String driver = "org.mariadb.jdbc.Driver";
        String url = "jdbc:mariadb://localhost:3306/dmk";
        String username = "root";
        String password = "toor";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean insert(String name, int score) {
        if (name.length() > 3)
            name = name.substring(0, 3);
        Connection conn = getConn();
        String sql = "INSERT INTO dmk.ranklist (name, score) VALUES (?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, String.valueOf(score));
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static int update(String name, int score) {
        if (name.length() > 3)
            name = name.substring(0, 3);
        Connection conn = getConn();
        int i = 0;
        String sql = "update dmk.ranklist set score=" + score + " where name='" + name + "'";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("sucess result: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int delete(String name) {
        if (name.length() > 3)
            name = name.substring(0, 3);
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from dmk.ranklist where name='" + name + "'";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("sucess result: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static Vector<Vector<String>> getRankList() {

        Connection conn = getConn();
        String sql = "select * from dmk.ranklist ORDER BY score DESC ";
        ResultSet resultSet = null;
        PreparedStatement pstmt;

        Vector<Vector<String>> res = new Vector<>();
        try {
            pstmt = conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Vector<String> stringVector = new Vector<>();
                stringVector.add(resultSet.getString("name"));
                stringVector.add(resultSet.getString("score"));
                res.add(stringVector);
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


        return res;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
