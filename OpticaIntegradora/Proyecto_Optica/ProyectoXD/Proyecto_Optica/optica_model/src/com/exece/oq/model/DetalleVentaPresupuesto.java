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
public class DetalleVentaPresupuesto {
    
    private Venta venta;
    private List<VentaPresupuesto> lvp;
    
    public DetalleVentaPresupuesto(){}

    public DetalleVentaPresupuesto(Venta venta, List<VentaPresupuesto> lvp) {
        this.venta = venta;
        this.lvp = lvp;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<VentaPresupuesto> getLvp() {
        return lvp;
    }

    public void setLvp(List<VentaPresupuesto> lvp) {
        this.lvp = lvp;
    }
    
    
}
