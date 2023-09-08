/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cinhtya;

import java.util.Scanner;

/**
 *
 * @author FELIPE.HERRERA
 */
public class Cinhtya {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner tec = new Scanner(System.in);
        
        int materia01, materia02, materia03, materia04, materia05, materia06, materia07, materia08;
        int i;
        int valores = 0;
        int x;
        int resultado;
        
        
        System.out.println("cuantos datos quieres?");
        i = tec.nextInt();
        
        if(i > 4 && i < 16){
            String querer;
            System.out.println("quieres continuar:");
            querer = tec.next();
            
            while( querer.equals("si")){
                
                if(valores >9 && valores <101){
                    for( i = 0; i <16 ; i++){
                        valores =  tec.nextInt();                    }
                }
            }
            
            resultado = valores/i;
            System.out.println(resultado);
        }else{
            System.out.println("Numero de datos exedido o menor al requerido");
        }
    }
    
}
