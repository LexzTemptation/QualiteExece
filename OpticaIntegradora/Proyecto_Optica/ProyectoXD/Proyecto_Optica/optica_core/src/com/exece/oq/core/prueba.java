/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class prueba {

    public static void main(String[] args) throws Exception {

        ConexionMySQL conMySQL = new ConexionMySQL();

        Connection conn = conMySQL.open();

        String sql = "select * from datos where boleta = ?";

        ResultSet rs;

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, 1);

        rs = pstmt.executeQuery();

        ServerSocket s = new ServerSocket(7);
        System.out.println("Servicio iniciado....");
        System.out.println("En espera de un cliente Android...");
        Socket cliente;

        while (true) {
            cliente = s.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            String entrada = input.readLine();
            System.out.println("cliente busca la boleta: " + entrada);
            pstmt.setInt(1, Integer.parseInt(entrada));
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String mensaje = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4);

                System.out.println(mensaje);

                PrintStream output = new PrintStream(cliente.getOutputStream());
                output.flush();
                output.println(mensaje);
                cliente.close();
            }

        }

    }

}
