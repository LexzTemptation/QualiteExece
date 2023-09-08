/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.Empleado;
import com.exece.oq.model.Persona;
import com.exece.oq.model.Usuario;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControllerAcesso {
    
    public Empleado acceder(Usuario u) throws Exception{
        
        Empleado emp = new Empleado();
        
        //1.-Gerar la query
        String query = "SELECT * from v_empleados where nombre = ? AND contrasenia = ?";
        
        //2.-Generar una conexion a la BD
        ConexionMySQL objCon = new ConexionMySQL();
        
        // 3.-Hacer la conexion
        Connection con = objCon.open();
        
        //4.- Preparar la sentencia
        PreparedStatement pstmt = con.prepareStatement(query);
        
        //5.-Agregar los parametros
        pstmt.setString(1, u.getNombre());
        pstmt.setString(2, u.getContrasenia());
        
        //6.-Ejecutar la query
        ResultSet res = pstmt.executeQuery();
        
        if(res.next()){
            while(res.next()){
                
               emp = fill(res);
            }
        }
        
        pstmt.close();
        con.close();
        objCon.close();
        
        return null;
    }
    
    
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
    
}
