/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.rest;

import com.exece.oq.model.Empleado;
import com.exece.oq.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.exece.oq.core.ControllerEmpleado;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author FELIPE.HERRERA
 */
@Path("empleado")
public class RESTEmpleado {

    /*
        Establecemos el tipo de metodo que tendra la ruta
        Establcemos el nombre de la ruta
        Establecemos el tipo de inforamciom que retornara la ruta en este caso ser Json
     */
    
    @POST
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response logout(@FormParam("datosEmpleado") @DefaultValue("") String jsonEmpleado) throws Exception{
        
        String out = "";
        Gson gson = new Gson();
        Empleado emp = new Empleado();
        ControllerEmpleado ce = new ControllerEmpleado();
        
        emp = gson.fromJson(jsonEmpleado, Empleado.class);
        
        boolean res = ce.deleteToken(emp.getUsuario().getIdUsuario());
        
        if(res) {
            out = """
                  {"message":"all ok"}
                  """;
        }
        else{
            out = """
                  {"message": "bad"}
                  """;
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    @POST
    @Path("save")
    @Produces(MediaType.APPLICATION_JSON)

    //Establecemos dentro de los parentesis del metodo los parametros que recibiremos 
    public Response save(@FormParam("datosEmpleado") @DefaultValue("") String jsonEmpleado) throws Exception {

        String out = "";
        Gson gson = null;
        Empleado emp = null;

        //Este objeto nos permitira acceder a los metodos que se encuentran en el ControllerEmpleado
        ControllerEmpleado ce = null;

        //Convetiremos el json que llega por paramtro en un objeto de tipo empleado con la ayuda de la libreria Gson
        try {

            gson = new Gson();
            emp = gson.fromJson(jsonEmpleado, Empleado.class);
            ce = new ControllerEmpleado();

            //Evaluaremos el idEmpleado en caso de ser igual a 0 significa que quieren registrar un empleado
            //En caso de ser diferente de 0 significa que ya exite y por ende quieren actualizar 
            if (emp.getIdEmpleado() == 0) {
                ce.insert(emp);
            } else {
                ce.update(emp);
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
        out = gson.toJson(emp);

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

        //Este objeto nos permitira acceder a los metodos que se encuentran en el ControllerEmpleado
        ControllerEmpleado ce = null;
        List<Empleado> empleados = null;

        //Mandaremos a llamar el metodo getAll para traer los empleados 
        //Convertiremos el objeto de tipo empleado en Json para enviarselo como respuesta al frontend
        try {
            ce = new ControllerEmpleado();
            empleados = ce.getAll(filtro);
            out = new Gson().toJson(empleados);
        } catch (Exception e) {

            e.printStackTrace();
            out = "{\"excepcion\": \"Error interno del servidor.\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

    /*
        Establecemos el tipo de metodo que tendra la ruta
        Establcemos el nombre de la ruta
        Establecemos el tipo de inforamciom que retornara la ruta en este caso ser Json
     */
    @GET
    @Path("/deleteEmpleado")
    @Produces(MediaType.APPLICATION_JSON)

    //Establecemos dentro de los parentesis del metodo los parametros que recibiremos 
    public Response borrarEmpleado(@QueryParam("idEmpleado") int id) throws Exception {

        //Este objeto nos permitira acceder a los metodos que se encuentran en el ControllerEmpleado
        ControllerEmpleado ce = new ControllerEmpleado();
        String out = " ";

        //Utilizaremos el metodo delete y evaluaremos la respuesta
        int respuesta = ce.delete(id);

        //Si es 1 significa que se borro y si es diferente de 1 significa que no pudo borrar
        if (respuesta == 1) {
            out = """
                  {"message": "user has been delete"}
                  """;
        } else {
            out = """
                  {"message": "hasn´t been delete"}
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
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)

    //Establecemos dentro de los parentesis del metodo los parametros que recibiremos 
    public Response login(@QueryParam("user") String user, @QueryParam("password") String password) throws Exception {

        //Este objeto nos permitira acceder a los metodos que se encuentran en el ControllerEmpleado
        ControllerEmpleado ce = new ControllerEmpleado();
        String out = "";

        //Mandamos a llamar el metodo login para pasando los parametros de user y password para saber si el empleado esta dentro de la base de datos
        //Como respuesta por parte del metodo recibiremos un json con los datos del empleado logueado e caso de que este bien
        //Si esta mal recibiremos un mensaje que nos diga si el usuario o el password estan mal 
        out = ce.login(user, password);

        return Response.status(Response.Status.OK).entity(out).build();
    }
    
}
