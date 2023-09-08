/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.rest;

import com.exece.oq.core.ControllerArmazon;
import com.exece.oq.model.Armazon;
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
import java.util.List;

/**
 *
 * @author jonai
 */
@Path("armazon")
public class RESTArmazon {
    
    @POST
    @Path("save")
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response save(@FormParam("datosArmazon") @DefaultValue("") String jsonArmazon){
        String out = null;
        Gson gson = null;
        Armazon arm = null;
        ControllerArmazon ca = null;
        
        try{
            gson = new Gson();
            
            arm = gson.fromJson(jsonArmazon, Armazon.class);
            ca = new ControllerArmazon();
            
            if(arm.getIdArmazon() == 0){
                ca.insert(arm);
            }else{
                ca.update(arm);
            }
            out = gson.toJson(arm);
        }catch(JsonParseException jpe){
            jpe.printStackTrace();
            out = "{\"exception\":\"Error en los datos introducidos o de formato.\"}";
        } catch(Exception e){
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
        ControllerArmazon ca = null;
        List<Armazon> armazon = null;
        
        try {
            ca = new ControllerArmazon();
            armazon = ca.getAll(filtro);
            out = new Gson().toJson(armazon);
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @GET
    @Path("deleteArmazon")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarArmazon(@QueryParam("idArmazon") int id) throws Exception{
        
        ControllerArmazon ca = new ControllerArmazon();
        String out = "";
        int respuesta = ca.delete(id);
        
        if (respuesta == 1) {
            out = """
                  {"message":"Se ha eliminado el Armazon de manera lógica"}
                  """;
        }else{
            out = """
                  {"message":"No se pudo eliminar el Armazon"}
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
}
