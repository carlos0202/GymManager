/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */

package ViewLayer.Registro;

import DomainModelLayer.Controllers.Registro;
import DomainModelLayer.Entidades.Membresia;
import DomainModelLayer.Entidades.Telefono;
import Utilidades.jText;
import Utilidades.jTextNum;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

public final class RegistrarSocio extends JPanel{
    private static RegistrarSocio instance;
    private JDesktopPane jdpFondo;
    private JPanel jpnlSocio;
    private JPanel jpnlSexo;
    private JPanel jpnlTelefono;
    private JPanel jpnlTelefono1;
    private JPanel jpnlTelefono2;
    private JPanel jpnlTelefono3;
    private JPanel jpnlMembresia;
    private JPanel jpnlDireccion;
    private JLabel jlblNombre;
    private JLabel jlblCedula;
    private JLabel jlblApellido;
    private JLabel jlblFechaNac;
    private JLabel jlblPeso;
    private JLabel jlblAltura;
    private JLabel jlblTipo1;
    private JLabel jlblTipo2;
    private JLabel jlblTipo3;
    private JLabel jlblNumero1;
    private JLabel jlblNumero2;
    private JLabel jlblNumero3;
    private JLabel jlblMembresia;
    private JLabel jlblMunicipio;
    private JLabel jlblSector;
    private JLabel jlblCalle;
    private JLabel jlblNumeroCasa;
    private jText jtxtNombre;
    private JTextField jtxtCedula;
    private jText jtxtApellido;
    private jTextNum jtxtPeso;
    private jTextNum jtxtAltura;
    private JTextField jtxtTelefono1;
    private JTextField jtxtTelefono2;
    private JTextField jtxtTelefono3;
    private jTextNum jtxtNumeroCasa;
    private jText jtxtMunicipio;
    private jText jtxtSector;
    private JTextField jtxtCalle;
    private JDateChooser jdcFechaNacimiento;
    private JRadioButton jradMasculino;
    private JRadioButton jradFemenino;
    private ButtonGroup radSexo;
    private JComboBox jcboTelefono1;
    private JComboBox jcboTelefono2;
    private JComboBox jcboTelefono3;
    private JComboBox jcboMembresia;
    private JButton jbtnRegistrar;
    private Vector<Membresia> membresias;
    private Membresia membresia;
    
    /**
    * Constructor privado para la clase RegistrarSocio.
    */
    private  RegistrarSocio() throws Exception{
        setLayout(null);
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
        jlblPeso = new JLabel("Peso");
        jtxtPeso = new jTextNum();
        jlblAltura = new JLabel("Altura");
        jtxtAltura = new jTextNum();
        jradMasculino = new JRadioButton("Masculino");
        jpnlSexo = new JPanel();
        jradFemenino = new JRadioButton("Femenino");
        jpnlTelefono1 = new JPanel();
        jlblMembresia = new JLabel("Membresia");
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
        radSexo = new ButtonGroup();
        jpnlDireccion = new JPanel();
        jlblMunicipio = new JLabel("Municipio");
        jlblCalle = new JLabel("Calle");
        jlblSector = new JLabel("Sector");
        jlblNumeroCasa = new JLabel("Numero");
        jbtnRegistrar = new JButton("Registrar");
        jtxtNumeroCasa = new jTextNum();
        jcboMembresia = new JComboBox();
        jpnlMembresia = new JPanel();
        jbtnRegistrar = new JButton("Registrar");
        jtxtMunicipio = new jText();
        jtxtSector = new jText();
        jtxtCalle = new JTextField();
        
        
        crearComponentes();
        addDefaultValues();
        addToPanel();
        setSize(715, 540);
        addActionListeners();
        
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

                        Date fechaNacimiento = jdcFechaNacimiento.getDate();
                        double peso = jtxtPeso.getDouble();
                        double altura = jtxtAltura.getDouble();
                        membresia = membresias.get(jcboMembresia.getSelectedIndex()-1);
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
                        Registro.registrarSocio(cedula, nombre, apellido, sexo,
                                fechaNacimiento, telefonos, municipio, sector,
                                calle, numero, peso, altura, membresia
                        );
                            
                        JOptionPane.showMessageDialog(instance, "El Socio " +
                                                      nombre + " " + apellido + " se registro exitosamente");
                    }
                    else{
                        JOptionPane.showMessageDialog(instance, "Los datos introducidos son incorrectos...");
                    }
                } catch (Exception ex) {

                    if( ex instanceof Exception)
                        JOptionPane.showMessageDialog(instance, ex.getMessage());
                    else
                        ((SQLException)ex).printStackTrace();
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
        jdpFondo.setSize(715, 540);
	
        jpnlSocio.setBounds(40, 40, 330, 150);
        jpnlSocio.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Datos de socio"));
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
        
        jlblPeso.setBounds(10, 115, 90, 30);
        
        jtxtPeso.setBounds(50, 120, 50, 20);
        
        jlblAltura.setBounds(110, 115, 90,30);
        
        jtxtAltura.setBounds(150, 120, 50, 20);
        
        jpnlSexo.setBounds(210, 10, 100, 80);
        jpnlSexo.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Sexo"));
        jpnlSexo.setBackground(Color.WHITE);
        jpnlSexo.setLayout(null);
        
        jradMasculino.setBounds(10, 20, 85, 20);
        jradMasculino.setBackground(Color.WHITE);
        
        jradFemenino.setBounds(10, 45, 85, 20);
        jradFemenino.setBackground(Color.WHITE);
        
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
        
        jpnlMembresia.setBounds(400, 40, 300, 130);
        jpnlMembresia.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Tipo de membresia"));
        jpnlMembresia.setBackground(Color.WHITE);
        jpnlMembresia.setLayout(null);
        
        jlblMembresia.setBounds(50, 60, 80, 20);
        
        jcboMembresia.setBounds(120, 60, 120, 20);
        jcboMembresia.addItem("<Seleccione>");
        membresias = Registro.buscarMembresias();
        for(Membresia memb: membresias)
            jcboMembresia.addItem(memb.getNombre());
        
        jpnlDireccion.setBounds(400, 180, 300, 200);
        jpnlDireccion.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Direccion"));
        jpnlDireccion.setBackground(Color.WHITE);
        jpnlDireccion.setLayout(null);
        
        jlblMunicipio.setBounds(20, 20, 80, 20);
        
        jlblSector.setBounds(20, 65, 80, 20);
        
        jlblCalle.setBounds(20, 110, 80, 20);
        
        jlblNumeroCasa.setBounds(20, 155, 80, 20);
        
        jtxtMunicipio.setBounds(131, 20, 120, 20);
        
        jtxtSector.setBounds(131, 65, 120, 20);
        
        jtxtCalle.setBounds(131, 110, 120, 20);
        
        jtxtNumeroCasa.setBounds(130, 155, 120, 20);
        
        jbtnRegistrar.setBounds(570, 410, 130, 40);
        jbtnRegistrar.setIcon(new ImageIcon(getClass().getResource("/imagenes/base-de-datos-de-guardar.png")));
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
        radSexo.add(jradMasculino);
        radSexo.add(jradFemenino);
        jpnlSocio.add(jlblCedula);
        jpnlSocio.add(jtxtCedula);
        jpnlSocio.add(jlblNombre);
        jpnlSocio.add(jtxtNombre);
        jpnlSocio.add(jlblApellido);
        jpnlSocio.add(jtxtApellido);
        jpnlSocio.add(jlblFechaNac);
        jpnlSocio.add(jdcFechaNacimiento);
        jpnlSocio.add(jlblPeso);
        jpnlSocio.add(jtxtPeso);
        jpnlSocio.add(jlblAltura);
        jpnlSocio.add(jtxtAltura);
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
        jdpFondo.add(jpnlMembresia);
        jpnlMembresia.add(jlblMembresia);
        jpnlMembresia.add(jcboMembresia);    
        jdpFondo.add(jpnlDireccion);
        jpnlDireccion.add(jlblMunicipio);
        jpnlDireccion.add(jlblSector);
        jpnlDireccion.add(jlblCalle);
        jpnlDireccion.add(jlblNumeroCasa);
        jpnlDireccion.add(jtxtMunicipio);
        jpnlDireccion.add(jtxtSector);
        jpnlDireccion.add(jtxtCalle);
        jpnlDireccion.add(jtxtNumeroCasa);
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
        
        return true;
    }
       
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Registrar Socio y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static RegistrarSocio getInstance() throws Exception{
         if(instance == null){
            instance = new RegistrarSocio();           
         }
        return instance;
    }

}
