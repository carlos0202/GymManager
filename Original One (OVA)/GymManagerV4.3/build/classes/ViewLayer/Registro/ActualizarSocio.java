/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */

package ViewLayer.Registro;

import DomainModelLayer.Controllers.Registro;
import DomainModelLayer.Entidades.*;
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
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class ActualizarSocio extends JPanel{
    private static ActualizarSocio instance;
    private JDesktopPane jdpFondo;
    private JPanel jpnlSocio;
    private JPanel jpnlSexo;
    private JPanel jpnlTelefono;
    private JPanel jpnlMembresia;
    private JPanel jpnlDireccion;
    private JLabel jlblNombre;
    private JLabel jlblCedula;
    private JLabel jlblApellido;
    private JLabel jlblFechaNac;
    private JLabel jlblMembresia;
    private JLabel jlblPeso;
    private JLabel jlblAltura;
    private JLabel jlblProvincia;
    private JLabel jlblCiudad;
    private JLabel jlblDistrito;
    private JLabel jlblSector;
    private JLabel jlblCalle;
    private JLabel jlblNumeroCasa;
    private jText jtxtNombre;
    private JTextField jtxtCedula;
    private jText jtxtApellido;
    private jTextNum jtxtNumeroCasa;
    private jTextNum jtxtPeso;
    private jTextNum jtxtAltura;
    private JDateChooser jdcFechaNacimiento;
    private JRadioButton jradMasculino;
    private JRadioButton jradFemenino;
    private ButtonGroup radSexo;
    private JComboBox jcboMembresia;
    private JButton jbtnActualizar;
    private JTable jtblTelefonos;
    private DefaultTableModel dtblmTelefonos;
    private JScrollPane jscrollPane;
    private jText jtxtMunicipio;
    private jText jtxtSector;
    private JTextField jtxtCalle;
    private JComboBox jcboTipoTelefono;
    private Socio socio;
    private JTextField jtxtIdSocio;
    private JPanel jpnlBuscar;
    private JButton jbtnBuscar;
    private JLabel lblIdSocio;
    private JButton jbtnEliminarTelefono;
    private JButton jbtnAgregarTelefono;
    private JLabel jlblLongitud;
    private JLabel jlblMasa;
    private Vector<Membresia> membresias;
    
    /**
    * Constructor privado para la clase ActualizarSocio.
    */
    private  ActualizarSocio() throws Exception{
        setLayout(null);
        jdpFondo = new JDesktopPane();
        jdpFondo.setLocation(10, 0);
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
        jlblMembresia = new JLabel("Membresia");
        jpnlTelefono = new JPanel();
        radSexo = new ButtonGroup();
        jpnlDireccion = new JPanel();
        jlblProvincia = new JLabel("Provincia");
        jlblCiudad = new JLabel("Ciudad");
        jlblDistrito = new JLabel("Municipio");
        jlblCalle = new JLabel("Calle");
        jlblSector = new JLabel("Sector");
        jlblNumeroCasa = new JLabel("Numero");
        jbtnActualizar = new JButton("Registrar");
        jtxtNumeroCasa = new jTextNum();
        jcboMembresia = new JComboBox();
        jpnlMembresia = new JPanel();
        radSexo = new ButtonGroup();
        jbtnActualizar = new JButton("Actualizar");
        jtblTelefonos = new JTable();
        jscrollPane = new JScrollPane(jtblTelefonos);
        jtxtMunicipio = new jText();
        jtxtSector = new jText();
        jtxtCalle = new JTextField();
        String [] tipos = {"Celular", "Residencia", "Oficina",
                           "Fax", "Otros"
        };
        jcboTipoTelefono = new JComboBox(tipos);
        jbtnEliminarTelefono = new JButton("Eliminar Tel.");
        jbtnAgregarTelefono = new JButton("Agregar Tel.");
        jpnlBuscar = new JPanel();
        jbtnBuscar = new JButton("Buscar");
        jtxtIdSocio = new JTextField();
        lblIdSocio = new JLabel("Id Socio");
        
        crearComponentes();
        addToPanel();
        addDefaultValues();
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
    private void addActionListeners(){
        
        jbtnBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(jtxtIdSocio.getText().length() > 0){
                        int idSocio = Integer.valueOf(jtxtIdSocio.getText());

                        eraseValues();
                        socio = Registro.buscarSocio(idSocio);
                        setValues();
                    }
                    else{
                        JOptionPane.showMessageDialog(instance, "Busqueda invalida");
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, ex.getMessage());
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
                    JOptionPane.showMessageDialog(
                            instance,
                            "no puede agregar mas telefonos"
                    );
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
        
        jbtnActualizar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(checkInputValues()){
                        obtainValues();
                        Registro.actualizarSocio(socio);
                        JOptionPane.showMessageDialog(instance,
                                "datos actualizados correctamente"
                        );
                    }
                    else{
                        JOptionPane.showMessageDialog(instance,
                                "datos incorrectos, revise su informacion"
                        );
                    }
                }catch(Exception ex){
                    if(!(ex instanceof Exception)){
                        JOptionPane.showMessageDialog(instance,
                                "datos incorrectos, revise su informacion");
                    }else{
                        JOptionPane.showMessageDialog(instance,ex.getMessage());
                    } 
                }
            }
        });
    }
       
    /**
    * Asigna valores por defecto a los radioButtons de tipo de Sexo 
    * y hace que los componentes texField numero de casa y cedula no sean
    * editables. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void addDefaultValues(){
        jradMasculino.setSelected(true);
        jtxtNumeroCasa.setEditable(true);
        jtxtCedula.setEditable(false);
               
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
        
        jpnlSocio.setBounds(40, 152, 330, 150);
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

        jlblAltura.setBounds(162, 115, 90,30);

        jtxtAltura.setBounds(202, 120, 50, 20);

        jpnlSexo.setBounds(210, 10, 100, 80);
        jpnlSexo.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Sexo"));
        jpnlSexo.setBackground(Color.WHITE);
        jpnlSexo.setLayout(null);

        jradMasculino.setBounds(10, 20, 85, 20);
        jradMasculino.setBackground(Color.WHITE);

        jradFemenino.setBounds(10, 45, 85, 20);
        jradFemenino.setBackground(Color.WHITE);
        
        radSexo.add(jradMasculino);
        radSexo.add(jradFemenino);

        jpnlTelefono.setBounds(40, 310, 330, 150);
        jpnlTelefono.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Telefono"));
        jpnlTelefono.setBackground(Color.WHITE);
        jpnlTelefono.setLayout(null);
        
        dtblmTelefonos = new DefaultTableModel(
             new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            }, new String [] {"Id", "Numero", "Tipo"}
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
        
        jscrollPane.setBounds(20, 20, 290, 71);

        jpnlMembresia.setBounds(400, 40, 300, 130);
        jpnlMembresia.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Tipo de membresia"));
        jpnlMembresia.setBackground(Color.WHITE);
        jpnlMembresia.setLayout(null);

        jlblMembresia.setBounds(50, 60, 80, 20);

        jcboMembresia.setBounds(120, 60, 120, 20);
        jcboMembresia.addItem("<Seleccione>");
        membresias = Registro.buscarMembresias();
        for(Membresia membresia: membresias)
            jcboMembresia.addItem(membresia.getNombre());

        jpnlDireccion.setBounds(400, 180, 300, 200);
        jpnlDireccion.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Direccion"));
        jpnlDireccion.setBackground(Color.WHITE);
        jpnlDireccion.setLayout(null);

        jlblDistrito.setBounds(20, 20, 80, 20);
        
        jlblSector.setBounds(20, 65, 80, 20);
        
        jlblCalle.setBounds(20, 110, 80, 20);
        
        jlblNumeroCasa.setBounds(20, 155, 80, 20);
        
        jtxtMunicipio.setBounds(131, 20, 120, 20);
        
        jtxtSector.setBounds(131, 65, 120, 20);
        
        jtxtCalle.setBounds(131, 110, 120, 20);
        
        jtxtNumeroCasa.setBounds(130, 155, 120, 20);
        
        jbtnActualizar.setBounds(570, 420, 130, 40);
        jbtnActualizar.setIcon(new ImageIcon(getClass().getResource("/imagenes/update.png")));
        
        jtxtIdSocio.setBounds(186, 20, 120, 20);
        jbtnBuscar.setBounds(176, 51, 130, 40);
        jbtnBuscar.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));
        lblIdSocio.setBounds(37, 23, 92, 14);
        
        jpnlBuscar.setLayout(null);
        jpnlBuscar.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Actualizar"));
        jpnlBuscar.setBackground(Color.WHITE);
        jpnlBuscar.setBounds(40, 40, 330, 105);
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
        
        jlblMasa = new JLabel("Libras");
        jlblMasa.setBounds(110, 123, 46, 14);
        jpnlSocio.add(jlblMasa);
        
        jlblLongitud = new JLabel("Pies");
        jlblLongitud.setBounds(262, 123, 46, 14);
        jpnlSocio.add(jlblLongitud);
        jdpFondo.add(jpnlTelefono);
        jpnlTelefono.add(jscrollPane, BorderLayout.CENTER);
        
        jbtnEliminarTelefono.setBounds(20, 99, 130, 40);
        jpnlTelefono.add(jbtnEliminarTelefono);
        
        jbtnAgregarTelefono.setBounds(170, 99, 130, 40);
        jpnlTelefono.add(jbtnAgregarTelefono);
        
        jdpFondo.add(jpnlMembresia);
        jpnlMembresia.add(jlblMembresia);
        jpnlMembresia.add(jcboMembresia);  
        jdpFondo.add(jpnlDireccion);
        jpnlDireccion.add(jtxtMunicipio);
        jpnlDireccion.add(jlblDistrito);
        jpnlDireccion.add(jtxtSector);
        jpnlDireccion.add(jlblSector);
        jpnlDireccion.add(jtxtCalle);
        jpnlDireccion.add(jlblCalle);
        jpnlDireccion.add(jlblNumeroCasa);
        jpnlDireccion.add(jtxtNumeroCasa);
        jdpFondo.add(jbtnActualizar);
        jdpFondo.add(jpnlBuscar);
        jpnlBuscar.add(jbtnBuscar);
        jpnlBuscar.add(jtxtIdSocio);
        jpnlBuscar.add(lblIdSocio);
    }
      
    /**
    * Inserta los valores obtenidos de un Socio provenientes de la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void setValues() throws ParseException{

        jtxtCedula.setText(socio.getCedula());
        jtxtNombre.setText(socio.getNombre());
        jtxtApellido.setText(socio.getApellido());
        jdcFechaNacimiento.setDate(socio.getFechaNacimiento());

        dtblmTelefonos.setRowCount(0);
        for(Telefono obj: socio.getTelefonos())
            dtblmTelefonos.addRow(obj.toVector());
        
        jcboMembresia.setSelectedIndex(socio.getMembresia().getIdMembresia());
        jtxtMunicipio.setText(socio.getDireccion().getMunicipio());
        jtxtSector.setText(socio.getDireccion().getSector());
        jtxtCalle.setText(socio.getDireccion().getCalle());        
        jtxtNumeroCasa.setText(String.valueOf(socio.getDireccion().getNumero()));
        jtxtPeso.setText(String.valueOf(socio.getPeso()));
        jtxtAltura.setText(String.valueOf(socio.getEstatura()));
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
        socio.setCedula(jtxtCedula.getText());
        socio.setNombre(jtxtNombre.getText());
        socio.setApellido(jtxtApellido.getText());
        
        if(jradMasculino.isSelected())
            socio.setSexo('M');
        else
            socio.setSexo('F');
        
        socio.setFechaNacimiento(jdcFechaNacimiento.getDate());
        socio.setTelefonos(new Vector<Telefono>());
        
        for(Vector data: (Vector<Vector>)dtblmTelefonos.getDataVector()){
            if( data.get(0) != null){
                socio.getTelefonos().add(
                    new Telefono(Integer.valueOf((String)data.get(0)),
                        (String)data.get(1),
                        (String)data.get(2)
                    )            
                );
            }
            else{
                socio.getTelefonos().add(
                    new Telefono( (String)data.get(1),
                        (String)data.get(2)
                    )            
                );
            }
        }
        
        socio.setMembresia(membresias.get(jcboMembresia.getSelectedIndex()-1));
        Direccion dir = new Direccion(socio.getDireccion().getIdDireccion(),
                jtxtMunicipio.getText(),
                jtxtSector.getText(),
                jtxtCalle.getText(),
                Integer.valueOf(jtxtNumeroCasa.getText())
        );                               
        socio.setDireccion(dir);
        socio.setPeso(jtxtPeso.getDouble());
        socio.setEstatura(jtxtAltura.getDouble());
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
        jdcFechaNacimiento.setDate(null);
        dtblmTelefonos.setRowCount(0);
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
        
        return true;
    }
        
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Actualizar Socio y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static ActualizarSocio getInstance() throws Exception{
         if(instance == null){
            instance = new ActualizarSocio();           
        }
        return instance;
    }
}
