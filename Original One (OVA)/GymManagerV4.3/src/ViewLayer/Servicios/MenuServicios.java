/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */


package ViewLayer.Servicios;

import DomainModelLayer.Entidades.PersAdministrativo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;



public class MenuServicios extends JFrame {
    private static MenuServicios instance;
    private JDesktopPane jdpFondo;
    private JXTaskPaneContainer jtpcContenedor;
    private JXTaskPane jtpPanel;
    private JXTaskPane jtpPanel2;
    private JPanel jpnlPanelPrincilal;
    private PersAdministrativo usuario;
    private String image_path;
    
    /**
    * Constructor privado para la clase MenuServicios.
    */
    private  MenuServicios(){
        super("Gym Manager Servicios a Socios");
        setBackground(Color.WHITE);
	getContentPane().setLayout(null);
        setResizable(false);
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pack();
	setSize(942,592);
	setLocationRelativeTo(null);
        image_path = "jar:" + getClass().getProtectionDomain().getCodeSource().
                        getLocation().toString() + "!/imagenes/";
        
        jdpFondo = new JDesktopPane();
        jdpFondo.setBackground(Color.WHITE);
        jtpcContenedor = new JXTaskPaneContainer();
        jtpPanel = new JXTaskPane();
        jtpPanel2 = new JXTaskPane();
        jpnlPanelPrincilal = new JPanel();
        jpnlPanelPrincilal.setBackground(Color.WHITE);
        jtpcContenedor.setBackground(Color.LIGHT_GRAY);
        
        paginaInicio();
        crearComponentes();
        addToPanel();
        accionComponentes();
        
    }
    
    /**
    * Despliega la clase Pagina Principal como vista por defecto
    * al momento de ejecutar la aplicacion
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros 
    */
    private void paginaInicio(){
        jpnlPanelPrincilal.setLayout(null);
        jpnlPanelPrincilal.removeAll();
        jpnlPanelPrincilal.add(PaginaPrincipal.getInstance(),BorderLayout.CENTER);
        jpnlPanelPrincilal.validate();
        repaint();
    }
    
    /**
    * Agrega los manejadores de eventos a los componentes visuales. 
    * Aqui se especifican las acciones a tomaar cuando se detecta
    * que un evento ha ocurrido en un componente visual
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void accionComponentes(){
        try{
            this.jtpPanel.add(new AbstractAction() {
                {
                    putValue(Action.NAME, "Registrar Asistencia");
                    putValue(Action.SHORT_DESCRIPTION, "Registra la asistencia diaria de un socio");
                    putValue(Action.SMALL_ICON, new ImageIcon(new URL(image_path +"final-asistencia.png")));
                }
                @Override
                public void actionPerformed(ActionEvent e){
                    jpnlPanelPrincilal.setLayout(null);
                    jpnlPanelPrincilal.removeAll();
                    jpnlPanelPrincilal.add(RegistrarAsistencia.getInstance(),BorderLayout.CENTER);
                    jpnlPanelPrincilal.validate();
                    repaint();
                }
            });
        
            this.jtpPanel.add(new AbstractAction() {
                {
                    putValue(Action.NAME, "Incribir Socio a Clase");
                    putValue(Action.SHORT_DESCRIPTION, "Incribe a un Socio en una de las Clases disponibles en el gimnasio");
                    putValue(Action.SMALL_ICON, new ImageIcon(new URL(image_path +"add.png")));
                }
                @Override
                public void actionPerformed(ActionEvent e){
                    jpnlPanelPrincilal.setLayout(null);
                    jpnlPanelPrincilal.removeAll();
                    jpnlPanelPrincilal.add(InscribirSocio.getInstance(),BorderLayout.CENTER);
                    jpnlPanelPrincilal.validate();
                    repaint();
                }
            });
        
            this.jtpPanel.add(new AbstractAction() {
                {
                    putValue(Action.NAME, "Retirar Socio de Clase");
                    putValue(Action.SHORT_DESCRIPTION, "Retira a un Socio de una de sus Clases inscritas");
                    putValue(Action.SMALL_ICON, new ImageIcon(new URL(image_path +"del.png")));
                }
                @Override
                public void actionPerformed(ActionEvent e){
                    jpnlPanelPrincilal.setLayout(null);
                    jpnlPanelPrincilal.removeAll();
                    jpnlPanelPrincilal.add(RetirarSocio.getInstance(),BorderLayout.CENTER);
                    jpnlPanelPrincilal.validate();
                    repaint();
            
                }
            });
        
            this.jtpPanel2.add(new AbstractAction() {
                {
                    putValue(Action.NAME, "Pagina Principal");
                    putValue(Action.SHORT_DESCRIPTION, "Accesa a pagina principal de Servicios");
                    putValue(Action.SMALL_ICON, new ImageIcon(new URL(image_path +"menu.png")));
                }
                @Override
                public void actionPerformed(ActionEvent e){
                    jpnlPanelPrincilal.setLayout(null);
                    jpnlPanelPrincilal.removeAll();
                    jpnlPanelPrincilal.add(PaginaPrincipal.getInstance(),BorderLayout.CENTER);
                    jpnlPanelPrincilal.validate();
                    repaint();
                }
            });
        }catch(Exception ex){
            JOptionPane.showMessageDialog(instance, ex.getMessage());
        }
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
	jdpFondo.setSize(942,592);
	
	jtpcContenedor.setSize(230,592);
	
	jtpPanel.setTitle("Opciones");
	jtpPanel2.setTitle("Volver");
        
        jpnlPanelPrincilal.setBounds(240, 10 ,685, 540);
        
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
        getContentPane().add(jdpFondo);
        jtpcContenedor.add(jtpPanel);
	jtpcContenedor.add(jtpPanel2);
        jdpFondo.add(jtpcContenedor, BorderLayout.CENTER);
        jdpFondo.add(jpnlPanelPrincilal);
    }
    
     /*Envia los datos del empleado que se logueo al menu principal
     * <p>
     * Este metodo no tiene ningun valor de retorno
     * @param recibe un objeto de la clase PersAdministrativo
     */
    public void setLoggedUser(PersAdministrativo usuario){
        this.usuario = usuario;
    }
    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Menu de servicios y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static MenuServicios getInstance(){
        if( instance == null){
            instance = new MenuServicios();
        }
        
        return instance;
    }       
              
}
