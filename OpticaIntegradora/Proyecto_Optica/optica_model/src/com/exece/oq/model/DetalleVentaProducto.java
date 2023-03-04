/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.model;

import java.util.List;

/**
 *
 * @author adria
 */
public class DetalleVentaProducto {
    Venta venta;
    List<VentaProducto> productos;

    public DetalleVentaProducto() {
    }

    public DetalleVentaProducto(Venta venta, List<VentaProducto> productos) {
        this.venta = venta;
        this.productos = productos;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<VentaProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<VentaProducto> productos) {
        this.productos = productos;
    }

    public String toString(){
        String msg="";
        for(int i=0; i<productos.size();i++){
            msg += productos.get(i).getProducto().getNombre();
        }
        return "DetalleVentaProducto{venta: " + venta.toString() + ", productos: " + msg;
    }
    
}
