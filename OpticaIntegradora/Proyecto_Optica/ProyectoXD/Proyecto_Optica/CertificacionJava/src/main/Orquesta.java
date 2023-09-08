/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controllers.controlerCalculadora;
import model.modeloCalculadora;
import view.vistaCalculadora;

/**
 *
 * @author FELIPE.HERRERA
 */
public class Orquesta {
    
    public static void main(String[] args) {
        
        vistaCalculadora vista_c = new vistaCalculadora();
        modeloCalculadora modelo_c = new modeloCalculadora();
        
        controlerCalculadora control_c = new controlerCalculadora(vista_c, modelo_c);
        
        vista_c.setVisible(true);
    }
}
