/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.Lente_contacto;
import com.exece.oq.model.Producto;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 *
 * @author david
 */
public class ControllerLente_contacto {
    
    //método para insertar lentes
    public int insert(Lente_contacto lc) throws Exception{
        
        //lamamos al procedimiento almacenado insertarLentesContacto
        String sql = "{call insertarLenteContacto(?, ?, ?, ?, ?, " +
"						   ?, ?, " +
"                                                  ?, ?, ?)}";
        //recuperamos los id's y el código de barras generados
        int idProductoGenerado = -1;
        int idLenteContactoGenerado = -1;
        String codigoBarrasGenarado = "";
        
        //objeto que va a conectar a la base de datos
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //abrir la conexión a MYSQL
        Connection conn = connMySQL.open();
        
        //Objeto con el cual invocamos a la consulta
        CallableStatement cstmt = conn.prepareCall(sql);
        
        //Llenar valores por orden
        //empezando por los datos de producto
        //y después con los de lente contacto, comenzando con el 1:
        cstmt.setString(1, lc.getProducto().getNombre());
        cstmt.setString(2, lc.getProducto().getMarca());
        cstmt.setDouble(3, lc.getProducto().getPrecioCompra());
        cstmt.setDouble(4, lc.getProducto().getPrecioVenta());
        cstmt.setInt(5, lc.getProducto().getExistencias());
        cstmt.setInt(6, lc.getKeratometria());
        cstmt.setString(7, lc.getFotografia());
        
        //Registramos los datos de salida
        cstmt.registerOutParameter(8, Types.INTEGER);
        cstmt.registerOutParameter(9, Types.INTEGER);
        cstmt.registerOutParameter(10, Types.VARCHAR);
        
        //Ya que registramos todos los datos en el procedimiento almacenado,
        //ejecutarlo
        cstmt.executeUpdate();
        
        //recuperamos los id's generados
        idLenteContactoGenerado = cstmt.getInt(8);
        idProductoGenerado = cstmt.getInt(9);
        codigoBarrasGenarado = cstmt.getString(10);
        
        lc.setIdLenteContacto(idLenteContactoGenerado);
        lc.getProducto().setIdProducto(idProductoGenerado);
        lc.getProducto().setCodigoBarras(codigoBarrasGenarado);
        
        //cerramos la conexión 
        cstmt.close();
        connMySQL.close();
        return idLenteContactoGenerado;
    }
    
    public void update(Lente_contacto lc) throws Exception{
        String sql = "{call actualizarLenteContacto(?, ?, ?, ?, ?, " + // Datos Personales
                                                   "?, ?, " +//Datos personales
                                                   "?, ?)}";
        
        //objeto de la clase que conecta a la base de datos
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //abrir la conexión con el método open()
        Connection conn = connMySQL.open();
        
        //invocamos al procedimiento almacenado con el siguiente objeto
        CallableStatement cstmt = conn.prepareCall(sql);
        
        //Llenamos los datos a actualizar
        cstmt.setString(1, lc.getProducto().getNombre());
        cstmt.setString(2, lc.getProducto().getMarca());
        cstmt.setDouble(3, lc.getProducto().getPrecioCompra());
        cstmt.setDouble(4, lc.getProducto().getPrecioVenta());
        cstmt.setInt(5, lc.getProducto().getExistencias());
        cstmt.setInt(6, lc.getKeratometria());
        cstmt.setString(7, lc.getFotografia());
        
        cstmt.setInt(8, lc.getProducto().getIdProducto());
        cstmt.setInt(9, lc.getIdLenteContacto());
        
        //ejecutamos el procedimiento almacenado
        cstmt.executeUpdate();
        
        //cerramos la conexión a mysql
        cstmt.close();
        connMySQL.close();        
    }
    
    public int delete(int id) throws Exception{
        //respuesta de si la función update fue correcta
        int respuesta;
        //no fue necesario hacer procedimiento almacenado para eliminar, al menos no por ahora xD
        String sql = "update v_lentes_contacto set estatus = 0 where idLenteContacto = "+id;
        
        //objeto para la conexión a MYSQL
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //abrir la conexión
        Connection conn = connMySQL.open();
        
        //objeto para ejecutar el delete
        PreparedStatement pstmt = conn.prepareCall(sql);
        
        //Como es eliminación lógica, se actualiza el estatus a 0, por lo tanto;
        //utilizamos un executeUpdate, ya que utilizamos un update en mysql.
        //Si la función es correcta, devuelve 1, si no, devuelve 0, que es incorrecta
        respuesta = pstmt.executeUpdate();
               
        //se cierra la conexión a la base de datos
        connMySQL.close();
        
        //devuelve la respuesta
        return respuesta;
    }
    
    public List<Lente_contacto> getAll(String flitro) throws Exception{
        //La consulta SQL a ejecutar a la vista de lentes de contacto
        String sql = "select * from v_lentes_contacto";
        
        //objeto para conectar a la Base de Datos
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión
        Connection conn = connMySQL.open();
        
        //Con este objeto ejecutaremos la consulta:
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Aquí guardaremos los resultados de la consulta:
        ResultSet rs = pstmt.executeQuery();
        //los resultados de la consulta lo mandamos a un objeto de tipo lente_contacto 
        //y mandarlo a un arraylist (una lista para los compas)
        List<Lente_contacto> lentes_contacto = new ArrayList<>();
        
        while (rs.next())
            lentes_contacto.add(fill(rs));
        
        rs.close();
        pstmt.close();
        connMySQL.close();
        
        return lentes_contacto;
    }
    
    //con este método hacemos que los datos se manden a la tabla
    private Lente_contacto fill(ResultSet rs) throws Exception{
        Lente_contacto lc = new Lente_contacto();
        Producto p = new Producto();
              
        //datos de lentes de contacto
        lc.setIdLenteContacto(rs.getInt("idLenteContacto"));
        lc.setKeratometria(rs.getInt("keratometria"));
        lc.setFotografia(rs.getString("fotografia"));
        
        //datos de producto
        p.setIdProducto(rs.getInt("idProducto"));
        p.setCodigoBarras(rs.getString("codigoBarras"));
        p.setNombre(rs.getString("nombre"));
        p.setMarca(rs.getString("marca"));
        p.setPrecioCompra(rs.getDouble("precioCompra"));
        p.setPrecioVenta(rs.getDouble("precioVenta"));
        p.setExistencias(rs.getInt("existencias"));
        p.setEstatus(rs.getInt("estatus"));
        lc.setProducto(p);
        return lc;
    }
}
