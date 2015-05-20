/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Entidades;

import java.util.Date;
import java.util.Vector;

/**
* Clase que almacena los datos de una Persona. 
*/
public class Persona {
    private int idPersona;
    private String cedula;
    private String nombre;
    private String apellido;
    private char sexo;
    private Date fechaNacimiento;
    private Vector<Telefono> telefonos;
    private Direccion direccion;

    /**
    * Instancia una nueva Persona usando los parametros recibidos. 
    * <p>
    * @param cedula          la cedula del instructor.
    * @param nombre          el nombre del instructor.
    * @param apellido        el apellido del instructor.
    * @param sexo            el sexo del instructor.
    * @param fechaNacimiento la fecha de nacimiento del instructor.
    * @param telefonos       los telefonos registrados a ese instructor.
    * @param direccion       la direccion donde reside el instructor.
    * 
    */
    public Persona(String cedula, String nombre, String apellido, char sexo,
                   Date fechaNacimiento, Vector<Telefono> telefonos, Direccion direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.telefonos = telefonos;
        this.direccion = direccion;
    }
    
    /**
    * Instancia una nueva Persona usando los parametros recibidos. 
    * <p>
    * @param idPersona       el identificador unico de persona (id).
    * @param cedula          la cedula del instructor.
    * @param nombre          el nombre del instructor.
    * @param apellido        el apellido del instructor.
    * @param sexo            el sexo del instructor.
    * @param fechaNacimiento la fecha de nacimiento del instructor.
    * @param telefonos       los telefonos registrados a ese instructor.
    * @param direccion       la direccion donde reside el instructor.
    * 
    */
    public Persona(int idPersona, String cedula, String nombre, String apellido, char sexo,
                   Date fechaNacimiento, Vector<Telefono> telefonos, Direccion direccion) {
        this.idPersona = idPersona;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.telefonos = telefonos;
        this.direccion = direccion;
    }

    /**
    * asigna un nuevo telefono a la lista de telefonos de esta persona. 
    * <p>
    * @param telefono el nuevo telefono a asignar a esta persona.
    *
    */
    public void setTelefono(Telefono telefono){
        this.telefonos.add(telefono);
    }
    
    /**
    * obtiene los datos de un telefono basado en su identificador unico (id),
    * de los telefonos asignados a esta persona. 
    * <p>
    * @return el telefono de la persona que coincida con el id.
    *
    */ 
    public Telefono getTelefono(int idTelefono){
        
        for( int i = 0; i < telefonos.size(); i++ ){
            if( telefonos.elementAt(i).getIdTelefono() == idTelefono )
                return telefonos.elementAt(i);
        }
        return null;
    }
    
    /**
    * obtiene los datos del apellido de la persona. 
    * <p>
    * @return  el apelido de la persona.
    *
    */ 
    public String getApellido() {
        return apellido;
    }

    /**
    * establece los datos del apellido de la persona. 
    * <p>
    * @param apellido el apellido de la persona.
    *
    */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
    * obtiene los datos de la cedula de la persona. 
    * <p>
    * @return  la cedula de la person.
    *
    */ 
    public String getCedula() {
        return cedula;
    }

    /**
    * establece los datos de la cedula de la persona. 
    * <p>
    * @param cedula la cedula de empleado.
    *
    */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
    * obtiene los datos de la direccion de la  persona. 
    * <p>
    * @return  la direccion donde reside el empleado.
    *
    */ 
    public Direccion getDireccion() {
        return direccion;
    }

    /**
    * establece los datos de la contraseña de la direcion de la persona. 
    * <p>
    * @param direccion la direccion de la persona.
    *
    */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
    * obtiene los datos de la fecha de nacimiento de la persona. 
    * <p>
    * @return  la fecha de nacimiento de la persona.
    *
    */ 
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
    * establece los datos de la fecha de nacimiento de la persona. 
    * <p>
    * @param fechaNacimiento la fecha de nacimiento de la persona.
    *
    */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
    * obtiene los datos del nombre de la persona. 
    * <p>
    * @return  el nombre de la persona.
    *
    */ 
    public String getNombre() {
        return nombre;
    }

    /**
    * establece los datos del nombre de la persona. 
    * <p>
    * @param nombre el nombre de la persona.
    *
    */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
    * obtiene los datos del sexo de la persona. 
    * <p>
    * @return  la contraseña del empleado.
    *
    */ 
    public char getSexo() {
        return sexo;
    }

    /**
    * establece los datos del sexo de la persona. 
    * <p>
    * @param sexo el sexo de la persona.
    *
    */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
    * obtiene los telefonos registrados de esta persona. 
    * <p>
    * @return  los telefonos asignados a esta persona.
    *
    */ 
    public Vector<Telefono> getTelefonos() {
        return telefonos;
    }

    /**
    * establece los telefonos asignados a este empleado. 
    * <p>
    * @param telefonos los telefonos a asignar a esta persona.
    *
    */
    public void setTelefonos(Vector<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    /**
    * obtiene los datos de la identificacion unica de la persona (id). 
    * <p>
    * @return  el id de la persona.
    *
    */ 
    public int getIdPersona() {
        return idPersona;
    }
    
}
