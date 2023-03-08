/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.rest;
import com.exece.oq.core.ControllerProductos;
import com.exece.oq.core.ControllerVentasProducto;
import com.exece.oq.model.DetalleVentaProducto;
import com.exece.oq.model.Empleado;
import com.exece.oq.model.Producto;
import com.exece.oq.model.Venta;
import com.exece.oq.model.VentaProducto;
import com.google.gson.Gson;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author adria
 */
@Path("venta")
public class RESTVentas {

    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response buscarPro() {
        ControllerProductos cp = new ControllerProductos();
        List<Producto> productos = cp.getAll("");
        Gson gson = new Gson();
        
        String out = gson.toJson(productos);
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("venta")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response venta(){
        String out = """
                     """;
        ControllerVentasProducto cvp = new ControllerVentasProducto();
        Producto producto;
        Empleado empleado;
        Venta venta;
        VentaProducto vntProducto;
        DetalleVentaProducto dvp;
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("insertarVenta")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarVenta(@FormParam("datosVenta") String jsonVenta) throws ClassNotFoundException, SQLException{
        
        String out = "";
        Gson gson = new Gson();
        
        ControllerVentasProducto cv = new ControllerVentasProducto();
        
        DetalleVentaProducto venta = gson.fromJson(jsonVenta, DetalleVentaProducto.class);
       
        System.out.println(jsonVenta);
        
        
        boolean result = cv.generarVenta(venta);
        System.out.println(result);
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
