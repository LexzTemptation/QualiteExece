/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.rest;

import com.exece.oq.core.ControllerLente_contacto;
import com.exece.oq.model.Lente_contacto;
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
 * @author david
 */
@Path("lente_contacto")
public class RESTLenteContacto {
    @POST
    @Path("save")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@FormParam("datosLenteContacto") @DefaultValue("") String datosLenteContacto){
        String out = null;
        Gson gson = new Gson();
        Lente_contacto len = new Lente_contacto();
        ControllerLente_contacto cl = new ControllerLente_contacto();
        try {
            len = gson.fromJson(datosLenteContacto, Lente_contacto.class);
            if (len.getIdLenteContacto() == 0){
                cl.insert(len);
                System.out.println("entré al if");
            }
            else{
                cl.update(len);
            }
        }
        catch(JsonParseException jpe){
            jpe.printStackTrace();
            out = """
                  {"message": "Error en los datos introducidos o de formato"}
                  """;
        }
        catch(Exception e){
            out = """
                  {"message": "Error interno del servidor"}
                  """;
        }
        out = gson.toJson(len);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("filtro") @DefaultValue("0") String filtro) throws Exception{
        String out = null;
        ControllerLente_contacto cl = null;
        List<Lente_contacto> lentes_contacto = null;
        try{
            cl = new ControllerLente_contacto();
            lentes_contacto = cl.getAll(filtro);
            out = new Gson().toJson(lentes_contacto);
        }
        catch (Exception e) {
            e.printStackTrace();
            out = "{\"excepcion\": \"Error interno del servidor.\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @GET
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("idLenteContacto") int idLenteContacto) throws Exception{
        String out = "";
        ControllerLente_contacto cl = new ControllerLente_contacto();
        try{
            int respuesta = cl.delete(idLenteContacto);
            if (respuesta == 1){
                out = """
                      {"Mensaje":"Lentes eliminados con éxito"}
                      """;
            }
            else{
                out = """
                      {"Mensaje":"¡Error! Inténtelo más tarde"}
                      """;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
