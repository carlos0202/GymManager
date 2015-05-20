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

public class EliminarClase extends JPanel {
    private static EliminarClase instance;
    private JPanel jpnlClase;
    private JPanel jpnlDatos;
    private JButton jbtnEliminar;
    private JTable jtblClase;
    private DefaultTableModel dtblmClases;
    private JScrollPane scrollPane;
    private JButton jbtnBuscar;
    private JComboBox jcboBuscarPor;
    private JTextField jtxtCampoBusqueda;
    private Vector<Clase> clases;
    private Clase clase;
   
    /**
    * Constructor privado para la clase EliminarClase.
    */ 
    private EliminarClase() {
        setLayout(null);
        setSize(715, 540);
        setBackground(Color.WHITE);

        jcboBuscarPor = new JComboBox();
        jpnlClase = new JPanel();
        jpnlDatos = new JPanel();
        jbtnEliminar = new JButton("Eliminar");
        jtblClase = new JTable();
        scrollPane = new JScrollPane(jtblClase);
        jbtnBuscar = new JButton("Buscar");
        clases = new Vector<Clase>();
        jtxtCampoBusqueda = new JTextField();

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

        jtxtCampoBusqueda.setBounds(210, 35, 120, 20);
            
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
        );
        jtblClase.setModel(dtblmClases);

        scrollPane.setBounds(10, 11, 633, 263);

        jbtnEliminar.setBounds(551, 426, 130, 40);
        jbtnEliminar.setIcon(new ImageIcon(getClass().getResource("/imagenes/base-de-datos-de-guardar.png")));
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
        jpnlClase.add(jtxtCampoBusqueda);
        jpnlClase.add(jcboBuscarPor);
        jpnlClase.add(jbtnBuscar);
        jpnlDatos.add(scrollPane);

    }
    
    /**
    * Setea los nuevos valores de la clase Clase a la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
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
	    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la Eliminar Clase y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static EliminarClase getInstance(){
        if( instance == null){
            instance = new EliminarClase();
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
        
        jbtnEliminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if( jtblClase.getSelectedRow() == -1 ){
                    JOptionPane.showMessageDialog(instance, "No selecciono ninguna clase");
                }
                else{
                    try{
                        int fila = jtblClase.getSelectedRow();
                        clase = clases.get(fila);
                        
                        if( clase.getSecciones().size() >= 1){
                            JOptionPane.showMessageDialog(
                                    instance,
                                    "Esta clase tiene secciones inscritas"
                            );
                        }
                        else{
                            ServiciosAdministrativos.eliminarClase(clase);
                            JOptionPane.showMessageDialog(
                                    instance,
                                    "Clase eliminada satisfactoriamente..."
                            );
                        }
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(instance,"Fallo al eliminar clase");
                    }
                }
            }
        });
    }
}
