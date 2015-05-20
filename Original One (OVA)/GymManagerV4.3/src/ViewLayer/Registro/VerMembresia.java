/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */

package ViewLayer.Registro;

import DomainModelLayer.Controllers.Registro;
import DomainModelLayer.Entidades.Membresia;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class VerMembresia extends JPanel {
    private static VerMembresia instance;
    private JPanel jpnlMembresia;
    private JPanel jpnlDatos;
    private JComboBox jcboMembresia;
    private JTable jtblMembresia;
    private DefaultTableModel dtblMembresia;
    private JScrollPane scrollPane;
    private Vector<Membresia> membresias;
    private Membresia membresia;

    /**
    * Constructor privado para la clase VerMembresia.
    */
    public VerMembresia() throws Exception {
        setLayout(null);
        setSize(715, 540);
        setBackground(Color.WHITE);

        jpnlDatos = new JPanel();
        jpnlMembresia = new JPanel();
        jcboMembresia = new JComboBox();
        jtblMembresia = new JTable();
        scrollPane = new JScrollPane(jtblMembresia);
        membresias = new Vector<Membresia>();
        
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
    private void crearComponentes() throws Exception{

        jpnlDatos.setLayout(null);
        jpnlDatos.setBackground(Color.WHITE);
        jpnlDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
        jpnlDatos.setBounds(28, 130, 653, 285);

        dtblMembresia = new DefaultTableModel(
                new Object [][] {
                {null, null, null, null,null}
            }, new String [] {"Id", "Nombre","Precio","Tipo", "Descripcion"}
        ){
            @Override
            public boolean isCellEditable(int fila, int columna){
                if( columna >= 0)
                    return false;
                return true;
            }
        };
        jtblMembresia.setModel(dtblMembresia);
        
        scrollPane.setBounds(10, 11, 633, 263);

        jpnlMembresia.setLayout(null);
        jpnlMembresia.setBackground(Color.WHITE);
        jpnlMembresia.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Seleccione Membresia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        jpnlMembresia.setBounds(28, 21, 292, 88);

        membresias = Registro.buscarMembresias();
        jcboMembresia.setBounds(39, 35, 209, 20);
        jcboMembresia.addItem("<Seleccione>");
        for(Membresia memb: membresias){
            jcboMembresia.addItem(memb.getNombre());
        }
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
        add(jpnlMembresia);
        jpnlDatos.add(scrollPane);
        jpnlMembresia.add(jcboMembresia);

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
        jcboMembresia.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(jcboMembresia.getSelectedIndex() > 0){
                    membresia = membresias.get(jcboMembresia.getSelectedIndex()-1);
                    insertarDatos();
                }
                else{
                    dtblMembresia.setRowCount(0);
                }
            }
        });
        
       
    }
    
     /**
     * Limpia la tabla en le GUI para despues agregar los datos de la membresia
     * que fue seleccionados para la busqueda 
     * <p>
     * Este metodo no tiene ningun valor de retorno y no recibe parametros
     */
    private void insertarDatos(){
        dtblMembresia.setRowCount(0);
        Vector<String> row = new Vector<String>();
        
        row.add(String.valueOf(membresia.getIdMembresia()));
        row.add(membresia.getNombre());
        row.add(String.valueOf(membresia.getPrecio()));
        row.add(membresia.getTipo());
        row.add(membresia.getDescripcion());
        
        dtblMembresia.addRow(row);
    }
    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Ver Membresia  y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static VerMembresia getInstance() throws Exception{
        if( instance == null){
            instance = new VerMembresia();
        }
        
        return instance;
    }
}
