/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.model;

import java.util.List;

/**
 *
 * @author FELIPE.HERRERA
 */
public class DetalleVentaP {
    
    private Venta venta;
    private List<VentaProducto> vp;

    public DetalleVentaP(Venta venta, List<VentaProducto> vp) {
        this.venta = venta;
        this.vp = vp;
    }
    
    public DetalleVentaP(){}

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<VentaProducto> getVp() {
        return vp;
    }

    public void setVp(List<VentaProducto> vp) {
        this.vp = vp;
    }

    @Override
    public String toString() {
        
        String mensaje="";
        
        for (int i = 0; i < vp.size(); i++) {
            
            mensaje +=  vp.get(i);
            
        }

        return "DetalleVentaP{" + "venta=" + venta.toString() + ", Lista de ventas =" + mensaje + '}';
    }
    
    
    
}
