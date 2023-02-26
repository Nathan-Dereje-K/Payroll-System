package com.example.demo1;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbcon {
    private static final String url = "jdbc:mysql://localhost:3306/payroll";
    private static final String user = "root";
    private static final String password = "";

    public static Connection connMethod() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            //JOptionPane.showMessageDialog(null, "connected");

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Error: " + ex);
        }
        return con;
    }
    public static void main(String[] args) throws ClassNotFoundException {
        Connection conn = connMethod();
        if (conn == null){
            System.out.println("not connected...");
        }
        else{
            System.out.println("connected...");

        }
    }
}
