/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.model;

/**
 *
 * @author FELIPE.HERRERA
 */
public class ExamenVista {
    
    private int idExamenVista;
    private String clave;
    private int idEmpleado;
    private int idCliente;
    private int idGraduacion;
    private String fecha;
    
    public ExamenVista(){}

    public ExamenVista(int idExamenVista, String clave, int idEmpleado, int idCliente, int idGraduacion, String fecha) {
        this.idExamenVista = idExamenVista;
        this.clave = clave;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
        this.idGraduacion = idGraduacion;
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdGraduacion() {
        return idGraduacion;
    }

    public void setIdGraduacion(int idGraduacion) {
        this.idGraduacion = idGraduacion;
    }
    
    
    
}
