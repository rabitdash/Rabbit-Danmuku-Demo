package com.rabyte.rabitdash.util;

import org.mariadb.jdbc.*;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Ϊ�˴տ�������ݿ�Ҫ��ǿ��Ū�������а�
public class RankList {
    //�ַ������飬�洢��Ϣ
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
