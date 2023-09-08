/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.model;

/**
 *
 * @author jonai
 */
public class Armazon {
    private int idArmazon;
    Producto producto;
    private String modelo;
    private String color;
    private String dimensiones;
    private String descripcion;
    private String fotografia;

    public Armazon() {
    }

    public Armazon(int idArmazon, Producto producto, String modelo, String color, String dimensiones, String descripcion, String fotografia) {
        this.idArmazon = idArmazon;
        this.producto = producto;
        this.modelo = modelo;
        this.color = color;
        this.dimensiones = dimensiones;
        this.descripcion = descripcion;
        this.fotografia = fotografia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdArmazon() {
        return idArmazon;
    }

    public void setIdArmazon(int idArmazon) {
        this.idArmazon = idArmazon;
    }


    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Armazon{" + "idArmazon=" + idArmazon + ", producto=" + producto + ", modelo=" + modelo + ", color=" + color + ", dimensiones=" + dimensiones + ", descripcion=" + descripcion + ", fotografia=" + fotografia + '}';
    }

    
}
