/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.model;

/**
 *
 * @author FELIPE.HERRERA
 */
public class Graduacion {
    
    private int idGraduacion;
    private String esferaod;
    private String esferaoi;
    private String cilindrood;
    private String cilindrooi;
    private String ejeod;
    private String ejeoi;
    private String dip;
    
    public Graduacion(){}

    public Graduacion(int idGraduacion, String esferaod, String esferaoi, String cilindrood, String cilindrooi, String ejeod, String ejeoi, String dip) {
        this.idGraduacion = idGraduacion;
        this.esferaod = esferaod;
        this.esferaoi = esferaoi;
        this.cilindrood = cilindrood;
        this.cilindrooi = cilindrooi;
        this.ejeod = ejeod;
        this.ejeoi = ejeoi;
        this.dip = dip;
    }

    public String getDip() {
        return dip;
    }

    public void setDip(String dip) {
        this.dip = dip;
    }

    public int getIdGraduacion() {
        return idGraduacion;
    }

    public void setIdGraduacion(int idGraduacion) {
        this.idGraduacion = idGraduacion;
    }

    public String getEsferaod() {
        return esferaod;
    }

    public void setEsferaod(String esferaod) {
        this.esferaod = esferaod;
    }

    public String getEsferaoi() {
        return esferaoi;
    }

    public void setEsferaoi(String esferaoi) {
        this.esferaoi = esferaoi;
    }

    public String getCilindrood() {
        return cilindrood;
    }

    public void setCilindrood(String cilindrood) {
        this.cilindrood = cilindrood;
    }

    public String getCilindrooi() {
        return cilindrooi;
    }

    public void setCilindrooi(String cilindrooi) {
        this.cilindrooi = cilindrooi;
    }

    public String getEjeod() {
        return ejeod;
    }

    public void setEjeod(String ejeod) {
        this.ejeod = ejeod;
    }

    public String getEjeoi() {
        return ejeoi;
    }

    public void setEjeoi(String ejeoi) {
        this.ejeoi = ejeoi;
    }
    
    
}
