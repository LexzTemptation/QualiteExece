/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package certificacionjava;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author FELIPE.HERRERA
 */
public class CertificacionJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       List<String> nombres = new ArrayList<>();
       
       nombres.add("Christian");
       nombres.add("Fernanda");
       nombres.add("Luis");
       nombres.add("Jose");
       nombres.add("Mari");
       nombres.add("Hernesto");
       nombres.add("Victor");
       nombres.add("Angel");
       
       System.out.println(nombres);
        
       nombres.add("Jhony");
       nombres.add("Dario");
       
        System.out.println("asdas" + nombres.get(9));
       
       nombres.remove(1);
       nombres.remove(0);
       
       
       Iterator nombre = nombres.iterator();
       
       while(nombre.hasNext()){
           System.out.println(nombre.next());
       }
       
    }

}
