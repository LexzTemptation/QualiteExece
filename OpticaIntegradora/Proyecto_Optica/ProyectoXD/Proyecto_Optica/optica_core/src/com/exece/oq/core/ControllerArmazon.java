/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.Armazon;
import com.exece.oq.model.Producto;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonai
 */
public class ControllerArmazon {
    
    /** public static boolean isAdmin(Empleado e)
    {
        if (e == null || e.getUsuario() == null || e.getUsuario().getNombre() == null)
            return false;
        else
            return e.getUsuario().getRol().trim().toLowerCase().equals("administrador");
    }
    **/
    public int insert(Armazon a) throws Exception{
        String sql = "{call insertarArmazon(?,?,?,?,?, " +
                                           "?,?,?,?,?," +
                                           "?,?,?)}";
        
        int idProducto = -1;
        int idArmazon = -1;
        String codigoBarras = "";
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        CallableStatement cstmt = conn.prepareCall(sql);
        
        cstmt.setString(1, a.getProducto().getNombre());
        cstmt.setString(2, a.getProducto().getMarca());
        cstmt.setInt(3, (int) a.getProducto().getPrecioCompra());
        cstmt.setInt(4, (int) a.getProducto().getPrecioVenta());
        cstmt.setInt(5, a.getProducto().getExistencias());
        
        cstmt.setString(6, a.getModelo());
        cstmt.setString(7, a.getColor());
        cstmt.setString(8, a.getDimensiones());
        cstmt.setString(9, a.getDescripcion());
        cstmt.setString(10, a.getFotografia());
        
        cstmt.registerOutParameter(11, Types.INTEGER);
        cstmt.registerOutParameter(12, Types.INTEGER);
        cstmt.registerOutParameter(13, Types.VARCHAR);
        
        cstmt.executeUpdate();
        
        idProducto = cstmt.getInt(11);
        idArmazon = cstmt.getInt(12);
        codigoBarras = cstmt.getString(13);
        
        a.setIdArmazon(idArmazon);
        a.getProducto().setIdProducto(idProducto);
        a.getProducto().setCodigoBarras(codigoBarras);
        
        cstmt.close();
        connMySQL.close();
        
        return idArmazon;
        
    }
    
    public void update(Armazon a) throws Exception
    {
        //Definimos la consulta SQL que invoca al Stored Procedure:
        String sql =    "{call actualizarArmazon(  ?, ?, ?, ?, ?, " + //Datos del producto
                                                  "?, ?, ?, ?, ?,  " + // Datos del armazon
                                                  "?, ?)}"; // IDs
        
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();
        
        //Con este objeto invocaremos al StoredProcedure:
        //No es prepared statement pq ejecuta ejecuciones que no son de SQL
        CallableStatement cstmt = conn.prepareCall(sql);
        
        //Establecemos los parámetros de los datos personales en el orden
        //en que los pide el procedimiento almacenado, comenzando en 1:
        cstmt.setString( 1, a.getProducto().getNombre());
        cstmt.setString( 2, a.getProducto().getMarca());
        cstmt.setDouble(3, a.getProducto().getPrecioCompra());
        cstmt.setDouble(4, a.getProducto().getPrecioVenta());
        cstmt.setInt( 5, a.getProducto().getExistencias());
        cstmt.setString( 6, a.getModelo());
        cstmt.setString( 7, a.getColor());
        cstmt.setString( 8, a.getDimensiones());
        cstmt.setString( 9, a.getDescripcion());
        cstmt.setString(10, a.getFotografia());
        
        cstmt.setInt(11, a.getProducto().getIdProducto());
        cstmt.setInt(12, a.getIdArmazon());
        
        //Ejecutamos el Stored Procedure:
        cstmt.executeUpdate();
        
        cstmt.close();
        connMySQL.close();        
    }
    
    public List<Armazon> getAll(String filtro) throws Exception
    {
        //La consulta SQL a ejecutar:
        // Nos ahorramos código con este código ya que no tenemos que poner
        // inner joins
        String sql = "SELECT * FROM v_armazones";
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();
        
        //Con este objeto ejecutaremos la consulta:
        // Es para ejecutar intruciones que son de SQL
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Aquí guardaremos los resultados de la consulta:
        ResultSet rs = pstmt.executeQuery();
        
        List<Armazon> armazon = new ArrayList<>();
        
        while (rs.next())
            armazon.add(fill(rs));
        
        rs.close();
        pstmt.close();
        connMySQL.close();
        
        return armazon;
    }
    
    public int delete(int id) throws Exception
    {
        String sql = "update producto set estatus = 0 where idProducto = " +id;
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        CallableStatement cstmt = conn.prepareCall(sql);
        int respuesta = cstmt.executeUpdate();
        return respuesta;
    }
    
    private Armazon fill(ResultSet rs) throws Exception
    {
        Armazon a = new Armazon();
        Producto p = new Producto();
        
        
        p.setIdProducto(rs.getInt("idProducto"));
        p.setCodigoBarras(rs.getString("codigoBarras"));
        p.setNombre(rs.getString("nombre"));
        p.setMarca(rs.getString("marca"));
        p.setPrecioCompra(rs.getDouble("precioCompra"));
        p.setPrecioVenta(rs.getDouble("precioVenta"));
        p.setExistencias(rs.getInt("existencias"));
        p.setEstatus(rs.getInt("estatus"));
        
        a.setIdArmazon(rs.getInt("idArmazon"));
        a.setModelo(rs.getString("modelo"));
        a.setColor(rs.getString("color"));
        a.setDimensiones(rs.getString("dimensiones"));
        a.setDescripcion(rs.getString("descripcion"));
        a.setFotografia(rs.getString("fotografia"));
        a.setProducto(p);
        
        return a;

    }
}
