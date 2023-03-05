/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.DetalleVentaProducto;
import com.exece.oq.model.Producto;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adria
 */
public class ControllerVentasProducto {

    public boolean generarVenta(DetalleVentaProducto dvp) throws ClassNotFoundException, SQLException {

        String sql = "INSERT INTO venta VALUES(NULL, ?, ?)";
        Statement stmt = null;

        boolean result = false;

        ConexionMySQL conexionMySQL = new ConexionMySQL();
        Connection con = conexionMySQL.open();

        try {
            
            con.setAutoCommit(false);
            
            stmt = con.createStatement();

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, dvp.getVenta().getEmpleado().getIdEmpleado());
            pstmt.setString(2, dvp.getVenta().getClave());

            pstmt.executeUpdate();

            String sql2 = "SELECT LAST_INSERT_ID()";

            ResultSet rs = stmt.executeQuery(sql2);

            if (rs.next()) {
                dvp.getVenta().setIdVenta(rs.getInt(1));
            }

            for (int i = 0; i < dvp.getProductos().size(); i++) {
                String sql3 = "INSERT INTO venta_producto VALUES(?,?,?,?,?)";

                PreparedStatement pstmt2 = con.prepareStatement(sql3);

                Producto currentProducto = dvp.getProductos().get(i).getProducto();

                pstmt2.setInt(1, dvp.getVenta().getIdVenta());
                pstmt2.setInt(2, currentProducto.getIdProducto());
                pstmt2.setInt(3, dvp.getProductos().get(i).getCantidad());
                pstmt2.setDouble(4, dvp.getProductos().get(i).getPrecioUnitario());
                pstmt2.setDouble(5, dvp.getProductos().get(i).getDescuento());

                pstmt2.executeUpdate();

                String sql4 = "UPDATE producto SET existencias = existencias - ? WHERE idProducto = ?";
                PreparedStatement pstmt4 = con.prepareStatement(sql4);
                pstmt4.setInt(1, dvp.getProductos().get(i).getCantidad());
                pstmt4.setInt(2, currentProducto.getIdProducto());
                
                pstmt.executeUpdate();
            }

            con.commit();

            con.setAutoCommit(true);

            rs.close();
            stmt.close();
            pstmt.close();
            con.close();

            result = true;

        } catch (Exception e) {
            
            System.out.println(e);
            
            try {
                con.rollback();
                con.setAutoCommit(true);
                result = false;
            } catch (Exception s) {
                System.out.println(s);
                result = false;
            }
            
        }
        return result;
    }
}
