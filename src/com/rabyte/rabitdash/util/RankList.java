package com.rabyte.rabitdash.util;

import org.mariadb.jdbc.*;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//为了凑课设的数据库要求强行弄出的排行榜
public class RankList {
    //字符串数组，存储信息
    String[] list;
    String dataBaseUrl;
    String username = "root";
    String password = "toor";
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

    @Override
    public String toString() {
        return super.toString();
    }
}
