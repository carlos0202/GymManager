/*
 * Responsable:
 * Saul Hernandez Sanchez
 */

package ViewLayer.Pago;

import DomainModelLayer.Controllers.Pago;
import DomainModelLayer.Entidades.PersAdministrativo;
import ViewLayer.MainMenu.MenuPrincipal;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.*;

public class LoginPago extends JFrame {
    private static LoginPago instance;
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
    * Constructor privado para la clase LoginPago.
    */
    private LoginPago(){
        super("Gym Manager Pagos Login");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pack();
        setSize(500, 300);
        setLocationRelativeTo(null);
        
        setLayout(null);
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
        jdpFondo = new JDesktopPane();
        jdpFondo.setSize(500, 300);
        jdpFondo.setLayout(null);
        
        jlblTitulo = new JLabel("Departemento de Pago");
        jlblTitulo.setFont(new Font("Sylfaen", Font.ITALIC, 26));
        jlblTitulo.setBounds(10, 20, 300, 30);
        
        jlblUser = new JLabel("User");
        jlblUser.setBounds(40, 80, 80, 20);
        
        jtxtUser = new JTextField();
        jtxtUser.setBounds(80, 80, 140, 20);
        
        jlblClave = new JLabel("Password");
        jlblClave.setBounds(10, 140, 80, 20);
        
        jtxtClave = new JPasswordField();
        jtxtClave.setBounds(80, 140, 140, 20);
        
        jbtnAceptar = new JButton("Aceptar");
        jbtnAceptar.setIcon(new ImageIcon(getClass().getResource("/imagenes/aceptar-icono-6494-32.png")));
        jbtnAceptar.setBounds(220, 200, 120, 40);
        
        jbtnSalir = new JButton("Salir");
        jbtnSalir.setIcon(new ImageIcon(getClass().getResource("/imagenes/cancelar-icono-4961-32.png")));
        jbtnSalir.setBounds(350, 200, 120, 40);
        
        jlblImagen = new JLabel();
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
                    
                    empleado = Pago.registrarLogin(userName, password);
                    
                    if(userName.equals(empleado.getUserName()) &&
                       password.equals(empleado.getPassword())  ){
                        
                        MenuPago.getInstance().setLoggedUser(empleado);
                        MenuPago.getInstance();
                        instance.setVisible(false); 
                    }
                    else{
                        JOptionPane.showMessageDialog(instance, "Su informacion de inicio de sesion es incorrecta");
                    }
                    
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(instance, "Informacion de inicio de sesion incorrecta");
                }
            }
        
        
        });
        
        jbtnSalir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                instance.setVisible(false);
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
    * @return la instancia actual de la clase Login de Pago  y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static LoginPago getInstance() throws ParseException {
         if(instance == null){
            instance = new LoginPago();           
        }
        return instance;
    }
    
}
