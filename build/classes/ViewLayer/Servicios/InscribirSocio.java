/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */


package ViewLayer.Servicios;

import DomainModelLayer.Controllers.Servicios;
import DomainModelLayer.Entidades.Seccion;
import DomainModelLayer.Entidades.Socio;
import Utilidades.jTextNum;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class InscribirSocio extends JPanel{
    private static InscribirSocio instance;
    private JTextField jtxtBusquedaSocio;
    private JPanel jpnlBuscaSocio;
    private JButton jbtnBuscarSocio;
    private JButton jbtnInscribir;
    private JComboBox jcboBusquedaSocio;
    private JTable jtblDatosSocio;
    private JScrollPane jscrollTabla;
    private DefaultTableModel dtblSocio;
    private DefaultTableModel dtmClase;
    private DefaultTableModel dtmInstructor;
    private JPanel jpnlDatosInscripcion;
    private JLabel jlblIdSocio;
    private jTextNum jtxtIdSocio;
    private JButton jbtnCancelar;
    private ButtonGroup jradBotones;
    private JButton jbtnSeleccionarSocio;
    private JPanel jpnlBuscarSeccion;
    private JComboBox jcboBusquedaSeccion;
    private JTextField jtxtBusquedaClase;
    private JScrollPane jscrollClase;
    private JTable jtblClase;
    private JButton jbtnBuscarSseccion;
    private JButton jbtnSeleccionarClase;
    private JLabel jlblIdClase;
    private jTextNum jtxtIdSeccion;
    private JLabel jlblDia;
    private JTextField jtxtDia;
    private JTextField jtxtHoraInicio;
    private JLabel jlblHoraInicio;
    private JTextField jtxtHoraFin;
    private JLabel jlblHoraFin;
    private Vector<Seccion> secciones;
    private Vector<Socio> socios;
    private Seccion seccion;
    private Socio socio;
    private JComboBox jcboDia;
    private JComboBox jcboDiaSec;
    private static final String [] dias = {
            "<Seleccione>", "Lunes", "Martes", "Miercoles",
            "Jueves", "Viernes", "Sabado", "Domingo" 
    };
        
    /**
    * Constructor privado para la clase InscribirSocio.
    */
    private InscribirSocio(){
    	setBackground(Color.WHITE);
    	setLayout(null);
    	setSize(685, 540);
    	jtxtBusquedaSocio = new JTextField();
    	jbtnInscribir = new JButton("Inscribir");  	
    	jbtnBuscarSocio = new JButton("Buscar");
    	jcboBusquedaSocio = new JComboBox(); 	
    	jpnlBuscaSocio = new JPanel();
    	dtblSocio = new DefaultTableModel();
    	dtmClase = new DefaultTableModel();
    	dtmInstructor = new DefaultTableModel();
       	jpnlDatosInscripcion = new JPanel();
       	jbtnCancelar = new JButton("Cancelar");
    	jradBotones = new ButtonGroup();
    	jtblDatosSocio = new JTable();
    	jscrollTabla = new JScrollPane(jtblDatosSocio);
    	jbtnSeleccionarSocio = new JButton("Selec. Socio");
    	jpnlBuscarSeccion = new JPanel();
    	jcboBusquedaSeccion = new JComboBox();
    	jtxtBusquedaClase = new JTextField();
    	jtblClase = new JTable();
    	jscrollClase = new JScrollPane(jtblClase);
    	jbtnBuscarSseccion = new JButton("Buscar");
    	jbtnSeleccionarClase = new JButton("Selec. Clase");
    	jtxtIdSocio = new jTextNum();
    	jlblIdSocio = new JLabel("Id Socio");
    	jlblIdClase = new JLabel("Id Clase");
    	jtxtIdSeccion = new jTextNum();
        jlblDia = new JLabel("Dia");
        jtxtDia = new JTextField();
        jlblHoraInicio = new JLabel("Hora Inicio");
        jtxtHoraInicio = new JTextField();
        jlblHoraFin = new JLabel("Hora Fin");
        jtxtHoraFin = new JTextField();
        secciones = new Vector<Seccion>();
        
        jcboDia = new JComboBox(dias);
        jcboDiaSec = new JComboBox(dias);
        
    	crearComponentes();
        addComponentes();
        addDefaultProperties();
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
    	
    	jpnlBuscaSocio.setLayout(null);    	
    	jpnlBuscaSocio.setBackground(Color.WHITE);
    	jpnlBuscaSocio.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Buscar Socio"));
    	jpnlBuscaSocio.setBounds(21, 28, 317, 266);
    	
    	jpnlDatosInscripcion.setLayout(null);    	
    	jpnlDatosInscripcion.setBackground(Color.WHITE);
    	jpnlDatosInscripcion.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Datos Inscripcion"));
    	jpnlDatosInscripcion.setBounds(362, 28, 283, 266);

    	jpnlBuscarSeccion.setLayout(null);
    	jpnlBuscarSeccion.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Buscar Clase"));
    	jpnlBuscarSeccion.setBackground(Color.WHITE);
    	jpnlBuscarSeccion.setBounds(21, 305, 624, 164);
    	
    	
    	jcboBusquedaSeccion.setBounds(24, 22, 120, 20);
        jcboBusquedaSeccion.addItem("<Seleccione>");
    	jcboBusquedaSeccion.addItem("Id");
    	jcboBusquedaSeccion.addItem("Nombre");
        jcboBusquedaSeccion.addItem("Salon");
        jcboBusquedaSeccion.addItem("Instructor");
        jcboBusquedaSeccion.addItem("Dia");
    	
    	jtxtBusquedaSocio.setBounds(174, 27, 120, 20);
    	
    	jbtnInscribir.setBounds(515, 480, 130, 40);
    	jbtnInscribir.setIcon(new ImageIcon(getClass().getResource("/imagenes/base-de-datos-de-guardar.png")));

       	jbtnCancelar.setBounds(362, 480, 130, 40);
    	jbtnCancelar.setIcon(new ImageIcon(getClass().getResource("/imagenes/cancelar-icono-4961-32.png")));
       	
    	jbtnBuscarSocio.setBounds(22, 84, 130, 40);
    	jbtnBuscarSocio.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));
    	
    	jbtnBuscarSseccion.setBounds(332, 19, 130, 40);
    	jbtnBuscarSseccion.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));
    	
    	jbtnSeleccionarClase.setBounds(472, 19, 130, 40);
    	
    	jcboBusquedaSocio.setBounds(22, 27, 120, 20);
    	jcboBusquedaSocio.addItem("<Seleccione>");
        jcboBusquedaSocio.addItem("Id");
        jcboBusquedaSocio.addItem("Cedula");
        jcboBusquedaSocio.addItem("Nombre");
        jcboBusquedaSocio.addItem("Apellido");
        jcboBusquedaSocio.addItem("Membresia");
    	
    	dtblSocio = new DefaultTableModel(
            new Object[][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String[] {
                "Id", "Nombre", "Cedula"
            }
        ){
            @Override
            public boolean isCellEditable (int fila, int columna) {
                if (columna >= 0)
                    return false;
                return true;
            }
        };
    	jtblDatosSocio.setModel(dtblSocio);
    	
    	dtmClase = new DefaultTableModel(
            new Object[][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String[] {
                "Nombre", "Hora Inicio", "Hora Fin",
                "Dia", "Instructor", "Salon"
            }
        ){
            @Override
            public boolean isCellEditable (int fila, int columna) {
                if (columna >= 0)
                    return false;
                return true;
            }
        };
    	jtblClase.setModel(dtmClase);
        
        jtblClase.getColumnModel().getColumn(3).setCellEditor(
            new DefaultCellEditor(jcboDiaSec)
        );
    	
    	dtmInstructor = new DefaultTableModel(
            new Object[][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String[] {
                "Id Instructor", "Nombre", "Especialidad"
            }
        );
    	
    	jtxtBusquedaClase.setBounds(172, 22, 120, 20);
        jcboDia.setBounds(172, 22, 120, 20);
    	
    	jbtnSeleccionarSocio.setBounds(164, 84, 130, 40);
    	
    	jscrollTabla.setBounds(10, 166, 300, 69);
    	jscrollClase.setBounds(24, 70, 572, 68);
    	
    	jtxtIdSocio.setBounds(119, 21, 120, 20);    	
    	jtxtIdSocio.setColumns(10);
    	
    	jlblIdSocio.setBounds(30, 21, 46, 20);
        jlblIdClase.setBounds(30, 71, 46, 20);
        
        jtxtIdSeccion.setBounds(119, 71, 120, 20);
    	jtxtIdSeccion.setColumns(10);
    	jtxtHoraInicio.setBounds(119, 171, 120, 20);
    	jtxtHoraInicio.setColumns(10);   	
    	jlblHoraFin.setBounds(30, 221, 46, 20);
    	jtxtHoraFin.setBounds(119, 221, 120, 20);
    	jtxtHoraFin.setColumns(10);
        jlblDia.setBounds(30, 121, 46, 20);
        jlblHoraInicio.setBounds(30, 171, 70, 20);
        jtxtDia.setBounds(119, 121, 120, 20);
    	jtxtDia.setColumns(10);
    }
    
    /**
    * Agrega todos los componentes visuales al GUI y sus subcomponentes. 
    * Tambien aqui se agregan los BorderLayout de los componentes agregados
    * de ser necesario
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void addComponentes(){
    	
    	add(jbtnInscribir);
       	add(jpnlDatosInscripcion);
    	add(jpnlBuscaSocio);       	
    	add(jbtnCancelar);
    	add(jpnlBuscarSeccion);
    	
    	jpnlBuscaSocio.add(jtxtBusquedaSocio);
    	jpnlBuscaSocio.add(jbtnBuscarSocio);
    	jpnlBuscaSocio.add(jcboBusquedaSocio);
    	jpnlBuscaSocio.add(jbtnSeleccionarSocio);
    	jpnlBuscaSocio.add(jscrollTabla);

    	jpnlDatosInscripcion.add(jtxtIdSocio);
    	jpnlDatosInscripcion.add(jlblIdSocio);
    	jpnlDatosInscripcion.add(jlblIdClase);
    	jpnlDatosInscripcion.add(jtxtIdSeccion);
    	jpnlDatosInscripcion.add(jtxtDia);
    	jpnlDatosInscripcion.add(jlblDia);
    	jpnlDatosInscripcion.add(jlblHoraInicio); 
        jpnlDatosInscripcion.add(jtxtHoraInicio);
    	jpnlDatosInscripcion.add(jtxtHoraFin);
        jpnlDatosInscripcion.add(jlblHoraFin);

    	jpnlBuscarSeccion.add(jcboBusquedaSeccion);
        jpnlBuscarSeccion.add(jcboDia);
    	jpnlBuscarSeccion.add(jtxtBusquedaClase);
    	jpnlBuscarSeccion.add(jscrollClase);
    	jpnlBuscarSeccion.add(jbtnBuscarSseccion);
    	jpnlBuscarSeccion.add(jbtnSeleccionarClase);
    	
    }
        
    /**
    * Hace que algunos componentes no sean modificables al inicio de la
    * ejecucion hasta que se cumpla una tarea especifica. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void addDefaultProperties(){
        jcboDia.setVisible(false);
        jtxtIdSocio.setEditable(false);
        jtxtIdSeccion.setEditable(false);
        jtxtDia.setEditable(false);
        jtxtHoraInicio.setEditable(false);
        jtxtHoraFin.setEditable(false);
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
    	
    	jbtnCancelar.addActionListener( new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                clearValues();
            }
    		
    	});
        
        jcboBusquedaSeccion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(jcboBusquedaSeccion.getSelectedIndex() == 5){
                    jtxtBusquedaClase.setVisible(false);
                    jcboDia.setVisible(true);
                }
                else{
                    jtxtBusquedaClase.setVisible(true);
                    jcboDia.setVisible(false);
                }
            }
        });
        
        jbtnBuscarSseccion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    secciones.clear();
                    String filtro = "";
                    int campoBusqueda = jcboBusquedaSeccion.getSelectedIndex();

                    if(jcboDia.isVisible()){
                        filtro = String.valueOf(jcboDia.getSelectedIndex());
                    }
                    else{
                        filtro = jtxtBusquedaClase.getText(); 
                    }

                    secciones = Servicios.buscarSecciones(campoBusqueda, filtro);
                    insertToTblSeccion();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(instance, "Datos incorrectos...");
                }
            }
        });
        
        jbtnBuscarSocio.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(jtxtBusquedaSocio.getText().length() > 0 &&
                       jcboBusquedaSocio.getSelectedIndex() > 0 ){
                        String filtroBusqueda = jtxtBusquedaSocio.getText();
                        int campoBusqueda = jcboBusquedaSocio.getSelectedIndex();
                        socios = Servicios.buscarSocio(filtroBusqueda, campoBusqueda);
                        insertToTblSocio();
                    }
                    else{
                        JOptionPane.showMessageDialog(instance, "Datos incorrectos..");
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, "Datos incorrectos..");
                }
            }
        });
        
        jbtnSeleccionarClase.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtblClase.getSelectedRow() != -1){
                    seccion = secciones.get(jtblClase.getSelectedRow());
                    jtxtIdSeccion.setNum(seccion.getIdSeccion());
                    jtxtHoraInicio.setText(String.valueOf(seccion.getHoraInicio()+":00"));
                    jtxtHoraFin.setText(String.valueOf(seccion.getHoraFin()+":00"));
                    jtxtDia.setText(dias[seccion.getDia()]);
                }
                else{
                    JOptionPane.showMessageDialog(instance, "no seleccionno ninguna fila");
                }
            }
        });
        
        jbtnSeleccionarSocio.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtblDatosSocio.getSelectedRow() != -1){
                    socio = socios.get(jtblDatosSocio.getSelectedRow());
                    if( socio.getStatus() == 'V'){
                        JOptionPane.showMessageDialog(instance, "Socio con deudas pendientes");
                        clearValues();
                    }
                    jtxtIdSocio.setNum(socio.getIdSocio());
                }
                else{
                    JOptionPane.showMessageDialog(instance, "no seleccionno ninguna fila");
                }
            }
        });
        
        jbtnInscribir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                if(jtxtIdSocio.getInteger() > 0 &&
                   jtxtIdSeccion.getInteger() > 0 ){
                    
                    Servicios.inscribirSeccion(socio, seccion);
                    JOptionPane.showMessageDialog(instance, "Socio inscrito.");
                    clearValues();
                }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, "Fallo en la operacion.");
                    ex.printStackTrace();
                }
            }
        });
        
    }
         
    /**
     * Limpia la tabla en le GUI para despues agregar los datos del socio
     * que fue seleccionados para la busqueda 
     * <p>
     * Este metodo no tiene ningun valor de retorno y no recibe parametros
     */
    private void insertToTblSocio(){
        dtblSocio.setRowCount(0);
        
        for(Socio soc: socios){
            Vector<Object> row = new Vector();
            row.add(soc.getIdSocio());
            row.add(soc.getNombre() + " "+ soc.getApellido());
            row.add(soc.getCedula());
            
            dtblSocio.addRow(row);
        }
    }

     /**
     * Limpia la tabla en le GUI para despues agregar los datos de la seccion
     * que fue seleccionados para la busqueda 
     * <p>
     * Este metodo no tiene ningun valor de retorno y no recibe parametros
     */
    private void insertToTblSeccion(){
        dtmClase.setRowCount(0);

        for(Seccion seccion: secciones){
            Vector<Object> row = new Vector<Object>();
            row.add(seccion.getClaseImpartida().getNombre());
            row.add(seccion.getHoraInicio()+":00");
            row.add(seccion.getHoraFin()+":00");
            row.add(dias[seccion.getDia()]);
            row.add(
                seccion.getInstrctAsignado().getNombre() + " "+
                seccion.getInstrctAsignado().getApellido()
            );
            row.add(seccion.getSalon().getNombre());

            dtmClase.addRow(row);
        }
    }
        
    /**
    * Elimina todos los valores de los componentes visuales de entrada de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void clearValues(){
        dtblSocio.setRowCount(0);
        dtmClase.setRowCount(0);
        jtxtIdSocio.setText(null);
        jtxtIdSeccion.setText(null);
        jtxtDia.setText(null);
        jtxtHoraInicio.setText(null);
        jtxtHoraFin.setText(null);
        jtxtBusquedaClase.setText(null);
        jtxtBusquedaSocio.setText(null);
    }
    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Inscribir Socio y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static InscribirSocio getInstance(){
        if( instance == null ){
            instance = new InscribirSocio();
        }
        return instance;
    }
}
