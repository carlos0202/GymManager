/*
 * 
 * Responsables:
 * Carlos Antonio González Canario
 * Andres de Jesús Frías Gúzman
 * 
 */

package View;

import Utils.FileOps;
import Utils.ScriptRunner;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.jar.JarFile;
import javax.swing.*;


public class ScriptDataInput extends JFrame{
    private static String url = "jdbc:mysql://localhost:3306/mysql";
    private static String driver = "com.mysql.jdbc.Driver";
    private String user;
    private String pass;
    private Connection conexionBD;
    private JTextField jtxtUserName;
    private JPasswordField jtxtPassword;
    private JButton jbtnAdelante;
    private JButton jbtnEnviar;
    private JLabel jlblUsuario;
    private JLabel jlblPassword;
    private JLabel jlblMensaje;
    private JLabel jlblFoto;
    private Properties objProps;
    private JLabel jlblMensajeConexion;
    private boolean done;
    private String image_path;
    private JarFile jarfile;

    public ScriptDataInput(){
        
        super("Script Data Input");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(580, 400);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setResizable(false);
        jtxtUserName = new JTextField();
        done = false;
        jtxtPassword = new JPasswordField();
        jbtnEnviar = new JButton("Enviar");
        jbtnAdelante = new JButton("Continuar");
        jlblMensaje = new JLabel(
            "Introduzca el nombre de usuario y el password de la cuenta del DBMS"
        );
        jlblUsuario = new JLabel("Usuario Base de Datos");
        jlblPassword = new JLabel("Password Usuario");
        jlblMensajeConexion = new JLabel();
        jlblFoto = new JLabel();
        objProps = new Properties();

        image_path = "jar:" + getClass().getProtectionDomain().getCodeSource().
                        getLocation().toString() + "!/imagenes/";

        try{
            crearComponentes();
            addToPanel();
            addActionListeners();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al cargar componentes");
        }
        
        this.setVisible(true);

    }
    
    private void executeScript() throws Exception{
        
       String destPath = System.getProperty("user.home")+"\\GymManager\\localhost.sql";
       String sourcePath = null;    
        
       final URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
       
       try{
            final File jarPath = new File(url.toURI()).getParentFile();
            sourcePath = jarPath.toString();
            sourcePath += "\\localhost.sql";
        } catch(final URISyntaxException e){
            JOptionPane.showMessageDialog(null, "no se pudo cargar el script");
        }
       
       FileOps.copyFile(sourcePath, destPath);
       InputStream in = new FileInputStream(sourcePath);
        ScriptRunner obj = new ScriptRunner(conexionBD, false, true);
        obj.runScript(new InputStreamReader(in));
    }
    
    private void  testConnection() throws SQLException, Exception{
        Class.forName(driver);
        conexionBD = DriverManager.getConnection(url, user, pass);
    }

    private void crearComponentes() throws Exception{       
        this.getContentPane().setBackground(Color.WHITE);
        
        jlblFoto.setBounds(45, 80, 200, 103);
        jlblFoto.setIcon(new ImageIcon(new URL(image_path +"logo.png")));

        jtxtUserName.setBounds(381, 103, 130, 20);
        jtxtPassword.setBounds(381, 166, 130, 20);
        
        jbtnEnviar.setBounds(380, 217, 130, 40);
        jbtnEnviar.setIcon(new ImageIcon(new URL(image_path + "aceptar-icono-6494-32.png")));
        
        jbtnAdelante.setBounds(380, 280, 130, 40);
        jbtnAdelante.setIcon(new ImageIcon(new URL(image_path + "Boton_avanza.gif")));
        
        jlblMensaje.setFont(new Font("Calibri", Font.PLAIN, 16));
        jlblMensaje.setBounds(45, 39, 507, 20);

        jlblUsuario.setFont(new Font("Calibri", Font.PLAIN, 14));
        jlblUsuario.setBounds(250, 103, 140, 20);

        jlblPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
        jlblPassword.setBounds(250, 166, 140, 20);
        
        jlblMensajeConexion.setFont(new Font("Calibri", Font.PLAIN, 16));
        jlblMensajeConexion.setBounds(45, 330, 507, 26);
    }

    private void addToPanel(){
        this.getContentPane().add(jlblFoto);
        this.getContentPane().add(jtxtUserName);
        this.getContentPane().add(jtxtPassword);
        this.getContentPane().add(jbtnEnviar);
        this.getContentPane().add(jlblUsuario);
        this.getContentPane().add(jlblPassword);
        this.getContentPane().add(jlblMensaje);
        this.getContentPane().add(jbtnAdelante);
        getContentPane().add(jlblMensajeConexion);
    }

    private void addActionListeners(){
        jbtnEnviar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(jtxtUserName.getText().equals(null) ||
                    jtxtUserName.getText().equals("")   )  {
                        throw new Exception("Debe introducir el usuario");
                    }
                    else if(jtxtPassword.getText().equals(null) ||
                    jtxtPassword.getText().equals("")   )  {
                        throw new Exception("Debe introducir el password");
                    }
                    else{
                        String path = System.getProperty("user.home");
                        
                        new File(path+"/GymManager").mkdir();

                        path = path +  "/GymManager";
                        user = jtxtUserName.getText();
                        pass = jtxtPassword.getText();                 

                        objProps.setProperty("DBuser", user);
                        objProps.setProperty("DBpass", pass);

                        objProps.store(new FileOutputStream(
                                new File(path+"/DB.properties")),
                                "Propiedades del acceso a la bd"
                        );

                        testConnection();
                        jlblMensajeConexion.setText("Conexion Establecida Exitosamente");
                        
                        executeScript();
                        jlblMensajeConexion.setText("Base de datos Creada Correctamente");
                        done = true;
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "error al cargar configuracion");
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    jlblMensajeConexion.setText("Los parametros para la conexion son incorrectos");
                }
            }
        });
        
        jbtnAdelante.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(done) {
                    System.exit(0);
                }
                else {
                    jlblMensajeConexion.setText("Debe introducir los parametros de conexion");
                } 
            }
        });
    }
}
