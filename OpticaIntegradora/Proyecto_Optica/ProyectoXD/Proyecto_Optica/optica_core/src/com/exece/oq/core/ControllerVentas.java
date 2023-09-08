/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.DetalleVentaP;
import com.exece.oq.model.DetalleVentaPresupuesto;
import com.exece.oq.model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author FELIPE.HERRERA
 */
public class ControllerVentas {

    public boolean generarVenta(DetalleVentaP dvp) throws ClassNotFoundException, SQLException {

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

            for (int i = 0; i < dvp.getVp().size(); i++) {
                String sql3 = "INSERT INTO venta_producto VALUES(?,?,?,?,?)";

                PreparedStatement pstmt2 = con.prepareStatement(sql3);

                Producto currentProducto = dvp.getVp().get(i).getProducto();

                pstmt2.setInt(1, dvp.getVenta().getIdVenta());
                pstmt2.setInt(2, currentProducto.getIdProducto());
                pstmt2.setInt(3, dvp.getVp().get(i).getCantidad());
                pstmt2.setDouble(4, dvp.getVp().get(i).getPrecio_unitario());
                pstmt2.setDouble(5, dvp.getVp().get(i).getDescuento());

                pstmt2.executeUpdate();
                
                //ACTUALIAR CANTIDADES
                
                String sq4 = "UPDATE Producto SET existencias = existencias - ? WHERE idProducto = ?";
                
                PreparedStatement pstmt4 = con.prepareStatement(sq4);
                
                pstmt4.setInt(1, dvp.getVp().get(i).getCantidad());
                pstmt4.setInt(2, currentProducto.getIdProducto());
                
                pstmt4.executeUpdate();

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

    
    public List<Producto> getAllProductos(String filtro) throws ClassNotFoundException, SQLException {

        Producto p;

        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT * FROM producto WHERE codigoBarras = ? or nombre = ?";

        ConexionMySQL conexionMySQL = new ConexionMySQL();

        Connection con = conexionMySQL.open();

        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1, filtro);
        pstmt.setString(2, filtro);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            p = fill(rs);

            productos.add(p);
        }

        return productos;
    }
    
    public Producto fill(ResultSet rs) throws SQLException {

        int idProducto = rs.getInt("idProducto");
        String codigoBarras = rs.getString("codigoBarras");
        String nombre = rs.getString("nombre");
        String marca = rs.getString("marca");
        double precioCompra = rs.getDouble("precioCompra");
        double precioVenta = rs.getDouble("precioVenta");
        int existencias = rs.getInt("existencias");
        int estatus = rs.getInt("estatus");

        Producto p = new Producto(idProducto, codigoBarras, nombre, marca, precioCompra, precioVenta, existencias, estatus);

        return p;
    }
    
        public boolean generarVentaLente(DetalleVentaPresupuesto dvp) throws Exception{
        boolean r = false;
        
        ConexionMySQL conMySql = new ConexionMySQL();
        Connection conn = conMySql.open();
        Statement stmnt = null;
        ResultSet rs = null;
        
        try
        {
            conn.setAutoCommit(false);
            stmnt = conn.createStatement();
            String query ="SELECT LAST_INSERT_ID();";
            
            String query0 = "INSERT INTO venta (idEmpleado, clave) VALUES('"
                    +dvp.getVenta().getEmpleado().getIdEmpleado() + "','"
                    +dvp.getVenta().getClave()+"');";
            stmnt.execute(query0);
            
            rs= stmnt.executeQuery(query);
            
            if(rs.next()){
                dvp.getVenta().setIdVenta(rs.getInt(1));
                // Ponemos el id que se generó en el rs de la columna 1 
            }
            
            for (int i = 0; i < dvp.getLvp().size(); i++)
            {
                         
            String query1 = "INSERT INTO presupuesto(idExamenVista, clave) VALUES ('" 
                    + dvp.getLvp().get(i).getPresupuesto().getIdExamenVista()
                    + "','" +dvp.getLvp().get(i).getPresupuesto().getClave() + "');";
            
            stmnt.execute(query1);
            
            rs = stmnt.executeQuery(query);
            
            if(rs.next()){
                dvp.getLvp().get(i).getPresupuesto().setIdPresupuesto(rs.getInt(1));
                // Ponemos el id que se generó en el rs de la columna 1 
            }
            
            String query2 = "INSERT INTO presupuesto_lentes"
                    + "(idPresupuesto, alturaOblea, idTipoMica, idMaterial, idArmazon) VALUES ('" 
                    + dvp.getLvp().get(i).getPresupuesto().getIdPresupuesto()
                    + "','" +dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().getAlturaOblea()
                    + "','" +dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().getIdTipoMica()
                    + "','" +dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().getIdMaterial()
                    + "','" +dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().getIdArmazon()
                    + "');";
            
            stmnt.execute(query2);
            
            rs = stmnt.executeQuery(query);
            
            if(rs.next()){
                dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().setIdPresupuestoLentes(rs.getInt(1));
                // Ponemos el id que se generó en el rs de la columna 1 
            }
            
            for(int j=0;j<dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().getLplt().size();j++){
                String query3 = "INSERT INTO presupuesto_lentes_tratamientos VALUES('"
                        +dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().getIdPresupuestoLentes()+"','"
                        +dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().getLplt().get(j).getIdTratamiento()+"');";
                
            stmnt.execute(query3);
            
            rs = stmnt.executeQuery(query);
            
            }
            String query4 = "INSERT INTO venta_presupuesto VALUES('"
                    +dvp.getVenta().getIdVenta()+"','"
                    +dvp.getLvp().get(i).getPresupuesto().getIdPresupuesto()+"','"
                    +dvp.getLvp().get(i).getCantidad()+"','"
                    +dvp.getLvp().get(i).getPrecioUnitario()+"','"
                    +dvp.getLvp().get(i).getDescuento()+"');";
            stmnt.execute(query4);
            }
            
            conn.commit();
            
            conn.setAutoCommit(true);
            
            rs.close();
            stmnt.close();
            conn.close();
            conMySql.close();
            
            r = true;
        } catch (SQLException ex)
        {
            Logger.getLogger(ControllerVentaLentes.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
                conn.setAutoCommit(true);
                conn.close();
                conMySql.close();
                r=false;
                } catch (SQLException ex1) {
            Logger.getLogger(ControllerVentaLentes.class.getName()).log(Level.SEVERE, null, ex1);
            }
            r=false;
        }
        return r;
    }

}
