/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Entidades;

/**
* Clase que almacena los datos de un detalle de una factura. 
*/
public class DetalleFactura {
    private int idDetalle;
    private String detalle;
    private double precio;

    /**
    * Instancia un nuevo Detalle de Factura con los parametros recibidos. 
    * <p>
    * @param detalle     los datos detallados del detalle de la factura.
    * @param precio      el precio del detalle.
    *
    */
    public DetalleFactura(String detalle, double precio) {
        this.detalle = detalle;
        this.precio = precio;
    }

    /**
    * Instancia un nuevo Detalle de Factura con los parametros recibidos.
    * <p>
    * @param idDetalle  los datos del identificador del detalle (id).
    * @param detalle    los datos detallados del detalle de la factura.
    * @param precio     el precio del detalle.
    *
    */
    public DetalleFactura(int idDetalle, String detalle, double precio) {
        this.idDetalle = idDetalle;
        this.detalle = detalle;
        this.precio = precio;
    }

    /**
    * obtiene los datos del detalle. 
    * <p>
    * @return los datos de un detalle de una factura.
    *
    */
    public String getDetalle() {
        return detalle;
    }

    /**
    * establece los datos del detalle. 
    * <p>
    * @param detalle el detalle de la factura.
    *
    */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
    * obtiene los datos del precio de un detalle 
    * <p>
    * @return el precio de costo del detalle.
    *
    */
    public double getPrecio() {
        return precio;
    }

    /**
    * establece los datos del precio del detalle. 
    * <p>
    * @param precio el precio de costo del detalle.
    *
    */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
    * obtiene los datos del identificador del detalle (id). 
    * <p>
    * @return la descripcion de la clase.
    *
    */
    public int getIdDetalle() {
        return idDetalle;
    }
        
}
