/*
 * Responsable:
 * Saul Hernandez Sanchez
 */

package ViewLayer.ServiciosAdministrativos;

import DomainModelLayer.Controllers.ServiciosAdministrativos;
import DomainModelLayer.Entidades.Clase;
import DomainModelLayer.Entidades.Instructor;
import DomainModelLayer.Entidades.Salon;
import DomainModelLayer.Entidades.Seccion;
import Utilidades.jTextNum;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class ActualizarSeccion extends JPanel {
    private static ActualizarSeccion instance;
    private JPanel jpnlDatosSeccion;
    private JPanel jpnlDatosInstructor;
    private JPanel jpnlBuscarSeccion;
    private JTable jtblInstructor;
    private DefaultTableModel dtblInstructor;
    private JScrollPane scrollPane;
    private JButton jbtnActualizar;
    private jTextNum jtxtIdInstructor;
    private JLabel jlblIdinstructor;
    private JLabel jlblHoraInicio;
    private JLabel jlblHoraFinal;
    private JLabel jlblSalon;
    private JComboBox jcboHoraI;
    private JComboBox jcboHoraF;
    private JComboBox jcboSalon;
    private JLabel jlblClase;
    private JComboBox jcboClase;
    private Vector<Clase> clases;
    private Vector<Salon> salones;
    private Instructor instAsignado;
    private Seccion seccion;
    private JButton jbtnBuscarInstructor;
    private JLabel jlblDia;
    private JComboBox jcboDia;
    private JLabel jlblNombre;
    private JTextField jtxtNombre;
    private JLabel jlblCapacidad;
    private jTextNum jtxtCapacidad;
    private jTextNum jtxtIdSeccion;
    private JButton jbtnBuscarSeccion;
    private JLabel lblIdSeccion;
            
    /**
    * Constructor privado para la clase ActualizarSeccion.
    */       
    public ActualizarSeccion() throws Exception {
        setLayout(null);
        setSize(715, 540);
        setBackground(Color.WHITE);

        jpnlBuscarSeccion = new JPanel();
        jpnlDatosSeccion = new JPanel();
        jpnlDatosInstructor = new JPanel();
        jtblInstructor = new JTable();
        scrollPane = new JScrollPane(jtblInstructor);
        jlblHoraInicio = new JLabel("Hora Inicio");
        jlblHoraFinal = new JLabel("Hora Final");
        jlblSalon = new JLabel("Salon");
        jtxtIdInstructor = new jTextNum();
        jlblIdinstructor = new JLabel("Id Instructor");
        jbtnActualizar = new JButton("Actualizar");
        jcboHoraI = new JComboBox();
        jcboHoraF = new JComboBox();
        jcboSalon = new JComboBox();
        jlblClase = new JLabel("Clase");
        jcboClase = new JComboBox();
        clases = new Vector<Clase>();
        jlblDia = new JLabel("Dia");
        String [] dias = {
            "<Seleccione>", "Lunes", "Martes", "Miercoles",
            "Jueves", "Viernes", "Sabado", "Domingo" 
        };
        jcboDia = new JComboBox(dias);
        jbtnBuscarInstructor = new JButton("Buscar");
        salones = new Vector<Salon>();
        jlblNombre = new JLabel("Nombre");
        jtxtNombre = new JTextField();
        jlblCapacidad = new JLabel("Capacidad");
        jtxtCapacidad = new jTextNum();
        lblIdSeccion = new JLabel("Id Seccion");
        jtxtIdSeccion = new jTextNum();
        jbtnBuscarSeccion = new JButton("Buscar");
        
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
    	jpnlBuscarSeccion.setLayout(null);
    	jpnlBuscarSeccion.setBackground(Color.WHITE);
    	jpnlBuscarSeccion.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Buscar Seccion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	jpnlBuscarSeccion.setBounds(28, 21, 653, 106);

    	lblIdSeccion.setBounds(21, 47, 100, 20);

    	jtxtIdSeccion.setBounds(90, 47, 120, 20);

    	jbtnBuscarSeccion.setBounds(252, 35, 130, 40);
    	jbtnBuscarSeccion.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));
    	
    	
    	
        jpnlDatosSeccion.setLayout(null);
        jpnlDatosSeccion.setBackground(Color.WHITE);
        jpnlDatosSeccion.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos Seccion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        jpnlDatosSeccion.setBounds(28, 137, 653, 180);
		
        jpnlDatosInstructor.setLayout(null);
        jpnlDatosInstructor.setBackground(Color.WHITE);
        jpnlDatosInstructor.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Instructor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        jpnlDatosInstructor.setBounds(28, 328, 653, 131);
		
        dtblInstructor = new DefaultTableModel(
             new Object [][] {
                {null, null, null, null,null}
            }, new String [] {"Id", "Nombre", "Apellido", "Especialidad"}
        );
        jtblInstructor.setModel(dtblInstructor);
        
        scrollPane.setBounds(10, 69, 633, 48);

        jlblHoraInicio.setBounds(22, 32, 68, 20);

        jlblHoraFinal.setBounds(236, 32, 68, 20);

        jcboHoraI.setBounds(89, 32,120, 20);
        jcboHoraI.addItem("<Seleccione>");
        jcboHoraI.addItem("06:00");
        jcboHoraI.addItem("07:00");
        jcboHoraI.addItem("08:00");
        jcboHoraI.addItem("09:00");
        jcboHoraI.addItem("10:00");
        jcboHoraI.addItem("11:00");
        jcboHoraI.addItem("12:00");
        jcboHoraI.addItem("13:00");
        jcboHoraI.addItem("14:00");
        jcboHoraI.addItem("15:00");
        jcboHoraI.addItem("16:00");
        jcboHoraI.addItem("17:00");
        jcboHoraI.addItem("18:00");
        jcboHoraI.addItem("19:00");
        jcboHoraI.addItem("20:00");
                
        jcboHoraF.setBounds(314, 32, 120, 20);
        jcboHoraF.addItem("<Seleccione>");
        jcboHoraF.addItem("07:00");
        jcboHoraF.addItem("08:00");
        jcboHoraF.addItem("09:00");
        jcboHoraF.addItem("10:00");
        jcboHoraF.addItem("11:00");
        jcboHoraF.addItem("12:00");
        jcboHoraF.addItem("13:00");
        jcboHoraF.addItem("14:00");
        jcboHoraF.addItem("15:00");
        jcboHoraF.addItem("16:00");
        jcboHoraF.addItem("17:00");
        jcboHoraF.addItem("18:00");
        jcboHoraF.addItem("19:00");
        jcboHoraF.addItem("20:00");
        jcboHoraF.addItem("21:00");

        jlblSalon.setBounds(236, 88, 46, 20);

        salones = ServiciosAdministrativos.buscarSalones();
        jcboSalon.setBounds(314, 88, 120, 20);
        jcboSalon.addItem("<Seleccione>");
        
        for(Salon salon: salones){
            jcboSalon.addItem(salon.getNombre());
        }

        jtxtIdInstructor.setBounds(120, 27, 120, 20);

        jlblIdinstructor.setBounds(21, 30, 72, 14);

        jbtnActualizar.setBounds(551, 470, 130, 40);
        jbtnActualizar.setIcon(new ImageIcon(getClass().getResource("/imagenes/update.png")));

        jlblClase.setBounds(22, 88, 46, 20);
        
        clases = ServiciosAdministrativos.buscarClases();
        jcboClase.setBounds(89, 88, 120, 20);
        jcboClase.addItem("<Seleccione>");
        
        for(Clase clase: clases){
            jcboClase.addItem(clase.getNombre());
        }
        
        jlblDia.setBounds(461, 32, 46, 20);
        jcboDia.setBounds(503, 32, 120, 20);
        
        jbtnBuscarInstructor.setBounds(290, 18, 130, 40);
        jbtnBuscarInstructor.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));
        
        jlblNombre.setBounds(22, 144, 46, 20);
        jtxtNombre.setBounds(89, 144, 120, 20);
        
        jlblCapacidad.setBounds(236, 147, 100, 20);
        jtxtCapacidad.setBounds(314, 144, 120, 20);
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
        add(jpnlDatosSeccion);
        add(jpnlDatosInstructor);
        add(jpnlBuscarSeccion);
        
        jpnlDatosSeccion.add(jlblHoraInicio);
        jpnlDatosSeccion.add(jlblHoraFinal);
        jpnlDatosSeccion.add(jcboHoraI);
        jpnlDatosSeccion.add(jcboHoraF);
        jpnlDatosSeccion.add(jlblSalon);
        jpnlDatosSeccion.add(jcboSalon);
        jpnlDatosSeccion.add(jlblClase);
        jpnlDatosSeccion.add(jcboClase);
        jpnlDatosSeccion.add(jlblDia);
        jpnlDatosSeccion.add(jcboDia);
        jpnlDatosSeccion.add(jlblNombre);
        jpnlDatosSeccion.add(jtxtNombre);
        jpnlDatosSeccion.add(jlblCapacidad);
        jpnlDatosSeccion.add(jtxtCapacidad);
        
        jpnlBuscarSeccion.add(lblIdSeccion);
    	jpnlBuscarSeccion.add(jtxtIdSeccion);
    	jpnlBuscarSeccion.add(jbtnBuscarSeccion);

    	jpnlDatosInstructor.add(scrollPane);
        jpnlDatosInstructor.add(jtxtIdInstructor);
        jpnlDatosInstructor.add(jlblIdinstructor);
        jpnlDatosInstructor.add(jbtnBuscarInstructor);
        add(jbtnActualizar);
		
    }
	    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Actualizar Seccion y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static ActualizarSeccion getInstance() throws Exception{
        if( instance == null){
            instance = new ActualizarSeccion();
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
        jbtnBuscarInstructor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int idInstructor = Integer.valueOf(jtxtIdInstructor.getText());
                    instAsignado = ServiciosAdministrativos.buscarInstructor(idInstructor);
                    insertToTable();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, "Datos incorrectos.");
                }               
            }
        });
        
        jbtnActualizar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(checkInputValues()){                  
                        obtainValues();   
                        ServiciosAdministrativos.actualizarSeccion(seccion);
                        JOptionPane.showMessageDialog(instance, "Seccion registrada correctamente");
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, "Seccion no pudo ser registrada");
                    //JOptionPane.showMessageDialog(instance, ex.getLocalizedMessage());
                }
            }
        });
        
        jbtnBuscarSeccion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int idSeccion = jtxtIdSeccion.getInteger();
                    seccion = ServiciosAdministrativos.buscarSeccion(idSeccion);
                    insertValues();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, ex.getLocalizedMessage());
                }
            }

        });
    }
        
    /**
    * Setea los nuevos valores de la clase Seccion a la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */ 
    private void insertValues() {
        jcboHoraI.setSelectedIndex(seccion.getHoraInicio()-5);
        jcboHoraF.setSelectedIndex(seccion.getHoraFin()-5);
        jcboClase.setSelectedItem(seccion.getClaseImpartida().getNombre());
        jcboDia.setSelectedIndex(seccion.getDia());
        jcboSalon.setSelectedItem(seccion.getSalon().getNombre());
        jtxtCapacidad.setNum(seccion.getCapacidad());
        jtxtNombre.setText(seccion.getNombre());

        instAsignado = seccion.getInstrctAsignado();
        jtxtIdInstructor.setNum(instAsignado.getIdInstructor());
        insertToTable();
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
        if(jcboHoraI.getSelectedIndex() > jcboHoraF.getSelectedIndex())
            throw new Exception("Hora inicio no puede ser mayor que Hora fin");
        
        if(jtxtCapacidad.getInteger() < 5 || jtxtCapacidad.getInteger() > 100)
            throw new Exception("Capacidad debe ser >= 5 y <= 100");
        
        if(instAsignado == null)
            throw new Exception("Debe asignar un instructor");
        
        if(jcboClase.getSelectedIndex() < 1 || jcboDia.getSelectedIndex() < 1 ||
           jcboHoraF.getSelectedIndex() < 1 || jcboHoraI.getSelectedIndex() < 1 ||
           jcboSalon.getSelectedIndex() < 1  ){
            throw new Exception("La informacion acerca de la seccion no esta completa..");
        }
        
        if(jtxtNombre.getText().equals("") || jtxtNombre.getText().equals(null))
            throw new Exception("El nombre de la seccion no puede estar vacio");
        
        return true;
    }
        
    /**
    * Obtiene valores de la clase Clase desde la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void obtainValues(){
        seccion.setSalon(salones.get(jcboSalon.getSelectedIndex()-1));
        seccion.setClaseImpartida(clases.get(jcboClase.getSelectedIndex()-1));
        seccion.setDia(jcboDia.getSelectedIndex());
        seccion.setHoraInicio(jcboHoraI.getSelectedIndex()+5);
        seccion.setHoraFin(jcboHoraF.getSelectedIndex()+5);
        seccion.setNombre(jtxtNombre.getText());
        seccion.setCapacidad(jtxtCapacidad.getInteger());
        seccion.setInstrctAsignado(instAsignado);
    }
        
    /**
     * Limpia la tabla en le GUI para despues agregar los datos del instructor
     * que fue seleccionados para la busqueda 
     * <p>
     * Este metodo no tiene ningun valor de retorno y no recibe parametros
     */
    private void insertToTable(){
        dtblInstructor.setRowCount(0);
        Vector<String> row = new Vector<String>();
        
        row.add(
            String.valueOf(instAsignado.getIdInstructor())
        );
        row.add(instAsignado.getNombre());
        row.add(instAsignado.getApellido());
        row.add(instAsignado.getEspecialidad());
        
        dtblInstructor.addRow(row);
    }
}

