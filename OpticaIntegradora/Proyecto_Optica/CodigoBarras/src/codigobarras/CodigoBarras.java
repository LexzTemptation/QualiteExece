/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package codigobarras;

import java.io.IOException;

/**
 *
 * @author FELIPE.HERRERA
 */
public class CodigoBarras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Barcode_Creator creator = new Barcode_Creator();
        
        creator.create128("Jose");
        creator.create39("Jose");
        
        System.out.println("Your barcode is create");
    }
    
}
