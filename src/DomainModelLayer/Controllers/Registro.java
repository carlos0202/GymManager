/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Controllers;

import DataAccessLayer.GymManagerDAO;
import DomainModelLayer.Entidades.*;
import java.util.Date;
import java.util.Vector;

/**
* Clase controlador que sirve de fachada al subsistema de Registro. 
*/
public class Registro {
    
    /**
    * Registra los datos de un nuevo socio en la base de datos del sistema. 
    * <p>
    * @param cedula          la cedula del empleado.
    * @param nombre          el nombre del empleado.
    * @param apellido        el apellido del empleado.
    * @param fechaNacimiento la fecha de nacimiento del empleado.
    * @param sexo            el sexo del empleado.
    * @param telefonos       los telefonos a registrar del empleado.
    * @param municipio       el municipio donde reside el empleado.
    * @param sector          el sector donde reside el empleado.
    * @param calle           la calle donde reside el emepleado.
    * @param numero          el numero de la casa donde reside el empleado.
    * @param peso            el peso en libras del empleado.
    * @param estatura        la altura del empleado en pies.
    * @param membresia       el tipo de membresia seleccionado por el socio.
    * 
    * @return los datos basicos del empleado que intenta iniciar sesion.
    */
    public static void registrarSocio(String cedula, String nombre, String apellido, char sexo,
                 Date fechaNacimiento, Vector<Telefono> telefonos,
                 String municipio, String sector, String calle, int numero,
                 double peso, double estatura, Membresia membresia) throws Exception{
        
        Direccion direccion = new Direccion(municipio,
                sector,
                calle,
                numero
        );

        Socio socio = new Socio(cedula, nombre, apellido, sexo, fechaNacimiento,
                telefonos, direccion, peso, estatura, membresia
        );
        
        GymManagerDAO.getInstance().registrarSocio(socio);
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
        return GymManagerDAO.getInstance().buscarEmpleado(userName, password, "Emp. Registro");
    }
    
    /**
    * Busca los datos de un socio basado en uno de sus datos personales. 
    * <p>
    * @param filtro         el dato actual por el que va a ser buscado el socio.
    * @param campoBusqueda  el tipo de datos por el cual va a ser buscado.
    *
    * @return los datos basicos del socio que se esta buscando.
    */
    public static Vector<Socio> buscarSocio(String filtro, int campoBusqueda) throws Exception{
        return GymManagerDAO.getInstance().buscarSocios(filtro, campoBusqueda);
    }
    
    /**
    * Busca los datos de un socio basado en su identificacion (id). 
    * <p>
    * @param idSocio el id del socio a buscar.
    *
    * @return los datos basicos del socio que se esta buscando.
    */
    public static Socio buscarSocio(int idSocio) throws Exception{
        
        return GymManagerDAO.getInstance().buscarSocio(idSocio);
    }
    
    /**
    * Actualiza los datos de un socio en la base de datos usando los cambios
    * presentes en el objeto socio recibido como parametro. 
    * <p>
    * @param socio los datos del socio.
    *
    */
    public static void actualizarSocio(Socio socio) throws Exception{
        GymManagerDAO.getInstance().actualizarSocio(socio);
    }
    
    /**
    * Busca la lista de todas las membresias disponibles para inscripcion
    * en el gimnasio. 
    * <p>
    *@return las membresias disponibles en el gimnasio.
    * 
    */
    public static Vector<Membresia> buscarMembresias() throws Exception{
        return GymManagerDAO.getInstance().buscarMembresias();
    }
    
    /**
    * Recibe los datos del socio que sera dado de baja. 
    * <p>
    * @param socio los datos del socio a eliminar.
    *
    */
    public static void eliminarSocio(Socio socio) throws Exception{
        GymManagerDAO.getInstance().eliminarSocio(socio);
    }
}
