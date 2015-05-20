/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Entidades;

import java.util.Date;

/**
* Clase que almacena los datos de un pago realizado a una factura pendiente. 
*/
public class Pago {
    private int idPago;
    private double monto;
    private Date fechaPago;

    /**
    * Instancia un nuevo Pago usando los parametros recibidos. 
    * <p>
    * @param idPago    el identificador unico del pago (id).
    * @param monto     el monto del pago realizado por la deuda.
    * @param fechaPago la fecha en que fue realizado el pago.
    * 
    */
    public Pago(int idPago, double monto, Date fechaPago) {
        this.idPago = idPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }

        /**
    * Instancia un nuevo Pago usando los parametros recibidos. 
    * <p>
    * @param monto     el monto del pago realizado por la deuda.
    * @param fechaPago la fecha en que fue realizado el pago.
    * 
    */
    public Pago(double monto, Date fechaPago) {
        this.monto = monto;
        this.fechaPago = fechaPago;
    }

    /**
    * obtiene los datos de la fecha en que se realizo el pago. 
    * <p>
    * @return  la fecha en que se realizo este pago.
    *
    */ 
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
    * establece los datos de la fecha en que se efectuo el pago. 
    * <p>
    * @param fechaPago la fecha del pago.
    *
    */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
    * obtiene los datos del monto del pago realizado. 
    * <p>
    * @return los datos del monto de este pago.
    *
    */ 
    public double getMonto() {
        return monto;
    }

    /**
    * establece los datos del monto del pago. 
    * <p>
    * @param monto el monto por el cual se efectuo el pago.
    *
    */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
    * obtiene los datos de la identificacion unica del pago (id). 
    * <p>
    * @return el id del pago.
    *
    */ 
    public int getIdPago() {
        return idPago;
    }
   
}
