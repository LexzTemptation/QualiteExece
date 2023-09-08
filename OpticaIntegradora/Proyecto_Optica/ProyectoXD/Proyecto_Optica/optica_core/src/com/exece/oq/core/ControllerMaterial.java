/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.Material;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author FELIPE.HERRERA
 */
public class ControllerMaterial {
    
    public List<Material> getAll() throws Exception{
       
        List<Material> listaMateriales = new ArrayList<>();
        
        String sql = "SELECT * FROM material";
        
        ConexionMySQL conMySQL = new ConexionMySQL();
        
        Connection conn = conMySQL.open();
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery(sql);
        
        while(rs.next()){
         
            listaMateriales.add(fill(rs));
        }
        
        return  listaMateriales;
       
    }
    
    
   public Material fill(ResultSet rs) throws Exception{
       
       Material m = new Material();
       
       
       m.setIdMaterial(rs.getInt("idMaterial"));
       m.setNombre(rs.getString("nombre"));
       m.setPrecioCompra(rs.getDouble("precioCompra"));
       m.setPrecioVenta(rs.getDouble("precioVenta"));
       
       
       return m;
       
   } 
    
}
