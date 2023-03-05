
package com.exece.oq.model;

import java.util.List;

public class DetalleVentaPresupuesto
{
    private Venta venta;
    private List<VentaPresupuesto> ventaPresupuesto;

    public DetalleVentaPresupuesto(){}

    public DetalleVentaPresupuesto(Venta venta, List<VentaPresupuesto> ventaPresupuesto)
    {
        this.venta = venta;
        this.ventaPresupuesto = ventaPresupuesto;
    }

    public Venta getVenta()
    {
        return venta;
    }

    public void setVenta(Venta venta)
    {
        this.venta = venta;
    }

    public List<VentaPresupuesto> getVentaPresupuesto()
    {
        return ventaPresupuesto;
    }

    public void setVentaPresupuesto(List<VentaPresupuesto> ventaPresupuesto)
    {
        this.ventaPresupuesto = ventaPresupuesto;
    }
    
    
    
}
