/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.model;

import java.util.List;

/**
 *
 * @author jonai
 */
public class PresupuestoLentes
{
    private int idPresupuestoLentes;
    private int alturaOblea;
    private TipoMica tipoMica;
    private Material material;
    private Armazon armazon;
    private List<PresupuestoLentesTratamientos> lplt;

    public PresupuestoLentes()
    {
    }

    public PresupuestoLentes(int idPresupuestoLentes, int alturaOblea, TipoMica tipoMica, Material material, Armazon armazon, List<PresupuestoLentesTratamientos> lplt)
    {
        this.idPresupuestoLentes = idPresupuestoLentes;
        this.alturaOblea = alturaOblea;
        this.tipoMica = tipoMica;
        this.material = material;
        this.armazon = armazon;
        this.lplt = lplt;
    }

    public PresupuestoLentes(int alturaOblea, TipoMica tipoMica, Material material, Armazon armazon, List<PresupuestoLentesTratamientos> lplt)
    {
        this.alturaOblea = alturaOblea;
        this.tipoMica = tipoMica;
        this.material = material;
        this.armazon = armazon;
        this.lplt = lplt;
    }

    public int getIdPresupuestoLentes()
    {
        return idPresupuestoLentes;
    }

    public void setIdPresupuestoLentes(int idPresupuestoLentes)
    {
        this.idPresupuestoLentes = idPresupuestoLentes;
    }

    public int getAlturaOblea()
    {
        return alturaOblea;
    }

    public void setAlturaOblea(int alturaOblea)
    {
        this.alturaOblea = alturaOblea;
    }

    public TipoMica getTipoMica()
    {
        return tipoMica;
    }

    public void setTipoMica(TipoMica tipoMica)
    {
        this.tipoMica = tipoMica;
    }

    public Material getMaterial()
    {
        return material;
    }

    public void setMaterial(Material material)
    {
        this.material = material;
    }

    public Armazon getArmazon()
    {
        return armazon;
    }

    public void setArmazon(Armazon armazon)
    {
        this.armazon = armazon;
    }

    public List<PresupuestoLentesTratamientos> getLplt()
    {
        return lplt;
    }

    public void setLplt(List<PresupuestoLentesTratamientos> lplt)
    {
        this.lplt = lplt;
    }

    @Override
    public String toString()
    {
        return "PresupuestoLentes{" + "idPresupuestoLentes=" + idPresupuestoLentes + ", alturaOblea=" + alturaOblea + ", tipoMica=" + tipoMica + ", material=" + material + ", armazon=" + armazon + ", lplt=" + lplt + '}';
    }

    
}
