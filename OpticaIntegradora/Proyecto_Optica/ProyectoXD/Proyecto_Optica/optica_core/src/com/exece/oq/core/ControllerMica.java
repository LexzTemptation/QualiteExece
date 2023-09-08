/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.TipoMica;
import com.mysql.cj.xdevapi.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author FELIPE.HERRERA
 */
public class ControllerMica {
    
    
    public List<TipoMica> getAll() throws Exception{
        
        List<TipoMica> listaMicas = new ArrayList<>();
        
        String sql = "SELECT * FROM tipo_mica";
        
        ConexionMySQL conMySQL = new ConexionMySQL();
        Connection conn = conMySQL.open();
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery();
        
        while(rs.next()){
            listaMicas.add(fill(rs));

        }
        
        return listaMicas;
        
    }
    
    
    public TipoMica fill(ResultSet rs) throws Exception{
        
        TipoMica tm = new TipoMica();
        
        tm.setIdTipoMica(rs.getInt("idTipoMica"));
        tm.setNombre(rs.getString("nombre"));
        tm.setPrecioCompra(rs.getDouble("precioCompra"));
        tm.setPrecioVenta(rs.getDouble("precioVenta"));

        
        return tm;
        
    }
    
}
