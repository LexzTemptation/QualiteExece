/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.modelo;

/**
 *
 * @author FELIPE.HERRERA
 */
public class Deposito {

    private int idDeposito;
    private Cuenta cuenta;
    private String fechaMovimiento;
    private String horaMovimiento;
    private double monto;
    
    public Deposito(Cuenta cuenta, String fechaMovimiento, String horaMovimiento, double monto) {
        this.cuenta = cuenta;
        this.fechaMovimiento = fechaMovimiento;
        this.horaMovimiento = horaMovimiento;
        this.monto = monto;
    }

    public Deposito(int idDeposito, Cuenta cuenta, String fechaMovimiento, String horaMovimiento, double monto) {
        this.idDeposito = idDeposito;
        this.cuenta = cuenta;
        this.fechaMovimiento = fechaMovimiento;
        this.horaMovimiento = horaMovimiento;
        this.monto = monto;
    }

    public Deposito() {
    }

    public int getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(int idDeposito) {
        this.idDeposito = idDeposito;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getHoraMovimiento() {
        return horaMovimiento;
    }

    public void setHoraMovimiento(String horaMovimiento) {
        this.horaMovimiento = horaMovimiento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    

}
