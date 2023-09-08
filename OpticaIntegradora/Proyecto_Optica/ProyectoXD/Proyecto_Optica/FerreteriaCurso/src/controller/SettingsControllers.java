/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import view.systemView;

/**
 *
 * @author FELIPE.HERRERA
 */
public class SettingsControllers implements MouseListener{

    
    private systemView views;

    public SettingsControllers(systemView views) {
        this.views = views;
        
        this.views.personal.addMouseListener(this);
        this.views.productos.addMouseListener(this);
        this.views.ventas.addMouseListener(this);
    }
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       if(e.getSource() == views.personal){
           views.personal.setBackground(new Color(250,191,135));
           
       }else if(e.getSource() == views.productos){
           views.productos.setBackground(new Color(250,191,135));
           
       }else if(e.getSource() == views.ventas){
           views.ventas.setBackground(new Color(250,191,135));
       }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == views.personal){
           views.personal.setBackground(new Color(204,102,0));
           
       }else if(e.getSource() == views.productos){
           views.productos.setBackground(new Color(204,102,0));
           
       }else if(e.getSource() == views.ventas){
           views.ventas.setBackground(new Color(204,102,0));
       }
    }
    
}
