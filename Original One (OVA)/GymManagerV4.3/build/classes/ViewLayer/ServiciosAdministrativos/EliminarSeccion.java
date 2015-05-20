/*
 * Responsable:
 * Saul Hernandez Sanchez
 */

package ViewLayer.ServiciosAdministrativos;

import DomainModelLayer.Controllers.ServiciosAdministrativos;
import DomainModelLayer.Entidades.Seccion;
import Utilidades.jTextNum;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class EliminarSeccion extends JPanel {
    private static EliminarSeccion instance;
    private JPanel jpnlClase;
    private JPanel jpnlDatos;
    private JButton jbtnEliminar;
    private JTable jtblSeccion;
    private DefaultTableModel dtblSeccion;
    private JScrollPane scrollPane;
    private jTextNum jtxtIdSeccion;
    private JLabel jlblIdSeccion;
    private JButton jbtnBuscarSeccion;
    private Seccion seccion;
    
    /**
    * Constructor privado para la clase EliminarSeccion.
    */ 
    private EliminarSeccion() {
	setLayout(null);
	setSize(715, 540);
	setBackground(Color.WHITE);
		
	jpnlClase = new JPanel();
	jpnlDatos = new JPanel();
	jbtnEliminar = new JButton("Eliminar");
	jtblSeccion = new JTable();
	scrollPane = new JScrollPane(jtblSeccion);
	jtxtIdSeccion = new jTextNum();
	jlblIdSeccion = new JLabel("Id Seccion");
	jbtnBuscarSeccion = new JButton("Buscar");
		
	CrearComponentes();
	addToPanel();
        addActionListeners();
        
    }
	    
    /**
    * Crea y posiciona los elementos contenidos dentro de la ventana. 
    * Tambien aqui se especifican las decoraciones usadas en los
    * componentes visuales presentados.
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void CrearComponentes(){
	jpnlDatos.setLayout(null);
        jpnlDatos.setBackground(Color.WHITE);
        jpnlDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
	jpnlDatos.setBounds(28, 130, 653, 285);
		
	jpnlClase.setLayout(null);
        jpnlClase.setBackground(Color.WHITE);
        jpnlClase.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Buscar Seccion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        jpnlClase.setBounds(28, 21, 345, 88);
        
        jtxtIdSeccion.setBounds(91, 34, 86, 20);
	
	jlblIdSeccion.setBounds(10, 37, 71, 14);
		
		
	jbtnBuscarSeccion.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));
	jbtnBuscarSeccion.setBounds(200, 25, 130, 40);
	jpnlClase.add(jbtnBuscarSeccion);
		
	dtblSeccion = new DefaultTableModel(
            new Object [][] {
                {null, null, null, null,null}
	    }, new String [] {"Id", "Hora Inicio", "Hora Fin", "Salon", "Instructor"}
	);
        jtblSeccion.setModel(dtblSeccion);
                
	scrollPane.setBounds(10, 11, 633, 263);
		
	jbtnEliminar.setBounds(551, 426, 130, 40);
        jbtnEliminar.setIcon(new ImageIcon(getClass().getResource("/imagenes/base-de-datos-de-guardar.png")));
    }
	    
    /**
    * Agrega los manejadores de eventos a los componentes visuales. 
    * Aqui se especifican las acciones a tomaar cuando se detecta
    * que un evento ha ocurrido en un componente visual
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void addActionListeners(){
        jbtnBuscarSeccion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idSeccion = jtxtIdSeccion.getInteger();
                    seccion = ServiciosAdministrativos.buscarSeccion(idSeccion);
                    insertToTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(instance, "Datos incorrectos...");
                }
            }
        });
            
        jbtnEliminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(seccion.equals(null) || seccion.getSociosInscritos().size() > 0){
                    JOptionPane.showMessageDialog(
                            instance,
                            "Esta seccion tiene socios inscritos."
                    );
                }
                else{
                    try {
                        ServiciosAdministrativos.eliminarSeccion(seccion);
                        JOptionPane.showMessageDialog(instance, "Seccion Eliminada");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(instance, "Fallo al eliminar seccion");
                        //JOptionPane.showMessageDialog(instance, ex.getLocalizedMessage());
                    }
                }
            }
        });
    }
	    
    /**
    * Agrega todos los componentes visuales al GUI y sus subcomponentes. 
    * Tambien aqui se agregan los BorderLayout de los componentes agregados
    * de ser necesario
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void addToPanel(){
        add(jpnlDatos);
	add(jbtnEliminar);
	add(jpnlClase);
	jpnlClase.add(jtxtIdSeccion);
	jpnlClase.add(jlblIdSeccion);
	jpnlDatos.add(scrollPane);
		
    }
          
    /**
    * Setea los nuevos valores de la clase Seccion a la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */  
   private void insertToTable(){
        dtblSeccion.setRowCount(0);
    
        Vector<String> row = new Vector<String>();
        row.add(String.valueOf(seccion.getIdSeccion()));
        row.add(String.valueOf(seccion.getHoraInicio())+":00");
        row.add(String.valueOf(seccion.getHoraFin())+":00");
        row.add(seccion.getSalon().getNombre());
        row.add(
            seccion.getInstrctAsignado().getNombre() + " " +
            seccion.getInstrctAsignado().getApellido()
        );
               
        dtblSeccion.addRow(row);
   }
	
   /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la Eliminar Seccion y si no
    * existe ninguna la crea y luego la retorna
    *
    */
   public static EliminarSeccion getInstance(){
        if( instance == null){
            instance = new EliminarSeccion();
        }
        
        return instance;
    }

}
