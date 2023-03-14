
package com.exece.oq.model;

public class Presupuesto
{
    private int idPresupuesto;
    private ExamenVista examenVista;
    private String clave;
    private PresupuestoLentes presupuestoLentes;

    public Presupuesto()
    {
    }

    public Presupuesto(int idPresupuesto, ExamenVista examenVista, String clave, PresupuestoLentes presupuestoLentes)
    {
        this.idPresupuesto = idPresupuesto;
        this.examenVista = examenVista;
        this.clave = clave;
        this.presupuestoLentes = presupuestoLentes;
    }

    public Presupuesto(ExamenVista examenVista, String clave, PresupuestoLentes presupuestoLentes)
    {
        this.examenVista = examenVista;
        this.clave = clave;
        this.presupuestoLentes = presupuestoLentes;
    }

    public int getIdPresupuesto()
    {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto)
    {
        this.idPresupuesto = idPresupuesto;
    }

    public ExamenVista getExamenVista()
    {
        return examenVista;
    }

    public void setExamenVista(ExamenVista examenVista)
    {
        this.examenVista = examenVista;
    }

    public String getClave()
    {
        return clave;
    }

    public void setClave(String clave)
    {
        this.clave = clave;
    }

    public PresupuestoLentes getPresupuestoLentes()
    {
        return presupuestoLentes;
    }

    public void setPresupuestoLentes(PresupuestoLentes presupuestoLentes)
    {
        this.presupuestoLentes = presupuestoLentes;
    }

    @Override
    public String toString()
    {
        return "Presupuesto{" + "idPresupuesto=" + idPresupuesto + ", examenVista=" + examenVista + ", clave=" + clave + ", presupuestoLentes=" + presupuestoLentes + '}';
    }

    
}
