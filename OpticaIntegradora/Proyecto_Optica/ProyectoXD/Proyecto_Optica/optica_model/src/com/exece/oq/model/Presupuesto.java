/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.model;

/**
 *
 * @author FELIPE.HERRERA
 */
public class Presupuesto {
    
    private int idPresupuesto;
    private int idExamenVista;
    private String clave;
    private PresupuestoLentes presupuestoLentes;
    
    public Presupuesto(){}

    public Presupuesto(int idPresupuesto, int idExamenVista, String clave, PresupuestoLentes presupuestoLentes) {
        this.idPresupuesto = idPresupuesto;
        this.idExamenVista = idExamenVista;
        this.clave = clave;
        this.presupuestoLentes = presupuestoLentes;
    }

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public int getIdExamenVista() {
        return idExamenVista;
    }

    public void setIdExamenVista(int idExamenVista) {
        this.idExamenVista = idExamenVista;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public PresupuestoLentes getPresupuestoLentes() {
        return presupuestoLentes;
    }

    public void setPresupuestoLentes(PresupuestoLentes presupuestoLentes) {
        this.presupuestoLentes = presupuestoLentes;
    }

    
   
    
   
        
}
