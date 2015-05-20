/*
 * Responsable:
 *      Luis Elias Gonzalez Perez
 */
package Utilidades;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class NumDocument extends PlainDocument{
    
    boolean puntoValido;
    
    public NumDocument() {
        super();
        this.puntoValido = true;
    }

    public NumDocument(boolean puntoValido) {
        super();
        this.puntoValido = puntoValido;
    }
    
    
   
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException{
        
        for(int i = 0; i < str.length(); i++){
            
            if(!Character.isDigit(str.charAt(i))){
                
                if(str.charAt(i) != '.'){
                    return;
                }
                
            }
            
            if(str.charAt(i) == '.' && !this.isPuntoPermitido(str)){   
                
                return;           
            
            }
            
        }
                                     
        super.insertString(offs, str, a);
                                      
    }
    
    private boolean isPuntoPermitido(String str) throws BadLocationException{
        
        int numPunto = 0;
        String text = super.getText(0, super.getLength());
    
        if(this.puntoValido == false){
            return false;
        }
        
        
        if(super.getLength() == 0 && str.length() == 1){
            return false;
        }
        
        for(int i = 0; i < text.length(); i++){
            
            if(text.charAt(i) == '.'){
                
                return false;
                
            }
        }
        
        for(int i = 0; i < str.length(); i++){
            
            if(str.charAt(i) == '.'){
                
                ++numPunto;
                
            }
        }
        
        if(numPunto >= 2){
            return false;
        }
                       
        return true;
        
    }

    public boolean isPuntoValido(){
        return this.puntoValido;
    }
    
    public void setPuntoValido(boolean puntoValido) {
        this.puntoValido = puntoValido;
    }
    
    
    
    
    
}
