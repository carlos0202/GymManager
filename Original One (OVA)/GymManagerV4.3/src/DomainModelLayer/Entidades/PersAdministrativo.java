/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Entidades;

import java.util.Date;
import java.util.Vector;

/**
* Clase que almacena los datos de un Empleado Administrativo del gimnasio. 
*/
public class PersAdministrativo extends Empleado{
    private String userName;
    private String password;

    /**
    * Instancia un nuevo Empleado Administrativo usando los parametros recibidos. 
    * <p>
    * @param idPersona       el identificador unico de persona (id).
    * @param cedula          la cedula del instructor.
    * @param nombre          el nombre del instructor.
    * @param apellido        el apellido del instructor.
    * @param sexo            el sexo del instructor.
    * @param fechaNacimiento la fecha de nacimiento del instructor.
    * @param telefonos       los telefonos registrados a ese instructor.
    * @param direccion       la direccion donde reside el instructor.
    * @param idEmpleado      el identificador unico de empleado (id).
    * @param puesto          el puesto que ocupa el instructor.
    * @param salario         el salario que devenga el instructor.
    * @param fechaIngreso    la fecha en que este empleaado ingreso a la empresa.
    * @param username        el nombre de usuario para el inicio de sesion;
    * @param password        la contraseña para el inicio de sesion de este empleado.
    * 
    */
    public PersAdministrativo(int idPersona, String cedula, String nombre, String apellido, char sexo,
            Date fechaNacimiento, Vector<Telefono> telefonos, Direccion direccion,
            int idEmpleado, String puesto, double salario, Date fechaIngreso,
            String userName, String password) {
        
        super(idPersona, cedula, nombre, apellido, sexo, fechaNacimiento, telefonos,
                direccion, idEmpleado, puesto, salario, fechaIngreso
        );
        this.userName = userName;
        this.password = password;
    }

    /**
    * Instancia un nuevo Empleado Administrativo usando los parametros recibidos. 
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
    * @param fechaIngreso    la fecha en que este empleaado ingreso a la empresa.
    * @param username        el nombre de usuario para el inicio de sesion;
    * @param password        la contraseña para el inicio de sesion de este empleado.
    * 
    */
    public PersAdministrativo(String cedula, String nombre, String apellido, char sexo, Date fechaNacimiento,
            Vector<Telefono> telefonos, Direccion direccion, String puesto, double salario,
            String userName, String password) {
        
        super(cedula, nombre, apellido, sexo, fechaNacimiento, telefonos, direccion, puesto, salario);
        this.userName = userName;
        this.password = password;
    }

    /**
    * obtiene los datos de la contraseña del empleado para su inicio de sesion. 
    * <p>
    * @return  la contraseña del empleado.
    *
    */ 
    public String getPassword() {
        return password;
    }

    /**
    * establece los datos de la contraseña de inicio de sesion del empleado. 
    * <p>
    * @param password la contraseña del empleado.
    *
    */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
    * obtiene los datos del nombre de usuario del inicio de sesion del empleado. 
    * <p>
    * @return  el nombre de usuario de inicio de sesion.
    *
    */ 
    public String getUserName() {
        return userName;
    }

    /**
    * establece los datos del nombre de usuario de inicio de sesion del empleado. 
    * <p>
    * @param username el nombre de usuario a establecer.
    *
    */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
