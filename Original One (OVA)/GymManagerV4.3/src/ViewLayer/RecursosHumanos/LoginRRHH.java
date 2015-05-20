/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */

package ViewLayer.RecursosHumanos;

import DomainModelLayer.Controllers.RecursosHumanos;
import DomainModelLayer.Entidades.PersAdministrativo;
import ViewLayer.MainMenu.MenuPrincipal;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginRRHH extends JFrame{
    private static LoginRRHH instance;
    private JDesktopPane jdpFondo;
    private JLabel jlblTitulo;
    private JLabel jlblUser;
    private JLabel jlblClave;
    private JLabel jlblImagen;
    private JTextField jtxtUser;
    private JTextField jtxtClave;
    private JButton jbtnAceptar;
    private JButton jbtnSalir;
    private PersAdministrativo empleado;
    
    /**
    * Constructor privado para la clase LoginRRHH.
    */
    private LoginRRHH(){
        super("Gym Manager Recursos Humanos Login");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(500, 300);
        setLocationRelativeTo(null);
        
        jdpFondo = new JDesktopPane();
        jlblTitulo = new JLabel("Recursos Humanos");
        jlblUser = new JLabel("User");
        jtxtUser = new JTextField();
        jlblClave = new JLabel("Password");
        jtxtClave = new JPasswordField();
        jbtnAceptar = new JButton("Aceptar");
        jbtnSalir = new JButton("Salir");
        jlblImagen = new JLabel();
        
        crearComponentes();
        addToPanel();
        addActionListeners();
        
        setVisible(true);
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
        jdpFondo.setSize(500, 300);
        jdpFondo.setLayout(null);
          
        jlblTitulo.setFont(new Font("Sylfaen", Font.ITALIC, 26));
        jlblTitulo.setBounds(10, 20, 300, 30);
         
        jlblUser.setBounds(40, 80, 80, 20);
         
        jtxtUser.setBounds(80, 80, 140, 20);
         
        jlblClave.setBounds(10, 140, 80, 20);
        
        jtxtClave.setBounds(80, 140, 140, 20);
        
        jbtnAceptar.setIcon(new ImageIcon(getClass().getResource("/imagenes/aceptar-icono-6494-32.png")));
        jbtnAceptar.setBounds(220, 200, 120, 40);
        
        jbtnSalir.setIcon(new ImageIcon(getClass().getResource("/imagenes/cancelar-icono-4961-32.png")));
        jbtnSalir.setBounds(350, 200, 120, 40);
        
        jlblImagen.setIcon(new ImageIcon(getClass().getResource("/imagenes/logo_tecno_gym_uruapan.jpg")));
        jlblImagen.setBounds(250, 70, 200, 100);
         
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
        jbtnAceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                try {
                    String userName = jtxtUser.getText();
                    String password = jtxtClave.getText();
                        
                    empleado = RecursosHumanos.registrarLogin(userName, password);

                    if(userName.equals(empleado.getUserName()) &&
                        password.equals(empleado.getPassword())  ){
                        jtxtUser.setText("");
                        jtxtClave.setText("");
                        MenuRRHH.getInstance().setLoggedUser(empleado);
                        MenuRRHH.getInstance().setVisible(true);
                        instance.setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(instance, "Informacion de inicio de sesion incorrecta");
                    }
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(instance, "Informacion de inicio de sesion incorrecta");
                }

            }

        });
        
         jbtnSalir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jtxtUser.setText("");
                jtxtClave.setText("");
                MenuPrincipal.getInstance().setVisible(true);
                instance.dispose();
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
    private void addToPanel(){
        add(jdpFondo);
        jdpFondo.add(jlblTitulo);
        jdpFondo.add(jlblUser);
        jdpFondo.add(jtxtUser);
        jdpFondo.add(jlblClave);
        jdpFondo.add(jtxtClave);
        jdpFondo.add(jbtnAceptar);
        jdpFondo.add(jbtnSalir);
        jdpFondo.add(jlblImagen);
        
    }
    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Login de Recursos Humanos  y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static LoginRRHH getInstance(){
         if(instance == null){
            instance = new LoginRRHH();           
         }

        return instance;
        
    }
    
}
