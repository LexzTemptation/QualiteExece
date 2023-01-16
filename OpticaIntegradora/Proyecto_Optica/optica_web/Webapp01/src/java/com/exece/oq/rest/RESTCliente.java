/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.rest;

import com.exece.oq.core.ControllerCliente;
import com.exece.oq.model.Cliente;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;
/**
 *
 * @author adria
 */
@Path("cliente")
public class RESTCliente {
    
    @POST
    @Path("save")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@FormParam("datosCliente") @DefaultValue("") String jsonCliente)
    {
        String out = null;
        Gson gson = null;
        Cliente cli = null;
        ControllerCliente cc= null;
        try{
            gson = new Gson();
            cli = gson.fromJson(jsonCliente, Cliente.class);
            cc = new ControllerCliente();
            
            if(cli.getIdCliente()==0) cc.insert(cli);    
            else cc.update(cli);
            out = gson.toJson(cli);
        }
        catch(JsonParseException jpe){
            jpe.printStackTrace();
            out = "{\"exception\":\"Error en los datos introducidos o de formato.\"}";
        }
        catch(Exception e){
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";            
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
        public Response getAll(@QueryParam("filtro") @DefaultValue("") String filtro){
        String out = null;
        ControllerCliente cc = null;
        List<Cliente> clientes = null;
        try{
            cc = new ControllerCliente();
            clientes = cc.getAll(filtro);
            out = new Gson().toJson(clientes);
        }catch(Exception e){
            e.printStackTrace();
            out =  "{\"exception\":\"Error interno del servidor.\"}";  
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(){
        String out ="todo";
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @GET
    @Path("deleteCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarCliente(@QueryParam("idCliente") int id) throws Exception{
        ControllerCliente cc = new ControllerCliente();
        
        String out ="";
        
        int respuesta = cc.delete(id);
        
        if(respuesta==1){
            out="""
                {"message":"user has been delete"}
                """;
        }else {
            out="""
                {"message":"user hasn't been delete"}
                """;            
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
