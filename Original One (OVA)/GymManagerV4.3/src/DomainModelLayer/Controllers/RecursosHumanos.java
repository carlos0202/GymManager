/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Controllers;

import DataAccessLayer.GymManagerDAO;
import java.util.Date;
import java.util.Vector;
import DomainModelLayer.Entidades.*;

/**
* Clase controlador que sirve de fachada al subsistema de Recursos Humanos. 
*/
public class RecursosHumanos{

    /**
    * Recibe los datos de un empleado para ser almacenados en la base de datos
    * del sistema. 
    * <p>
    * @param cedula          la cedula del empleado.
    * @param nombre          el nombre del empleado.
    * @param apellido        el apellido del empleado.
    * @param fechaNacimiento la fecha de nacimiento del empleado.
    * @param sexo            el sexo del empleado.
    * @param telefonos       los telefonos a registrar del empleado.
    * @param puesto          el puesto que va a ocupar el empleado.
    * @param sueldo          el sueldo que va a devengar el empleado.
    * @param municipio       el municipio donde reside el empleado.
    * @param sector          el sector donde reside el empleado.
    * @param calle           la calle donde reside el emepleado.
    * @param numero          el numero de la casa donde reside el empleado.
    * @param userName        el nombre de usuario para el inicio de sesion
    *                        del empleado al sistema.
    * @param password        los datos para el inicio de sesion del empleado
    *                        al sistema.
    */
    public static void registrarEmpleado(String cedula, String nombre, String apellido,
                                          Date fechaNacimiento, char sexo, Vector<Telefono> telefonos,
                                          String puesto, double sueldo, String municipio,
                                          String sector, String calle,int numero,
                                          String userName, String password) throws Exception{
        
        Direccion direccion = new Direccion(municipio, sector, calle, numero);
        
         Empleado empleado = new PersAdministrativo(cedula, nombre, apellido, sexo,
                 fechaNacimiento, telefonos, direccion, puesto, sueldo, userName,
                 password
         );
        
        GymManagerDAO.getInstance().registrarEmpleado(empleado);
    
    }

    /**
    * Recibe los datos de un instructor para ser almacenados en la base de datos
    * del sistema. 
    * <p>
    * @param cedula          la cedula del instructor.
    * @param nombre          el nombre del instructor.
    * @param apellido        el apellido del instructor.
    * @param fechaNacimiento la fecha de nacimiento del instructor.
    * @param sexo            el sexo del instructor.
    * @param telefonos       los telefonos a registrar del instructor.
    * @param puesto          el puesto que va a ocupar el instructor.
    * @param sueldo          el sueldo que va a devengar el instructor.
    * @param municipio       el municipio donde reside el instructor.
    * @param sector          el sector donde reside el instructor.
    * @param calle           la calle donde reside el instructor.
    * @param numero          el numero de la casa donde reside el instructor.
    * @param area            el area en la que este instructor se especializa
    */
    public static void registrarInstructor(String cedula, String nombre, String apellido,
                                          Date fechaNacimiento, char sexo, Vector<Telefono> telefonos,
                                          String puesto, double sueldo,
                                          String municipio, String sector, String calle,
                                          int numero, String area) throws Exception{
        
        Direccion direccion = new Direccion(municipio, sector, calle, numero);
        Instructor empleado = new Instructor(cedula, nombre, apellido, sexo, fechaNacimiento,
                                             telefonos, direccion, puesto, sueldo, area);
        
        GymManagerDAO.getInstance().registrarEmpleado(empleado);
    
    }
    
    /**
    * Envia  a la base de datos los parametros de inicio de sesion de un empleado. 
    * <p>
    * @param userName El nombre de usuario del empleado.
    * @param password El password el empleado.
    *
    * @return los datos basicos del empleado que intenta iniciar sesion.
    */
    public static PersAdministrativo registrarLogin(String userName, String password) throws Exception{
        return GymManagerDAO.getInstance().buscarEmpleado(userName, password, "Emp. Recursos Humanos");
    }

    /**
    * Busca los datos de un empleado basado en uno de sus datos personales. 
    * <p>
    * @param filtro         el dato actual por el que va a ser buscado el empleado.
    * @param campoBusqueda  el tipo de datos por el cual va a ser buscado.
    *
    * @return los datos basicos del empleado que se esta buscando.
    */
    public static Vector<Empleado> buscarEmpleado(String filtro, int campoBusqueda) throws Exception{
        return GymManagerDAO.getInstance().buscarEmpleado(filtro, campoBusqueda);
    }
    
    /**
    * Busca los datos de un empleado basado en su identificacion (id)
    * y su puesto en la empresa. 
    * <p>
    * El puesto puede ser Instructor o Emp. Administrativo.
    * 
    * @param idEmpleado el id del empleado a buscar.
    * @param tipo       el puesto que ocupa el empleado en la empresa.
    *
    * @return los datos basicos del empleado que se esta buscando.
    */
    public static Empleado buscarEmpleado(int idEmpleado, String tipo) throws Exception{
        return GymManagerDAO.getInstance().buscarEmpleado(idEmpleado, tipo);
    }
    
    /**
    * Actualiza los datos de un empleado en la base de datos usando los cambios
    * presentes en el objeto emp recibido como parametro. 
    * <p>
    * @param emp los datos del empleado.
    *
    */
    public static void actualizarEmpleado(Empleado emp) throws Exception{
       
        GymManagerDAO.getInstance().actualizarEmpleado(emp);
    }
    
    /**
    * Recibe los datos del empleado a cancelar. 
    * <p>
    * @param emp los datos del empleado a cancelar.
    *
    */
    public static void cancelarEmpleado(Empleado emp) throws Exception{
        GymManagerDAO.getInstance().cancelarEmpleado(emp);
    }
    
    
}
