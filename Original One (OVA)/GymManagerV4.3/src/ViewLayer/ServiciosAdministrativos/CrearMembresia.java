/*
 * Responsable:
 * Saul Hernandez Sanchez
 */

package ViewLayer.ServiciosAdministrativos;

import DomainModelLayer.Controllers.ServiciosAdministrativos;
import Utilidades.jText;
import Utilidades.jTextNum;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class CrearMembresia extends JPanel {
    private static CrearMembresia instance;
    private jText jtxtNombre;
    private jTextNum jtxtPrecio;
    private JPanel jpnlDatos;
    private JLabel jlblNombre;
    private JLabel lblPrecio;
    private JLabel lblTipo;
    private JPanel jpnlDescripcion;
    private JTextArea JtxtaDescripcion;
    private JComboBox jcboTipoMembresia;
    private JButton jbtnGuardar;
    private JButton jbtnNuevo;
    
    /**
    * Constructor privado para la clase CrearMembresia.
    */ 
    private CrearMembresia() {
        setLayout(null);
        setSize(715, 540);
        setBackground(Color.WHITE);

        jpnlDatos = new JPanel();
        jlblNombre = new JLabel("Nombre");
        lblPrecio = new JLabel("Precio");
        jtxtNombre = new jText();
        jtxtPrecio = new jTextNum();
        jpnlDescripcion = new JPanel();
        JtxtaDescripcion = new JTextArea();
        jcboTipoMembresia = new JComboBox();
        lblTipo = new JLabel("Tipo");
        jbtnGuardar = new JButton("Guardar");
        jbtnNuevo = new JButton("Nuevo");

        CrearComponentes();
        AddToPanel();
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
    private void CrearComponentes(){
        jpnlDatos.setLayout(null);
        jpnlDatos.setBackground(Color.WHITE);
        jpnlDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
        jpnlDatos.setBounds(50, 67, 601, 285);

        jlblNombre.setBounds(24, 23, 50, 14);

        lblPrecio.setBounds(32, 76, 50, 14);

        jtxtNombre.setBounds(83, 20, 109, 20);

        jtxtPrecio.setBounds(83, 73, 109, 20);

        jpnlDescripcion.setLayout(null);
        jpnlDescripcion.setBackground(Color.WHITE);
        jpnlDescripcion.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Descripcion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        jpnlDescripcion.setBounds(292, 23, 299, 236);

        JtxtaDescripcion.setWrapStyleWord(true);
        JtxtaDescripcion.setBounds(20, 22, 258, 192);

        jcboTipoMembresia.setBounds(83, 129, 109, 20);
        jcboTipoMembresia.addItem("<Seleccione>");
        jcboTipoMembresia.addItem("Diario");
        jcboTipoMembresia.addItem("Semanal");
        jcboTipoMembresia.addItem("Quincenal");
        jcboTipoMembresia.addItem("Mensual");
        jcboTipoMembresia.addItem("Anual");

        lblTipo.setBounds(41, 132, 50, 14);

        jbtnGuardar.setBounds(551, 426, 130, 40);
        jbtnGuardar.setIcon(new ImageIcon(getClass().getResource("/imagenes/base-de-datos-de-guardar.png")));

        jbtnNuevo.setBounds(399, 426, 130, 40);
        jbtnNuevo.setIcon(new ImageIcon(getClass().getResource("/imagenes/nuevo-archivo-icono-9028-32.png")));

    }
    
    /**
    * Agrega todos los componentes visuales al GUI y sus subcomponentes. 
    * Tambien aqui se agregan los BorderLayout de los componentes agregados
    * de ser necesario
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void AddToPanel(){
        add(jpnlDatos);
        jpnlDatos.add(jlblNombre);
        jpnlDatos.add(lblPrecio);
        jpnlDatos.add(jtxtNombre);
        jpnlDatos.add(jtxtPrecio);
        jpnlDatos.add(jpnlDescripcion);
        jpnlDescripcion.add(JtxtaDescripcion);
        jpnlDatos.add(jcboTipoMembresia);
        jpnlDatos.add(lblTipo);
        add(jbtnGuardar);
        add(jbtnNuevo);	
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
        jbtnGuardar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String nombre = jtxtNombre.getText();
                    String descripcion = JtxtaDescripcion.getText();
                    String tipo = jcboTipoMembresia.getSelectedItem().toString();
                    double precio = jtxtPrecio.getDouble();
                    
                    ServiciosAdministrativos.crearMembresia(nombre, descripcion, tipo, precio);
                    JOptionPane.showMessageDialog(instance, "Membresia registrada satisfactoriamente");
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, "Membresia no pudo ser registrada");
                }
            }
        });
    }
	    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la Crear Membresia y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static CrearMembresia getInstance(){
        if( instance == null){
            instance = new CrearMembresia();
        }
        
        return instance;
    }
}
