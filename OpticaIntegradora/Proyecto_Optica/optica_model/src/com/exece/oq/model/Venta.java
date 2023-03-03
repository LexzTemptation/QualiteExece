
package com.exece.oq.model;

public class Venta
{
    private int idVenta;
    private int idEmpleado;
    String clave;

    public Venta(){}

    public Venta(int idVenta, int idEmpleado, String clave)
    {
        this.idVenta = idVenta;
        this.idEmpleado = idEmpleado;
        this.clave = clave;
    }

    public int getIdVenta()
    {
        return idVenta;
    }

    public void setIdVenta(int idVenta)
    {
        this.idVenta = idVenta;
    }

    public int getIdEmpleado()
    {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado)
    {
        this.idEmpleado = idEmpleado;
    }

    public String getClave()
    {
        return clave;
    }

    public void setClave(String clave)
    {
        this.clave = clave;
    }
    
    
    
}
