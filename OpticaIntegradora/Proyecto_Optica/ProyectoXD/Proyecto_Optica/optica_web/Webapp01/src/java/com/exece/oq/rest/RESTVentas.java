/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.rest;

import com.exece.oq.core.ControllerEmpleado;
import com.exece.oq.core.ControllerSoluciones;
import com.exece.oq.core.ControllerVentas;
import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.DetalleVentaP;
import com.exece.oq.model.DetalleVentaPresupuesto;
import com.exece.oq.model.Empleado;
import com.exece.oq.model.Producto;
import com.exece.oq.model.Solucion;
import com.exece.oq.model.Venta;
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
import java.sql.SQLException;
import java.util.List;


@Path("ventas")
public class RESTVentas {
   
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductos(@QueryParam("filtro") String filtro) throws Exception{
        
        String out = "";
        ControllerVentas cv = new ControllerVentas();
        
        List<Producto> productos = cv.getAllProductos(filtro);
        
        return Response.status(Response.Status.OK).entity(productos).build();
        
    }
    
    @Path("insertarVenta")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarVenta(@FormParam("datosVenta") String jsonVenta) throws ClassNotFoundException, SQLException {
        
        String out = "";
        Gson gson = new Gson();
        
        ControllerVentas cv = new ControllerVentas();
        
        DetalleVentaP venta = gson.fromJson(jsonVenta, DetalleVentaP.class);
        
        boolean result = cv.generarVenta(venta);
        
        System.out.println(result);
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("insertarVentaLentes")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarVentaLentes(@FormParam("datosVentaLentes") String jsonVentaLentes) throws Exception{
        
        String out = "";
        Gson gson = new Gson();
        
        ControllerVentas cv = new ControllerVentas();
        
        DetalleVentaPresupuesto detalle = gson.fromJson(jsonVentaLentes, DetalleVentaPresupuesto.class);
        
        System.out.println(detalle.getLvp().get(0).getCantidad());
        
        boolean res = cv.generarVentaLente(detalle);
        
        System.out.println(res);
        
        return Response.status(Response.Status.OK).entity(out).build();
        
    }
}
