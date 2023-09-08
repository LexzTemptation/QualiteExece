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
public class PresupuestoLentes {
    
    private int idPresupuestoLentes;
    private String alturaOblea;
    private int idTipoMica;
    private int idMaterial;
    private int idArmazon;
    private List<PresupuestoLentesTratamiento> lplt;
    
   public PresupuestoLentes(){}

    public PresupuestoLentes(int idPresupuestoLentes, String alturaOblea, int idTipoMica, int idMaterial, int idArmazon, List<PresupuestoLentesTratamiento> lplt) {
        this.idPresupuestoLentes = idPresupuestoLentes;
        this.alturaOblea = alturaOblea;
        this.idTipoMica = idTipoMica;
        this.idMaterial = idMaterial;
        this.idArmazon = idArmazon;
        this.lplt = lplt;
    }

    public int getIdPresupuestoLentes() {
        return idPresupuestoLentes;
    }

    public void setIdPresupuestoLentes(int idPresupuestoLentes) {
        this.idPresupuestoLentes = idPresupuestoLentes;
    }

    public String getAlturaOblea() {
        return alturaOblea;
    }

    public void setAlturaOblea(String alturaOblea) {
        this.alturaOblea = alturaOblea;
    }

    public int getIdTipoMica() {
        return idTipoMica;
    }

    public void setIdTipoMica(int idTipoMica) {
        this.idTipoMica = idTipoMica;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public int getIdArmazon() {
        return idArmazon;
    }

    public void setIdArmazon(int idArmazon) {
        this.idArmazon = idArmazon;
    }

    public List<PresupuestoLentesTratamiento> getLplt() {
        return lplt;
    }

    public void setLplt(List<PresupuestoLentesTratamiento> lplt) {
        this.lplt = lplt;
    }
    
   
    
}
