
package com.exece.oq.model;

public class VentaPresupuesto
{
    private Venta venta;
    private Presupuesto presupuesto;
    private int cantidad;
    private float precioUnitario;
    private float descuento;
    
    public VentaPresupuesto(){}

    public VentaPresupuesto(Venta venta, Presupuesto presupuesto, int cantidad, float precioUnitario, float descuento)
    {
        this.venta = venta;
        this.presupuesto = presupuesto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
    }

    public Venta getVenta()
    {
        return venta;
    }

    public void setVenta(Venta venta)
    {
        this.venta = venta;
    }

    public Presupuesto getPresupuesto()
    {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto)
    {
        this.presupuesto = presupuesto;
    }

    public int getCantidad()
    {
        return cantidad;
    }

    public void setCantidad(int cantidad)
    {
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario()
    {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario)
    {
        this.precioUnitario = precioUnitario;
    }

    public float getDescuento()
    {
        return descuento;
    }

    public void setDescuento(float descuento)
    {
        this.descuento = descuento;
    }
    
    
}
