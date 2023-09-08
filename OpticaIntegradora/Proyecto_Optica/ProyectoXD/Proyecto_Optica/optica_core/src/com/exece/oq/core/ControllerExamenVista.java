/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.ExamenVista;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author FELIPE.HERRERA
 */
public class ControllerExamenVista {
    
    public List<ExamenVista> getAll() throws Exception{
        
        List<ExamenVista> listaExamenVita = new ArrayList<>();
        
        String sql = "SELECT * FROM examen_vista";
        
        ConexionMySQL conMySQL = new ConexionMySQL();
        
        Connection conn = conMySQL.open();
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery();
        
        
        while(rs.next()){
            listaExamenVita.add(fill(rs));
        }
        
        return listaExamenVita;
    }
    
    public ExamenVista fill(ResultSet rs) throws Exception{
        
        ExamenVista ev = new ExamenVista();
        
        ev.setIdExamenVista(rs.getInt("idExamenVista"));
        ev.setIdCliente(rs.getInt("idCliente"));
        ev.setClave(rs.getString("clave"));
        
        return ev;
        
    }
    
}
