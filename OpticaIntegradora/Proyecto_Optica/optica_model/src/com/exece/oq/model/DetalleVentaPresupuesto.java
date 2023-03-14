
package com.exece.oq.model;

import java.util.List;

public class DetalleVentaPresupuesto
{
    private Venta venta;
    private List<VentaPresupuesto> lvp;

    public DetalleVentaPresupuesto(){}

    public DetalleVentaPresupuesto(Venta venta, List<VentaPresupuesto> lvp)
    {
        this.venta = venta;
        this.lvp = lvp;
    }

    public Venta getVenta()
    {
        return venta;
    }

    public void setVenta(Venta venta)
    {
        this.venta = venta;
    }

    public List<VentaPresupuesto> getLvp()
    {
        return lvp;
    }

    public void setLvp(List<VentaPresupuesto> lvp)
    {
        this.lvp = lvp;
    }

    @Override
    public String toString()
    {
        return "DetalleVentaPresupuesto{" + "venta=" + venta + ", lvp=" + lvp + '}';
    }

    
}
