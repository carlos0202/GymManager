/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */

package ViewLayer.ServiciosAdministrativos;

import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.*;


public class Reporte extends JPanel{
    private static Reporte instance; 
    private JLabel jlblFoto;
    private JButton jbtnVerReporte;
    
    private Connection conn;
    private static String dbUser;
    private static String dbPass;
    private static String url = "jdbc:mysql://localhost:3306/gymmanagerV5";
    private static final String driver = "com.mysql.jdbc.Driver";
    private Properties dbProps;
        
    /**
    * Constructor privado para la clase Reporte.
    */   
    private Reporte() {
        setLayout(null);
	setSize(715, 540);
	setBackground(Color.WHITE);
        
        
        
        try{
            dbProps = new Properties();
            String path = System.getProperty("user.home")+"\\GymManager\\DB.properties";
            dbProps.load(new FileInputStream(path));
            dbUser = dbProps.getProperty("DBuser");
            dbPass = dbProps.getProperty("DBpass");    
            
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,dbUser,dbPass);
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }

        jbtnVerReporte = new JButton("Reporte");
        jlblFoto = new JLabel();
        
        crearComponentes();
        addToPanel();
        addActionListeners();
    }
    /*
     * Se encarga de buscar y cargar el reporte para mostrarlo.
     * <p>
     * Este metodo no tiene ningun valor de retorno y no recibe parametros
     */
    public void runReporte(){
        try{
            URL url = null;
            url = this.getClass().getResource( "/ViewLayer/ServiciosAdministrativos/reporteIngresos.jasper" );
            
            System.out.println("master " + url);
            if(url == null){
                JOptionPane.showMessageDialog(instance, "No encuentro el archivos del reportes maestro");
                System.exit(2);
            }
        
            JasperReport masterReport = null;
          
            try{
                masterReport = (JasperReport) JRLoader.loadObject(url);
            }catch(JRException e){
                JOptionPane.showMessageDialog(instance, "Error cargando el reporte maestro: " + e.getMessage());
                System.exit(3);
            }
            Map paremetro = new HashMap();
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, paremetro,conn);
            JasperViewer jviewer = new JasperViewer(jasperPrint,false);
            jviewer.setVisible(true);
        }catch(Exception j){
            JOptionPane.showMessageDialog(instance, "Mensaje de Error: " + j.getMessage());
        }
    }
    /**
     * Para cerrar la conexion a la base de datos.
     * <p>
     * Este metodo no tiene ningun valor de retorno y no recibe parametros
     */
    public void cerrar(){
        try{
            conn.close();
        }catch(SQLException ex){
            ex.printStackTrace();
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
    public void crearComponentes(){
        jlblFoto.setBounds(110, 50, 500, 331);
        jlblFoto.setIcon(new ImageIcon(getClass().getResource("/imagenes/bicis_gimnasio.jpg")));  
        
        jbtnVerReporte.setBounds(480, 478, 130, 40);
        jbtnVerReporte.setIcon(new ImageIcon(getClass().getResource("/imagenes/reporte.png")));
    }
        
    /**
    * Agrega los manejadores de eventos a los componentes visuales. 
    * Aqui se especifican las acciones a tomaar cuando se detecta
    * que un evento ha ocurrido en un componente visual
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */ 
    public void addActionListeners(){
        jbtnVerReporte.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                runReporte();
            }
            
            
        });
    }
        
    /**
    * Agrega todos los componentes visuales al GUI y sus subcomponentes. 
    * Tambien aqui se agregan los BorderLayout de los componentes agregados
    * de ser necesario
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    public void addToPanel(){
        add(jlblFoto);
        add(jbtnVerReporte);
    }
        
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Reporte y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static Reporte getInstance() throws ParseException{
        if( instance == null){
            instance = new Reporte();
        }
        
        return instance;
    }
}
