/*
 * Responsable:
 * Saul Hernandez Sanchez
 */

package ViewLayer.ServiciosAdministrativos;

import DomainModelLayer.Controllers.ServiciosAdministrativos;
import Utilidades.jTextNum;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class CrearClase extends JPanel {
    private static CrearClase instance;
    private JPanel jpnlDatos;
    private JLabel jlblNombre;
    private JTextField jtxtNombreClase;
    private JLabel jlblCosto;
    private jTextNum jtxtCosto;
    private JPanel jpnlDescripcion;
    private JTextArea jtxtaDescripcion; 
    private JButton jbtnNueva;
    private JButton jbtnGuardar;
    private JLabel jlblFoto;
    
    /**
    * Constructor privado para la clase CrearClase.
    */  
    private CrearClase() {
        setLayout(null);
        setSize(715, 540);
        setBackground(Color.WHITE);

        jpnlDatos = new JPanel();
        jpnlDatos.setLayout(null);
        jlblNombre = new JLabel("Nombre");
        jtxtNombreClase = new JTextField();
        jlblCosto = new JLabel("Costo");
        jtxtCosto = new jTextNum();
        jpnlDescripcion = new JPanel();
        jtxtaDescripcion = new JTextArea();
        jbtnNueva = new JButton("Nueva");
        jbtnGuardar = new JButton("Guardar");
        jlblFoto = new JLabel();

        CrearComponetes();
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
    private void CrearComponetes(){
        jpnlDatos.setBackground(Color.WHITE);
        jpnlDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
        jpnlDatos.setBounds(56, 89, 258, 130);

        jlblNombre.setBounds(24, 29, 50, 14);

        jtxtNombreClase.setBounds(91, 26, 120, 20);

        jlblCosto.setBounds(24, 81, 50, 14);

        jtxtCosto.setBounds(91, 78, 120, 20);

        jpnlDescripcion.setBackground(Color.WHITE);
        jpnlDescripcion.setLayout(null);
        jpnlDescripcion.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Descripcion", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        jpnlDescripcion.setBounds(56, 230, 258, 249);

        jtxtaDescripcion.setBounds(10, 21, 238, 217);

        jbtnNueva.setBounds(372, 440, 130, 40);
        jbtnNueva.setIcon(new ImageIcon(getClass().getResource("/imagenes/nuevo-archivo-icono-9028-32.png")));

        jbtnGuardar.setBounds(538, 440, 130, 40);
        jbtnGuardar.setIcon(new ImageIcon(getClass().getResource("/imagenes/base-de-datos-de-guardar.png")));

        jlblFoto.setBounds(372, 89, 290, 249);
        jlblFoto.setIcon(new ImageIcon(getClass().getResource("/imagenes/GYM.jpg"))); 
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
        add(jpnlDatos);
        jpnlDatos.add(jlblNombre);
        jpnlDatos.add(jtxtNombreClase);
        jpnlDatos.add(jlblCosto);
        jpnlDatos.add(jtxtCosto);
        add(jpnlDescripcion);
        jpnlDescripcion.add(jtxtaDescripcion);
        add(jbtnGuardar);
        add(jlblFoto);
    }
    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la Crear Clase y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static CrearClase getInstance(){
        if( instance == null){
            instance = new CrearClase();
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
        jbtnGuardar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                String nombre = jtxtNombreClase.getText();
                Double costo = jtxtCosto.getDouble();
                String descripcion = jtxtaDescripcion.getText();

                ServiciosAdministrativos.crearClase(nombre, descripcion, costo);
                JOptionPane.showMessageDialog(instance, "Clase registrada");
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, "Fallo al registrar clase");
                }
            }
        });
    }
}
