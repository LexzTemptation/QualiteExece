/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.controller;

import org.utl.dsm.bd.ConexionMySQL;
import java.sql.*;
import org.utl.dsm.modelo.Deposito;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.utl.dsm.controller.ControllerTransacciones;

/**
 *
 * @author FELIPE.HERRERA
 */
public class ControllerTransacciones {
    
    
    public boolean ejecutarTransaccion(Deposito dep) throws Exception
    {
        boolean r = false;
        ConexionMySQL conMysql = new ConexionMySQL();
        Connection conn = (Connection) conMysql.open();
        Statement stmnt = null;
        ResultSet rs = null;
        
      
        try
        {
            conn.setAutoCommit(false);
            stmnt = conn.createStatement();

            String query1 = "INSERT INTO cliente (nombre, apellidoP, apellidoM, email, telefono) VALUES ('" + dep.getCuenta().getCliente().getNombre() + "','"
                    + dep.getCuenta().getCliente().getApellidoP()
                    + "','" + dep.getCuenta().getCliente().getApellidoM() + "','"
                    + dep.getCuenta().getCliente().getEmail() + "','"
                    + dep.getCuenta().getCliente().getTelefono() + "');";
            stmnt.execute(query1);
            rs = stmnt.executeQuery("SELECT LAST_INSERT_ID();");

            if (rs.next())
            {
                dep.getCuenta().getCliente().setIdCliente(rs.getInt(1));
            }

            String query2 = "INSERT INTO cuenta (numCuenta,fechaCreacion,sucursal,idCliente) VALUES (" + dep.getCuenta().getNumCuenta() + ","
                    + "STR_TO_DATE('" + dep.getCuenta().getFechaCreacion() + "','%d/%m/%Y')" + ",'"
                    + dep.getCuenta().getSucursal()
                    + "'," + dep.getCuenta().getCliente().getIdCliente() + ");";
            stmnt.execute(query2);
            rs = stmnt.executeQuery("SELECT LAST_INSERT_ID();");

            if (rs.next())
            {
                dep.getCuenta().setidCuenta(rs.getInt(1));
                
            }

            String query3 = "INSERT INTO deposito (idCuenta,fechaMovimiento,horaMovimiento,monto) VALUES("
                    + dep.getCuenta().getidCuenta() + "," + "STR_TO_DATE('" + dep.getFechaMovimiento() + "', '%d/%m/%Y'),now()," + dep.getMonto() + ");";

            stmnt.execute(query3);
            rs = stmnt.executeQuery("SELECT LAST_INSERT_ID();");

            if (rs.next())
            {
                dep.setIdDeposito(rs.getInt(1));
            }

            conn.commit();
            conn.setAutoCommit(true);

            rs.close();
            stmnt.close();
            conn.close();
            conMysql.close();

            r = true;

        } catch (SQLException ex)
        {
            
  
            Logger.getLogger(ControllerTransacciones.class.getName()).log(Level.SEVERE, null, ex);
            try
            {
                conn.rollback();
                conn.setAutoCommit(true);
                conn.close();
                conMysql.close();
                r = false;
            } catch (SQLException ex1)
            {
                Logger.getLogger(ControllerTransacciones.class.getName()).log(Level.SEVERE, null, ex1);
            }
            r = false;
        }


        return r;
    }
    
}
