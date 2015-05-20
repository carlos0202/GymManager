/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Entidades;

import java.util.Date;
import java.util.Vector;

/**
* Clase que almacena los datos de una factira de un socio. 
*/
public class Factura {
    private int idFactura;
    private Date fechaFactura;
    private Vector<DetalleFactura> detalles;
    private double total;
    private String ncf;
    private Empleado encargado;
    private Socio socio;
    private Pago pago;

    /**
    * Instancia una nueva Factura usando los parametros recibidos. 
    * <p>
    * @param idFactura    el identificador unico de la factura (id).
    * @param fechaFactura la fecha de creacion de la factura.
    * @param detalles     los detalles de cobro de la factura
    * @param ncf          el numero de comprobante fiscal de la factura.
    * @param encargado    el empleado responsable de cobrar la factura.
    * @param socio        el socio que adeuda la factura.
    * 
    */
    public Factura(int idFactura, Date fechaFactura, Vector<DetalleFactura> detalles, String ncf, Empleado encargado, Socio socio) {
        this.idFactura = idFactura;
        this.fechaFactura = fechaFactura;
        this.detalles = detalles;
        this.ncf = ncf;
        this.encargado = encargado;
        this.socio = socio;
        calcularTotal();
    }

    /**
    * Instancia una nueva Factura usando los parametros recibidos. 
    * <p>
    * @param detalles     los detalles de cobro de la factura
    * @param ncf          el numero de comprobante fiscal de la factura.
    * @param encargado    el empleado responsable de cobrar la factura.
    * 
    */
    public Factura(Vector<DetalleFactura> detalles, String ncf, Empleado encargado) {
        this.detalles = detalles;
        this.ncf = ncf;
        this.encargado = encargado;
        this.fechaFactura = new Date();
        calcularTotal();
    }

    /**
    * Instancia una nueva Factura usando los parametros recibidos. 
    * <p>
    * @param ncf          el numero de comprobante fiscal de la factura.
    * @param encargado    el empleado responsable de cobrar la factura.
    * 
    */
    public Factura(String ncf, Empleado encargado) {
        this.ncf = ncf;
        this.encargado = encargado;
        this.fechaFactura = new Date();
        this.detalles = new Vector<DetalleFactura>();
    }

    /**
    * obtiene los datos del pago realizado a esta factura si existe
    * o retorna null. 
    * <p>
    * @return los datos del pago realizado a la factura.
    *
    */
    public Pago getPago() {
        return pago;
    }

    /**
    * establece los datos del pago realizado a esta factura. 
    * <p>
    * @param pago el pago realizado a la factura por un socio.
    *
    */
    public void setPago(Pago pago) {
        this.pago = pago;
    }

    /**
    * obtiene los datos del socio que adeuda la factura. 
    * <p>
    * @return los datos del socio deudor de la factura.
    *
    */
    public Socio getSocio() {
        return socio;
    }

    /**
    * establece los datos del socio que debe la factura. 
    * <p>
    * @param socio los datos del socio deudor de la factura.
    *
    */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }
    
    /**
    * obtiene los datos de los detalles de la factura. 
    * <p>
    * @return los datos de los detalles de factura.
    *
    */
    public Vector<DetalleFactura> getDetalles() {
        return detalles;
    }

    /**
    * establece los datos de los detalle de factura. 
    * <p>
    * @param detalles los detalles de la factura.
    *
    */
    public void setDetalles(Vector<DetalleFactura> detalles) {
        this.detalles = detalles;
    }

    /**
    * obtiene los datos del encargado de registrar el pago de la factura. 
    * <p>
    * @return los datos del empleado que registro la factura.
    *
    */
    public Empleado getEncargado() {
        return encargado;
    }

    /**
    * establece los datos del encargado de registrar la factura. 
    * <p>
    * @param encargado el encargado de registrar el pago de la factura.
    *
    */
    public void setEncargado(Empleado encargado) {
        this.encargado = encargado;
    }

    /**
    * obtiene los datos de la fecha de creacion de la factura. 
    * <p>
    * @return los datos de la fecha de creacion de factura.
    *
    */
    public Date getFechaFactura() {
        return fechaFactura;
    }

    /**
    * establece los datos de la fecha de creacion de la factura. 
    * <p>
    * @param fechaFactura la fecha en que se creo la factura.
    *
    */
    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    /**
    * obtiene los datos del numero de comprobante fiscal de la factura. 
    * <p>
    * @return los datos del comprobante fiscal de la factura correspondiente.
    *
    */
    public String getNcf() {
        return ncf;
    }

    /**
    * establece los datos del numero de comprobante fiscal. 
    * <p>
    * @param ncf el comprobante fiscal de la factura.
    *
    */
    public void setNcf(String ncf) {
        this.ncf = ncf;
    }

    /**
    * obtiene los datos de la identificacion de la factura (id). 
    * <p>
    * @return los datos del id de la factura.
    *
    */
    public int getIdFactura() {
        return idFactura;
    }

    /**
    * obtiene los datos del total de la factura. 
    * <p>
    * @return el monto total a pagar de la factura.
    *
    */
    public double getTotal() {
        calcularTotal();
        return total;
    }

    /**
    * Calcula el total a pagar de la factura en base a los subtotales
    * parciales de los detalles de factura. 
    * <p>
    * Este metodo no retorna ningun valor ni recibe ningun parametro.
    *
    */
    private void calcularTotal(){
        this.total = 0;
        
        for( DetalleFactura obj : detalles){
            total += obj.getPrecio();
        }
    }
    
    /**
    * obtiene los datos de un detalle de los detalles de factura. 
    * <p>
    * @return los datos de la fecha de ingreso.
    *
    */
    private DetalleFactura getDetalle(int idDetalle){
        
        for( int i = 0; i < detalles.size(); i++){
            if( detalles.elementAt(i).getIdDetalle() == idDetalle )
                return detalles.elementAt(i);
        }
        return null;
    }
}
