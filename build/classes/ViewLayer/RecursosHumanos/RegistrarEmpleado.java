/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */

package ViewLayer.RecursosHumanos;

import DomainModelLayer.Controllers.RecursosHumanos;
import DomainModelLayer.Entidades.Telefono;
import Utilidades.jText;
import Utilidades.jTextNum;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

public final class RegistrarEmpleado extends JPanel{ 
    private static RegistrarEmpleado instance;
    private JDesktopPane jdpFondo;
    private JPanel jpnlSocio;
    private JPanel jpnlSexo;
    private JPanel jpnlTelefono;
    private JPanel jpnlTelefono1;
    private JPanel jpnlTelefono2;
    private JPanel jpnlTelefono3;
    private JPanel jpnlEmpleado;
    private JPanel jPanelDireccion;
    private JPanel jpnlDatosEmp;
    private JLabel jlblNombre;
    private JLabel jlblCedula;
    private JLabel jlblApellido;
    private JLabel jlblFechaNac;
    private JLabel jlblTipo1;
    private JLabel jlblTipo2;
    private JLabel jlblTipo3;
    private JLabel jlblNumero1;
    private JLabel jlblNumero2;
    private JLabel jlblNumero3;
    private JLabel jlblEmpleado;
    private JLabel jlblMunicipio;
    private JLabel jlblSector;
    private JLabel jlblCalle;
    private JLabel jlblNumeroCasa;
    private JLabel jlblEspecialidad;
    private JLabel jlblPassword;
    private JLabel jlblSueldo;
    private JLabel jlblUserName;
    private jText jtxtNombre;
    private JTextField jtxtCedula;
    private jText jtxtApellido;
    private JTextField jtxtTelefono1;
    private JTextField jtxtTelefono2; 
    private JTextField jtxtTelefono3;
    private JTextField jtxtNumeroCasa;
    private jText jtxtEspecialidad;
    private JTextField jtxtUserName;
    private JPasswordField jtxtPassword;
    private jTextNum jtxtSueldo;
    private JDateChooser jdcFechaNacimiento;
    private JRadioButton jradMasculino;
    private JRadioButton jradFemenino;
    private ButtonGroup jbtgSexo;
    private JComboBox jcboTelefono1;
    private JComboBox jcboTelefono2;
    private JComboBox jcboTelefono3;
    private JComboBox jcboTipoEmpleado;
    private JButton jbtnRegistrar;
    private jText jtxtMunicipio;
    private jText jtxtSector;
    private JTextField jtxtCalle;
    private JPasswordField jtxtPassword2;
    private JLabel jlblPassword2;
      
    /**
    * Constructor privado para la clase CancelarEmpleado.
    */
    private RegistrarEmpleado() throws ParseException{
        setLayout(null);
        setSize(715, 540);
        
        jdpFondo = new JDesktopPane();
        jdpFondo.setBackground(Color.WHITE);
        jpnlSocio = new JPanel();
        jlblCedula = new JLabel("Cedula");
        jtxtCedula = new JFormattedTextField(new MaskFormatter("###########"));
        jlblNombre = new JLabel("Nombre");
        jtxtNombre = new jText();
        jlblApellido = new JLabel("Apellido");
        jtxtApellido = new jText();
        jlblFechaNac = new JLabel("Fec. Nacimiento");
        jdcFechaNacimiento = new JDateChooser();
        jradMasculino = new JRadioButton("Masculino");
        jpnlSexo = new JPanel();
        jradFemenino = new JRadioButton("Femenino");
        jpnlTelefono1 = new JPanel();
        jbtgSexo = new ButtonGroup();
        jpnlTelefono = new JPanel();
        jlblTipo1 = new JLabel("Tipo");
        jcboTelefono1 = new JComboBox();
        jlblNumero1 = new JLabel("Numero");
        jtxtTelefono1 = new JFormattedTextField(new MaskFormatter("##########"));
        jpnlTelefono2 = new JPanel();
        jlblTipo2 = new JLabel("Tipo");
        jcboTelefono2 = new JComboBox();
        jlblNumero2 = new JLabel("Numero");
        jtxtTelefono2 = new JFormattedTextField(new MaskFormatter("##########"));
        jpnlTelefono3 = new JPanel();
        jlblTipo3 = new JLabel("Tipo");
        jcboTelefono3 = new JComboBox();
        jlblNumero3 = new JLabel("Numero");
        jtxtTelefono3 = new JFormattedTextField(new MaskFormatter("##########"));
        jpnlEmpleado = new JPanel();
        jlblEspecialidad = new JLabel("Especialidad");
        jtxtEspecialidad = new jText();
        jlblSueldo = new JLabel("Sueldo");
        jtxtSueldo = new jTextNum();
        jlblPassword = new JLabel("Clave");
        jtxtPassword = new JPasswordField(30);
        jlblEmpleado = new JLabel("Empleado");
        jcboTipoEmpleado = new JComboBox();
        jpnlDatosEmp = new JPanel();
        jPanelDireccion = new JPanel();
        jlblMunicipio = new JLabel("Municipio");
        jlblCalle = new JLabel("Calle");
        jlblSector = new JLabel("Sector");
        jlblNumeroCasa = new JLabel("Numero");
        jbtnRegistrar = new JButton("Registrar");
        jtxtNumeroCasa = new jTextNum();
        jtxtMunicipio = new jText();
        jtxtSector = new jText();
        jtxtCalle = new JTextField();
        jtxtUserName = new JTextField();
        jlblUserName = new JLabel("Usuario");
        jtxtPassword2 = new JPasswordField();
        jlblPassword2 = new JLabel("Confirmar Clave");
        
        crearComponentes();
        addToPanel();
        addDefaultValues();
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
    public void crearComponentes() throws ParseException{
        jdpFondo.setSize(715, 540);
        
        jpnlSocio.setBounds(40, 40, 330, 150);
        jpnlSocio.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Datos de empleado"));
        jpnlSocio.setBackground(Color.WHITE);
        jpnlSocio.setLayout(null);
        
        jlblCedula.setBounds(60, 15, 50, 30);
        
        jtxtCedula.setBounds(110, 20, 90, 20);
        
        jlblNombre.setBounds(55, 40, 50, 30);
        
        jtxtNombre.setBounds(110, 45, 90, 20);
        
        jlblApellido.setBounds(55, 65, 50, 30);
        
        jtxtApellido.setBounds(110, 70, 90, 20);
        
        jlblFechaNac.setBounds(10, 90, 90, 30);
        
        jdcFechaNacimiento.setBounds(110, 95, 90, 20);
        
        jpnlSexo.setBounds(210, 10, 100, 80);
        jpnlSexo.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Sexo"));
        jpnlSexo.setBackground(Color.WHITE);
        jpnlSexo.setLayout(null);
        
        jradMasculino.setBounds(10, 20, 85, 20);
        jradMasculino.setBackground(Color.WHITE);
        
        jradFemenino.setBounds(10, 45, 85, 20);
        jradFemenino.setBackground(Color.WHITE);
        
        jbtgSexo.add(jradMasculino);
        jbtgSexo.add(jradFemenino);
        
        jpnlTelefono.setBounds(40, 200, 330, 270);
        jpnlTelefono.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Telefono"));
        jpnlTelefono.setBackground(Color.WHITE);
        jpnlTelefono.setLayout(null);
        
        jpnlTelefono1.setBounds(10, 20, 310,70);
        jpnlTelefono1.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Telefono 1"));
        jpnlTelefono1.setBackground(Color.WHITE);
        jpnlTelefono1.setLayout(null);
        
        jlblTipo1.setBounds(10, 30, 50, 20);
        
        jcboTelefono1.setBounds(40, 30, 100, 20);
        jcboTelefono1.addItem("<Seleccione>");
        jcboTelefono1.addItem("Celular");
        jcboTelefono1.addItem("Residencia");
        jcboTelefono1.addItem("Oficina");
        jcboTelefono1.addItem("Fax");
        jcboTelefono1.addItem("Otros");
        
        jlblNumero1.setBounds(150, 30, 50, 20);
        
        jtxtTelefono1.setBounds(200, 30, 90, 20);
        
        jpnlTelefono2.setBounds(10, 100, 310,70);
        jpnlTelefono2.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Telefono 2"));
        jpnlTelefono2.setBackground(Color.WHITE);
        jpnlTelefono2.setLayout(null);
        
        jlblTipo2.setBounds(10, 30, 50, 20);
        
        jcboTelefono2.setBounds(40, 30, 100, 20);
        jcboTelefono2.addItem("<Seleccione>");
        jcboTelefono2.addItem("Celular");
        jcboTelefono2.addItem("Residencia");
        jcboTelefono2.addItem("Oficina");
        jcboTelefono2.addItem("Fax");
        jcboTelefono2.addItem("Otros");
        
        jlblNumero2.setBounds(150, 30, 50, 20);
        
        jtxtTelefono2.setBounds(200, 30, 90, 20);
        
        jpnlTelefono3.setBounds(10, 180, 310,70);
        jpnlTelefono3.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Telefono 3"));
        jpnlTelefono3.setBackground(Color.WHITE);
        jpnlTelefono3.setLayout(null);
        
        jlblTipo3.setBounds(10, 30, 50, 20);
        
        jcboTelefono3.setBounds(40, 30, 100, 20);
        jcboTelefono3.addItem("<Seleccione>");
        jcboTelefono3.addItem("Celular");
        jcboTelefono3.addItem("Residencia");
        jcboTelefono3.addItem("Oficina");
        jcboTelefono3.addItem("Fax");
        jcboTelefono3.addItem("Otros");
        
        jlblNumero3.setBounds(150, 30, 50, 20);
        
        jtxtTelefono3.setBounds(200, 30, 90, 20);
        
        jpnlEmpleado.setBounds(400, 40, 300, 50);
        jpnlEmpleado.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Tipo de empleado"));
        jpnlEmpleado.setBackground(Color.WHITE);
        jpnlEmpleado.setLayout(null);
        
        jlblEspecialidad.setBounds(20, 10, 110, 20);
        
        jtxtEspecialidad.setBounds(145, 10, 120, 20);
        
        jlblSueldo.setBounds(20, 40, 100, 20);
        
        jtxtSueldo.setBounds(145, 40, 120, 20);
        
        jlblUserName.setBounds(20, 70, 110, 20);
        
        jtxtUserName.setBounds(145, 70, 120, 20);
        
        jlblPassword.setBounds(20, 100, 110, 20);
        
        jtxtPassword.setBounds(145, 100, 120, 20);
        
        jlblPassword2.setBounds(20, 130, 110, 20);
        
        jtxtPassword2.setBounds(145, 130, 120, 20);
        
        jlblEmpleado.setBounds(20, 20, 80, 20);
        
        jcboTipoEmpleado.setBounds(99, 20, 181, 20);
        jcboTipoEmpleado.addItem("<Seleccione>");
        jcboTipoEmpleado.addItem("Instructor");
        jcboTipoEmpleado.addItem("Recepcionista");
        jcboTipoEmpleado.addItem("Cajero");
        jcboTipoEmpleado.addItem("Emp. Registro");
        jcboTipoEmpleado.addItem("Emp. Recursos Humanos");
        
        jpnlDatosEmp.setBounds(400, 98, 300, 165);
        jpnlDatosEmp.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        jpnlDatosEmp.setBackground(Color.WHITE);
        jpnlDatosEmp.setLayout(null);
        
        jPanelDireccion.setBounds(400, 270, 300, 200);
        jPanelDireccion.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Direccion"));
        jPanelDireccion.setBackground(Color.WHITE);
        jPanelDireccion.setLayout(null);
        
        jlblMunicipio.setBounds(24, 31, 60, 14);
        
        jlblSector.setBounds(24, 71, 48, 14);
        
        jlblCalle.setBounds(24, 114, 48, 14);
        
        jlblNumeroCasa.setBounds(24, 158, 48, 14);
        
        jtxtMunicipio.setBounds(131, 28, 120, 20);
        
        jtxtSector.setBounds(131, 68, 120, 20);
        
        jtxtCalle.setBounds(131, 111, 120, 20);
        
        jtxtNumeroCasa.setBounds(131, 155, 120, 20);
       
        jbtnRegistrar.setBounds(570, 478, 130, 40);
        jbtnRegistrar.setIcon(new ImageIcon(getClass().getResource("/imagenes/base-de-datos-de-guardar.png")));
        
    }
    
    /**
    * Agrega los manejadores de eventos a los componentes visuales. 
    * Aqui se especifican las acciones a tomaar cuando se detecta
    * que un evento ha ocurrido en un componente visual
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */ 
    public void addActionListeners(){
        jbtnRegistrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if( checkInputValues() ){

                        String nombre = jtxtNombre.getText();
                        String apellido = jtxtApellido.getText();
                        String cedula = jtxtCedula.getText();
                        
                        Character sexo;

                        if(jradMasculino.isSelected()){
                            sexo = 'M';
                        }
                        else{
                            sexo = 'F';
                        }

                        String puesto = jcboTipoEmpleado.getSelectedItem().toString();
                        Double sueldo = Double.valueOf(jtxtSueldo.getText());
                        Date fechaNacimiento = jdcFechaNacimiento.getDate();


                        Vector<Telefono> telefonos = new Vector<Telefono>();

                        if( jcboTelefono1.getSelectedIndex() != 0 ){
                            telefonos.add(new Telefono( jtxtTelefono1.getText(),
                                                        (String)jcboTelefono1.getSelectedItem()));
                        }
                        if( jcboTelefono2.getSelectedIndex() != 0 ){
                            telefonos.add(new Telefono( jtxtTelefono2.getText(),
                                                        (String)jcboTelefono2.getSelectedItem()));
                        }
                        if( jcboTelefono3.getSelectedIndex() != 0 ){
                            telefonos.add(new Telefono( jtxtTelefono3.getText(),
                                                        (String)jcboTelefono3.getSelectedItem()));
                        }

                        String municipio = jtxtMunicipio.getText();
                        String sector = jtxtSector.getText();
                        String calle = jtxtCalle.getText();
                        int numero = Integer.valueOf(jtxtNumeroCasa.getText());
                        
                        if( puesto.equals("Instructor"))
                        {    
                            String especialidad = jtxtEspecialidad.getText();
                            RecursosHumanos.registrarInstructor(cedula, nombre, apellido,
                                    fechaNacimiento,sexo, telefonos, puesto, sueldo,
                                    municipio, sector, calle, numero, especialidad
                            );
                        }
                        
                        else
                        {
                            String userName = jtxtUserName.getText();
                            String password = String.valueOf(jtxtPassword.getPassword());
                            RecursosHumanos.registrarEmpleado(cedula, nombre, apellido, fechaNacimiento,
                                sexo, telefonos, puesto, sueldo, municipio,
                                sector, calle, numero, userName, password
                            );
                        }
                            
                        JOptionPane.showMessageDialog(instance, "El empleado " +
                                                      nombre + " " + apellido + " se registro exitosamente");
                        clearCampos();
                    }
                    else{
                        JOptionPane.showMessageDialog(instance, "Los datos introducidos son incorrectos...");
                    }
                } catch (Exception ex) {
                    if(!(ex instanceof Exception)){
                        JOptionPane.showMessageDialog(instance, "Los datos introducidos son incorrectos...");
                    } else{
                        JOptionPane.showMessageDialog(instance, ex.getMessage());
                    }
                }

            }
        });
        
        jcboTipoEmpleado.addItemListener(new ItemListener() {
            
            @Override
            public void itemStateChanged(ItemEvent e){
                if(jcboTipoEmpleado.getSelectedIndex() == 1){
                    jtxtEspecialidad.setEnabled(true);
                    jtxtPassword.setEnabled(false);
                    jtxtPassword2.setEnabled(false);
                    jtxtSueldo.setEnabled(true);
                }
                else if(jcboTipoEmpleado.getSelectedIndex() >= 2){
                    jtxtEspecialidad.setEnabled(false);
                    jtxtPassword.setEnabled(true);
                    jtxtPassword2.setEnabled(true);
                    jtxtSueldo.setEnabled(true); 
                    jtxtUserName.setEnabled(true);
                }
            }
        });
    }
    
    /**
    * Asigna valores por defecto al radioButtons de tipo de Sexo
    * y hace que varios textFields no sean editables. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    public void addDefaultValues(){
        jradMasculino.setSelected(true);
       
        jtxtEspecialidad.setEnabled(false);
        jtxtPassword.setEnabled(false);
        jtxtSueldo.setEnabled(false);
        jtxtUserName.setEnabled(false);
        jtxtPassword2.setEnabled(false);
        
    }
    
    /**
    * Elimina todos los valores de los componentes visuales de entrada de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    public void clearCampos(){
        jtxtCedula.setText("");
        jtxtApellido.setText("");
        jtxtNombre.setText("");
        jtxtNumeroCasa.setText("");
        jtxtEspecialidad.setText("");
        jtxtPassword.setText("");
        jtxtPassword2.setText("");
        jtxtCalle.setText("");
        jtxtSector.setText("");
        jtxtSueldo.setText("");
        jtxtTelefono1.setText("");
        jtxtTelefono2.setText("");
        jtxtTelefono3.setText("");
        jtxtUserName.setText("");
        jtxtMunicipio.setText("");
    }
    
    /**
    * Agrega todos los componentes visuales al GUI y sus subcomponentes. 
    * Tambien aqui se agregan los BorderLayout de los componentes agregados
    * de ser necesario
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    public void addToPanel(){
        add(jdpFondo);
        jpnlSocio.add(jlblCedula);
        jpnlSocio.add(jtxtCedula);
        jpnlSocio.add(jlblNombre);
        jpnlSocio.add(jtxtNombre);
        jpnlSocio.add(jlblApellido);
        jpnlSocio.add(jtxtApellido);
        jpnlSocio.add(jlblFechaNac);
        jpnlSocio.add(jdcFechaNacimiento);
        jpnlSocio.add(jpnlSexo);
        jpnlSexo.add(jradMasculino);
        jpnlSexo.add(jradFemenino);
        jdpFondo.add(jpnlSocio);
        jdpFondo.add(jpnlTelefono);
        jpnlTelefono.add(jpnlTelefono1);
        jpnlTelefono.add(jpnlTelefono2);
        jpnlTelefono.add(jpnlTelefono3);
        jpnlTelefono1.add(jlblTipo1);
        jpnlTelefono1.add(jcboTelefono1);
        jpnlTelefono1.add(jlblNumero1);
        jpnlTelefono1.add(jtxtTelefono1);
        jpnlTelefono2.add(jlblTipo2);
        jpnlTelefono2.add(jcboTelefono2);
        jpnlTelefono2.add(jlblNumero2);
        jpnlTelefono2.add(jtxtTelefono2); 
        jpnlTelefono3.add(jlblTipo3);
        jpnlTelefono3.add(jcboTelefono3);
        jpnlTelefono3.add(jlblNumero3);
        jpnlTelefono3.add(jtxtTelefono3);
        jdpFondo.add(jpnlEmpleado);
        jpnlEmpleado.add(jlblEmpleado);
        jpnlEmpleado.add(jcboTipoEmpleado); 
        jdpFondo.add(jpnlDatosEmp);
        jpnlDatosEmp.add(jlblEspecialidad);
        jpnlDatosEmp.add(jtxtEspecialidad);
        jpnlDatosEmp.add(jlblSueldo);
        jpnlDatosEmp.add(jtxtSueldo);
        jpnlDatosEmp.add(jlblPassword);
        jpnlDatosEmp.add(jtxtPassword);
        jpnlDatosEmp.add(jtxtUserName);
        jpnlDatosEmp.add(jlblUserName);
        jpnlDatosEmp.add(jlblPassword2);
        jpnlDatosEmp.add(jtxtPassword2);
        jdpFondo.add(jPanelDireccion);
        jPanelDireccion.add(jlblMunicipio);
        jPanelDireccion.add(jlblSector);
        jPanelDireccion.add(jlblCalle);
        jPanelDireccion.add(jlblNumeroCasa);
        jPanelDireccion.add(jtxtNumeroCasa);
        jPanelDireccion.add(jtxtMunicipio);
        jPanelDireccion.add(jtxtSector);
        jPanelDireccion.add(jtxtCalle);
        jdpFondo.add(jbtnRegistrar);
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
        if(jtxtCedula.getText().length() != 11  )
            return false;
        
        if((jcboTelefono1.getSelectedIndex() >= 1 && jtxtTelefono1.getText().length() != 10) ||
           (jcboTelefono2.getSelectedIndex() >= 1 && jtxtTelefono2.getText().length() != 10) || 
           (jcboTelefono2.getSelectedIndex() >= 1 && jtxtTelefono1.getText().length() != 10) )
            throw new Exception("Revise la informacion de los telefonos");
        
        if( !jcboTipoEmpleado.getSelectedItem().equals("Instructor")  && (
            jtxtPassword.getPassword() == null || jtxtUserName.getText() == null ||
            (jtxtPassword.getText().length() < 6 || jtxtUserName.getText().length() < 6 ) ) )
            throw new Exception("debe introducir un usuario y clave de al menos 6 caracteres");
        
        if( jcboTipoEmpleado.getSelectedItem().equals("Instructor")  &&
            jtxtEspecialidad.getText().length() < 1 )
            throw new Exception("Por favor introduzca la especialidad del instructor");
        
        if( !jtxtPassword.getText().equals(jtxtPassword2.getText()) )
            throw new Exception("Las Contraseñas no coinciden");

        return true;
    }
    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Registrar empleado y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static RegistrarEmpleado getInstance() throws ParseException{
         if(instance == null){
            instance = new RegistrarEmpleado();           
        }
        return instance;
    }
    
}
