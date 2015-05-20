/*
 * Responsable:
 * Saul Hernandez Sanchez
 */

package ViewLayer.ServiciosAdministrativos;


import DomainModelLayer.Controllers.ServiciosAdministrativos;
import DomainModelLayer.Entidades.Clase;
import Utilidades.jTextNum;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ActualizarClase extends JPanel{
    private static ActualizarClase instance;
    private JPanel jpnlDatos;
    private JLabel jlblNombre;
    private JTextField jtxtNombreClase;
    private JLabel jlblCosto;
    private jTextNum jtxtCosto;
    private JPanel jpnlDescripcion;
    private JTextArea jtxtaDescripcion; 
    private JButton jbtnActualizar;
    private JLabel jlblFoto;
    private JLabel jlblIdClase;
    private jTextNum jtxtIdClase;
    private JButton jbtnBuscar;
    private Clase clase;
       
    /**
    * Constructor privado para la clase ActualizarClase.
    */
    private ActualizarClase() {
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
	jbtnActualizar = new JButton("Actualizar");
	jlblFoto = new JLabel();
        jlblIdClase = new JLabel("Id Clase");
        jtxtIdClase = new jTextNum();
        jbtnBuscar = new JButton("Buscar");
		
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
        jlblIdClase.setBounds(56,30, 120, 20);

        jtxtIdClase.setBounds(110, 30, 120, 20);

        jbtnBuscar.setBounds(100, 62,130 , 40);
        jbtnBuscar.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));

        jpnlDatos.setBackground(Color.WHITE);
        jpnlDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
        jpnlDatos.setBounds(56, 128, 258, 130);

        jlblNombre.setBounds(24, 29, 50, 14);

        jtxtNombreClase.setBounds(91, 26, 120, 20);

        jlblCosto.setBounds(24, 81, 50, 14);

        jtxtCosto.setBounds(91, 78, 120, 20);

        jpnlDescripcion.setBackground(Color.WHITE);
        jpnlDescripcion.setLayout(null);
        jpnlDescripcion.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Descripcion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        jpnlDescripcion.setBounds(56, 269, 258, 211);

        jtxtaDescripcion.setBounds(10, 18, 238, 182);

        jbtnActualizar.setBounds(538, 440, 130, 40);
        jbtnActualizar.setIcon(new ImageIcon(getClass().getResource("/imagenes/update.png")));

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
        add(jlblIdClase);
        add(jpnlDatos);
        add(jtxtIdClase);
        add(jbtnBuscar);
        jpnlDatos.add(jlblNombre);
        jpnlDatos.add(jtxtNombreClase);
        jpnlDatos.add(jlblCosto);
        jpnlDatos.add(jtxtCosto);
        add(jpnlDescripcion);
        jpnlDescripcion.add(jtxtaDescripcion);
        add(jbtnActualizar);
        add(jlblFoto);
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
                    int idClase = jtxtIdClase.getInteger();
                    clase = ServiciosAdministrativos.buscarClase(idClase);
                    insertarDatos();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, "Datos incorectos...");
                }
            }
        });

        jbtnActualizar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    obtenerDatos();
                    ServiciosAdministrativos.actualizarClase(clase);
                    JOptionPane.showMessageDialog(instance, "Clase actualizada correctamente");
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(instance, "Clase no pudo ser actualizada");
                }
            }
        });
    }
            
    /**
    * Setea los nuevos valores de la clase Clase a la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */ 
    private void insertarDatos(){
        jtxtNombreClase.setText(clase.getNombre());
        jtxtCosto.setNum(clase.getPrecio());
        jtxtaDescripcion.setText(clase.getDescripcion());
    }
    
    /**
    * Obtiene valores de la clase Clase desde la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */ 
    private void obtenerDatos() throws Exception{
        clase.setNombre(jtxtNombreClase.getText());
        clase.setPrecio(jtxtCosto.getDouble());
        clase.setDescripcion(jtxtaDescripcion.getText());
    }
    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Actualizar Clase y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static ActualizarClase getInstance(){
        if( instance == null){
            instance = new ActualizarClase();
        }

        return instance;
    }

    
}
