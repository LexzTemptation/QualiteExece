/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.utl.dsm.vista;

import org.utl.dsm.controller.ControllerTransacciones;
import org.utl.dsm.modelo.Cliente;
import org.utl.dsm.modelo.Cuenta;
import org.utl.dsm.modelo.Deposito;

/**
 *
 * @author FELIPE.HERRERA
 */
public class Transacciones {
    
    
  
    public static void main(String[] args) throws Exception {
        
        Cliente cl = new Cliente("Fabian", "Farallon", "LUZ", "corre@gmail.com", "1234567890");
        Cuenta cu = new Cuenta("98989898", "02/02/2023", "SUC12", cl);
        Deposito dep = new Deposito(cu, "02/02/2023", "13:13", 50000);
        
        ControllerTransacciones ct = new ControllerTransacciones();
        boolean r = ct.ejecutarTransaccion(dep);
        
        if(r){
            System.out.println("Transaccion echa");
        }else{
            System.out.println("Transaccion mal echa");
        }
    }
    
}
