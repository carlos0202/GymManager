/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */

package ViewLayer.RecursosHumanos;

import DomainModelLayer.Entidades.PersAdministrativo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

public class MenuRRHH extends JFrame{
    private static MenuRRHH instance;
    private JDesktopPane jdpFondo;
    private JXTaskPaneContainer jtpccontenedor;
    private JXTaskPane jtkpPaneles;
    private JXTaskPane jtkpPaneles2;
    private JPanel jpnlPanelPrincilal;
    private PersAdministrativo usuario;
    private String image_path;
	
    /**
    * Constructor privado para la clase MenuRRHH.
    */
    private  MenuRRHH(){
	super("Gym Manager Recursos Humanos");
	setLayout(null);
        setResizable(false);
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pack();
	setSize(942,592);
	setLocationRelativeTo(null);
        image_path = "jar:" + getClass().getProtectionDomain().getCodeSource().
                        getLocation().toString() + "!/imagenes/";
        
        jpnlPanelPrincilal = new JPanel();
        jdpFondo = new JDesktopPane();
        jtpccontenedor = new JXTaskPaneContainer();
        jtkpPaneles = new JXTaskPane();
        jtkpPaneles2 = new JXTaskPane();
        
        paginaInicio();
        crearComponentes();
	addListeners();
        addToPanel();

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
    * Crea y posiciona los elementos contenidos dentro de la ventana. 
    * Tambien aqui se especifican las decoraciones usadas en los
    * componentes visuales presentados.
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void crearComponentes(){
        jdpFondo.setSize(942,592);
        
        jtpccontenedor.setBackground(Color.LIGHT_GRAY);
        jtpccontenedor.setSize(200,592);
	
	jtkpPaneles.setTitle("Mantenimiento");	
        
        jtkpPaneles2.setTitle("");
        
        jpnlPanelPrincilal.setBounds(210,10 ,715, 540);
    }
    
    /**
    * Agrega los manejadores de eventos a los componentes visuales. 
    * Aqui se especifican las acciones a tomaar cuando se detecta
    * que un evento ha ocurrido en un componente visual
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void addListeners(){
        try{
            this.jtkpPaneles.add(new AbstractAction() {
                {
                    putValue(Action.NAME, "Registrar Empleado");
                    putValue(Action.SHORT_DESCRIPTION, "Accesa a Registrar Empleado");
                    putValue(Action.SMALL_ICON, new ImageIcon(new URL(image_path + "delete.png")));
                }
                @Override
                 public void actionPerformed(ActionEvent e){
                    jpnlPanelPrincilal.setLayout(null);
                    jpnlPanelPrincilal.removeAll();
                    try {
                        jpnlPanelPrincilal.add(RegistrarEmpleado.getInstance(),BorderLayout.CENTER);
                    } catch (ParseException ex) {
                        Logger.getLogger(MenuRRHH.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jpnlPanelPrincilal.validate();
                    repaint();
                }
            });
            this.jtkpPaneles.add(new AbstractAction() {
                {
                    putValue(Action.NAME, "Consultar Empleado");
                    putValue(Action.SHORT_DESCRIPTION, "Accesa a Consultar Empleado");
                    putValue(Action.SMALL_ICON, new ImageIcon(new URL(image_path + "search.png")));
                }
                @Override
                public void actionPerformed(ActionEvent e){
                    jpnlPanelPrincilal.setLayout(null);
                    jpnlPanelPrincilal.removeAll();
                    jpnlPanelPrincilal.add(VerEmpleado.getInstance(),BorderLayout.CENTER);
                    jpnlPanelPrincilal.validate();
                    repaint();
                }
            });
        
            this.jtkpPaneles.add(new AbstractAction() {
                {
                    putValue(Action.NAME, "Actualizar Empleado");
                    putValue(Action.SHORT_DESCRIPTION, "Accesa a Actualizar Empleado");
                    putValue(Action.SMALL_ICON, new ImageIcon(new URL(image_path + "update.png")));
                }
                @Override
                public void actionPerformed(ActionEvent e){
                    jpnlPanelPrincilal.setLayout(null);
                    jpnlPanelPrincilal.removeAll();
                    try {
                        jpnlPanelPrincilal.add(ActualizarEmpleado.getInstance(),BorderLayout.CENTER);
                    } catch (Exception ex) {
                        Logger.getLogger(MenuRRHH.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jpnlPanelPrincilal.validate();
                    repaint();
                }
            });
        
            this.jtkpPaneles.add(new AbstractAction() {
                {
                    putValue(Action.NAME, "Eliminar Empleado");
                    putValue(Action.SHORT_DESCRIPTION, "Accesa a Eliminar Empleado");
                    putValue(Action.SMALL_ICON, new ImageIcon(new URL(image_path + "delete.png")));
                }
                @Override
                public void actionPerformed(ActionEvent e){
                    jpnlPanelPrincilal.setLayout(null);
                    jpnlPanelPrincilal.removeAll();
                    jpnlPanelPrincilal.add(CancelarEmpleado.getInstance(),BorderLayout.CENTER);
                    jpnlPanelPrincilal.validate();
                    repaint(); 
                }
            });
               
            this.jtkpPaneles2.add(new AbstractAction() {
                {
                    putValue(Action.NAME, "Pagina Principal");
                    putValue(Action.SHORT_DESCRIPTION, "Accesa a pagina principal de recursos humanos");
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
    * Agrega todos los componentes visuales al GUI y sus subcomponentes. 
    * Tambien aqui se agregan los BorderLayout de los componentes agregados
    * de ser necesario
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void addToPanel(){
        add(jdpFondo);
	jtpccontenedor.add(jtkpPaneles);
        jtpccontenedor.add(jtkpPaneles2);
	jdpFondo.add(jtpccontenedor, BorderLayout.CENTER);
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
    * @return la instancia actual de la clase Menu de recursos humanos y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static MenuRRHH getInstance(){
         if(instance == null){
            instance = new MenuRRHH();      
        }
        return instance;
    }
	
    
}