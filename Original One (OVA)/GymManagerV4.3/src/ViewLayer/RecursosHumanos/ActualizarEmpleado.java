/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */

package ViewLayer.RecursosHumanos;

import DomainModelLayer.Controllers.RecursosHumanos;
import DomainModelLayer.Entidades.Direccion;
import DomainModelLayer.Entidades.Empleado;
import DomainModelLayer.Entidades.Instructor;
import DomainModelLayer.Entidades.PersAdministrativo;
import DomainModelLayer.Entidades.Telefono;
import Utilidades.CheckField;
import Utilidades.jText;
import Utilidades.jTextNum;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class ActualizarEmpleado extends JPanel{
    private static ActualizarEmpleado instance;
    private JDesktopPane jdpFondo;
    private JPanel jpnlSocio;
    private JPanel jpnlSexo;
    private JPanel jpnlTelefono;
    private JPanel jpnlDireccion;
    private JPanel jpnlDatosEmp;
    private JPanel jpnlSelect;
    private JLabel jlblNombre;
    private JLabel jlblCedula;
    private JLabel jlblApellido;
    private JLabel jlblDechaNac;
    private JLabel jlblMunicipio;
    private JLabel jlblSector;
    private JLabel jlblCalle;
    private JLabel jlblNumeroCasa;
    private JLabel jlblArea;
    private JLabel jlblUserName;
    private JLabel jlblPassword;
    private JLabel jlblPassword2;
    private JLabel jlblSueldo;
    private jText jtxtNombre; 
    private JTextField jtxtCedula;
    private jText jtxtApellido;
    private jTextNum jtxtNumeroCasa;
    private jText jtxtEspecialidad;
    private JTextField jtxtUserName;
    private JPasswordField jtxtPassword;
    private JPasswordField jtxtPassword2;
    private jTextNum jtxtCampoBusqueda;
    private JTextField jtxtCalle;
    private jText jtxtSector;
    private jText jtxtMunicipio;
    private jTextNum jtxtSueldo;
    private JDateChooser jdtcFechaNac;
    private JRadioButton jradMasculino;
    private JRadioButton jradFemenino;
    private JRadioButton jradPersAadministrativo;
    private JRadioButton jradInstructor;
    private ButtonGroup jradSexo;
    private ButtonGroup jradEmpleado;
    private JComboBox jcboTipoTelefono;
    private JButton jbtnActualizar;
    private JButton jbtnBuscar;
    private JTable jtblTelefonos;
    private DefaultTableModel dtblmTelefonos;
    private JScrollPane jscrollTelefonos;
    private Empleado empleado;
    private JButton jbtnEliminarTelefono;
    private JButton jbtnAgregarTelefono;
    private JLabel lblIdEmpleado;
        
    /**
    * Constructor privado para la clase ActualizarEmpleado.
    */
    private  ActualizarEmpleado() throws Exception{
        setLayout(null);
        setSize(715, 540);
        
        jdpFondo = new JDesktopPane();
        jdpFondo.setBackground(Color.WHITE);
        jpnlSelect = new JPanel();
        jtxtCampoBusqueda = new jTextNum();
        jradPersAadministrativo = new JRadioButton("Pers. Administrativo");
        jradInstructor = new JRadioButton("Instructor");
        jradEmpleado = new ButtonGroup();
        jbtnBuscar = new JButton("Buscar");
        jpnlSocio = new JPanel();
        jlblCedula = new JLabel("Cedula");
        jtxtCedula = new JFormattedTextField(new MaskFormatter("###########"));
        jlblNombre = new JLabel("Nombre");
        jtxtNombre = new jText();
        jlblApellido = new JLabel("Apellido");
        jtxtApellido = new jText();
        jlblDechaNac = new JLabel("Fec. Nacimiento");
        jdtcFechaNac = new JDateChooser();
        jpnlSexo = new JPanel();
        jradMasculino = new JRadioButton("Masculino");
        jradFemenino = new JRadioButton("Femenino");
        jradSexo = new ButtonGroup();
        jpnlTelefono = new JPanel();
        jtblTelefonos = new JTable();
        dtblmTelefonos = new DefaultTableModel();
        jscrollTelefonos = new JScrollPane(jtblTelefonos);
        jlblArea = new JLabel("Area");
        jtxtEspecialidad = new jText();
        jlblSueldo = new JLabel("Sueldo");
        jlblUserName = new JLabel("Usuario");
        jtxtUserName = new JTextField();
        jlblPassword = new JLabel("Clave");
        jtxtPassword = new JPasswordField();
        jlblPassword2 = new JLabel("Confirmar Clave");
        jtxtPassword2 = new JPasswordField();
        jpnlDatosEmp = new JPanel();
        jpnlDireccion = new JPanel();
        jlblMunicipio = new JLabel("Municipio");
        jlblSector = new JLabel("Sector");
        jlblCalle = new JLabel("Calle");
        jlblNumeroCasa = new JLabel("Numero");
        jtxtNumeroCasa = new jTextNum();
        jbtnActualizar = new JButton("Actualizar");
        jtxtCalle = new JTextField();
        jtxtSector = new jText();
        jtxtMunicipio = new jText();
        jtxtSueldo = new jTextNum();
        String [] tipos = {"Celular", "Residencia", "Oficina",
                           "Fax", "Otros"
        };
        jcboTipoTelefono = new JComboBox(tipos);
        jbtnEliminarTelefono = new JButton("Eliminar Tel.");
        jbtnAgregarTelefono = new JButton("Agregar Tel.");
        lblIdEmpleado = new JLabel("Id Empleado");
                
        crearComponentes();
        addToPanel();
        eraseValues();        
        addActionListeners();
        addDefaultValues();
    }
    
    
    /**
    * Crea y posiciona los elementos contenidos dentro de la ventana. 
    * Tambien aqui se especifican las decoraciones usadas en los
    * componentes visuales presentados.
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void crearComponentes() throws ParseException{
        jdpFondo.setSize(715, 540);
	
        jpnlSelect.setBounds(23, 21, 356, 115);
        jpnlSelect.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Actualizar"));
        jpnlSelect.setBackground(Color.WHITE);
        jpnlSelect.setLayout(null);

        jtxtCampoBusqueda.setBounds(210, 20, 120, 20);
         
        jradPersAadministrativo.setBounds(20, 55, 160, 20);
        jradPersAadministrativo.setBackground(Color.WHITE);
        
        jradInstructor.setBounds(20, 78, 109, 20);
        jradInstructor.setBackground(Color.WHITE);
           
        jbtnBuscar.setBounds(200, 55, 130, 40);
        jbtnBuscar.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));
                
        jpnlSocio.setBounds(23, 135, 356, 163);
        jpnlSocio.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Datos de empleado"));
        jpnlSocio.setBackground(Color.WHITE);
        jpnlSocio.setLayout(null);
        
        jlblCedula.setBounds(22, 23, 50, 20);
        
        jtxtCedula.setBounds(112, 23, 120, 20);
        
        jlblNombre.setBounds(22, 58, 50, 20);
        
        jtxtNombre.setBounds(112, 58, 120, 20);
        
        jlblApellido.setBounds(22, 93, 50, 20);
        
        jtxtApellido.setBounds(112, 93, 120, 20);
        
        jlblDechaNac.setBounds(22, 128, 90, 20);
        
        jdtcFechaNac.setBounds(112, 128, 120, 20);
        
        jpnlSexo.setBounds(246, 15, 100, 80);
        jpnlSexo.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Sexo"));
        jpnlSexo.setBackground(Color.WHITE);
        jpnlSexo.setLayout(null);
        
        jradMasculino.setBounds(10, 20, 85, 20);
        jradMasculino.setBackground(Color.WHITE);
        
        jradFemenino.setBounds(10, 45, 85, 20);
        jradFemenino.setBackground(Color.WHITE);
        
        
        
        jpnlTelefono.setBounds(21, 302, 358, 156);
        jpnlTelefono.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Telefono"));
        jpnlTelefono.setBackground(Color.WHITE);
        jpnlTelefono.setLayout(null);
        dtblmTelefonos = new DefaultTableModel(

             new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            }, new String [] {"idTelefono", "Numero", "Tipo"}
                
        ){
            @Override
             public boolean isCellEditable (int fila, int columna) {
                 if (columna != 0)
                     return true;
                 return false;
             }
        };
        jtblTelefonos.setModel(dtblmTelefonos);

        MaskFormatter mask = new MaskFormatter("##########");
        JFormattedTextField numTel = new JFormattedTextField(mask);
        
        jtblTelefonos.getColumnModel().getColumn(1).setCellEditor(
                new DefaultCellEditor(numTel)
        );
        jtblTelefonos.getColumnModel().getColumn(2).setCellEditor(
                new DefaultCellEditor(jcboTipoTelefono)
        );

        jscrollTelefonos.setBounds(10, 21, 338, 71);
        
        jbtnEliminarTelefono.setBounds(38, 103, 130, 40 );
        jbtnAgregarTelefono.setBounds(188, 103, 130, 40);
        
        jlblArea.setBounds(20, 10, 100, 20);
        jtxtEspecialidad.setBounds(130, 10, 120, 20);
        
        jlblSueldo.setBounds(20, 40, 100, 20);
        jtxtSueldo.setBounds(130, 40, 120, 20);
        
        jlblUserName.setBounds(20, 70, 100, 20);
        jtxtUserName.setBounds(130, 70, 120, 20);
        
        jlblPassword.setBounds(20, 100, 100, 20);
        jtxtPassword.setBounds(130, 100, 120, 20);
        
        jlblPassword2.setBounds(20, 130, 100, 20);
        jtxtPassword2.setBounds(130, 130, 120, 20);
        
        jpnlDatosEmp.setBounds(400, 25, 284, 163);
        jpnlDatosEmp.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        jpnlDatosEmp.setBackground(Color.WHITE);
        jpnlDatosEmp.setLayout(null);
        
        jpnlDireccion.setBounds(400, 256, 284, 202);
        jpnlDireccion.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Direccion"));
        jpnlDireccion.setBackground(Color.WHITE);
        jpnlDireccion.setLayout(null);
        
        jlblMunicipio.setBounds(21, 30, 100, 20);
        
        jlblSector.setBounds(21, 70, 80, 20);
        
        jlblCalle.setBounds(21, 110, 80, 20);
        
        jlblNumeroCasa.setBounds(21, 150, 80, 20);
        
        jtxtNumeroCasa.setBounds(130, 150, 120, 20);
        
        jbtnActualizar.setBounds(554, 473, 130, 40);
        jbtnActualizar.setIcon(new ImageIcon(getClass().getResource("/imagenes/update.png")));
        
        jtxtCalle.setBounds(130, 110, 120, 20);
        jtxtSector.setBounds(130, 70, 120, 20);
        jtxtMunicipio.setBounds(131, 30, 120, 20);
        
        lblIdEmpleado.setBounds(37, 23, 92, 14);
           
    }
   
    /**
    * Asigna valores por defecto a los radioButtons de tipo de Empleado y Sexo. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void addDefaultValues(){
        jradPersAadministrativo.setSelected(true);
        jradMasculino.setSelected(true);
    }
        
   /**
    * Agrega los manejadores de eventos a los componentes visuales. 
    * Aqui se especifican las acciones a tomaar cuando se detecta
    * que un evento ha ocurrido en un componente visual
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */ 
    private void addActionListeners() throws Exception{
        jbtnBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(jtxtCampoBusqueda.getText().length() > 0){
                        int idEmpleado = Integer.valueOf(jtxtCampoBusqueda.getText());
                        String puesto = "";
                        
                        if( jradInstructor.isSelected()){
                            puesto = "Instructor";
                        }
                        else{
                            puesto = "Recepcionista";
                        }
                        eraseValues();
                        empleado = RecursosHumanos.buscarEmpleado(idEmpleado, puesto);
                        setValues(empleado);
                    }
                    else{
                        JOptionPane.showMessageDialog(instance, "Busqueda invalida");
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, ex.getMessage());
                }
            }
        });
        
        jbtnActualizar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if( checkInputValues() ){
                        if(JOptionPane.showConfirmDialog(instance, "¿Enviar cambios?") == 0){
                            obtainValues();
                            RecursosHumanos.actualizarEmpleado(empleado);
                            JOptionPane.showMessageDialog(instance,
                                "Datos Actualizados Correctamente"
                            );
                            eraseValues();
                        }
                        else{
                            JOptionPane.showMessageDialog(instance, "Operacion Cancelada");
                        }
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(instance, "Datos incorrectos..");
                    }
                }catch(Exception ex){
                    if(!(ex instanceof Exception)){
                        JOptionPane.showMessageDialog(instance, "Error al actualizar los datos");
                    }
                    else{
                        JOptionPane.showMessageDialog(instance, ex.getMessage());
                    }
                }
            }
        });
        
        jbtnAgregarTelefono.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(dtblmTelefonos.getRowCount() < 3){
                    dtblmTelefonos.addRow(new Vector());
                }
                else{
                    JOptionPane.showMessageDialog(instance, "no puede agregar mas telefonos");
                }
            }
        });
        
        jbtnEliminarTelefono.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jtblTelefonos.getSelectedRow();

                if( selectedRow == -1){
                    JOptionPane.showMessageDialog(instance,
                            "No selecciono ninguna fila"
                    );
                }
                else{
                    dtblmTelefonos.removeRow(selectedRow);
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
        add(jdpFondo);
        jdpFondo.add(jpnlSelect);
        jpnlSelect.add(jbtnBuscar);
        jradEmpleado.add(jradPersAadministrativo);
        jradEmpleado.add(jradInstructor);
        jradSexo.add(jradMasculino);
        jradSexo.add(jradFemenino);
        jpnlSelect.add(jradPersAadministrativo);
        jpnlSelect.add(jradInstructor);
        jpnlSelect.add(jtxtCampoBusqueda);
        jpnlSelect.add(lblIdEmpleado);
        jpnlSocio.add(jlblCedula);
        jpnlSocio.add(jtxtCedula);
        jpnlSocio.add(jlblNombre);
        jpnlSocio.add(jtxtNombre);
        jpnlSocio.add(jlblApellido);
        jpnlSocio.add(jtxtApellido);
        jpnlSocio.add(jlblDechaNac);
        jpnlSocio.add(jdtcFechaNac);
        jpnlSocio.add(jpnlSexo);
        jpnlSexo.add(jradMasculino);
        jpnlSexo.add(jradFemenino);
        jdpFondo.add(jpnlSocio);
        jdpFondo.add(jpnlTelefono);
        jpnlTelefono.add(jscrollTelefonos, BorderLayout.CENTER);
        jpnlTelefono.add(jbtnEliminarTelefono);
        jpnlTelefono.add(jbtnAgregarTelefono);
        jdpFondo.add(jpnlDatosEmp);
        jpnlDatosEmp.add(jlblArea);
        jpnlDatosEmp.add(jtxtEspecialidad);
        jpnlDatosEmp.add(jlblSueldo);
        jpnlDatosEmp.add(jtxtSueldo);
        jpnlDatosEmp.add(jlblUserName);
        jpnlDatosEmp.add(jtxtUserName);
        jpnlDatosEmp.add(jlblPassword);
        jpnlDatosEmp.add(jtxtPassword);
        jpnlDatosEmp.add(jlblPassword2);
        jpnlDatosEmp.add(jtxtPassword2);
        jdpFondo.add(jpnlDireccion);
        jpnlDireccion.add(jlblMunicipio);
        jpnlDireccion.add(jlblSector);
        jpnlDireccion.add(jlblCalle);
        jpnlDireccion.add(jlblNumeroCasa);
        jpnlDireccion.add(jtxtNumeroCasa);
        jpnlDireccion.add(jtxtCalle);
        jpnlDireccion.add(jtxtSector);
        jpnlDireccion.add(jtxtMunicipio);
        jdpFondo.add(jbtnActualizar);
    
    }
    
    /**
    * Inserta los valores obtenidos de un Empleado provenientes de la base de datos. 
    * <p>
    * @param emp El empleado requerido para actualizar sus datos.
    *
    */
    private void setValues(Empleado emp) throws ParseException{

        jtxtCedula.setText(emp.getCedula());
        jtxtNombre.setText(emp.getNombre());
        jtxtApellido.setText(emp.getApellido());
        jdtcFechaNac.setDate(emp.getFechaNacimiento());

        dtblmTelefonos.setRowCount(0);
        for(Telefono obj: emp.getTelefonos())
            dtblmTelefonos.addRow(obj.toVector());
        
        jtxtMunicipio.setText(emp.getDireccion().getMunicipio());
        jtxtSector.setText(emp.getDireccion().getSector());
        jtxtCalle.setText(emp.getDireccion().getCalle());        
        jtxtNumeroCasa.setText(String.valueOf(emp.getDireccion().getNumero()));

        if(emp instanceof Instructor)
            jtxtEspecialidad.setText(((Instructor)emp).getEspecialidad());
        else{
            jtxtUserName.setText(((PersAdministrativo)emp).getUserName());
        }

        jtxtSueldo.setText(String.valueOf(emp.getSalario()) );

    }
    
    /**
    * Este metodo recoje todos los valores introducidos en los componentes
    * visuales y los almacena en un objeto de tipo empleado para luego
    * enviar los cambios a la base de datos.
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void obtainValues(){
        empleado.setCedula(jtxtCedula.getText());
        empleado.setNombre(jtxtNombre.getText());
        empleado.setApellido(jtxtApellido.getText());
        
        if(jradMasculino.isSelected())
            empleado.setSexo('M');
        else
            empleado.setSexo('F');
        
        empleado.setFechaNacimiento(jdtcFechaNac.getDate());
        empleado.setTelefonos(new Vector<Telefono>());
        
        for(Vector data: (Vector<Vector>)dtblmTelefonos.getDataVector()){
            if( data.get(0) != null){
                empleado.getTelefonos().add(
                    new Telefono(Integer.valueOf((String)data.get(0)),
                        (String)data.get(1),
                        (String)data.get(2)
                    )            
                );
            }
            else{
                empleado.getTelefonos().add(
                    new Telefono( (String)data.get(1),
                        (String)data.get(2)
                    )            
                );
            }
        }
        
        empleado.setSalario(Double.valueOf(jtxtSueldo.getText()));
        
        Direccion dir = new Direccion(empleado.getDireccion().getIdDireccion(),
                jtxtMunicipio.getText(),
                jtxtSector.getText(),
                jtxtCalle.getText(),
                Integer.valueOf(jtxtNumeroCasa.getText())
        );                               
        empleado.setDireccion(dir);
        
        if( empleado instanceof Instructor)
            ((Instructor)empleado).setEspecialidad(jtxtEspecialidad.getText());
        else{
            ((PersAdministrativo)empleado).setUserName(String.valueOf(jtxtUserName.getText()));
            if(jtxtPassword.getText() != null && !jtxtPassword.getText().equals(""))
                ((PersAdministrativo)empleado).setPassword(String.valueOf(jtxtPassword.getText()));
        }
    }
    
    /**
    * Elimina todos los valores de los componentes visuales de entrada de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void eraseValues(){
        jtxtCedula.setText(null);
        jtxtNombre.setText(null);
        jtxtApellido.setText(null);
        jdtcFechaNac.setDate(null);
        dtblmTelefonos.setRowCount(0);
        jtxtEspecialidad.setText(null);
        jtxtSueldo.setText(null);
        jtxtUserName.setText(null);
        jtxtPassword.setText(null);
        jtxtPassword2.setText(null);
        jtxtMunicipio.setText(null);
        jtxtSector.setText(null);
        jtxtCalle.setText(null);
        jtxtNumeroCasa.setText(null);
    }
    
    /**
    * Verifica la validez en terminos generales de los valores introducidos
    * en los componentes visuales. 
    * <p>
    * Este metodo lanaza una excepcion indicando que campo tiene valores incorrectos.
    * 
    * @return un valor booleano true si los valores son correctos.
    *
    */
    private boolean checkInputValues() throws Exception{
        for(Vector data: (Vector<Vector>)dtblmTelefonos.getDataVector()){
            if( !CheckField.checkTel((String)data.get(1)) )
                return false;
        }
        if( jtxtPassword.getPassword() == null || jtxtUserName.getText() == null ||
            (jtxtPassword.getText().length() < 6 || jtxtUserName.getText().length() < 6 ) ) 
            throw new Exception("debe introducir un usuario y clave de al menos 6 caracteres");

        if(!jtxtPassword.getText().equals(jtxtPassword2.getText()))
            throw new Exception("Las Contraseñas no coinciden");
           
        if(jtxtCampoBusqueda.getText() == null)
            throw new Exception("Debe seleccionar una busqueda");
        return true;
    }
    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Actualizar empleado y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static ActualizarEmpleado getInstance() throws Exception{
         if(instance == null){
            instance = new ActualizarEmpleado();           
        }
        return instance;
    }
}
