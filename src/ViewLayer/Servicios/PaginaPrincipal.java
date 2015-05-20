/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */


package ViewLayer.Servicios;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class PaginaPrincipal extends JPanel{
     private static PaginaPrincipal instance;
     private JDesktopPane lpdFondo;
     private JLabel jlblTitulo;
     private JLabel jlblSubTitulo;
     private JLabel jlblFoto;
     private JButton jbtnLogout;    

    /**
    * Constructor privado para la clase PaginaPrincipal.
    */
    private PaginaPrincipal(){
    	setBackground(Color.WHITE);
        setLayout(null);
        setSize(685, 540);
        
        lpdFondo = new JDesktopPane();
        lpdFondo.setBackground(Color.WHITE);
        jlblTitulo = new JLabel("Gym Manager");
        jlblSubTitulo = new JLabel("Departamento de Servicios");
        jlblFoto = new JLabel();
        jlblFoto.setBackground(Color.WHITE);
        jbtnLogout = new JButton("Logout");
        
        crearComponentes();
        addComponentes();
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
        jbtnLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LoginServicios.getInstance().setVisible(true);
                ((JFrame)instance.getTopLevelAncestor()).dispose();                
            }       
        
        });
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
        
        lpdFondo.setSize(685, 540);
	
        jlblTitulo.setBounds(161, 11, 256, 84);
        jlblTitulo.setFont(new Font("Sylfaen", Font.ITALIC, 36));
        
        jlblSubTitulo.setBounds(161, 47, 310, 113);
        jlblSubTitulo.setFont(new Font("Sylfaen", Font.ITALIC, 26));
        
        jlblFoto.setBounds(110, 128, 400, 300);
        jlblFoto.setIcon(new ImageIcon(getClass().getResource("/imagenes/servicios_gimnasio.jpg")));   
        
        jbtnLogout.setBounds(529, 456, 111, 40);
        jbtnLogout.setIcon(new ImageIcon(getClass().getResource("/imagenes/salir-de-gnome-icono-5366-32.png")));
        
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
         add(lpdFondo);
         lpdFondo.add(jlblTitulo);
         lpdFondo.add(jlblSubTitulo);
         lpdFondo.add(jlblFoto);
         lpdFondo.add(jbtnLogout);
     }
     
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Login de Pagina Principal  y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static PaginaPrincipal getInstance() {
        if( instance == null ){
            instance = new PaginaPrincipal();
        }
        return instance;
    }
    
}
