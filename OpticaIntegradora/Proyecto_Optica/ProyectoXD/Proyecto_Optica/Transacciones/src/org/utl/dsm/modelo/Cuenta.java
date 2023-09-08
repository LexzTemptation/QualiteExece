/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.modelo;

/**
 *
 * @author FELIPE.HERRERA
 */
public class Cuenta {
    
    private int idCuenta;
    private String numCuenta;
    private String fechaCreacion;
    private String sucursal;
    private Cliente cliente;
    
    public Cuenta(String numCuenta, String fechaCreacion, String sucursal, Cliente cliente) {
        this.numCuenta = numCuenta;
        this.fechaCreacion = fechaCreacion;
        this.sucursal = sucursal;
        this.cliente = cliente;
    }

    public Cuenta(int idCuenta, String numCuenta, String fechaCreacion, String sucursal, Cliente cliente) {
        this.idCuenta = idCuenta;
        this.numCuenta = numCuenta;
        this.fechaCreacion = fechaCreacion;
        this.sucursal = sucursal;
        this.cliente = cliente;
    }

    public Cuenta() {
    }

    public int getidCuenta() {
        return idCuenta;
    }

    public void setidCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
}
