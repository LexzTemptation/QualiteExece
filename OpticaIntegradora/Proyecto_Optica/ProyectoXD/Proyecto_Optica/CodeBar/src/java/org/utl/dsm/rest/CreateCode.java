/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.rest;

import MySQL.ConnectionSerial;
import MySQL.ConnectionMySQL;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import org.utl.dsm.model.Usuario;
import java.sql.*;


@Path("alumnos")
public class CreateCode {
    
    ConnectionMySQL con = new ConnectionMySQL();
    Usuario alumno = new Usuario();
    ConnectionSerial serial = new ConnectionSerial();
    
    public void inicializar(){
        serial.conexion("COM3", 9600);
        serial.busDatos();
    }
    
    @Path("getAlumno")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCodeBar(@QueryParam("matricula") String matricula) throws ClassNotFoundException, SQLException, IOException, InterruptedException{
        
        Statement stmt = con.getConnection();
        
        ResultSet res = stmt.executeQuery("SELECT * FROM Alumnos where matricula = " + matricula);
        
        while(res.next()){
            
            alumno.setMatricula(res.getString("matricula"));
            alumno.setNombre(res.getString("nombre"));
            alumno.setApellidoPaterno(res.getString("apellidoPaterno"));
            alumno.setApellidoMaterno(res.getString("apellidoMaterno"));
            alumno.setGrupo(res.getString("grupo"));
            alumno.setCarrera(res.getString("carrera"));
            
        }
        
        Thread.sleep(1600);
        
        serial.enviarDatos(alumno.showAlumno());
        
        return Response.status(Response.Status.OK).entity(alumno).build();
    }
    
   
    
}
