/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Entidades;

import java.util.Date;
import java.util.Vector;

/**
* Clase que almacena los datos de un Instructor del gimnasio. 
*/
public class Instructor extends Empleado{
    private int idInstructor;
    private String especialidad;
    private Vector<Seccion> secciones;

    /**
    * Instancia un nuevo Instructor usando los parametros recibidos. 
    * <p>
    * @param cedula          la cedula del instructor.
    * @param nombre          el nombre del instructor.
    * @param apellido        el apellido del instructor.
    * @param sexo            el sexo del instructor.
    * @param fechaNacimiento la fecha de nacimiento del instructor.
    * @param telefonos       los telefonos registrados a ese instructor.
    * @param direccion       la direccion donde reside el instructor.
    * @param puesto          el puesto que ocupa el instructor.
    * @param salario         el salario que devenga el instructor.
    * @param especialidad    el area en que se especializa este instructor;
    * 
    */
    public Instructor(String cedula, String nombre, String apellido, char sexo,
            Date fechaNacimiento, Vector<Telefono> telefonos, Direccion direccion,
            String puesto, double salario, String especialidad) {
        super(cedula, nombre, apellido, sexo, fechaNacimiento, telefonos, direccion, puesto, salario);
        this.especialidad = especialidad;
        this.secciones = new Vector<Seccion>();
    }
    
    /**
    * Instancia un nuevo Empleado usando los parametros recibidos. 
    * <p>
    * @param idPersona       el identificar uncio de persona (id).
    * @param cedula          la cedula del instructor.
    * @param nombre          el nombre del instructor.
    * @param apellido        el apellido del instructor.
    * @param sexo            el sexo del instructor.
    * @param fechaNacimiento la fecha de nacimiento del instructor.
    * @param telefonos       los telefonos registrados a ese instructor.
    * @param direccion       la direccion donde reside el instructor.
    * @param idEmpleado      el identificador unico del empleado (id).
    * @param puesto          el puesto que ocupa el instructor.
    * @param salario         el salario que devenga el instructor.
    * @param fechaIngreso    la fecha en la cual ingreso el instructor.
    * @param idInstructor    el identificador unico del instructor (id).
    * @param especialidad    la especialidad del instructor.
    * 
    */
    public Instructor(int idPersona, String cedula, String nombre, String apellido, char sexo,
            Date fechaNacimiento, Vector<Telefono> telefonos, Direccion direccion,
            int idEmpleado, String puesto, double salario, Date fechaIngreso, int idInstructor, String especialidad){
        super(idPersona, cedula, nombre, apellido, sexo, fechaNacimiento, telefonos,
                direccion, idEmpleado, puesto, salario, fechaIngreso
        );
        this.idInstructor = idInstructor;
        this.especialidad = especialidad;
    }

    /**
    * obtiene los datos de la especialidad del instructor. 
    * <p>
    * @return los datos de la especialidad del instructor.
    *
    */    
    public String getEspecialidad() {
        return especialidad;
    }

    /**
    * establece los datos de la especialidad del instructor. 
    * <p>
    * @param especialidad la especialidad del instructor.
    *
    */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
    * obtiene los datos de las secciones de clases impartidas por el instructor. 
    * <p>
    * @return los datos de las secciones que imparte este instructor.
    *
    */
    public Vector<Seccion> getSecciones() {
        return secciones;
    }

    /**
    * establece los datos de las secciones de clases impartidas por
    * este instructor. 
    * <p>
    * @param secciones las secciones de clases del instructor.
    *
    */
    public void setSecciones(Vector<Seccion> secciones) {
        this.secciones = secciones;
    }

    /**
    * obtiene los datos de la identificacion unica del instructor (id). 
    * <p>
    * @return los datos del id del instructor.
    *
    */
    public int getIdInstructor() {
        return idInstructor;
    }
    
}
