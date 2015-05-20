/*
 * Responsable:
 *      Luis Elias Gonzalez Perez
 */
package Utilidades;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class TextDocument extends PlainDocument{
    
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException{
        
         for(int i = 0; i < str.length(); i++){
            
            if(!Character.isLetter(str.charAt(i))){
                
                if(!Character.isSpaceChar(str.charAt(i))){
                    
                    return;
                }
                                         
            }
                         
        }
                                     
        super.insertString(offs, str, a);
         
    }
    
    
}
