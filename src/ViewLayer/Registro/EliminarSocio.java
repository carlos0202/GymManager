/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */

package ViewLayer.Registro;

import DomainModelLayer.Controllers.Registro;
import DomainModelLayer.Entidades.Socio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class EliminarSocio extends JPanel{
    private static EliminarSocio instance;
    private JDesktopPane jdpFondo;
    private JPanel jpnlBuscar;
    private JPanel jpnlDatos;
    private JComboBox jcboBuscarPor;
    private JButton jbtnBuscar;
    private JButton jbtnEliminar;
    private JTextField jtxtBuscar;
    private JLabel jlblFoto;
    private JTable jtblSocio;
    private DefaultTableModel dtblmSocio;
    private JScrollPane jscrollPane;
    private Vector<Socio> socios;
    
    /**
    * Constructor privado para la clase EliminarSocio.
    */
    private  EliminarSocio(){
        setLayout(null);
        jdpFondo = new JDesktopPane();
        jcboBuscarPor = new JComboBox();
        jbtnBuscar = new JButton("Buscar");
        jbtnEliminar = new JButton("Eliminar");
        jtxtBuscar = new JTextField();
        jlblFoto = new JLabel();
        jtblSocio = new JTable();
        jscrollPane = new JScrollPane(jtblSocio);
        jpnlBuscar = new JPanel();
        jpnlDatos = new JPanel();
        socios = new Vector<Socio>();
        
        crearComponentes();
        addActionListeners();
        addToPanel();
        setSize(715, 540);
        
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
        jdpFondo.setSize(715, 540);
         
        jpnlBuscar.setBounds(40, 40, 300, 190);
        jpnlBuscar.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Buscar Socio por"));
        jpnlBuscar.setBackground(Color.WHITE);
        jpnlBuscar.setLayout(null);
        
        jcboBuscarPor.setBounds(25,30,150,20);
        jcboBuscarPor.addItem("<Seleccione>");
        jcboBuscarPor.addItem("Id");
        jcboBuscarPor.addItem("Cedula");
        jcboBuscarPor.addItem("Nombre");
        jcboBuscarPor.addItem("Apellido");
        jcboBuscarPor.addItem("Membresia");
        
        jbtnBuscar.setBounds(170, 100, 120, 40);
        jbtnBuscar.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));
        
        jbtnEliminar.setBounds(568, 210, 120, 40);
        jbtnEliminar.setIcon(new ImageIcon(getClass().getResource("/imagenes/delete.png")));
        
        jtxtBuscar.setBounds(180,30,100,20);
        
        jlblFoto.setBounds(500, 30, 180, 180);
        jlblFoto.setIcon(new ImageIcon(getClass().getResource("/imagenes/logo_tecno_gym_uruapan.jpg"))); 
        
        jpnlDatos.setBounds(40, 250, 650, 250);
        jpnlDatos.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Datos de Socio"));
        jpnlDatos.setBackground(Color.WHITE);
        jpnlDatos.setLayout(null);

        dtblmSocio = new DefaultTableModel(
             new Object [][] {
                {null, null, null, null, null, null}
            }, new String [] {"Id", "Cedula", "Nombre", "Apellido","membresia", "Sexo"}
        ){
            @Override
             public boolean isCellEditable (int fila, int columna) {
                 if (columna >= 0)
                     return false;
                 return true;
             }
        };
        
        jtblSocio.setModel(dtblmSocio);
        jscrollPane.setBounds(20, 30, 610, 200);
        
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
        add(jdpFondo);
        jpnlDatos.add(jscrollPane, BorderLayout.CENTER);
        jpnlBuscar.add(jtxtBuscar);
        jpnlBuscar.add(jbtnBuscar);
        jpnlBuscar.add(jcboBuscarPor);
        jdpFondo.add(jbtnEliminar);
        jdpFondo.add(jpnlBuscar);
        jdpFondo.add(jpnlDatos);
        jdpFondo.add(jlblFoto);
    } 
         
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Cancelar Socio y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static EliminarSocio getInstance(){
         if(instance == null){
            instance = new EliminarSocio();           
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
                    if(jtxtBuscar.getText().length() > 0 &&
                       jcboBuscarPor.getSelectedIndex() > 0 ){
                        String filtroBusqueda = jtxtBuscar.getText();
                        int campoBusqueda = jcboBuscarPor.getSelectedIndex();
                        socios = Registro.buscarSocio(filtroBusqueda, campoBusqueda);
                        insertToTable(socios);
                    }
                    else{
                        JOptionPane.showMessageDialog(instance, "Datos incorrectos..");
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, ex.getMessage());
                }
            }
        });
        
        jbtnEliminar.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = jtblSocio.getSelectedRow();
                    
                    if( selectedRow == -1){
                        JOptionPane.showMessageDialog(instance,
                                "No selecciono ninguna fila"
                        );
                    }
                    else{
                        Socio socio = socios.get(selectedRow);
                        try {
                            Registro.eliminarSocio(socio);
                            JOptionPane.showMessageDialog(instance,
                                    "el Socio " + socio.getNombre() + " ha sido dado de baja");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(instance, ex.getMessage());
                        }
                    }
                }
            });
    }
         
    /**
     * Limpia la tabla en le GUI para despues agregar los datos del empleado
     * que fue seleccionados para la busqueda 
     * <p>
     * Este metodo no tiene ningun valor de retorno
     * @param recibe como parametro un objeto tipo arreglo de socios
     */
    private void insertToTable(Vector<Socio> socios){
        dtblmSocio.setRowCount(0);
        
        for(Socio soc: socios){
            Vector<String> row = new Vector();
            row.add(String.valueOf(soc.getIdSocio()));
            row.add(soc.getCedula());
            row.add(soc.getNombre());
            row.add(soc.getApellido());
            row.add(soc.getMembresia().getTipo());
            row.add(String.valueOf(soc.getSexo()));
            
            dtblmSocio.addRow(row);
        }
    }
    
}
