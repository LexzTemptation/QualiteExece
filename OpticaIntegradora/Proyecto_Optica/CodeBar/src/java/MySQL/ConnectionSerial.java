/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MySQL;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.OutputStream;

public class ConnectionSerial {
    
    SerialPort puertoSerial;
    OutputStream salida;
    
    public void conexion(String puerto, int vel){
        
        try {
            
          puertoSerial = SerialPort.getCommPort(puerto);
          puertoSerial.setBaudRate(vel);
          puertoSerial.setNumDataBits(8);
          puertoSerial.setNumStopBits(1);
          puertoSerial.setParity(1);
          puertoSerial.openPort();
          
            System.out.println("Conexion");
            
        } catch (Exception e) {
        }
    }
    
    public void busDatos(){
        salida = puertoSerial.getOutputStream();
        
        System.out.println("bus datos right");
    }
    
    public void enviarDatos(String b) throws IOException{
        
        salida.write(b.getBytes());
        salida.flush();
        
        System.out.println("mensaje enviado");
    }
    
}
