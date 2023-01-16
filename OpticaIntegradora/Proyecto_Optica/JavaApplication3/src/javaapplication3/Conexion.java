/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Conexion {
  
    InputStream entrada;//RX
    OutputStream salida;//TX
    SerialPort puertoSerial;
    public void conexion(String puerto, int vel){
        try{
          puertoSerial = SerialPort.getCommPort(puerto);
          puertoSerial.setBaudRate(vel);
          puertoSerial.setNumDataBits(8);
          puertoSerial.setNumStopBits(1);
          puertoSerial.setParity(1);
          puertoSerial.openPort();
          
            System.out.println("Conexion echa");
        }
        catch(Exception e){
            System.out.println("Por favor revisa tu puerto COM");
            
        }
    }
    public void busDatos (){
        salida = puertoSerial.getOutputStream();
        entrada = puertoSerial.getInputStream();
       
    }
    
    public void enviarDaatos(String d) throws IOException{
        
        salida.write(d.getBytes());
        salida.flush();//Es para limpiar el canal bus de datos
        
        System.out.println("mensaje enviado");
    }
   
}
