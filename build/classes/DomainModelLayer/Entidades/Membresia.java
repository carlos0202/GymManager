/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Entidades;

/**
* Clase que almacena los datos de una Membresia del gimnasio. 
*/
public class Membresia {
    private int idMembresia;
    private String nombre;
    private String descripcion;
    private String tipo;
    private double precio;

    /**
    * Instancia un nuevo Instructor usando los parametros recibidos. 
    * <p>
    * @param idMembresia el identificador unico de la membresia (id).
    * @param nombre      el nombre de la membresia.
    * @param descripcion la descripcion de esta membresia.
    * @param tipo        el tipo de pago de esta membresia.
    * @param precio      el precio de costo de esta membresia.
    * 
    */
    public Membresia(int idMembresia, String nombre, String descripcion, String tipo, double precio) {
        this.idMembresia = idMembresia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.precio = precio;
    }

    /**
    * Instancia un nuevo Instructor usando los parametros recibidos. 
    * <p>
    * @param nombre      el nombre de la membresia.
    * @param descripcion la descripcion de esta membresia.
    * @param tipo        el tipo de pago de esta membresia.
    * @param precio      el precio de costo de esta membresia.
    * 
    */
    public Membresia(String nombre, String descripcion, String tipo, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.precio = precio;
    }

    /**
    * obtiene los datos de la descripcion de la membresia. 
    * <p>
    * @return los datos de la descripciion de la membresia.
    *
    */ 
    public String getDescripcion() {
        return descripcion;
    }

    /**
    * establece los datos de la descripcion de la membresia. 
    * <p>
    * @param descripcion la descripcion de la membresia.
    *
    */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
    * obtiene los datos del nombre de la membresia. 
    * <p>
    * @return los datos del nombre de la membresia.
    *
    */ 
    public String getNombre() {
        return nombre;
    }

    /**
    * establece los datos del nombre de la membresia. 
    * <p>
    * @param nombre el nombre de la membresia.
    *
    */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
    * obtiene los datos del precio de la membresia. 
    * <p>
    * @return los datos del precio de la membresia.
    *
    */ 
    public double getPrecio() {
        return precio;
    }

    /**
    * establece los datos del precio de la membresia. 
    * <p>
    * @param precio el precio de la membresia.
    *
    */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
    * obtiene los datos del tipo de membresia. 
    * <p>
    * @return el tipo de membresia .
    *
    */ 
    public String getTipo() {
        return tipo;
    }

    /**
    * establece los datos del tipo de membresia. 
    * <p>
    * @param tipo la especialidad del instructor.
    *
    */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
    * obtiene los datos del identificador unico de una membresia (id). 
    * <p>
    * @return  el id de la membresia.
    *
    */ 
    public int getIdMembresia() {
        return idMembresia;
    }
    
}
