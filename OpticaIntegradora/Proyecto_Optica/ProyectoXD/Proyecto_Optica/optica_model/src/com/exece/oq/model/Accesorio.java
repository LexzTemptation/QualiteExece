package com.exece.oq.model;

/**
 *
 * @author medin
 */
public class Accesorio {
    
    private int idAccesorio;
    private Producto producto;

    public Accesorio() {
    }

    public Accesorio(int idAccesorio, Producto producto) {
        this.idAccesorio = idAccesorio;
        this.producto = producto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getIdAccesorio() {
        return idAccesorio;
    }

    public void setIdAccesorio(int idAccesorio) {
        this.idAccesorio = idAccesorio;
    }
    
}

