/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serial;

import java.util.Scanner;

/**
 *
 * @author FELIPE.HERRERA
 */
public class Datos {
 
    public static void main(String[] args) {
        
        Scanner x = new Scanner(System.in);
        boolean validation = true;
        int sumaDeDatos=0;
        int currentDato=0;
        
        while(validation){
            
            System.out.println("Escrobe un numero mayor a 10 y menor a 100");
            currentDato = x.nextInt();
            
            if(currentDato > 10 && currentDato < 100){
                
                sumaDeDatos += currentDato;
            }
            
            System.out.println("Quieres salir del app");
            
            if(x.next().equals("si")) validation = false;
            
        }
        
        System.out.println(sumaDeDatos);
    }
}
