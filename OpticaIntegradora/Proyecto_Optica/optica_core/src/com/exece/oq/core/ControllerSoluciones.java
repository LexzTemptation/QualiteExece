/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.Empleado;
import com.exece.oq.model.Persona;
import com.exece.oq.model.Producto;
import com.exece.oq.model.Solucion;
import com.exece.oq.model.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;

/**
 *
 * @author FELIPE.HERRERA
 */
public class ControllerSoluciones {
    
    
    public int insert(Solucion s) throws Exception{
        Producto p = s.getProducto();
        
        //Datos del producto
        String sql = "{call insertarSolucion(?,?,?,?,?,?," //aqui insertamos los datos que se actualizaran del producto
                + "?,?,?)}"; //parametros de salida idProducto, idSolucion y el codigo de barras
        
        //ids y codigo de barras que se generaran
        int idProducto = -1;
        int idSolucion = -1;
        String codigoBarras = null;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();

        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();

        //Gracias a la interfaz del CallableStatement podremos llamar al StoredProcedure con la sentencias sql previamente definida
        CallableStatement cstmt = conn.prepareCall(sql);
        
        //Gracias al objeto de tipo Solucion que llega por parametro
        //Establecemos los parámetros de los datos del productoo en el orden
        //en que nos pide el procedimiento almacenado, comenzando en 1:
        cstmt.setString(1, p.getCodigoBarras());
        cstmt.setString(2, p.getNombre());
        cstmt.setString(3, p.getMarca());
        cstmt.setDouble(4, p.getPrecioCompra());
        cstmt.setDouble(5, p.getPrecioVenta());
        cstmt.setInt(6, p.getExistencias());
        
        //Registramos los parámetros de salida:
        cstmt.registerOutParameter(7, Types.INTEGER);
        cstmt.registerOutParameter(8, Types.INTEGER);
        cstmt.registerOutParameter(9, Types.VARCHAR);
        
        //Ejecutamos el Stored Procedure:
        cstmt.executeUpdate();
        
        //Recuperamos los ID's generados y el codigo de barras:
        idProducto = cstmt.getInt(7);
        idSolucion = cstmt.getInt(8);
        codigoBarras = cstmt.getString(9);
        
        //Liberamos los recursos que estaban consumiendo la conexion (a pesar de ser automaticamente liberados)
        cstmt.close();
        
        //Cerramos la conexion con la base de datos
        connMySQL.close();
        
        return idSolucion;
    }
    
    public void update(Solucion s) throws Exception{
        
        Producto p = s.getProducto();
        
        //Definimos la consulta SQL que invoca al Stored Procedure para poder actualizar la solucion:
        String sql = "{call actualizarSolucion(?, ?, ?, ?, ? , ?, ?)}"; //datos del producto 

        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();

        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();

        //Gracias a la interfaz del CallableStatement podremos llamar al StoredProcedure con la sentencias sql previamente definida
        CallableStatement cstmt = conn.prepareCall(sql);
    
        //Gracias al objeto de tipo Soluicon que llega por parametro
        //Establecemos los parámetros de los datos personales en el orden
        //en que los pide el procedimiento almacenado, comenzando en 1:
        cstmt.setString(1, p.getCodigoBarras());
        cstmt.setString(2, p.getNombre());
        cstmt.setString(3, p.getMarca());
        cstmt.setDouble(4, p.getPrecioCompra());
        cstmt.setDouble(5, p.getPrecioVenta());
        cstmt.setInt(6, p.getExistencias());
        cstmt.setInt(7, p.getIdProducto());
        
        //Ejecutamos el Stored Procedure:
        cstmt.executeUpdate();
        
        //Liberamos los recursos que estaban consumiendo la conexion (a pesar de ser automaticamente liberados)
        cstmt.close();
        
        //Cerramos la conexion con la base de datos 
        connMySQL.close();
    }
    
    public List<Solucion> getAll(String filtro) throws Exception{
        
        //Preparamos la consulta SQL para traer todas las soluciones de la vista 'v_soluciones'
        String sql = "SELECT * FROM v_soluciones";
        
        //Con esto evaluamos si el paramtro filtro viene en 0 o con algun numero diferente de 0
        //De ser diferente de 0 significa que quieren traer un empleado en especifico con base al id de la solucion
        //Cuando sea difente de 0 le agregaremos una pequeña sentencia con 'where' para poder filtrarlo
        if(!filtro.equals("0")) sql += " where idSolucion = " + filtro;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();

        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();

        //Con este objeto ejecutaremos la consulta:
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //Aquí guardaremos los resultados de la consulta:
        //Utilizaremos un ResultSet porque nuestra consulta es un select
        ResultSet rs = pstmt.executeQuery();

        //Creamos una Lista de tipo Solucion la cual nos permitira guardar todos las soluciones que traiga la consulta
        List<Solucion> soluciones = new ArrayList<>();
        
        //Utlizamos un while para poder recorrer la respuesta de la query
        //Utilizamos un metodo llamado fill el cual retorna objetos de tipo Solucion para agregarlos a nuestra Lista
        while(rs.next()){
            soluciones.add(fill(rs));
        }
        
        //Liberamos los recursos que estaban consumiendo la conexion (a pesar de ser automaticamente liberados)
        rs.close();
        pstmt.close();
        
        //Cerramos la conexion con la base de datos 
        connMySQL.close();
        
        return soluciones;
    }
    
      public int delete(int id) throws Exception {
        
        //Definimos la instruccion sql que nos permitira cambiar el estado pasandole como idEmpleado el id que nos llega como parametro
        String sql = "update producto set estatus = 0 where idProducto = " + id;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySLQ = new ConexionMySQL();
        
        //Abrimos la conexion con la Base de Datos
        Connection conn = connMySLQ.open();
        
        //Utilizamos un PreparedStatement para ejecutar nuestro update
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Ejecutamos nuestro update con la ayuda de executeUpdate ya que es un update
        //Obtenemos la respuesta que nos trajo la ejecucion
        //Si obtenemos 1 significa que la ejecuin ha sido correcta y de ser 0 significa que la ejecucion estuvo mal
        int respuesta = pstmt.executeUpdate();
        
        //Liberamos los recursos que estaban consumiendo la conexion (a pesar de ser automaticamente liberados)
        pstmt.close();
        
        //Cerramos la conexion con la base de datos 
        connMySLQ.close();
        
        return respuesta;
    }
    
     /*
    
        Generamos los Objetos de Solucion y Producto
    
        Gracias al parametro de tipo ResultSet podemos extraer los datos de Producto, Solucion retornar el objeto de tipo Solucion
     */
      
    private Solucion fill(ResultSet rs) throws Exception {
        Solucion s = new Solucion();
        Producto p = new Producto();
        
        s.setIdSolucion(rs.getInt("idSolucion"));
        
        p.setIdProducto(rs.getInt("idProducto"));
        p.setCodigoBarras(rs.getString("codigoBarras"));
        p.setNombre(rs.getString("nombre"));
        p.setMarca(rs.getString("marca"));
        p.setPrecioCompra(rs.getDouble("precioCompra"));
        p.setPrecioVenta(rs.getDouble("precioVenta"));
        p.setExistencias(rs.getInt("existencias"));
        p.setEstatus(rs.getInt("estatus"));
        
        s.setProducto(p);

        return s;
    }
}
