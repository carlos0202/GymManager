/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Entidades;

import java.util.Vector;

/**
* Clase que almacena los datos de una clase que se imparte en el gimnasio. 
*/
public class Clase {
    private int idClase;
    private String nombre;
    private String descripcion;
    private double precio;
    private Vector<Seccion> secciones;

    /**
    * Constructor publico de la clase Clase. 
    * <p>
    * @param idClase     el id de la clase.
    * @param nombre      el nombre de la clase.
    * @param descripcion la descripcion de la clase.
    * @param precio      el precio de inscripcion de la clase.
    *
    */
    public Clase(int idClase, String nombre, String descripcion, double precio) {
        this.idClase = idClase;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.secciones = new Vector<Seccion>();
    }

    /**
    * Constructor publico de la clase Clase. 
    * <p>
    * @param nombre      el nombre de la clase.
    * @param descripcion la descripcion de la clase.
    * @param precio      el precio de inscripcion de la clase.
    *
    */
    public Clase(String nombre, String descripcion, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.secciones = new Vector<Seccion>();
    }

    /**
    * obtiene los datos de la descripcion de la clase. 
    * <p>
    * @return la descripcion de la clase.
    *
    */
    public String getDescripcion() {
        return descripcion;
    }

    /**
    * establece los datos de la descripcion de la clase. 
    * <p>
    * @param descripcion la descripcion de la clase.
    *
    */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
    * obtiene los datos de la nombre de la clase. 
    * <p>
    * @return el nombre de la clase.
    *
    */
    public String getNombre() {
        return nombre;
    }

    /**
    * establece los datos del nombre de la clase. 
    * <p>
    * @param nombre el nombre de la clase.
    *
    */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
    * obtiene los datos del precio de la clase. 
    * <p>
    * @return el precio de inscripcion de la clase.
    *
    */
    public double getPrecio() {
        return precio;
    }

    /**
    * establece los datos del precio de la clase. 
    * <p>
    * @param precio el precio de la clase.
    *
    */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
    * obtiene los datos de las secciones de la clase. 
    * <p>
    * @return las secciones de la clase.
    *
    */
    public Vector<Seccion> getSecciones() {
        return secciones;
    }

    /**
    * establece los datos de las secciones de la clase. 
    * <p>
    * @param secciones las secciones de la clase.
    *
    */
    public void setSecciones(Vector<Seccion> secciones) {
        this.secciones = secciones;
    }

    /**
    * obtiene los datos de la identificacion de la clase (id). 
    * <p>
    * @return el id de la clase.
    *
    */
    public int getIdClase() {
        return idClase;
    }
    
}
