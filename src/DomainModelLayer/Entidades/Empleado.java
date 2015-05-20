/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Entidades;

import java.util.Date;
import java.util.Vector;

/**
* Clase que almacena los datos de un Empleado. 
*/
public class Empleado extends Persona{
    private int idEmpleado;
    private String puesto;
    private double salario;
    private Date fechaIngreso;

    
    /**
    * Instancia un nuevo Empleado usando los parametros recibidos. 
    * <p>
    * @param idPersona       el identificar uncio de persona (id).
    * @param cedula          la cedula del empleado.
    * @param nombre          el nombre del empleado.
    * @param apellido        el apellido del empleado.
    * @param sexo            el sexo del empleado.
    * @param fechaNacimiento la fecha de nacimiento del empleado.
    * @param telefonos       los telefonos registrados a ese empleado.
    * @param direccion       la direccion donde reside el empleado.
    * @param idEmpleado      el identificador unico del empleado (id).
    * @param puesto          el puesto que ocupa el empleado.
    * @param salario         el salario que devenga el empleado..
    * @param fechaIngreso    la fecha en la cual ingreso el empleado.
    * 
    */
    public Empleado(int idPersona, String cedula, String nombre, String apellido, char sexo,
                    Date fechaNacimiento, Vector<Telefono> telefonos, Direccion direccion,
                    int idEmpleado, String puesto, double salario, Date fechaIngreso) {
        super(idPersona, cedula, nombre, apellido, sexo, fechaNacimiento, telefonos, direccion);
        this.idEmpleado = idEmpleado;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
    }
    
    /**
    * Instancia un nuevo Empleado usando los parametros recibidos. 
    * <p>
    * @param cedula          la cedula del empleado.
    * @param nombre          el nombre del empleado.
    * @param apellido        el apellido del empleado.
    * @param sexo            el sexo del empleado.
    * @param fechaNacimiento la fecha de nacimiento del empleado.
    * @param telefonos       los telefonos registrados a ese empleado.
    * @param direccion       la direccion donde reside el empleado.
    * @param puesto          el puesto que ocupa el empleado.
    * @param salario         el salario que devenga el empleado..
    * 
    */
    public Empleado(String cedula, String nombre, String apellido, char sexo,
                    Date fechaNacimiento, Vector<Telefono> telefonos, Direccion direccion,
                    String puesto, double salario) {
        super(cedula, nombre, apellido, sexo, fechaNacimiento, telefonos, direccion);
        this.puesto = puesto;
        this.salario = salario;
    }

    /**
    * obtiene los datos de la fecha de ingreso del empleado. 
    * <p>
    * @return los datos de la fecha de ingreso.
    *
    */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /**
    * establece los datos de la fecha de ingreso del empleado. 
    * <p>
    * @param fechaIngreso la fecha en que ingreso el empleado.
    *
    */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
    * obtiene los datos del puesto que ocupa el empleado. 
    * <p>
    * @return los datos del puesto del empleado.
    *
    */
    public String getPuesto() {
        return puesto;
    }

    /**
    * establece los datos del puesto del empleado. 
    * <p>
    * @param puesto el puesto a ocupar por el empleado.
    *
    */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
    * obtiene los datos del salario que devenga el empleado. 
    * <p>
    * @return los datos del salario del empleado.
    *
    */
    public double getSalario() {
        return salario;
    }

    /**
    * establece los datos del salario del empleado. 
    * <p>
    * @param salario el salario devengado por el empleado.
    *
    */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
    * obtiene los datos del identificador unico del empleado (id). 
    * <p>
    * @return los datos del id del empleado.
    *
    */
    public int getIdEmpleado() {
        return idEmpleado;
    }
    
}
