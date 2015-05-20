/*
 * Responsable:
 * Saul Hernandez Sanchez
 */

package ViewLayer.ServiciosAdministrativos;

import DomainModelLayer.Controllers.ServiciosAdministrativos;
import DomainModelLayer.Entidades.Clase;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class VerClase extends JPanel {
    private static VerClase instance;
    private JPanel jpnlClase;
    private JPanel jpnlDatos;
    private JTable jtblClases;
    private DefaultTableModel dtblmClases;
    private JScrollPane scrollPane;
    private JButton jbtnBuscar;
    private JComboBox jcboBuscarPor;
    private JTextField jtxtCampoBusqueda;
    private Vector<Clase> clases;
	
    /**
    * Constructor privado para la clase VerClase.
    */
    private VerClase() {
	setLayout(null);
	setSize(715, 540);
	setBackground(Color.WHITE);
		
	jpnlClase = new JPanel();
	jpnlDatos = new JPanel();
	jtblClases = new JTable();
	scrollPane = new JScrollPane(jtblClases);
        jbtnBuscar = new JButton("Buscar");
        jcboBuscarPor = new JComboBox();
        clases  = new Vector<Clase>();
	CrearComponentes();
	AddToPanel();
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
        jpnlClase.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Buscar Clase", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        jpnlClase.setBounds(28, 21, 528, 88);
		
        jcboBuscarPor.setBounds(50,35,110,20);
        jcboBuscarPor.addItem("<Seleccione>");
        jcboBuscarPor.addItem("Id");
        jcboBuscarPor.addItem("Nombre");
        jcboBuscarPor.addItem("Costo");
		
        jbtnBuscar.setBounds(361, 25, 130, 40);
        jbtnBuscar.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));
		
        dtblmClases = new DefaultTableModel(
            new Object [][] {
                {null, null, null, null,null}
	    }, new String [] {"Id", "Nombre", "Costo", "Descripcion"}
	 ){
            @Override
             public boolean isCellEditable (int fila, int columna) {
                if (columna >= 0)
                    return false;
                    return true;
            }
          };
            scrollPane.setBounds(10, 11, 633, 263);
		
            jtblClases.setModel(dtblmClases);
	}
	
    /**
    * Agrega todos los componentes visuales al GUI y sus subcomponentes. 
    * Tambien aqui se agregan los BorderLayout de los componentes agregados
    * de ser necesario
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void AddToPanel(){
        add(jpnlDatos);
        add(jpnlClase);
        jpnlClase.add(jcboBuscarPor);
        jpnlClase.add(jbtnBuscar);

        jtxtCampoBusqueda = new JTextField();
        jtxtCampoBusqueda.setBounds(210, 35, 120, 20);
        jpnlClase.add(jtxtCampoBusqueda);
        jtxtCampoBusqueda.setColumns(10);
        jpnlDatos.add(scrollPane);
		
    }
	
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Ver Clase  y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static VerClase getInstance(){
        if( instance == null){
            instance = new VerClase();
        }
        
        return instance;
    }

    /**
    * Agrega los manejadores de eventos a los componentes visuales. 
    * Aqui se especifican las acciones a tomaar cuando se detecta
    * que un evento ha ocurrido en un componente visual
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */    
    private void addActionListeners() {
        jbtnBuscar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(jtxtCampoBusqueda.getText().length() >= 1){
                        clases.clear();
                        int campoBusqueda = jcboBuscarPor.getSelectedIndex();
                        String filtroBusqueda = jtxtCampoBusqueda.getText();
                        if(campoBusqueda == 1)
                            clases.add(ServiciosAdministrativos.buscarClase(Integer.valueOf(filtroBusqueda)));
                        else
                            clases = ServiciosAdministrativos.buscarClases(filtroBusqueda, campoBusqueda);
                        
                        insertToTable();
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, "error al procesar los datos");
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
    public void insertToTable(){
        dtblmClases.setRowCount(0);
       
        for(Clase clase: clases){
            Vector<String> row = new Vector<String>();
            row.add(String.valueOf(clase.getIdClase()));
            row.add(clase.getNombre());
            row.add(String.valueOf(clase.getPrecio()));
            row.add(clase.getDescripcion());
            
            dtblmClases.addRow(row);
        }
    }
}
