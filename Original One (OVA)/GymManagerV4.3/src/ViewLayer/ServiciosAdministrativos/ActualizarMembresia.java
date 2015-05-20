/*
 * Responsable:
 * Saul Hernandez Sanchez
 */

package ViewLayer.ServiciosAdministrativos;

import DomainModelLayer.Controllers.ServiciosAdministrativos;
import DomainModelLayer.Entidades.Membresia;
import Utilidades.jTextNum;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ActualizarMembresia extends JPanel {
    private static ActualizarMembresia instance;
    private JTextField jtxtNombre;
    private jTextNum jtxtPrecio;
    private JPanel jpnlDatos;
    private JLabel jlblNombre;
    private JLabel lblPrecio;
    private JLabel lblTipo;
    private JPanel jpnlDescripcion;
    private JTextArea JtxtaDescripcion;
    private JComboBox jcboTipoMembresia;
    private JButton jbtnActualizar;
    private JPanel jpnlMembresia;
    private JComboBox jcboMembresia;
    private Vector<Membresia> membresias;
    private Membresia membresia;
    
    /**
    * Constructor privado para la clase ActualizarMembresia.
    */
    public ActualizarMembresia() throws Exception {
        setLayout(null);
        setSize(715, 540);
        setBackground(Color.WHITE);

        jpnlDatos = new JPanel();
        jlblNombre = new JLabel("Nombre");
        lblPrecio = new JLabel("Precio");
        jtxtNombre = new JTextField();
        jtxtPrecio = new jTextNum();
        jtxtPrecio.setColumns(10);
        jpnlDescripcion = new JPanel();
        JtxtaDescripcion = new JTextArea();
        jcboTipoMembresia = new JComboBox();
        lblTipo = new JLabel("Tipo");
        jbtnActualizar = new JButton("Actualizar");
        jpnlMembresia = new JPanel();
        jcboMembresia = new JComboBox();
        membresias = new Vector<Membresia>();
        
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
    private void crearComponentes() throws Exception{
        jpnlDatos.setLayout(null);
        jpnlDatos.setBackground(Color.WHITE);
        jpnlDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
        jpnlDatos.setBounds(28, 130, 653, 285);

        jlblNombre.setBounds(24, 23, 50, 14);

        lblPrecio.setBounds(32, 76, 50, 14);

        jtxtNombre.setBounds(83, 20, 109, 20);

        jtxtPrecio.setBounds(83, 73, 109, 20);

        jpnlDescripcion.setLayout(null);
        jpnlDescripcion.setBackground(Color.WHITE);
        jpnlDescripcion.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Descripcion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        jpnlDescripcion.setBounds(292, 23, 299, 236);

        JtxtaDescripcion.setWrapStyleWord(true);
        JtxtaDescripcion.setBounds(20, 22, 258, 192);

        jcboTipoMembresia.setBounds(83, 129, 109, 20);
        jcboTipoMembresia.addItem("<Seleccione>");
        jcboTipoMembresia.addItem("Diario");
        jcboTipoMembresia.addItem("Semanal");
        jcboTipoMembresia.addItem("Quincenal");
        jcboTipoMembresia.addItem("Mensual");
        jcboTipoMembresia.addItem("Anual");

        lblTipo.setBounds(41, 132, 50, 14);

        jbtnActualizar.setBounds(551, 439, 130, 40);
        jbtnActualizar.setIcon(new ImageIcon(getClass().getResource("/imagenes/update.png")));

        jpnlMembresia.setLayout(null);
        jpnlMembresia.setBackground(Color.WHITE);
        jpnlMembresia.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Seleccione Membresia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        jpnlMembresia.setBounds(28, 21, 323, 88);

        membresias = ServiciosAdministrativos.buscarMembresias();
        jcboMembresia.setBounds(33, 36, 258, 20);
        jcboMembresia.addItem("<Seleccione>");
        for(Membresia memb: membresias){
            jcboMembresia.addItem(memb.getNombre());
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
        add(jpnlDatos);
        jpnlDatos.add(jlblNombre);
        jpnlDatos.add(lblPrecio);
        jpnlDatos.add(jtxtNombre);
        jpnlDatos.add(jtxtPrecio);
        jpnlDatos.add(jpnlDescripcion);
        jpnlDescripcion.add(JtxtaDescripcion);
        jpnlDatos.add(jcboTipoMembresia);
        jpnlDatos.add(lblTipo);
        add(jbtnActualizar);
        add(jpnlMembresia);
        jpnlMembresia.add(jcboMembresia);
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
        
        jcboMembresia.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(jcboMembresia.getSelectedIndex() > 0){
                    membresia = membresias.get(jcboMembresia.getSelectedIndex()-1);
                    insertarDatos();
                }
            }
        });
        
        jbtnActualizar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(checkInputValues()){
                        obtenerDatos();
                        ServiciosAdministrativos.actualizarMembresia(membresia);
                        JOptionPane.showMessageDialog(instance, "Membresia actualizada correctamente");
                    }                    
                }catch(Exception ex){
                     if(!(ex instanceof Exception)){
                         JOptionPane.showMessageDialog(instance, "Fallo al actualizar membresia");
                     }else{
                        JOptionPane.showMessageDialog(instance, ex.getMessage());
                        ex.printStackTrace();
                     }
                }
            }
        });
    }
    /**
    * Setea los nuevos valores de la clase Membresia a la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */ 
    private void insertarDatos(){
        jcboTipoMembresia.setSelectedItem(membresia.getTipo());
        jtxtNombre.setText(membresia.getNombre());
        JtxtaDescripcion.setText(membresia.getDescripcion());
        jtxtPrecio.setNum(membresia.getPrecio());
    }
        
    /**
    * Obtiene valores de la clase Membresia desde la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void obtenerDatos(){
        membresia.setNombre(jtxtNombre.getText());
        membresia.setDescripcion(JtxtaDescripcion.getText());
        membresia.setTipo(jcboTipoMembresia.getSelectedItem().toString());
        membresia.setPrecio(jtxtPrecio.getDouble());
    }
        
    /**
    * Verifica la validez en terminos generales de los valores introducidos
    * en los componentes visuales. 
    * <p>
    * Este metodo lanaza una excepcion indicando que campo tiene valores incorrectos.
    * 
    * @return un valor booleano true si los valores son correctos.
    *
    */
    private boolean checkInputValues() throws Exception{
        if(jtxtNombre.getText().equals(null) || jtxtNombre.getText().equals("")){
            throw new Exception("El nombre de la seccion no puede estar vacio.");
        }
        
        if( jtxtPrecio.getDouble() == 0 || jtxtPrecio.getText().equals(null) ){
            throw new Exception("debe introducir un precio valido.");        
        }

        if( JtxtaDescripcion.getText().equals(null) || JtxtaDescripcion.getText().equals("")){
            throw new Exception("debe introducir una descripcion.");
        }
        
        if(jcboTipoMembresia.getSelectedIndex() == 0){
            throw new Exception("debe seleccionar un tipo de membresia valido.");
        }
        
        return true;
    }
	    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase Actualizar Membresia y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static ActualizarMembresia getInstance() throws Exception{
        if( instance == null){
            instance = new ActualizarMembresia();
        }
        
        return instance;
    }
}
