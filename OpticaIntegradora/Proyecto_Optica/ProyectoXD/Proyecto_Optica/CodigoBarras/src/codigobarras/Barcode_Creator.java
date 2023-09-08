/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigobarras;

import net.sourceforge.jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Code128;
import net.sourceforge.jbarcodebean.model.Code39;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Barcode_Creator {
    
    JBarcodeBean cb = new JBarcodeBean();
    
    public void create128(String c) throws IOException{
        
        cb.setCodeType(new Code128());
        cb.setCode(c);
        System.out.println(cb);
        BufferedImage b1 = cb.draw(new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB));
        File f = new File("./src/CodigoBarras/" + c + ".jpg");
        ImageIO.write(b1,"jpg", f);
    }
    
    public void create39(String c) throws IOException{
        
        cb.setCodeType(new Code39());
        cb.setCode(c);
        System.out.println(cb);
        BufferedImage b1 = cb.draw(new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB));
        File f = new File("./src/CodigoBarras/" + c + ".png");
        ImageIO.write(b1,"png", f);
    }
}
