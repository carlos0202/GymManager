/*
 * Responsable:
 * Saul Hernandez Sanchez
 */

package ViewLayer.ServiciosAdministrativos;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        setLayout(null);
        lpdFondo = new JDesktopPane();
        jlblTitulo = new JLabel("Gym Manager");
        jlblSubTitulo = new JLabel("Servicios Administrativos");
        jlblFoto = new JLabel();
        jbtnLogout = new JButton("Logout");
        
        crearComponentes();
        addActionListeners();
        addToPanel();
        setSize(715, 540);
        
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
                LoginServiciosAdministrativos.getInstance().setVisible(true);
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
        lpdFondo.setSize(715, 540);
	
        jlblTitulo.setBounds(250, 100, 500, 200);
        jlblTitulo.setFont(new Font("Sylfaen", Font.ITALIC, 36));
        
        jlblSubTitulo.setBounds(250, 140, 430, 200);
        jlblSubTitulo.setFont(new Font("Sylfaen", Font.ITALIC, 26));
        
        jlblFoto.setBounds(250, 270, 290, 199);
        jlblFoto.setIcon(new ImageIcon(getClass().getResource("/imagenes/GYM.jpg")));   
        
        jbtnLogout.setBounds(550, 430, 110, 40);
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
     private void addToPanel(){
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
     public static PaginaPrincipal getInstance(){
        
        if(instance == null){
            instance = new PaginaPrincipal();           
        }
        
        return instance;
    }
    
    
}
