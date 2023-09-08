/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.model;

/**
 *
 * @author FELIPE.HERRERA
 */
public class PresupuestoLentesTratamiento {
    
    
    private int idPresupuestoLentes;
    private int idTratamiento;
    
    public PresupuestoLentesTratamiento(){}

    public PresupuestoLentesTratamiento(int idPresupuestoLentes, int idTratamiento) {
        this.idPresupuestoLentes = idPresupuestoLentes;
        this.idTratamiento = idTratamiento;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public int getIdPresupuestoLentes() {
        return idPresupuestoLentes;
    }

    public void setIdPresupuestoLentes(int idPresupuestoLentes) {
        this.idPresupuestoLentes = idPresupuestoLentes;
    }
    
    
    
}
