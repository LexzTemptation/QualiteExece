/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package EscritorioRemoto;

/**
 *
 * @author FELIPE.HERRERA
 */

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Image;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Screen extends javax.swing.JFrame implements Runnable{

    
    ObjectInputStream entrada;
    ServerSocket servidor;
    Socket conexion;
    Image foto;
    
    public Screen() {
        initComponents();
        
        Thread hilo = new Thread(this);
        hilo.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        screenView = new javax.swing.JLabel();
        btnShowScreen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(screenView, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 16, 390, 170));

        btnShowScreen.setText("Mostrar Pantalla Cliente");
        btnShowScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowScreenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(259, Short.MAX_VALUE)
                .addComponent(btnShowScreen)
                .addGap(233, 233, 233))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addGap(17, 17, 17)
                .addComponent(btnShowScreen)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowScreenActionPerformed
        
        try {
            
            construyendo_img(entrada);
            
            
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_btnShowScreenActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Screen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnShowScreen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel screenView;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try {
            servidor = new ServerSocket(4000);
            
            while(true){
                
                conexion = servidor.accept();
                System.out.println("Ready...");
                entrada = new ObjectInputStream(conexion.getInputStream());
                construyendo_img(entrada);
                
            }
            
        } catch (Exception e) {
        }
        
    }
    
     public void construyendo_img(ObjectInputStream entrada) throws IOException{
                
         try {
             
             byte[] bytes_img = (byte[]) entrada.readObject();
             
             ByteArrayInputStream entrada_img = new ByteArrayInputStream(bytes_img);
             BufferedImage img_buffer = ImageIO.read(entrada_img);
             
             String ruta = "C:/foto/foto.jpg";
             
             ImageIO.write(img_buffer, "jpg", new File(ruta));
             
             foto = Toolkit.getDefaultToolkit().getImage("C:/foto/foto.jpg");
             
             screenView.setIcon(new ImageIcon(foto.getScaledInstance(636, 409, 0)));
             
         } catch (Exception e) {
         }
     }
}
