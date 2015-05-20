/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */

package ViewLayer.RecursosHumanos;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PaginaPrincipal extends JPanel{
    private static PaginaPrincipal instance;
    private JDesktopPane jdpFondo;
    private JLabel jpnlTitulo;
    private JLabel jpnlSubTitulo;
    private JLabel jlblFoto;
    private JButton jbtnLogout;
    
    /**
    * Constructor privado para la clase PaginaPrincipal.
    */
    private PaginaPrincipal(){
        setLayout(null);
        setSize(715, 540);
        
        jdpFondo = new JDesktopPane();
        jpnlTitulo = new JLabel("Gym Manager");
        jpnlSubTitulo = new JLabel("Departamento de Recursos Humanos");
        jlblFoto = new JLabel();
        jbtnLogout = new JButton("Logout");
        
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
    private void crearComponentes(){
        jdpFondo.setSize(715, 540);
	
        jpnlTitulo.setBounds(250, 100, 300, 200);
        jpnlTitulo.setFont(new Font("Sylfaen", Font.ITALIC, 36));
        
        jpnlSubTitulo.setBounds(250, 140, 430, 200);
        jpnlSubTitulo.setFont(new Font("Sylfaen", Font.ITALIC, 26));
        
        jlblFoto.setBounds(250, 270, 290, 199);
        jlblFoto.setIcon(new ImageIcon(getClass().getResource("/imagenes/Ambro1-300x199.jpg")));   
        
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
        add(jdpFondo);
        jdpFondo.add(jpnlTitulo);
        jdpFondo.add(jpnlSubTitulo);
        jdpFondo.add(jlblFoto);
        jdpFondo.add(jbtnLogout);
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
               
                LoginRRHH.getInstance().setVisible(true);
                ((JFrame)instance.getTopLevelAncestor()).dispose();
            }
        
        
        });
    
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
