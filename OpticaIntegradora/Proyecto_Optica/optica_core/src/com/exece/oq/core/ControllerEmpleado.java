package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.Empleado;
import com.exece.oq.model.Persona;
import com.exece.oq.model.Usuario;
import com.google.gson.Gson;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 *
 * @author LiveGrios
 */
public class ControllerEmpleado {

    public static boolean isAdmin(Empleado e) {

        // Si el empleado que obtenemos por parametro esta null o el usuario es null o el nombre es null retornaremos false
        if (e == null || e.getUsuario() == null || e.getUsuario().getNombre() == null) {
            return false;
        } else {
            //Si no entonces hacemos lo siguiente:

            /*
           1.- Nos traemos el rol del usuario que nos llega del objeto Empleado
           2.- Eliminamos los espacios en blanco que puedan llegar a existir
           3.- Lo convertimos a minusculas
           4.- Evaluamos si es igual a 'administrador' de ser admin entonces retornara 'true' y si no entonces 'false' 
             */
            return e.getUsuario().getRol().trim().toLowerCase().equals("administrador");
        }
    }

    public String login(String user, String password) throws Exception {

        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        //Creamos un objeto de tipo empleado
        Empleado em = new Empleado();
        
        //Creamos una variable que almacenara el json de respuesta
        String jsonInfo = "";

        //Preparamos la sentencia SQL
        String sql = "SELECT * FROM v_empleados where nombreUsuario = ? and contrasenia = ?";

        //Con este objeto ejecutaremos la consulta:
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //Aqui le ponemos los dos parametros que es nombre de usuario y contraseña
        pstmt.setString(1, user);
        pstmt.setString(2, password);

        //Aquí guardaremos los resultados de la consulta:
        //Utilizaremos un ResultSet porque nuestra consulta es un select
        ResultSet res = pstmt.executeQuery();

        //Si la query fue exitosa utilizaremos el metodo fill para traer el empleado que se logeo ademas de convertirlo en Json y retornarlo
        //Si la query fallo entonces la varible json sera igual a lo que retorne el metodo 
        if (res.next()) {

            em = fill(res);
            jsonInfo = new Gson().toJson(em);
            em.getUsuario().createLastToken();
            guardarToken(em);
            
        } 

        //Liberamos los recursos que estaban consumiendo la conexion (a pesar de ser automaticamente liberados)
        pstmt.close();

        //Cerramos la conexion con la base de datos
        connMySQL.close();

        return jsonInfo;
    }

    public int insert(Empleado e) throws Exception {

        //Definimos la consulta SQL que invoca al Stored Procedure:
        String sql = "{call insertarEmpleado(?, ?, ?, ?, ?, ?, ?, "
                + // Datos Personales
                "?, ?, ?, ?, ?, ?, ?, "
                + "?, ?, ?, "
                + // Datos de Seguridad
                "?, ?, ?, ?, ?)}";  // Valores de Retorno

        //Aquí guardaremos los ID's que se generarán:
        int idPersonaGenerado = -1;
        int idEmpleadoGenerado = -1;
        int idUsuarioGenerado = -1;
        String numeroUnicoGenerado = "";

        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();

        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();

        //Gracias a la interfaz del CallableStatement podremos llamar al StoredProcedure con la sentencias sql previamente definida
        CallableStatement cstmt = conn.prepareCall(sql);

        //Gracias al objeto de tipo Empleado que llega por parametro
        //Establecemos los parámetros de los datos personales en el orden
        //en que los pide el procedimiento almacenado, comenzando en 1:
        cstmt.setString(1, e.getPersona().getNombre());
        cstmt.setString(2, e.getPersona().getApellidoPaterno());
        cstmt.setString(3, e.getPersona().getApellidoMaterno());
        cstmt.setString(4, e.getPersona().getGenero());
        cstmt.setString(5, e.getPersona().getFechaNacimiento());
        cstmt.setString(6, e.getPersona().getCalle());
        cstmt.setString(7, e.getPersona().getNumero());
        cstmt.setString(8, e.getPersona().getColonia());
        cstmt.setString(9, e.getPersona().getCp());
        cstmt.setString(10, e.getPersona().getCiudad());
        cstmt.setString(11, e.getPersona().getEstado());
        cstmt.setString(12, e.getPersona().getTelCasa());
        cstmt.setString(13, e.getPersona().getTelMovil());
        cstmt.setString(14, e.getPersona().getEmail());

        // Registramos parámetros de datos de seguridad:
        cstmt.setString(15, e.getUsuario().getNombre());
        cstmt.setString(16, e.getUsuario().getContrasenia());
        cstmt.setString(17, e.getUsuario().getRol());

        //Registramos los parámetros de salida, definiendo a su vez el tipo de dato que retornaran con la ayuda de Types:
        cstmt.registerOutParameter(18, Types.INTEGER);
        cstmt.registerOutParameter(19, Types.INTEGER);
        cstmt.registerOutParameter(20, Types.INTEGER);
        cstmt.registerOutParameter(21, Types.VARCHAR);
        cstmt.registerOutParameter(22, Types.VARCHAR);

        //Ejecutamos el Stored Procedure:
        cstmt.executeUpdate();

        //Recuperamos los ID's generados:
        idPersonaGenerado = cstmt.getInt(18);
        idUsuarioGenerado = cstmt.getInt(19);
        idEmpleadoGenerado = cstmt.getInt(20);
        numeroUnicoGenerado = cstmt.getString(21);

        e.setIdEmpleado(idEmpleadoGenerado);
        e.getPersona().setIdPersona(idPersonaGenerado);
        e.getUsuario().setIdUsuario(idUsuarioGenerado);
        e.setNumeroUnico(numeroUnicoGenerado);

        //Liberamos los recursos que estaban consumiendo la conexion (a pesar de ser automaticamente liberados)
        cstmt.close();

        //Cerramos la conexion con la base de datos
        connMySQL.close();

        //Devolvemos el ID de Empleado generado:
        return idEmpleadoGenerado;
    }

    public void update(Empleado e) throws Exception {
        //Definimos la consulta SQL que invoca al Stored Procedure:
        String sql = "{call actualizarEmpleado(  ?, ?, ?, ?, ?, ?, ?, "
                + //Datos Personales
                "?, ?, ?, ?, ?, ?, ?, "
                + "?, ?, ?, "
                + // Datos de Seguridad
                "?, ?, ?)}"; // IDs

        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();

        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();

        //Gracias a la interfaz del CallableStatement podremos llamar al StoredProcedure con la sentencias sql previamente definida
        CallableStatement cstmt = conn.prepareCall(sql);

        //Gracias al objeto de tipo Empleado que llega por parametro
        //Establecemos los parámetros de los datos personales en el orden
        //en que los pide el procedimiento almacenado, comenzando en 1:
        cstmt.setString(1, e.getPersona().getNombre());
        cstmt.setString(2, e.getPersona().getApellidoPaterno());
        cstmt.setString(3, e.getPersona().getApellidoMaterno());
        cstmt.setString(4, e.getPersona().getGenero());
        cstmt.setString(5, e.getPersona().getFechaNacimiento());
        cstmt.setString(6, e.getPersona().getCalle());
        cstmt.setString(7, e.getPersona().getNumero());
        cstmt.setString(8, e.getPersona().getColonia());
        cstmt.setString(9, e.getPersona().getCp());
        cstmt.setString(10, e.getPersona().getCiudad());
        cstmt.setString(11, e.getPersona().getEstado());
        cstmt.setString(12, e.getPersona().getTelCasa());
        cstmt.setString(13, e.getPersona().getTelMovil());
        cstmt.setString(14, e.getPersona().getEmail());
        cstmt.setString(15, e.getUsuario().getNombre());
        cstmt.setString(16, e.getUsuario().getContrasenia());
        cstmt.setString(17, e.getUsuario().getRol());

        //Establecemos los parametros de los IDS de persona, usuario, empleado con base al objeto de tipo empleado que entra
        cstmt.setInt(18, e.getPersona().getIdPersona());
        cstmt.setInt(19, e.getUsuario().getIdUsuario());
        cstmt.setInt(20, e.getIdEmpleado());

        //Ejecutamos el Stored Procedure:
        cstmt.executeUpdate();

        //Liberamos los recursos que estaban consumiendo la conexion (a pesar de ser automaticamente liberados)
        cstmt.close();

        //Cerramos la conexion con la base de datos 
        connMySQL.close();
    }

    public int delete(int id) throws Exception {

        //Definimos la instruccion sql que nos permitira cambiar el estado pasandole como idEmpleado el id que nos llega como parametro
        String sql = "update empleado set estatus = 0 where idEmpleado = " + id;

        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();

        //Abrimos la conexion con la Base de Datos
        Connection conn = connMySQL.open();

        //Utilizamos un PreparedStatement para ejecutar nuestro update
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //Ejecutamos nuestro update con la ayuda de executeUpdate ya que es un update
        //Obtenemos la respuesta que nos trajo la ejecucion
        //Si obtenemos 1 significa que la ejecuin ha sido correcta y de ser 0 significa que la ejecucion estuvo mal
        int respuesta = pstmt.executeUpdate();

        //Liberamos los recursos que estaban consumiendo la conexion (a pesar de ser automaticamente liberados)
        pstmt.close();

        //Cerramos la conexion con la base de datos 
        connMySQL.close();

        return respuesta;
    }

    public List<Empleado> getAll(String filtro) throws Exception {
        //Hacemos una consulta SQL para traer los datos dentro de nuestra vista 'v_empleados':
        String sql = "SELECT * FROM v_empleados";

        //Con esto evaluamos si el paramtro filtro viene en 0 o con algun numero diferente de 0
        //De ser diferente de 0 significa que quieren traer un empleado en especifico con base al id del empleado
        //Cuando sea difente de 0 le agregaremos una pequeña sentencia con 'where' para poder filtrarlo
        if (!filtro.equals("0")) {
            sql += " where idEmpleado = " + filtro;
        }

        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();

        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();

        //Con este objeto ejecutaremos la consulta:
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //Aquí guardaremos los resultados de la consulta:
        //Utilizaremos un ResultSet porque nuestra consulta es un select
        ResultSet rs = pstmt.executeQuery();

        //Creamos una Lista de tipo Empleado la cual nos permitira guardar todos los emepleados que traiga la consulta
        List<Empleado> empleados = new ArrayList<>();

        //Utlizamos un while para poder recorrer la respuesta de la query
        //Utilizamos un metodo llamado fill el cual retorna objetos de tipo Emleado para agregarlos a nuestra Lista
        while (rs.next()) {
            empleados.add(fill(rs));
        }

        //Liberamos los recursos que estaban consumiendo la conexion (a pesar de ser automaticamente liberados)
        rs.close();
        pstmt.close();

        //Cerramos la conexion con la base de datos 
        connMySQL.close();

        //Por utlimo retornamos la Lista de los empleados que se guradaron 
        return empleados;
    }

    /*
    
        Generamos los Objetos de Persona y Empleado
    
        Gracias al parametro de tipo ResultSet podemos extraer los datos de persona, empleado y usuario y retornar el objeto de tipo Empleado
     */
    private Empleado fill(ResultSet rs) throws Exception {

        Empleado e = new Empleado();
        Persona p = new Persona();

        p.setApellidoMaterno(rs.getString("apellidoMaterno"));
        p.setApellidoPaterno(rs.getString("apellidoPaterno"));
        p.setCalle(rs.getString("calle"));
        p.setCiudad(rs.getString("ciudad"));
        p.setColonia(rs.getString("colonia"));
        p.setCp(rs.getString("cp"));
        p.setEmail(rs.getString("email"));
        p.setEstado(rs.getString("estado"));
        p.setFechaNacimiento(rs.getString("fechaNacimiento"));
        p.setGenero(rs.getString("genero"));
        p.setIdPersona(rs.getInt("idPersona"));
        p.setNombre(rs.getString("nombre"));
        p.setNumero(rs.getString("numero"));
        p.setTelCasa(rs.getString("telcasa"));
        p.setTelMovil(rs.getString("telmovil"));

        e.setIdEmpleado(rs.getInt("idEmpleado"));
        e.setNumeroUnico(rs.getString("numeroUnico"));
        e.setEstatus(rs.getInt("estatus"));
        e.setUsuario(new Usuario());
        e.getUsuario().setContrasenia(rs.getString("contrasenia"));
        e.getUsuario().setIdUsuario(rs.getInt("idUsuario"));
        e.getUsuario().setNombre(rs.getString("nombreUsuario"));
        e.getUsuario().setRol(rs.getString("rol"));
        e.getUsuario().setLastToken(rs.getString("lastToken"));
        e.getUsuario().setDateLastToken(rs.getString("dateLastToken"));
        e.setNumeroUnico(rs.getString("numeroUnico"));

        e.setPersona(p);

        return e;
    }
    
    public void guardarToken(Empleado e)throws Exception{
        String query = "UPDATE usuario SET lastToken = ?, dateLastToken=NOW() WHERE idUsuario = ?";
        
        ConexionMySQL conexionMySQL = new ConexionMySQL();
        
        Connection connection = conexionMySQL.open();
        
        PreparedStatement preparedStatement = connection.prepareCall(query);
        
        preparedStatement.setString(1, e.getUsuario().getLastToken());
        preparedStatement.setInt(2, e.getUsuario().getIdUsuario());
        
        preparedStatement.execute();
        
        preparedStatement.close();
        connection.close();
        conexionMySQL.close();
    }
    
    public boolean eliminarToken(Empleado e)throws Exception{
        boolean r = false;
        String query = "UPDATE usuario SET lastToken='' WHERE idUsuario = ?";
        
        ConexionMySQL conexionMySQL = new ConexionMySQL();
        
        Connection connection = conexionMySQL.open();
        
        PreparedStatement preparedStatement = connection.prepareCall(query);
        
        preparedStatement.setInt(1, e.getUsuario().getIdUsuario());
        
        preparedStatement.execute();
        r=true;
        
        preparedStatement.close();
        connection.close();
        conexionMySQL.close();
        
        return r;   
    }
    
    public boolean validarToken(String t) throws Exception{
        boolean r = false;
        String query = "SELECT * FROM v_empleados WHERE lastToken='"+t+"'";
        
        ConexionMySQL con = new ConexionMySQL();
        Connection cone = con.open();
        Statement stmt = cone.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        if(rs.next()) r = true;
        
        stmt.close();
        cone.close();
        con.close();
        
        return r;
    }

    public boolean deleteToken(int idUsuario)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean deleteToken(int idUsuario)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}