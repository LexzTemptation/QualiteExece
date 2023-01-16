package com.exece.oq.core;
import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.Accesorio;
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
 * @author medin
 */
public class ControllerAccesorio {
    public int insert(Accesorio a) throws Exception
    {
        //Definimos la consulta SQL que invoca al Stored Procedure:
        //inserta los datos en la base de datos
        String sql =    "{call insertarAccesorio(?, ?, ?, ?, ?, ?," + // Datos del producto //por cada parametro se agrega un signo de interrogación
                                               "?, ?, ?)}";  // Valores de Retorno
        
        //se declaran las variables para guardar las id's que se van a generar
        //Aquí guardaremos los ID's que se generarán:
        int idProductoGenerado = -1; //poner -1 es un valor fuera de rango 
        int idAccesorioGenerado = -1;
        String codigoBarrasGenerado = "";
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();
        
        //sql se refiere a los datos que insertamos en la base de datos
        //Con este objeto invocaremos al StoredProcedure:
        CallableStatement cstmt = conn.prepareCall(sql);
        
        //Establecemos los parámetros de los datos personales en el orden
        //en que los pide el procedimiento almacenado, comenzando en 1:
        cstmt.setString(1, a.getProducto().getCodigoBarras());
        cstmt.setString(2, a.getProducto().getNombre());
        cstmt.setString(3, a.getProducto().getMarca());
        cstmt.setDouble(4, a.getProducto().getPrecioCompra());
        cstmt.setDouble(5, a.getProducto().getPrecioVenta());
        cstmt.setInt(6, a.getProducto().getExistencias());
        
        //Registramos los parámetros de salida:
        cstmt.registerOutParameter(7, Types.INTEGER);
        cstmt.registerOutParameter(8, Types.INTEGER);
        cstmt.registerOutParameter(9, Types.VARCHAR);
        
        //Ejecutamos el Stored Procedure:
        cstmt.executeUpdate();
        
        //Recuperamos los ID's generados:
        idProductoGenerado = cstmt.getInt(7); //el 18 es la posición del parametro 
        idAccesorioGenerado = cstmt.getInt(8);
        codigoBarrasGenerado = cstmt.getString(9);
        
        
        a.getProducto().setIdProducto(idProductoGenerado);
        a.setIdAccesorio(idAccesorioGenerado);
        a.getProducto().setCodigoBarras(codigoBarrasGenerado);
        
        //se cierra la conexión 
        cstmt.close();
        connMySQL.close();
        
        //Devolvemos el ID de Cliente generado:
        return idAccesorioGenerado;
    }
    public void update(Accesorio a) throws Exception
    {
        //Definimos la consulta SQL que invoca al Stored Procedure:
        String sql =    "{call actualizarAccesorio(  ?, ?, ?, ?, ?, ?, ?)}"; // Datos del producto
        
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();
        
        //Con este objeto invocaremos al StoredProcedure:
        CallableStatement cstmt = conn.prepareCall(sql);
        
        //Establecemos los parámetros de los datos personales en el orden
        //en que los pide el procedimiento almacenado, comenzando en 1:
        cstmt.setString( 1, a.getProducto().getCodigoBarras());
        cstmt.setString( 2, a.getProducto().getNombre());
        cstmt.setString( 3, a.getProducto().getMarca());
        cstmt.setDouble(4, a.getProducto().getPrecioCompra());
        cstmt.setDouble(5, a.getProducto().getPrecioVenta());
        cstmt.setInt( 6, a.getProducto().getExistencias());
        cstmt.setInt(7, a.getProducto().getIdProducto());
        
        //Ejecutamos el Stored Procedure:
        cstmt.executeUpdate();
        
        cstmt.close();
        connMySQL.close();        
    }
    
    public int  delete(int id) throws Exception
    {
        String sql = "update accesorio acc join producto pro on acc.idProducto = pro.idProducto set estatus = 0 where idAccesorio =" + id; //si es 0 no hubo cambios, si es un 1 es que hizo cambios
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        CallableStatement cstmt = conn.prepareCall(sql);
        
        int respuesta = cstmt.executeUpdate();
        
        return respuesta;
    }
    
    public List<Accesorio> getAll(String filtro) throws Exception
    {
        //La consulta SQL a ejecutar:
        String sql = "SELECT * FROM v_accesorios";
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();
        
        //preparedStament ejecuta instrucciones SQL
        //Con este objeto ejecutaremos la consulta:
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Aquí guardaremos los resultados de la consulta:
        ResultSet rs = pstmt.executeQuery();
        
        List<Accesorio> accesorios = new ArrayList<>();
        
        while (rs.next())
            accesorios.add(fill(rs));
        
        rs.close();
        pstmt.close();
        connMySQL.close();
        
        return accesorios;
    }
    
    private Accesorio fill(ResultSet rs) throws Exception
    {
        Accesorio a = new Accesorio();
        Producto p = new Producto();
        
        p.setIdProducto(rs.getInt("idProducto"));
        p.setCodigoBarras(rs.getString("codigoBarras"));
        p.setNombre(rs.getString("nombre"));
        p.setMarca(rs.getString("marca"));
        p.setPrecioCompra(rs.getDouble("precioCompra"));
        p.setPrecioVenta(rs.getDouble("precioVenta"));
        p.setExistencias(rs.getInt("existencias"));
        p.setEstatus(rs.getInt("estatus"));
        
        a.setIdAccesorio(rs.getInt("idAccesorio"));
        
        a.setProducto(p);
        
        return a;
    }
    
}