/*
 * Responsable:
 * Saul Hernandez Sanchez
 */

package ViewLayer.ServiciosAdministrativos;

import DomainModelLayer.Controllers.ServiciosAdministrativos;
import DomainModelLayer.Entidades.Seccion;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class VerSeccion extends JPanel{
    private static VerSeccion instance;
    private JPanel jpnlClase;
    private JPanel jpnlDatos;
    private JTable jtblSecciones;
    private DefaultTableModel dtblmSecciones;
    private JScrollPane scrollPane;
    private JButton jbtnBuscarSseccion;
    private JComboBox jcboBusquedaSeccion;
    private JTextField jtxtCampoBusqueda;
    private Vector<Seccion> secciones;

    /**
    * Constructor privado para la clase VerSeccion.
    */
    private VerSeccion() {
	setLayout(null);
	setSize(715, 540);
	setBackground(Color.WHITE);
		
	jpnlClase = new JPanel();
	jpnlDatos = new JPanel();
	jtblSecciones = new JTable();
	scrollPane = new JScrollPane(jtblSecciones);
        jbtnBuscarSseccion = new JButton("Buscar");
        jcboBusquedaSeccion = new JComboBox();
        secciones = new Vector<Seccion>();
		
        crearComponentes();
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
    private void crearComponentes(){
	jpnlDatos.setLayout(null);
        jpnlDatos.setBackground(Color.WHITE);
        jpnlDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
	jpnlDatos.setBounds(28, 130, 653, 285);
		
	jpnlClase.setLayout(null);
        jpnlClase.setBackground(Color.WHITE);
        jpnlClase.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Buscar Seccion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        jpnlClase.setBounds(28, 21, 528, 88);
		
        jcboBusquedaSeccion.setBounds(50,35,110,20);
        jcboBusquedaSeccion.addItem("<Seleccione>");
        jcboBusquedaSeccion.addItem("Id");
        jcboBusquedaSeccion.addItem("Nombre");
        jcboBusquedaSeccion.addItem("Salon");
        jcboBusquedaSeccion.addItem("Instructor");
                
	jbtnBuscarSseccion.setBounds(361, 25, 130, 40);
        jbtnBuscarSseccion.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));
		
        dtblmSecciones = new DefaultTableModel(
            new Object [][] {
                {null, null, null, null,null}
	    }, new String [] {"Id Seccion", "Clase", "Instructor", "Descripcion"}
	 ){
            @Override
            public boolean isCellEditable (int fila, int columna) {
                if (columna >= 0)
                    return false;
                    return true;
            }
         };
            scrollPane.setBounds(10, 11, 633, 263);
		
            jtblSecciones.setModel(dtblmSecciones);
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
	add(jpnlClase);
	jpnlClase.add(jcboBusquedaSeccion);
        jpnlClase.add(jbtnBuscarSseccion);
                
        jtxtCampoBusqueda = new JTextField();
        jtxtCampoBusqueda.setBounds(210, 35, 120, 20);
        jpnlClase.add(jtxtCampoBusqueda);
        jtxtCampoBusqueda.setColumns(10);
        jpnlDatos.add(scrollPane);
		
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
        jbtnBuscarSseccion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    secciones.clear();
                    int campoBusqueda = jcboBusquedaSeccion.getSelectedIndex();
                    String filtro = jtxtCampoBusqueda.getText();
                    secciones = ServiciosAdministrativos.buscarSecciones(campoBusqueda, filtro);
                    insertToTable();
                 } catch (Exception ex) {
                    JOptionPane.showMessageDialog(instance, "Datos incorrectos...");
                       
                 }
             }
        });
    }
    
     /**
     * Limpia la tabla en le GUI para despues agregar los datos del empleado
     * que fue seleccionados para la busqueda 
     * <p>
     * Este metodo no tiene ningun valor de retorno y no recibe parametros
     */
    private void insertToTable(){
        dtblmSecciones.setRowCount(0);
            
        for(Seccion seccion: secciones){
            Vector<String> row = new Vector<String>();
            row.add(String.valueOf(seccion.getIdSeccion()));
            row.add(seccion.getClaseImpartida().getNombre());
            row.add(
                seccion.getInstrctAsignado().getNombre() + " "+
                seccion.getInstrctAsignado().getApellido()
            );
            row.add(seccion.getClaseImpartida().getDescripcion());
                
            dtblmSecciones.addRow(row);
        }
    }
	
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Ver Seccion  y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static VerSeccion getInstance(){
        if( instance == null){
            instance = new VerSeccion();
        }
        
        return instance;
    }

}
