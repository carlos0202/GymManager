/*
 * Responsable:
 * Saul Hernandez Sanchez
 */

package ViewLayer.ServiciosAdministrativos;

import DomainModelLayer.Controllers.ServiciosAdministrativos;
import DomainModelLayer.Entidades.Clase;
import DomainModelLayer.Entidades.Instructor;
import DomainModelLayer.Entidades.Salon;
import Utilidades.jTextNum;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

public class CrearSeccion extends JPanel {
    private static CrearSeccion instance;
    private JPanel jpnlDatosSeccion;
    private JPanel jpnlDatosInstructor;
    private JTable jtblInstructor;
    private DefaultTableModel dtblInstructor;
    private JScrollPane scrollPane;
    private JButton jbtnGuardar;
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
    private JButton jbtnBuscar;
    private JLabel jlblDia;
    private JComboBox jcboDia;
    private JLabel jlblNombre;
    private JTextField jtxtNombre;
    private JLabel jlblCapacidad;
    private jTextNum jtxtCapacidad;
    private JLabel jlblFechaI;
    private JLabel jlblFechaf;
    private JDateChooser jdtcFechaI;
    private JDateChooser jdtcFechaF;
                 
    /**
    * Constructor privado para la clase CrearSeccion.
    */   
    public CrearSeccion() throws Exception {
        setLayout(null);
        setSize(715, 540);
        setBackground(Color.WHITE);

        jpnlDatosSeccion = new JPanel();
        jpnlDatosInstructor = new JPanel();
        jtblInstructor = new JTable();
        scrollPane = new JScrollPane(jtblInstructor);
        jlblHoraInicio = new JLabel("Hora Inicio");
        jlblHoraFinal = new JLabel("Hora Final");
        jlblSalon = new JLabel("Salon");
        jtxtIdInstructor = new jTextNum();
        jlblIdinstructor = new JLabel("Id Instructor");
        jbtnGuardar = new JButton("Guardar");
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
        jbtnBuscar = new JButton("Buscar");
        salones = new Vector<Salon>();
        jlblNombre = new JLabel("Nombre");
        jtxtNombre = new JTextField();
        jlblCapacidad = new JLabel("Capacidad");
        jtxtCapacidad = new jTextNum();
        jlblFechaI = new JLabel("Fecha Inicio");
        jlblFechaf = new JLabel("Fecha Fin");
        jdtcFechaI = new JDateChooser();
        jdtcFechaF = new JDateChooser();
      
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
        jpnlDatosSeccion.setLayout(null);
        jpnlDatosSeccion.setBackground(Color.WHITE);
        jpnlDatosSeccion.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos Seccion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        jpnlDatosSeccion.setBounds(31, 41, 650, 180);
		
        jpnlDatosInstructor.setLayout(null);
        jpnlDatosInstructor.setBackground(Color.WHITE);
        jpnlDatosInstructor.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Instructor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        jpnlDatosInstructor.setBounds(31, 231, 650, 228);
		
        dtblInstructor = new DefaultTableModel(
             new Object [][] {
                {null, null, null, null,null}
            }, new String [] {"Id", "Nombre", "Apellido", "Especialidad"}
        );
        jtblInstructor.setModel(dtblInstructor);
        
        scrollPane.setBounds(10, 69, 633, 136);

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
                
        jcboHoraF.setBounds(306, 32, 120, 20);
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
        jcboSalon.setBounds(306, 88, 120, 20);
        jcboSalon.addItem("<Seleccione>");
        
        for(Salon salon: salones){
            jcboSalon.addItem(salon.getNombre());
        }

        jtxtIdInstructor.setBounds(120, 27, 120, 20);

        jlblIdinstructor.setBounds(21, 30, 72, 14);

        jbtnGuardar.setBounds(551, 470, 130, 40);
        jbtnGuardar.setIcon(new ImageIcon(getClass().getResource("/imagenes/base-de-datos-de-guardar.png")));

        jlblClase.setBounds(22, 88, 46, 20);
        
        clases = ServiciosAdministrativos.buscarClases();
        jcboClase.setBounds(89, 88, 120, 20);
        jcboClase.addItem("<Seleccione>");
        
        for(Clase clase: clases){
            jcboClase.addItem(clase.getNombre());
        }
        
        jlblDia.setBounds(458, 32, 46, 20);
        jcboDia.setBounds(501, 32, 120, 20);
        
        jbtnBuscar.setBounds(290, 18, 130, 40);
        jbtnBuscar.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));
        
        jlblNombre.setBounds(22, 144, 46, 20);
        jtxtNombre.setBounds(89, 144, 120, 20);
        
        jlblCapacidad.setBounds(236, 147, 100, 20);
        jtxtCapacidad.setBounds(306, 144, 120, 20);
        
        jlblFechaI.setBounds(458, 88, 76, 20);
        jlblFechaf.setBounds(458, 144, 76, 20);
        
        jdtcFechaI.setBounds(531, 88, 90, 20);
        jdtcFechaF.setBounds(531, 144, 90, 20);
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
        add(jbtnGuardar);
        jpnlDatosInstructor.add(scrollPane);
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
        jpnlDatosSeccion.add(jlblFechaI);
        jpnlDatosSeccion.add(jdtcFechaI);
        jpnlDatosSeccion.add(jdtcFechaF);
        jpnlDatosSeccion.add(jlblFechaf);
        jpnlDatosInstructor.add(jtxtIdInstructor);
        jpnlDatosInstructor.add(jlblIdinstructor);
        jpnlDatosInstructor.add(jbtnBuscar);       
    }
    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la Crear Seccion y si no
    * existe ninguna la crea y luego la retorna
    *
    */	
    public static CrearSeccion getInstance() throws Exception{
        if( instance == null){
            instance = new CrearSeccion();
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
                    int idInstructor = Integer.valueOf(jtxtIdInstructor.getText());
                    instAsignado = ServiciosAdministrativos.buscarInstructor(idInstructor);
                    insertToTable();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, "Datos incorrectos.");
                }               
            }
        });
        
        jbtnGuardar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(checkInputValues()){
                        Salon salon = salones.get(jcboSalon.getSelectedIndex()-1);
                        Clase clase = clases.get(jcboClase.getSelectedIndex()-1);
                        int dia = jcboDia.getSelectedIndex();
                        int horaI = jcboHoraI.getSelectedIndex()+5;
                        int horaF = jcboHoraF.getSelectedIndex()+5;
                        String nombre = jtxtNombre.getText();
                        int capacidad = jtxtCapacidad.getInteger();
                        Date fechaI = jdtcFechaI.getDate();
                        Date fechaF = jdtcFechaF.getDate();
                        
                        ServiciosAdministrativos.crearSeccion(nombre, capacidad, horaI, horaF, dia, clase, salon, instAsignado, fechaI, fechaF);
                        
                        JOptionPane.showMessageDialog(instance, "Seccion registrada correctamente");
                    }
                }catch(Exception ex){
                    if(!(ex instanceof Exception)){
                        JOptionPane.showMessageDialog(instance, "Fallo en registrar seccion");
                    }else{
                        JOptionPane.showMessageDialog(instance, ex.getLocalizedMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });
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
        
        if(jdtcFechaI.getDate().before(new Date()))
            throw new Exception("La fecha de inicio para la seccion es invalido");
        
        if(jdtcFechaI.getDate().after(jdtcFechaF.getDate()))
            throw new Exception("La fecha inicio no debe ser mayor que fecha final");
        
        return true;
    }
        
    /**
    * Setea los nuevos valores de la clase Instructor a la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */ 
    private void insertToTable(){
        dtblInstructor.setRowCount(0);
        Vector<String> row = new Vector();
        
        row.add(
            String.valueOf(instAsignado.getIdInstructor())
        );
        row.add(instAsignado.getNombre());
        row.add(instAsignado.getApellido());
        row.add(instAsignado.getEspecialidad());
        
        dtblInstructor.addRow(row);
    }
}

