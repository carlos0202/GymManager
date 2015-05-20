/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Entidades;

import java.util.Vector;

/**
* Clase que almacena los datos de un Salon donde se imparten clases. 
*/
public class Salon {
    private int idSalon;
    private String nombre;
    private Vector<Seccion> secciones;

    /**
    * Instancia un nuevo Salon usando los parametros recibidos. 
    * <p>
    * @param idSalon   el identicador unico del salon (id).
    * @param nombre    el nombre del salon.
    * @param secciones las secciones de clase que se imparten en este salon.
    * 
    */
    public Salon(int idSalon, String nombre, Vector<Seccion> secciones) {
        this.idSalon = idSalon;
        this.nombre = nombre;
        this.secciones = secciones;
    }

    /**
    * Instancia un nuevo Salon usando los parametros recibidos. 
    * <p>
    * @param idSalon   el identicador unico del salon (id).
    * @param nombre    el nombre del salon.
    * 
    */
    public Salon(int idSalon, String nombre) {
        this.idSalon = idSalon;
        this.nombre = nombre;
    }
    
    /**
    * Instancia un nuevo Salon usando los parametros recibidos. 
    * <p>
    * @param nombre    el nombre del salon.
    * 
    */
    public Salon(String nombre) {
        this.nombre = nombre;
    }

    /**
    * obtiene los datos del nombre del salon. 
    * <p>
    * @return  el nombre del salon.
    *
    */ 
    public String getNombre() {
        return nombre;
    }

    /**
    * establece el nombre de este salon de clases. 
    * <p>
    * @param nombre el nombre que sera asignado a este salon de clase.
    *
    */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
    * obtiene los datos del apellido de la persona. 
    * <p>
    * @return  el apelido de la persona.
    *
    */ 
    public Vector<Seccion> getSecciones() {
        return secciones;
    }

    /**
    * establece los datos de las secciones de clase a impartir en este salon. 
    * <p>
    * @param secciones la lista de secciones a registrar a este salon de clase.
    *
    */
    public void setSecciones(Vector<Seccion> secciones) {
        this.secciones = secciones;
    }

    /**
    * obtiene el identificador unico del salon (id). 
    * <p>
    * @return  el id del salon.
    *
    */ 
    public int getIdSalon() {
        return idSalon;
    }
    
    /**
    * Agrega una seccion a la lista de secciones de clase que se imparten
    * en este salon. 
    * <p>
    * @param seccion la seccion que sera añadida a la lista de clases del salon.
    *
    */ 
    public void addSeccion(Seccion seccion){
        
        
        this.secciones.add(seccion);
    }
    
    /**
    * Busca una seccion basado en su identificacion unica (id) de
    * la lista de secciones de clase de este salon. 
    * <p>
    * @param idSeccion el identificador unico de la seccion a buscar.
    * @return  el apelido de la persona.
    *
    */ 
    public boolean findSeccion(int idSeccion){
        
        for( int i = 0; i < secciones.size(); i++ ){
            if( secciones.elementAt(i).getIdSeccion() == idSeccion)
                return true;
        }
        return false;
    }
    
}
