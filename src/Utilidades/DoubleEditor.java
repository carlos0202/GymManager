/*
 * Responsable:
 *      Luis Elias Gonzalez Perez
 */
package Utilidades;

import java.awt.Component;
import javax.swing.JTable;


public class DoubleEditor extends  IntegerEditor{

    public DoubleEditor() {
        super();
    }
       
    public DoubleEditor(Double numDefault) {
        super();
        super.jtxtNum = new jTextNum(numDefault);
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelect, int row, int column) {
        
        if(isSelect){
            
            try{
                             
                this.jtxtNum.setNum(new Double(value.toString()));
                               
            }catch(Exception e){
                
                this.jtxtNum.limpiar();                
            }
                         
        }
        
        return this.jtxtNum;
    }

    @Override
    public Object getCellEditorValue() {

        try{
            
            return this.jtxtNum.getDouble();
                                
        }catch(Exception e){
            
            return  "0.00";            
        }        
       
    }   
    
}
