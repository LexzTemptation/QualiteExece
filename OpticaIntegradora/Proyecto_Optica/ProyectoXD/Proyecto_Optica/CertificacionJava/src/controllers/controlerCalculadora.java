/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.modeloCalculadora;
import view.vistaCalculadora;

/**
 *
 * @author FELIPE.HERRERA
 */
public class controlerCalculadora implements ActionListener{
    
    private vistaCalculadora view;
    private modeloCalculadora modelo;

    public controlerCalculadora(vistaCalculadora view, modeloCalculadora modelo) {
        this.view = view;
        this.modelo = modelo;
        
        this.view.btnSuma.addActionListener(this);
        this.view.btnResta.addActionListener(this);
        this.view.btnMulti.addActionListener(this);
        this.view.btnDiv.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        modelo.setNum1(Integer.parseInt(view.txtNum1.getText()));
        modelo.setNum2(Integer.parseInt(view.txtNum2.getText()));
        
        if(e.getSource() == view.btnSuma){
            
            modelo.sumar();
            view.txtRest.setText(String.valueOf(modelo.getResultado()));
            
        }
        else if(e.getSource() == view.btnResta){
            modelo.restar();
            view.txtRest.setText(String.valueOf(modelo.getResultado()));
        }
        else if(e.getSource() == view.btnMulti){
            modelo.multiplicar();
            view.txtRest.setText(String.valueOf(modelo.getResultado()));
        }
        else if(e.getSource() == view.btnDiv){
            modelo.dividir();
            view.txtRest.setText(String.valueOf(modelo.getResultado()));
        }
        
    }
    
    
}
