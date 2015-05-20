/*
 * Responsable:
 * Saul Hernandez Sanchez
 */
package ViewLayer.Pago;

import DomainModelLayer.Controllers.Pago;
import DomainModelLayer.Entidades.DetalleFactura;
import DomainModelLayer.Entidades.Factura;
import DomainModelLayer.Entidades.PersAdministrativo;
import DomainModelLayer.Entidades.Socio;
import Utilidades.jTextNum;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class MenuPago extends JFrame{
    private static MenuPago instance;
    private JDesktopPane jdpFondo;
    private JPanel jpnlPanelPrincipal;
    private JLabel jlblTitulo;
    private JLabel jlblFoto;
    private JLabel jlblFoto2;
    private JLabel jlblNCF;
    private JLabel jlblIdSocio;
    private JLabel jlblMonto;
    private JLabel jlblCambio;
    private JPanel jpnlMetodoPago;
    private JRadioButton jradTarjeta;
    private JRadioButton jradEfectivo;
    private ButtonGroup btngFormaPago;
    private jTextNum jtxtIdSocio;
    private JTextField jtxtNCF;
    private jTextNum jtxtMonto;
    private jTextNum jtxtCambio;
    private JButton jbtnRegistrarPago;
    private JButton jbtnLogout;
    private JButton jbtnBuscar;
    private JTable jtblDetalle;
    private JScrollPane jscrollPane;  
    private DefaultTableModel jdtblmDetalle;
    private PersAdministrativo usuario;
    private JPanel jpnlDetalle;
    private Factura factura;
    private Socio socio;
    private JLabel jlblTotalFactura;
    private JLabel jlblTotal;
    private JLabel jlblPendientes;
    
    /**
    * Constructor privado para la clase MenuPago.
    */
    private MenuPago() throws ParseException{
        super("Gym Manager Pagos");
        setResizable(false);
	setSize(842,592);
	setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	getContentPane().setLayout(null);
        
        jdpFondo = new JDesktopPane();
        jpnlPanelPrincipal = new JPanel();
        jlblTitulo = new JLabel("Departamentos de Pagos");
        jlblFoto = new JLabel();
        jlblFoto2 = new JLabel();
        jlblNCF = new JLabel("NCF");
        jlblIdSocio = new JLabel("Id Socio");
        jpnlMetodoPago = new JPanel();
        jradTarjeta = new JRadioButton("Tarjeta");
        jradEfectivo = new JRadioButton("Efectivo");
        btngFormaPago = new ButtonGroup();
        jtxtIdSocio = new jTextNum();
        jtxtNCF = new JTextField();
        jlblMonto = new JLabel("Monto");
        jtxtMonto = new jTextNum();
        jlblCambio = new JLabel("Cambio");
        jtxtCambio = new jTextNum();
        jbtnRegistrarPago = new JButton("Registrar");
        jbtnLogout = new JButton("Logout");
        jbtnBuscar = new JButton("Buscar");
        jtblDetalle = new JTable();
        jdtblmDetalle = new DefaultTableModel();
        jscrollPane = new JScrollPane(jtblDetalle);
        jpnlDetalle = new JPanel();
        jlblTotalFactura = new JLabel("Total Factura: ");
        jlblTotal = new JLabel("");
        jlblPendientes = new JLabel("");

        jpnlDetalle.setLayout(null);
        crearComponentes();
        addToPanel();
        defaultComponents();
        addActionListeners();

    }
    
    /**
    * Asigna valores por defecto al radioButtons de tipo de Tarjeta
    * y los textFields monto y cambio para q no sean editables . 
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private void defaultComponents(){
        jradTarjeta.setSelected(true);
        jtxtMonto.setEnabled(false);
        jtxtCambio.setEditable(false);
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
        jdpFondo.setSize(842, 592);
        
        jlblTitulo.setBounds(10, 10, 400, 100);
        jlblTitulo.setFont(new Font("Sylfaen", Font.ITALIC, 36));
        
        jlblFoto.setBounds(10, 100, 200, 180);
        jlblFoto.setIcon(new ImageIcon(getClass().getResource("/imagenes/images.jpg"))); 
        
        jlblFoto2.setBounds(10, 350, 200, 180);
        jlblFoto2.setIcon(new ImageIcon(getClass().getResource("/imagenes/pago.jpg"))); 
        
        jpnlPanelPrincipal.setBounds(250, 90, 550, 450);
        jpnlPanelPrincipal.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Pago"));
        jpnlPanelPrincipal.setLayout(null);
        jpnlPanelPrincipal.setBackground(Color.WHITE);
        
        jlblIdSocio.setBounds(30, 40, 60, 20);
        
        jbtnBuscar.setBounds(200, 30, 120, 40);
        jbtnBuscar.setIcon(new ImageIcon(getClass().getResource("/imagenes/buscar.png")));
        
        jlblNCF.setBounds(55, 190, 60, 20);
        
        jpnlMetodoPago.setBounds(30, 80, 150, 100);
        jpnlMetodoPago.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Metodo de pago"));
        jpnlMetodoPago.setLayout(null);
        jpnlMetodoPago.setBackground(Color.WHITE);
        
        jradTarjeta.setBounds(10, 20, 100, 30);
        jradTarjeta.setBackground(Color.WHITE);
        
        jradEfectivo.setBounds(10, 50, 100, 30);
        jradEfectivo.setBackground(Color.WHITE);
        
        jtxtIdSocio.setBounds(90, 40, 90, 20);
        
        jtxtNCF.setBounds(90, 190, 90, 20);
        
        jlblMonto.setBounds(40, 230, 60, 20);
        
        jtxtMonto.setBounds(90, 230, 90, 20);
        
        jlblCambio.setBounds(30, 270, 60, 20);
        
        jtxtCambio.setBounds(90, 270, 90, 20);
        
        jpnlDetalle.setBounds(200, 81, 335, 209);
        jpnlDetalle.setBackground(Color.WHITE);
        jpnlDetalle.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true),"Detalle factura"));
    
        jbtnRegistrarPago.setBounds(405, 350, 130, 40);
        jbtnRegistrarPago.setIcon(new ImageIcon(getClass().getResource("/imagenes/pago.png")));
        
        jbtnLogout.setBounds(30, 350, 130, 40);
        jbtnLogout.setIcon(new ImageIcon(getClass().getResource("/imagenes/salir-de-gnome-icono-5366-32.png")));
        
        jlblTotalFactura.setBounds(325, 301, 100, 20);
        
        jlblTotal.setEnabled(true);
        jlblTotal.setFont(new Font("Calibri", Font.PLAIN, 20));
        jlblTotal.setBounds(435, 301, 100, 20);
        
        jlblPendientes.setFont(new Font("Calibri", Font.PLAIN, 20));
        jlblPendientes.setBounds(30, 311, 260, 20);
        
        jdtblmDetalle = new DefaultTableModel(
             new Object [][] {
                {null, null,null}
            }, new String [] {"Detalle", "Precio"}
        ){
            @Override
             public boolean isCellEditable (int fila, int columna) {
                 if (columna >= 0)
                     return false;
                 return true;
             }
        };
        jtblDetalle.setModel(jdtblmDetalle);
        
        jscrollPane.setBounds(10, 20, 315, 176);
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
        
        jradEfectivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jradEfectivo.isSelected() == true){
                    jtxtMonto.setEnabled(true);
                }
            }
        
        });
        
        jradTarjeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jradTarjeta.isSelected() == true){
                    jtxtMonto.setEnabled(false);
                }
            }
        
        });

        jbtnLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LoginPago.getInstance().setVisible(true);
                    dispose();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(instance, "Error en ventana");
                }
            }
        
        });
        
        jbtnBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jdtblmDetalle.setRowCount(0);
                int idSocio = jtxtIdSocio.getInteger();
                
                try{
                    socio = Pago.buscarDatosSocio(idSocio);
                    
                    if( socio == null)
                        JOptionPane.showMessageDialog(instance, "Socio no encontrado");
                    else if(socio.getFacturas().size() < 1)
                        JOptionPane.showMessageDialog(instance, "No hay facturas pendientes");
                    else
                        insertValues();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        
        jbtnRegistrarPago.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                double monto = 0;
                if(jradEfectivo.isSelected())
                    monto = jtxtMonto.getDouble();
                else
                    monto = factura.getTotal();
                try{
                    if(factura.getTotal() <= monto){
                        double cambio = Pago.registrarPago(factura, usuario, monto, socio);
                        jtxtCambio.setNum(cambio);
                        JOptionPane.showMessageDialog(instance, "Pago realizado exitosamente");
                    }
                    else{
                        JOptionPane.showMessageDialog(instance, "Monto insuficiente");
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(
                            instance,
                            "No se pudo registrar el pago, revise los datos."
                    );
                    ex.printStackTrace();
                }
            }
        });
    
    }
    
     /**
     * Obtiene las facturas hechas por el socio y crea nuevas para el socio
     * que fue seleccionado para la busqueda 
     * <p>
     * Este metodo no tiene ningun valor de retorno y no recibe parametros
     */
    private void insertValues(){
        int facturas = socio.getFacturas().size();
        if(facturas > 1)
            jlblPendientes.setText(
                    String.valueOf(facturas-1) + " " +
                    "Facturas Pendientes"
            );
        else
            jlblPendientes.setText("");
        
        jdtblmDetalle.setRowCount(0);
        factura = socio.getFacturas().get(0);
        
        for(DetalleFactura actual: factura.getDetalles()){
            Vector<String> row = new Vector();
            
            row.add(actual.getDetalle());
            row.add(String.valueOf(actual.getPrecio()));
            
            jdtblmDetalle.addRow(row);
        }
        
        jtxtNCF.setText(factura.getNcf());
        jlblTotal.setText(String.valueOf(factura.getTotal()));
        
    }
    
    /**
    * Agrega todos los componentes visuales al GUI y sus subcomponentes. 
    * Tambien aqui se agregan los BorderLayout de los componentes agregados
    * de ser necesario
    * <p>
    * Este metodo no tiene ningun valor de retorno y no recibe parametros
    *
    */
    private  void addToPanel(){
        getContentPane().add(jdpFondo);
        jdpFondo.add(jlblTitulo);
        jdpFondo.add(jlblFoto);
        jdpFondo.add(jlblFoto2);
        jdpFondo.add(jpnlPanelPrincipal);
        jpnlPanelPrincipal.add(jbtnBuscar);
        jpnlPanelPrincipal.add(jlblIdSocio);
        jpnlPanelPrincipal.add(jtxtIdSocio);
        jpnlPanelPrincipal.add(jlblNCF);
        jpnlPanelPrincipal.add(jtxtNCF);
        jpnlPanelPrincipal.add(jlblMonto);
        jpnlPanelPrincipal.add(jtxtMonto);
        jpnlPanelPrincipal.add(jlblCambio);
        jpnlPanelPrincipal.add(jtxtCambio);
        jpnlPanelPrincipal.add(jpnlMetodoPago);
        jpnlMetodoPago.add(jradTarjeta);
        jpnlMetodoPago.add(jradEfectivo);
        btngFormaPago.add(jradTarjeta);
        btngFormaPago.add(jradEfectivo);
        jpnlPanelPrincipal.add(jpnlDetalle);
        jpnlDetalle.add(jscrollPane, BorderLayout.CENTER);
        jpnlPanelPrincipal.add(jbtnRegistrarPago);
        jpnlPanelPrincipal.add(jbtnLogout);
        jpnlPanelPrincipal.add(jlblTotalFactura);
        jpnlPanelPrincipal.add(jlblTotal);
        jpnlPanelPrincipal.add(jlblPendientes);
        
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
    * @return la instancia actual de la clase Menu de pagos y si no
    * existe ninguna la crea y luego la retorna
    *
    */
    public static MenuPago getInstance() throws ParseException{
        if(instance == null){
            instance = new MenuPago();           
        }
        return instance;
    }
    
}
