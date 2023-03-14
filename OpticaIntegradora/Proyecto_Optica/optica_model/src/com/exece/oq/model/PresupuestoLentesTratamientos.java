/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.model;

/**
 *
 * @author jonai
 */
public class PresupuestoLentesTratamientos
{
 
    private int idPresupuestoLentesTratamientos;
    private Tratamiento tratamiento;

    public PresupuestoLentesTratamientos()
    {
    }
    
    public PresupuestoLentesTratamientos(int idPresupuestoLentesTratamientos, Tratamiento tratamiento)
    {
        this.idPresupuestoLentesTratamientos = idPresupuestoLentesTratamientos;
        this.tratamiento = tratamiento;
    }

    public PresupuestoLentesTratamientos(Tratamiento tratamiento)
    {
        this.tratamiento = tratamiento;
    }

    public Tratamiento getTratamiento()
    {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento)
    {
        this.tratamiento = tratamiento;
    }

    public int getIdPresupuestoLentesTratamientos()
    {
        return idPresupuestoLentesTratamientos;
    }

    public void setIdPresupuestoLentesTratamientos(int idPresupuestoLentesTratamientos)
    {
        this.idPresupuestoLentesTratamientos = idPresupuestoLentesTratamientos;
    }

    @Override
    public String toString()
    {
        return "PresupuestoLentesTratamientos{" + "idPresupuestoLentesTratamientos=" + idPresupuestoLentesTratamientos + ", tratamiento=" + tratamiento + '}';
    }
    
    
}
