/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.rest;

import com.exece.oq.core.ControllerEmpleado;
import com.exece.oq.core.ControllerSoluciones;
import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.Empleado;
import com.exece.oq.model.Solucion;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

@Path("soluciones")
public class RESTSoluciones {

    /*
        Establecemos el tipo de metodo que tendra la ruta
        Establcemos el nombre de la ruta
        Establecemos el tipo de inforamciom que retornara la ruta en este caso ser Json
     */
    @POST
    @Path("save")
    @Produces(MediaType.APPLICATION_JSON)

    //Establecemos dentro de los parentesis del metodo los parametros que recibiremos 
    public Response save(@FormParam("datosSolucion") String jsonSolucion) throws Exception {

        String out = "";
        Gson gson = null;
        Solucion sol = null;

        //Este objeto nos permitira acceder a los metodos que se encuentran en el ControllerSoluciones
        ControllerSoluciones cs = null;

        try {

            //Convetiremos el json que llega por paramtro en un objeto de tipo solucion con la ayuda de la libreria Gson
            gson = new Gson();
            sol = gson.fromJson(jsonSolucion, Solucion.class);
            cs = new ControllerSoluciones();

            //Evaluaremos el idSolucion en caso de ser igual a 0 significa que quieren registrar un empleado
            //En caso de ser diferente de 0 significa que ya exite y por ende quieren actualizar 
            if (sol.getIdSolucion() == 0) {
                cs.insert(sol);
            } else {
                cs.update(sol);
            }

        } catch (JsonParseException jpe) {
            jpe.printStackTrace();
            
            out = """
                  {"message": "Error en los datos introducidos o de formato"}
                  """;
        }catch(Exception e){
            out = """
                  {"message": "Error interno del servidor"}
                  """;
        }

        //Convertirmos el objeto de tipo empleado a Json para enviarlo al frontend
        out = gson.toJson(sol);

        return Response.status(Response.Status.OK).entity(out).build();

    }

    /*
        Establecemos el tipo de metodo que tendra la ruta
        Establcemos el nombre de la ruta
        Establecemos el tipo de inforamciom que retornara la ruta en este caso ser Json
     */
    @GET
    @Path("/deleteSolucion")
    @Produces(MediaType.APPLICATION_JSON)

    //Establecemos dentro de los parentesis del metodo los parametros que recibiremos 
    public Response borrarEmpleado(@QueryParam("idProducto") int id) throws Exception {

        //Este objeto nos permitira acceder a los metodos que se encuentran en el ControllerSoluciones
        ControllerSoluciones ce = new ControllerSoluciones();
        String out = "";

        //Utilizaremos el metodo delete y evaluaremos la respuesta
        int respuesta = ce.delete(id);

        //Si es 1 significa que se borro y si es diferente de 1 significa que no pudo borrar
        if (respuesta == 1) {
            out = """
                  {"message": "user has been delete"}
                  """;
        } else {
            out = """
                  {"message": "something have been hapened"}
                  """;
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

    /*
        Establecemos el tipo de metodo que tendra la ruta
        Establcemos el nombre de la ruta
        Establecemos el tipo de inforamciom que retornara la ruta en este caso ser Json
     */
    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)

    //Establecemos dentro de los parentesis del metodo los parametros que recibiremos 
    public Response getAll(@QueryParam("filtro") @DefaultValue("0") String filtro) {

        String out = null;

        //Este objeto nos permitira acceder a los metodos que se encuentran en el ControllerSoluciones
        ControllerSoluciones cs = null;
        List<Solucion> soluciones = null;

        //Mandaremos a llamar el metodo getAll para traer los empleados 
        //Convertiremos el objeto de tipo empleado en Json para enviarselo como respuesta al frontend
        try {
            cs = new ControllerSoluciones();
            soluciones = cs.getAll(filtro);
            out = new Gson().toJson(soluciones);
        } catch (Exception e) {

            e.printStackTrace();
            out = "{\"excepcion\": \"Error interno del servidor.\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @GET
    @Path("insertIMG")
    @Produces(MediaType.APPLICATION_JSON)
    public void insertIMG(){
        
    }

}
