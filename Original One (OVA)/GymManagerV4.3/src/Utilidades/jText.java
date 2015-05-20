/*
 * Responsable:
 *      Luis Elias Gonzalez Perez
 */
package Utilidades;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;


public class jText extends JTextField implements KeyListener{

    public jText() {
        super();
        this.setDocument(new TextDocument());
        this.addKeyListener(this);
    }

    public jText(String text) {
        super(text);
        this.setDocument(new TextDocument());
        this.addKeyListener(this);
    }
          
    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyChar() == KeyEvent.VK_ENTER){
            this.transferFocus();
        }
        
    }

    public void keyReleased(KeyEvent e) {
       
    }
    
}
