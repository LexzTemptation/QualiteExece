
package com.exece.oq.model;

public class Presupuesto
{
    int idPresupuesto;
    int idExamenVista;
    String clave;

    public Presupuesto(){}

    public Presupuesto(int idPresupuesto, int idExamenVista, String clave)
    {
        this.idPresupuesto = idPresupuesto;
        this.idExamenVista = idExamenVista;
        this.clave = clave;
    }

    public int getIdPresupuesto()
    {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto)
    {
        this.idPresupuesto = idPresupuesto;
    }

    public int getIdExamenVista()
    {
        return idExamenVista;
    }

    public void setIdExamenVista(int idExamenVista)
    {
        this.idExamenVista = idExamenVista;
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
