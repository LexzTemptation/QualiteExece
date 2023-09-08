/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.model;

/**
 *
 * @author david
 */
public class Lente_contacto {
    int idLenteContacto;
    Producto producto;
    int keratometria;
    String fotografia;

    public int getIdLenteContacto() {
        return idLenteContacto;
    }

    public void setIdLenteContacto(int idLenteContacto) {
        this.idLenteContacto = idLenteContacto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getKeratometria() {
        return keratometria;
    }

    public void setKeratometria(int keratometria) {
        this.keratometria = keratometria;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public Lente_contacto() {
    }

    public Lente_contacto(Producto producto, int keratometria, String fotografia) {
        this.producto = producto;
        this.keratometria = keratometria;
        this.fotografia = fotografia;
    }

    public Lente_contacto(int idLenteContacto, Producto producto, int keratometria, String fotografia) {
        this.idLenteContacto = idLenteContacto;
        this.producto = producto;
        this.keratometria = keratometria;
        this.fotografia = fotografia;
    }
    
}
