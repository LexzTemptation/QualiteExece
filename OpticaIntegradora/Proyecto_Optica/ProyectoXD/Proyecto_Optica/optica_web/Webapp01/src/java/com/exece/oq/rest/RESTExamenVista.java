/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.rest;

import com.exece.oq.core.ControllerExamenVista;
import com.exece.oq.model.ExamenVista;
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

@Path("examenVista")
public class RESTExamenVista {
    
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws Exception{
        
        ControllerExamenVista ce = new ControllerExamenVista();
        
        List<ExamenVista> listaExamenVista = ce.getAll();
        
        return Response.status(Response.Status.OK).entity(listaExamenVista).build();
    }
    
}
