/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication3;

import java.io.IOException;
import javax.swing.JOptionPane;
import Conexiones.ConexionSerial;
import java.time.LocalDateTime;
import java.util.Calendar;

public class JavaApplication3 {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        
        
        ConexionSerial serial = new ConexionSerial();
        
        boolean validation = true;
        
        serial.conexion("COM3", 9600);
        serial.busDatos();
        
        Thread.sleep(1600);
        
        while(validation){
            LocalDateTime today = LocalDateTime.now();
        
            String hora = String.valueOf(today.getHour());
            String minute = String.valueOf(today.getMinute());
            
            if(Integer.parseInt(hora) < 10) hora = "0" + hora;
            
            if(Integer.parseInt(minute) < 10) minute = "0" + minute;
            
            String time = hora + ":" + minute + "pm";
            System.out.println(time);
            serial.enviarDatos(time);
            
            Thread.sleep(60000);
            
            if(minute.equals("14")) validation = false;
        }
        
        

    }
    
}
 