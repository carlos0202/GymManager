/*
 * Responsable:
 *      Luis Elias Gonzalez Perez
 */
package Utilidades;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class jTextNum extends JTextField implements KeyListener, FocusListener{

    String numDefault;
    
    public jTextNum() {
        super();      
        super.setDocument(new NumDocument());
        this.addKeyListener(this);
        this.addFocusListener(this);
        this.numDefault = "0";
        super.setText(numDefault);
  
    }

    public jTextNum(Integer numDefault) {
        super();
        super.setDocument(new NumDocument(false));
        this.addKeyListener(this);
        this.addFocusListener(this);
        this.numDefault = numDefault.toString();
        this.setNum(numDefault);
 
    }
     
    public jTextNum(Double numDefault) {
        super();
        super.setDocument(new NumDocument());
        this.addKeyListener(this);
        this.addFocusListener(this);
        this.numDefault = NumFormat.format(numDefault);   
        super.setText(NumFormat.format(numDefault));
   
    }
        
    public jTextNum(Long numDefault) {
        super();
        super.setDocument(new NumDocument(false));
        this.addKeyListener(this);
        this.addFocusListener(this);
        this.numDefault = numDefault.toString();
        this.setNum(numDefault);
    
    }

    public String getNumDefault() {
        return numDefault;
    }

    public void setNumDefault(String numDefault) {
        super.setText(numDefault);
        this.numDefault = numDefault;
    }
    
    
        
    public Integer getInteger(){       
        return new Integer(super.getText());
    }
    
    public final void setNum(Integer num){       
        super.setText(num.toString());      
    }
    
    public Long getLong(){
        return new Long(super.getText());
    }
    
    public final void setNum(Long num){
        super.setText(num.toString());
    }
    
    public Double getDouble(){
        
        try{
            return new Double(super.getText());
        }catch(Exception e){
            return new Double(this.numDefault);
        }
    }
    
    public final void setNum(Double num){      
        super.setText(NumFormat.format(num));
    }
 
    public void setPuntoValido(boolean puntoValido) {      
        this.setDocument(new NumDocument(puntoValido));       
    }
    
    public boolean isPuntoValido(){
        return ((NumDocument) this.getDocument()).isPuntoValido();                            
    }
    
    public void limpiar(){
        super.setText(this.numDefault);
    }
                 
    /*
     * Metodos implementados de KeyListener
     */

    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyChar() == KeyEvent.VK_ENTER){
            
            this.transferFocus();
            if(((NumDocument)this.getDocument()).isPuntoValido()){
                super.setText(NumFormat.format(this.getDouble()));
            }
            
        }
        
    }

    public void keyReleased(KeyEvent e) {
       
    }
    
    /*
     * Metodos implementados de FocusListener
     *
     */

    public void focusGained(FocusEvent e) {
        
        if(super.getText().equals(this.numDefault)){
            super.setText("");
        }
        
    }

    public void focusLost(FocusEvent e) {
        
        if(super.getText().isEmpty()){
            super.setText(this.numDefault);
        }
        
    }
    
     
   
}
