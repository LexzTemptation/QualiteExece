package org.utl.dsm.bd;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.*;

public class ConexionMySQL {
    
     Connection conn;
    
    public Connection open() throws ClassNotFoundException, SQLException{
        
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://127.0.0.1:3306/bancoutl?" + 
                     "useSSL=false&" +
                     "allowPublicKeyRetrieveal=true&" +
                     "useUnicode=true&"  +
                     "characterEncoding=utf-8";
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        conn = DriverManager.getConnection(url, user, password);
        
        return conn;
    } 
    
    public void close() throws Exception{
        
        if(conn != null){
            
            conn.close();
            System.out.println("close");
        }
        else{
            System.out.println("Something have been happened");
        }
    }
    
}
