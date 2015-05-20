/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */

package ViewLayer.RecursosHumanos;

import DomainModelLayer.Controllers.RecursosHumanos;
import DomainModelLayer.Entidades.Empleado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class CancelarEmpleado extends JPanel{
    private static CancelarEmpleado instance;
    private JDesktopPane jdoFondo;
    private JPanel jpnlBuscar;
    private JPanel jpnlDatos;
    private JComboBox jcboBuscarPor;
    private JButton jbtnBuscar;
    private JButton jbtnEliminar;
    private JTextField jtxtBuscar;
    private JLabel jlblFoto;
    private JTable jtblEmpleado;
    private DefaultTableModel jdtblmEmpleado;
    private JScrollPane jscrollPane;
    private Vector<Empleado> empleados;
    
    /**
    * Constructor privado para la clase CancelarEmpleado.
    */
    private  CancelarEmpleado(){
        setLayout(null);
        setSize(715, 540);
        jdoFondo = new JDesktopPane();
        jpnlBuscar = new JPanel();
        jcboBuscarPor = new JComboBox();
        jbtnBuscar = new JButton("Buscar");
        jbtnEliminar = new JButton("Eliminar");
        jtxtBuscar = new JTextField();
        jlblFoto = new JLabel();
        jpnlDatos = new JPanel();
        jtblEmpleado = new JTable();
        jdtblmEmpleado = new DefaultTableModel();
        jscrollPane = new JScrollPane(jtblEmpleado);
        empleados = new Vector();
        
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
        jdoFondo.setSize(715, 540);
	
        jpnlBuscar.setBounds(40, 40, 300, 190);
        jpnlBuscar.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Buscar Empleado por"));
        jpnlBuscar.setBackground(Color.WHITE);
        jpnlBuscar.setLayout(null);
        
        jcboBuscarPor.setBounds(25,30,150,20);
        jcboBuscarPor.addItem("<Seleccione>");
        jcboBuscarPor.addItem("Id");
        jcboBuscarPor.addItem("Cedula");
        jcboBuscarPor.addItem("Nombre");
        jcboBuscarPor.addItem("Apellido");
        
        jbtnBuscar.setBounds(170, 100, 120, 40);
        jbtnBuscar.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));
        
        jbtnEliminar.setBounds(568, 210, 120, 40);
        jbtnEliminar.setIcon(new ImageIcon(getClass().getResource("/imagenes/delete.png")));
        
        jtxtBuscar.setBounds(180,30,100,20);
        
        jlblFoto.setBounds(500, 30, 180, 180);
        jlblFoto.setIcon(new ImageIcon(getClass().getResource("/imagenes/logo_tecno_gym_uruapan.jpg"))); 
        
        jpnlDatos.setBounds(40, 250, 650, 250);
        jpnlDatos.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Datos de Empleado"));
        jpnlDatos.setBackground(Color.WHITE);
        jpnlDatos.setLayout(null);

        jdtblmEmpleado = new DefaultTableModel(
             new Object [][] {
                {null, null, null, null, null}
            }, new String [] {"Id", "Cedula", "Nombre", "Apellido", "Puesto"}
        ){
            @Override
             public boolean isCellEditable (int fila, int columna) {
                 if (columna >= 0)
                     return false;
                 return true;
             }
        };
        jtblEmpleado.setModel(jdtblmEmpleado);
        jscrollPane.setBounds(20, 30, 610, 200);
          
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
        jbtnBuscar.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        if(jtxtBuscar.getText().length() > 0 &&
                           jcboBuscarPor.getSelectedIndex() > 0 ){
                            String filtroBusqueda = jtxtBuscar.getText();
                            int campoBusqueda = jcboBuscarPor.getSelectedIndex();
                            empleados = RecursosHumanos.buscarEmpleado(filtroBusqueda, campoBusqueda);
                            insertToTable();
                        }
                        else{
                            JOptionPane.showMessageDialog(instance, "Datos incorrectos..");
                        }
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(instance, "Datos incorrectos..");
                    }
                }
            });
                       
            jbtnEliminar.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = jtblEmpleado.getSelectedRow();
                    
                    if( selectedRow == -1){
                        JOptionPane.showMessageDialog(instance,
                                "No selecciono ninguna fila"
                        );
                    }
                    else{
                        Empleado emp = empleados.get(selectedRow);
                        try {
                            RecursosHumanos.cancelarEmpleado(emp);
                            JOptionPane.showMessageDialog(instance,
                                    "Empleado cancelado");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(instance, "Datos incorrectos..");
                        }
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
        jdtblmEmpleado.setRowCount(0);
        
        for(Empleado emp: empleados){
            Vector<String> row = new Vector();
            row.add(String.valueOf(emp.getIdEmpleado()));
            row.add(emp.getCedula());
            row.add(emp.getNombre());
            row.add(emp.getApellido());
            row.add(emp.getPuesto());
           
            jdtblmEmpleado.addRow(row);
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
        add(jdoFondo);
        jpnlDatos.add(jscrollPane, BorderLayout.CENTER);
        jpnlBuscar.add(jtxtBuscar);
        jpnlBuscar.add(jbtnBuscar);
        jpnlBuscar.add(jcboBuscarPor);
        jdoFondo.add(jbtnEliminar);
        jdoFondo.add(jpnlBuscar);
        jdoFondo.add(jpnlDatos);
        jdoFondo.add(jlblFoto);
    }
    
     /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Cancelar empleado y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static CancelarEmpleado getInstance(){
         if(instance == null){
            instance = new CancelarEmpleado();           
        }
        return instance;
    }

}
