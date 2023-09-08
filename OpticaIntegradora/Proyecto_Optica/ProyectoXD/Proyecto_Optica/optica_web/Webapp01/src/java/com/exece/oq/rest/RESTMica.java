/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.rest;

import com.exece.oq.core.ControllerMica;
import com.exece.oq.model.TipoMica;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author FELIPE.HERRERA
 */

@Path("mica")
public class RESTMica {
    
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws Exception{
        
        ControllerMica cm = new ControllerMica();
        
        List<TipoMica> listaMicas = cm.getAll();
        
        return Response.status(Response.Status.OK).entity(listaMicas).build();
        
    }
}
