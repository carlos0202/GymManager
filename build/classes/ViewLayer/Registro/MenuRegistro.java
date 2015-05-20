/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */

package ViewLayer.Registro;

import DomainModelLayer.Entidades.PersAdministrativo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

public class MenuRegistro extends JFrame {
    private static MenuRegistro instance;
    private JDesktopPane jdpFondo;
    private JXTaskPaneContainer jtpcContenedor;
    private JXTaskPane jtpPaneles;
    private JXTaskPane jtpPaneles2; 
    private JPanel jpnlPanelPrincilal;
    private PersAdministrativo usuario;
    private String image_path;
    
    /**
    * Constructor privado para la clase MenuRegistro.
    */
    private  MenuRegistro(){
        super("Gym Manager Registro de Socios");
	setLayout(null);
        setResizable(false);
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pack();
	setSize(942,592);
	setLocationRelativeTo(null);
        image_path = "jar:" + getClass().getProtectionDomain().getCodeSource().
                        getLocation().toString() + "!/imagenes/";
        
        jdpFondo = new JDesktopPane();
        jtpcContenedor = new JXTaskPaneContainer();
        jtpPaneles = new JXTaskPane();
        jtpPaneles2 = new JXTaskPane();
        jpnlPanelPrincilal = new JPanel();
        jtpcContenedor.setBackground(Color.LIGHT_GRAY);
        
        paginaInicio();
        crearComponentes();
        addToPanel();
        acionComponentes();
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
    private void acionComponentes(){
       try{
        this.jtpPaneles.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Registrar Socio");
                putValue(Action.SHORT_DESCRIPTION, "Accesa a Registrar Socio");
                putValue(Action.SMALL_ICON, new ImageIcon(new URL(image_path + "add.png")));
            }
            @Override
            public void actionPerformed(ActionEvent e){
                jpnlPanelPrincilal.setLayout(null);
                jpnlPanelPrincilal.removeAll();
                try {
                    jpnlPanelPrincilal.add(RegistrarSocio.getInstance(),BorderLayout.CENTER);
                } catch (Exception ex) {
                    Logger.getLogger(MenuRegistro.class.getName()).log(Level.SEVERE, null, ex);
                }
                jpnlPanelPrincilal.validate();
                repaint();
            }
        });
        
        this.jtpPaneles.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Actualizar Socio");
                putValue(Action.SHORT_DESCRIPTION, "Accesa a Actualizar Socio");
                putValue(Action.SMALL_ICON, new ImageIcon(new URL(image_path + "update.png")));
            }
            @Override
            public void actionPerformed(ActionEvent e){
                jpnlPanelPrincilal.setLayout(null);
                jpnlPanelPrincilal.removeAll();
                try {
                    jpnlPanelPrincilal.add(ActualizarSocio.getInstance(),BorderLayout.CENTER);
                } catch (Exception ex) {
                    Logger.getLogger(MenuRegistro.class.getName()).log(Level.SEVERE, null, ex);
                }
                jpnlPanelPrincilal.validate();
                repaint();
            }
        });
        
        this.jtpPaneles.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Ver Socio");
                putValue(Action.SHORT_DESCRIPTION, "Visualiza los datos de los Socios");
                putValue(Action.SMALL_ICON, new ImageIcon(new URL(image_path + "search.png")));
            }
            @Override
            public void actionPerformed(ActionEvent e){
                jpnlPanelPrincilal.setLayout(null);
                jpnlPanelPrincilal.removeAll();
                try {
                    jpnlPanelPrincilal.add(VerSocio.getInstance(),BorderLayout.CENTER);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(instance, ex.getMessage());
                }
                jpnlPanelPrincilal.validate();
                repaint();
            }
        });
        
        this.jtpPaneles.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Eliminar Socio");
                putValue(Action.SHORT_DESCRIPTION, "Accesa a Eliminar Socio");
                putValue(Action.SMALL_ICON, new ImageIcon(new URL(image_path + "delete.png")));
            }
            @Override
            public void actionPerformed(ActionEvent e){
                jpnlPanelPrincilal.setLayout(null);
                jpnlPanelPrincilal.removeAll();
                jpnlPanelPrincilal.add(EliminarSocio.getInstance(),BorderLayout.CENTER);
                jpnlPanelPrincilal.validate();
                repaint();
            
            }
        });
        this.jtpPaneles.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Ver Membresia");
                putValue(Action.SHORT_DESCRIPTION, "Accesa a Visualizar las Membresias disponibles");
                putValue(Action.SMALL_ICON, new ImageIcon(new URL(image_path + "search.png")));
            }
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    jpnlPanelPrincilal.setLayout(null);
                    jpnlPanelPrincilal.removeAll();
                    jpnlPanelPrincilal.add(VerMembresia.getInstance(),BorderLayout.CENTER);
                    jpnlPanelPrincilal.validate();
                    repaint();
                } catch (Exception ex) {
                    Logger.getLogger(MenuRegistro.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        });
        
         this.jtpPaneles2.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Pagina Principal");
                putValue(Action.SHORT_DESCRIPTION, "Accesa a pagina principal de registro");
                putValue(Action.SMALL_ICON, new ImageIcon(new URL(image_path + "menu.png")));
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
	
	jtpcContenedor.setSize(200,592);
	
	jtpPaneles.setTitle("Mantenimiento");	
	
        jtpPaneles2.setTitle("");

        jpnlPanelPrincilal.setBounds(210,10 ,715, 540);
       
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
        jtpcContenedor.add(jtpPaneles);
        jtpcContenedor.add(jtpPaneles2);
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
    * @return la instancia actual de la clase Menu de registros y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static MenuRegistro getInstance(){
        
        if(instance == null){
            instance = new MenuRegistro();           
        }
        
        return instance;
    }
          
}
