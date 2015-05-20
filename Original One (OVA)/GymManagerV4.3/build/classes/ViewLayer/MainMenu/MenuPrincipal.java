/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */


package ViewLayer.MainMenu;

import ViewLayer.Pago.LoginPago;
import ViewLayer.RecursosHumanos.LoginRRHH;
import ViewLayer.Registro.LoginRegistro;
import ViewLayer.Servicios.LoginServicios;
import ViewLayer.ServiciosAdministrativos.LoginServiciosAdministrativos;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MenuPrincipal extends JFrame{
    private static MenuPrincipal instance;
    private JMenuItem jmntRecursosHumanos;
    private JMenuItem jmntRegistro;
    private JMenuItem jmntServicios;
    private JMenuItem jmntServiciosAdministrativos;
    private JMenuItem jmntPago;
    private JMenu mnAyuda;
    private JMenu jmnArchivo;
    private JMenuBar jmnbBarra;
    private JMenuItem jmntAcercaDe;
    private JMenuItem jmntSalir;
    private JLabel jlblTitulo;
    private JLabel jlblFoto;
    private JLabel jlblDerchos;
    private JLabel jlblDerechos2;

    /**
    * Constructor privado para la clase MenuPrincipal.
    */
    private MenuPrincipal(){
        super("Menu Principal");

        this.getContentPane().setLayout(null);
        this.setSize(705,680);
        this.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        jmnbBarra = new JMenuBar();
        jmnArchivo = new JMenu("Archivo");
        jmntRecursosHumanos = new JMenuItem("Ir a Recursos Humanos");    
        jmntRegistro = new JMenuItem("Ir a Registro de Socios");
        jmntServicios = new JMenuItem("Ir a Servicios");
        jmntServiciosAdministrativos = new JMenuItem("Ir a Servicios Administrativos");
        jmntPago = new JMenuItem("Ir a Pagos");
        mnAyuda = new JMenu("Ayuda");
        jmntAcercaDe = new JMenuItem("Acerca De GymManager");
        jmntSalir = new JMenuItem("Salir");
        jlblTitulo = new JLabel("Gym Manager System");
        jlblFoto = new JLabel();
        jlblDerchos = new JLabel("Gym Manager LTD. 2012 Copyrigth © ");
        jlblDerechos2 = new JLabel("Todos los derechos reservados");
        
        crearComponentes();
        addToPanel();
        addActionListeners();

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
    	getContentPane().add(jmnbBarra);
        getContentPane().add(jlblTitulo);
        getContentPane().add(jlblFoto);
        getContentPane().add(jlblDerchos);
        getContentPane().add(jlblDerechos2);
    	
    	jmnbBarra.add(jmnArchivo);
        jmnbBarra.add(mnAyuda);
        
    	jmnArchivo.add(jmntRecursosHumanos);
    	jmnArchivo.add(jmntRegistro);
    	jmnArchivo.add(jmntServicios);   	
    	jmnArchivo.add(jmntServiciosAdministrativos);
    	jmnArchivo.add(jmntPago);
    	jmnArchivo.add(jmntSalir);
    	
    	mnAyuda.add(jmntAcercaDe);
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
    	jmnbBarra.setBounds(0, 0, 689, 21);
        
        jlblTitulo.setBounds(50,100,400,40);
        jlblTitulo.setFont(new Font("Sylfaen", Font.ITALIC, 36));
        
        jlblFoto.setBounds(100, 200, 400, 429);
        jlblFoto.setIcon(new ImageIcon(getClass().getResource("/imagenes/gym-clip-art-7.jpg")));  
        
        jlblDerchos.setBounds(500, 550, 400, 100);
        jlblDerchos.setFont(new Font("Sylfaen", Font.ITALIC, 12));
        
        jlblDerechos2.setBounds(500, 570, 400, 100);
        jlblDerechos2.setFont(new Font("Sylfaen", Font.ITALIC, 12));
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
    	jmntRecursosHumanos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LoginRRHH.getInstance().setVisible(true);
                instance.setVisible(false);
            }
        });
        
        jmntRegistro.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LoginRegistro.getInstance().setVisible(true);
                instance.setVisible(false);
            }
        });
        
        jmntServicios.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LoginServicios.getInstance().setVisible(true);
                instance.setVisible(false);
            }
        });
        
        jmntServiciosAdministrativos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LoginServiciosAdministrativos.getInstance().setVisible(true);
                instance.setVisible(false);
            }
        });
        
        jmntPago.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LoginPago.getInstance().setVisible(true);
                    instance.setVisible(false);
                } catch (ParseException ex) {
                    Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        jmntAcercaDe.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(instance,
                        "GymManager, 2011-2012 todos los derechos reservados."+"\n"+
                        "Creado por Andres Frias, Carlos Gonzalez, Luis Gonzalez "+
                        "y Saul Hernandez",
                        "Acerca de GymManager",
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon(getClass().getResource("/imagenes/Gym1.jpg"))
                        
                );
            }
        });
        
        jmntSalir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(instance, "Hasta la Proxima","Adios",JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });
    }
    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Menu Principal  y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static MenuPrincipal getInstance(){
        if( instance == null){
            instance = new MenuPrincipal();
        }
        return instance;
    }
}
