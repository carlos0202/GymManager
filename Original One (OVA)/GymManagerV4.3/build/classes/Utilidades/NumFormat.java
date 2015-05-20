/*
 * Responsable:
 *      Luis Elias Gonzalez Perez
 */
package Utilidades;

import java.text.DecimalFormat;


public class NumFormat {

    private static DecimalFormat formato;
  
   
    
    public static String format(Double num){
        
        formato = new DecimalFormat("#.00");
        formato.setMaximumFractionDigits(2);
        formato.setMinimumFractionDigits(2);
        formato.setMinimumIntegerDigits(1);
        
        return formato.format(num);              
    }
    
}
