package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.DetalleVentaPresupuesto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerVentaLentes
{
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
                    +dvp.getVenta().getEmpleado().getIdEmpleado() + ","
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
                    + dvp.getLvp().get(i).getPresupuesto().getExamenVista().getIdExamenVista()
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
                    + "','" +dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().getTipoMica()
                    + "','" +dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().getMaterial().getIdMaterial()
                    + "','" +dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().getArmazon().getIdArmazon()
                    + "');";
            
            stmnt.execute(query2);
            
            rs = stmnt.executeQuery(query);
            
            if(rs.next()){
                dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().setIdPresupuestoLentes(rs.getInt(1));
                // Ponemos el id que se generó en el rs de la columna 1 
            }
            
            for(int j=0;j<dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().getLplt().size();j++){
                String query3 = "INSERT INTO presupuesto_lentes_tratamiento VALUES('"
                        +dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().getIdPresupuestoLentes()+","
                        +dvp.getLvp().get(i).getPresupuesto().getPresupuestoLentes().getLplt().get(j).getTratamiento().getIdTratamiento()+"');";
                
            stmnt.execute(query3);
            
            rs = stmnt.executeQuery(query);
            
            }
            String query4 = "INSERT INTO venta_presupuesto VALUES('"
                    +dvp.getVenta().getIdVenta()+","
                    +dvp.getLvp().get(i).getPresupuesto().getIdPresupuesto()+","
                    +dvp.getLvp().get(i).getCantidad()+","
                    +dvp.getLvp().get(i).getPrecioUnitario()+","
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
            Logger.getLogger(ControllerVentasProducto.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
                conn.setAutoCommit(true);
                conn.close();
                conMySql.close();
                r=false;
                } catch (SQLException ex1) {
            Logger.getLogger(ControllerVentasProducto.class.getName()).log(Level.SEVERE, null, ex1);
            }
            r=false;
        }
        return r;
    }
}
