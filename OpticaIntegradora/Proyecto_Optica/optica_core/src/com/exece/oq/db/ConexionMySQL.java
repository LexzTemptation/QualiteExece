/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.db;

import java.sql.*;

public class ConexionMySQL {
    
    Connection conn;
    
    public Connection open() throws ClassNotFoundException, SQLException{
<<<<<<< HEAD
        //Dario: Aquí cambié el user y password para acceder con mi base de datos
        //Ale: Hagan un usuario en mysql con el siguiente usuario y contraseña, esto para no estar cambiandolo a cada rato
=======
        //Dario: aquí cambié el user y password para acceder con mi base de datos
        /*Ale: Hagan un usuario con las caracteristicas en este archivo para que no
               la estemos cambiando a cada rato que subamos cambios al repo */
>>>>>>> 058a1b36d8a9f8c34cb98b6c81cc9557124a661f
        String user = "exece";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/optiqalumnos?" + 
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
