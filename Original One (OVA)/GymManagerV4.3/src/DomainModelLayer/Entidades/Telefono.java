/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Entidades;

import java.util.Vector;

/**
* Clase que almacena los telefonos de las personas. 
*/
public class Telefono {
    private int idTelefono;
    private String numero;
    private String tipo;

    /**
    * Constructor publico de la clase Clase. 
    * <p>
    * @param numero    el numero del telefono.
    * @param tipo      el tipo de telefono.
    *
    */
    public Telefono(String numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }
    
    /**
    * Constructor publico de la clase Clase. 
    * <p>
    * @param idTelefono  el id del telefono.
    * @param numero      el numero del telefono.
    * @param tipo        el tipo de telefono.
    *
    */
    public Telefono(int idTelefono, String numero, String tipo) {
        this.idTelefono = idTelefono;
        this.numero = numero;
        this.tipo = tipo;
    }
        
    /**
    * obtiene los datos del numero de telefono. 
    * <p>
    * @return el numero de telefono.
    *
    */
    public String getNumero() {
        return numero;
    }

    /**
    * establece los datos del numero de telefono. 
    * <p>
    * @param  numero de telefono.
    *
    */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    /**
    * obtiene los datos del tipo de telefono. 
    * <p>
    * @return el tipo de telefono.
    *
    */
    public String getTipo() {
        return tipo;
    }
    
    /**
    * establece los datos del tipo de telefono. 
    * <p>
    * @param tipo de telefono.
    *
    */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
    * obtiene los datos del id del telefono. 
    * <p>
    * @return el id del telefono.
    *
    */
    public int getIdTelefono() {
        return idTelefono;
    }
     
    public Vector toVector(){
        Vector telefono = new Vector();
        
        telefono.add(String.valueOf(idTelefono));
        telefono.add(numero);
        telefono.add(tipo);
        
        return telefono;
    }
}
