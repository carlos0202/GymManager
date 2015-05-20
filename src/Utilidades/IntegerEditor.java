/*
 * Responsable:
 *      Luis Elias Gonzalez Perez
 */
package Utilidades;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class IntegerEditor extends AbstractCellEditor implements TableCellEditor, FocusListener {
    
    protected jTextNum jtxtNum;

    public IntegerEditor() {
        this.jtxtNum = new jTextNum(new Integer(0));
        this.jtxtNum.addFocusListener(this); 
    }
      
    public IntegerEditor(Integer numDefault) {
        this.jtxtNum = new jTextNum(numDefault);
        this.jtxtNum.addFocusListener(this);     
    }
          
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelect, int row, int column) {
        
        if(isSelect){
            
            try{
                             
                this.jtxtNum.setNum(new Integer(value.toString()));
                               
            }catch(Exception e){
                
                this.jtxtNum.setNum(0);                                
            }
                         
        }
        
        return this.jtxtNum;
    }

    public Object getCellEditorValue() {

        try{
            
            return this.jtxtNum.getInteger();
                                
        }catch(Exception e){
            
            return  0;            
        }        
       
    }
    
    @Override
   public boolean isCellEditable(EventObject e){
       
       MouseEvent me;
       
       try{
            me =  (MouseEvent) e;
       
            if(me.getClickCount() == 2){
                return true;
            }
            
       }catch(Exception ex){      
            return false;         
       }
       
       return false;
       
   }

    public void focusGained(FocusEvent fe) {       
    }

    public void focusLost(FocusEvent e) {      
        this.stopCellEditing();
    }
    
    
}
