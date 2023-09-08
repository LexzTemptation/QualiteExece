/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;

public class ConecctionMySQL {

    private String dbName = "ferreteria";
    private String user = "root";
    private String password = "root";
    private String url = "jdbc:mysql://127.0.0.1:3306/" + dbName;

    Connection conn = null;

    public Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, password);

            return conn;
        } catch (ClassNotFoundException e) {

            System.out.println(e);
        } catch (SQLException s) {

            System.out.println(s);
        }

        return conn;
    }
}
