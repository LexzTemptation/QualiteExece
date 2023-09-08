/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author FELIPE.HERRERA
 */
public class Empleado {
    
    private int idEmpleado;
    private String nombre;
    private String userName;
    private String rol;
    private String direccion;
    private String telefono;
    private String correo;
    private String contrasenia;
    private String created;
    private String updated;
    
    public Empleado(){}

    public Empleado(int idEmpleado, String nombre, String userName, String rol, String direccion, String telefono, String correo, String contrasenia, String created, String updated) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.userName = userName;
        this.rol = rol;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.created = created;
        this.updated = updated;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
    
    
    
}
