/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.Producto;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adria
 */
public class ControllerProductos {

    public List<Producto> getAll(String filtro) {
        List<Producto> productos = new ArrayList<>();

        try {
            String query = "SELECT * FROM producto";
            ConexionMySQL connMySQL = new ConexionMySQL();
            Connection conn = connMySQL.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                productos.add(fill(rs));
            }

            rs.close();
            pstmt.close();
            connMySQL.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControllerProductos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerProductos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControllerProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productos;
    }

    private Producto fill(ResultSet rs) throws Exception {
        Producto p = new Producto();

        //datos de producto
        p.setIdProducto(rs.getInt("idProducto"));
        p.setCodigoBarras(rs.getString("codigoBarras"));
        p.setNombre(rs.getString("nombre"));
        p.setMarca(rs.getString("marca"));
        p.setPrecioCompra(rs.getDouble("precioCompra"));
        p.setPrecioVenta(rs.getDouble("precioVenta"));
        p.setExistencias(rs.getInt("existencias"));
        p.setEstatus(rs.getInt("estatus"));
        return p;
    }
}
