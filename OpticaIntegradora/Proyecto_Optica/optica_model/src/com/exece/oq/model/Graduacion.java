/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.model;

/**
 *
 * @author jonai
 */
public class Graduacion
{
    private int idGraduacion;
    private Double esferaod;
    private Double esferaoi;
    private int cilindrood;
    private int cilindrooi;
    private int ejeod;
    private int ejeoi;
    private String dip;

    public Graduacion()
    {
    }

    public Graduacion(int idGraduacion, Double esferaod, Double esferaoi, int cilindrood, int cilindrooi, int ejeod, int ejeoi, String dip)
    {
        this.idGraduacion = idGraduacion;
        this.esferaod = esferaod;
        this.esferaoi = esferaoi;
        this.cilindrood = cilindrood;
        this.cilindrooi = cilindrooi;
        this.ejeod = ejeod;
        this.ejeoi = ejeoi;
        this.dip = dip;
    }

    public Graduacion(Double esferaod, Double esferaoi, int cilindrood, int cilindrooi, int ejeod, int ejeoi, String dip)
    {
        this.esferaod = esferaod;
        this.esferaoi = esferaoi;
        this.cilindrood = cilindrood;
        this.cilindrooi = cilindrooi;
        this.ejeod = ejeod;
        this.ejeoi = ejeoi;
        this.dip = dip;
    }

    public int getIdGraduacion()
    {
        return idGraduacion;
    }

    public void setIdGraduacion(int idGraduacion)
    {
        this.idGraduacion = idGraduacion;
    }

    public Double getEsferaod()
    {
        return esferaod;
    }

    public void setEsferaod(Double esferaod)
    {
        this.esferaod = esferaod;
    }

    public Double getEsferaoi()
    {
        return esferaoi;
    }

    public void setEsferaoi(Double esferaoi)
    {
        this.esferaoi = esferaoi;
    }

    public int getCilindrood()
    {
        return cilindrood;
    }

    public void setCilindrood(int cilindrood)
    {
        this.cilindrood = cilindrood;
    }

    public int getCilindrooi()
    {
        return cilindrooi;
    }

    public void setCilindrooi(int cilindrooi)
    {
        this.cilindrooi = cilindrooi;
    }

    public int getEjeod()
    {
        return ejeod;
    }

    public void setEjeod(int ejeod)
    {
        this.ejeod = ejeod;
    }

    public int getEjeoi()
    {
        return ejeoi;
    }

    public void setEjeoi(int ejeoi)
    {
        this.ejeoi = ejeoi;
    }

    public String getDip()
    {
        return dip;
    }

    public void setDip(String dip)
    {
        this.dip = dip;
    }

    @Override
    public String toString()
    {
        return "Graduacion{" + "idGraduacion=" + idGraduacion + ", esferaod=" + esferaod + ", esferaoi=" + esferaoi + ", cilindrood=" + cilindrood + ", cilindrooi=" + cilindrooi + ", ejeod=" + ejeod + ", ejeoi=" + ejeoi + ", dip=" + dip + '}';
    }
    
    
}
