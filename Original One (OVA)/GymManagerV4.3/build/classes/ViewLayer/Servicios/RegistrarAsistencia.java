/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */


package ViewLayer.Servicios;

import DomainModelLayer.Controllers.Servicios;
import DomainModelLayer.Entidades.Socio;
import Utilidades.jText;
import Utilidades.jTextNum;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class RegistrarAsistencia extends JPanel{
    private static RegistrarAsistencia instance;
    private jTextNum jtxtIdSocio;
    private JTextField jtxtBusquedaSocio;
    private jText jtxtNombreSocio;
    private JComboBox jcboBusquedaSocio;
    private JTable jtblSocio;
    private DefaultTableModel dtblSocio;
    private JLabel jlblIdSocio;
    private JLabel jlblNombreSocio;
    private JButton jbtnRegistrar;
    private JScrollPane scrollSocio;
    private JPanel jpnlBusquedaSocio;
    private JPanel jpnlDatosAsistencia;
    private JButton jbtnBuscarSocio;
    private JButton jbtnSeleccionarSocio;
    private JLabel jlblFoto;
    private Vector<Socio> socios;
    private Socio socio;
        
    /**
    * Constructor privado para la clase RegistrarAsistencia.
    */
    private RegistrarAsistencia(){
    	setBackground(Color.WHITE);
        setLayout(null);
        setSize(685, 540);
        
        jtxtIdSocio = new jTextNum();
        jlblIdSocio = new JLabel("ID Socio");
        jbtnRegistrar = new JButton("Registrar");
        jbtnSeleccionarSocio = new JButton("Selec. Socio");
        dtblSocio = new DefaultTableModel();
        jtblSocio = new JTable();
        scrollSocio = new JScrollPane(jtblSocio);
        jpnlBusquedaSocio = new JPanel();
        jpnlBusquedaSocio.setBackground(Color.WHITE);
        jpnlDatosAsistencia = new JPanel();
        jpnlDatosAsistencia.setBackground(Color.WHITE);
        jbtnBuscarSocio = new JButton("Buscar");
        jbtnRegistrar = new JButton("Registrar");
        jcboBusquedaSocio = new JComboBox();
        jtxtBusquedaSocio = new JTextField();
        jlblIdSocio = new JLabel("Id Socio");
        jlblNombreSocio = new JLabel("Nombre");
        jtxtNombreSocio = new jText();
        jlblFoto = new JLabel();
        
        crearComponentes();
        addComponentes();
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
    	jpnlBusquedaSocio.setLayout(null);
    	jpnlBusquedaSocio.setBounds(36, 41, 607, 208);
    	jpnlBusquedaSocio.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Busqueda Socio"));
    	
    	jpnlDatosAsistencia.setLayout(null);
    	jpnlDatosAsistencia.setBounds(343, 260, 300, 120);
    	jpnlDatosAsistencia.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Datos Asistencia"));
    	
        jbtnBuscarSocio.setBounds(444, 20, 130, 40);
        jbtnBuscarSocio.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));
        jbtnRegistrar.setBounds(513, 462, 130, 40);
        jbtnRegistrar.setIcon(new ImageIcon(getClass().getResource("/imagenes/base-de-datos-de-guardar.png")));
        
        jbtnSeleccionarSocio.setBounds(296, 20, 130, 40);
        
        scrollSocio.setBounds(29, 78, 545, 100);
        
        jcboBusquedaSocio.setBounds(29, 20, 84, 20);
        jcboBusquedaSocio.addItem("<Seleccione>");
        jcboBusquedaSocio.addItem("Id");
        jcboBusquedaSocio.addItem("Cedula");
        jcboBusquedaSocio.addItem("Nombre");
        jcboBusquedaSocio.addItem("Apellido");
        jcboBusquedaSocio.addItem("Membresia");
        
        dtblSocio = new DefaultTableModel(
    		new Object[][] {
        		{null, null, null, null},
        		{null, null, null, null},
        		{null, null, null, null},
        		{null, null, null, null},
        		{null, null, null, null},
        	},
        	new String[] {
        		"Id", "Nombre", "Cedula", "Tipo Membresia"
        	}
        );
        jtblSocio.setModel(dtblSocio);
        
        jlblIdSocio.setBounds(29, 20, 80, 20);
        jlblNombreSocio.setBounds(29, 60, 80, 20);
        
        jtxtBusquedaSocio.setBounds(130, 20, 104, 20);
        jtxtBusquedaSocio.setColumns(10);
        
        jtxtNombreSocio.setBounds(125, 60, 104, 20);
        jtxtNombreSocio.setColumns(20);
        jtxtIdSocio.setBounds(125, 20, 104, 20);
        jtxtIdSocio.setColumns(20);
        
        jlblFoto.setBounds(36, 260, 297, 242);
        jlblFoto.setBackground(Color.WHITE);
        jlblFoto.setIcon(new ImageIcon(getClass().getResource("/imagenes/asistencia.png")));
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
    	add(jpnlBusquedaSocio);
    	add(jpnlDatosAsistencia);
    	add(jbtnRegistrar);
    	add(jlblFoto);
    	
    	jpnlBusquedaSocio.add(jbtnBuscarSocio);
    	jpnlBusquedaSocio.add(scrollSocio);
    	jpnlBusquedaSocio.add(jcboBusquedaSocio);
    	jpnlBusquedaSocio.add(jtxtBusquedaSocio);
    	jpnlBusquedaSocio.add(jbtnSeleccionarSocio);
    	
    	jpnlDatosAsistencia.add(jlblIdSocio);
    	jpnlDatosAsistencia.add(jtxtIdSocio);
    	jpnlDatosAsistencia.add(jlblNombreSocio);
    	jpnlDatosAsistencia.add(jtxtNombreSocio);

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
                    JOptionPane.showMessageDialog(instance, "Datos incorrectos...");
                }
            }
        });
        
        jbtnSeleccionarSocio.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtblSocio.getSelectedRow() != -1){
                    socio = socios.get(jtblSocio.getSelectedRow());

                    jtxtIdSocio.setNum(socio.getIdSocio());
                    jtxtNombreSocio.setText(socio.getNombre() + " "+
                            socio.getApellido()
                    );
                }
                else{
                    JOptionPane.showMessageDialog(instance, "no seleccionno ninguna fila");
                }
            }
        });
        
        jbtnRegistrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int idSocio = jtxtIdSocio.getInteger();
                    Servicios.registrarAsistencia(idSocio);
                    JOptionPane.showMessageDialog(instance, "Asistencia registrada.");
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, "Fallo al registrar la asistencia");
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(instance, ex.getMessage());
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
            row.add(soc.getMembresia().getTipo());
            
            dtblSocio.addRow(row);
        }
    }
       
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Registrar Asistencia y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static RegistrarAsistencia getInstance(){
        if( instance == null){
            instance = new RegistrarAsistencia();
        }
        
        return instance;
    }
}
