/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinhtya;

import java.time.LocalDateTime;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;

public class pruebaLolis {
    
    public static void main(String[] args) {
        
        LocalDateTime d = LocalDateTime.now();
        
        String date = d.getYear() + "-" + d.getMonthValue() + "-" + d.getDayOfMonth();
        
        System.out.println(date);
    }
    
}
