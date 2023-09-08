/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.rest;

import com.exece.oq.core.ControllerTratamiento;
import com.exece.oq.model.Tratamiento;
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
@Path("tratamiento")
public class RESTTratamiento {
    
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws Exception{ 
        
        ControllerTratamiento ct = new ControllerTratamiento();
        
        List<Tratamiento> listaTratamientos = ct.getAll();
        
        return Response.status(Response.Status.OK).entity(listaTratamientos).build();
    }
    
}
