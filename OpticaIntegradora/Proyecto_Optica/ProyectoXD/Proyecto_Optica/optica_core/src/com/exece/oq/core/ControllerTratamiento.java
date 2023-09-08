/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.Tratamiento;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author FELIPE.HERRERA
 */
public class ControllerTratamiento {
    
    
    public List<Tratamiento> getAll() throws Exception{
        
        List<Tratamiento> listaTratamientos = new ArrayList<>();
        
        ConexionMySQL conMySLQ = new ConexionMySQL();
        
        Connection conn = conMySLQ.open();
        
        String sql = "SELECT * FROM tratamiento";
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery(sql);
        
        while(rs.next()){
            listaTratamientos.add(fill(rs));
        }
        
        return listaTratamientos;
    }
    
    public Tratamiento fill(ResultSet rs) throws Exception{
        
        Tratamiento tm = new Tratamiento();
        
        tm.setIdTratamiento(rs.getInt("idTratamiento"));
        tm.setNombre(rs.getString("nombre"));
        tm.setPrecioCompra(rs.getDouble("precioCompra"));
        tm.setPrecioVenta(rs.getDouble("precioVenta"));
        tm.setEstatus(rs.getInt("estatus"));
        
        
        return tm;
    }
    
}
