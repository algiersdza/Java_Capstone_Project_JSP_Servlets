package com.group18.capstone.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDao {
    private static String url = null;
    private static Connection conn = null;
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/user?useSSL=false";
            conn = DriverManager.getConnection(url,"root","CST2355Database");
        }   catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}
