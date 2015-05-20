/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Controllers;

import DataAccessLayer.GymManagerDAO;
import DomainModelLayer.Entidades.PersAdministrativo;
import DomainModelLayer.Entidades.Seccion;
import DomainModelLayer.Entidades.Socio;
import java.util.Vector;

/**
* Clase controlador que sirve de fachada al subsistema de Servicios. 
*/
public class Servicios {
    
    /**
    * Busca en la base de datos todas las secciones que se correspondan al
    * filtro y campo de busqueda especificados como parametro. 
    * <p>
    * @param filtro         el dato actual por el que van a ser buscadas las secciones.
    * @param campoBusqueda  el tipo de datos por el cual va a ser buscado.
    *
    * @return los datos basicos de las secciones que coinciden con la busqueda.
    */
    public static Vector<Seccion> buscarSecciones(int campoBusqueda, String filtro) throws Exception{
        
        return GymManagerDAO.getInstance().buscarSecciones(campoBusqueda, filtro, true);
    }
    
    /**
    * Envia  a el data access los parametros de inicio de sesion de un empleado. 
    * <p>
    * @param userName El nombre de usuario del empleado.
    * @param password El password el empleado.
    *
    * @return los datos basicos del empleado que intenta iniciar sesion.
    */
    public static PersAdministrativo registrarLogin(String userName, String password) throws Exception{
        return GymManagerDAO.getInstance().buscarEmpleado(userName, password, "Recepcionista");
    }
    
    /**
    * Busca los datos de un empleado basado en uno de sus datos personales. 
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
    * Envia los datos del socio seleccionado y la seccion seleccionada a la
    * base de datos para su inscripcion en la seccion seleccionada. 
    * <p>
    * @param socio    el dato actual por el que va a ser buscado el empleado.
    * @param seccion  el tipo de datos por el cual va a ser buscado.
    *
    * @return los datos basicos del empleado que se esta buscando.
    */
    public static void inscribirSeccion(Socio socio, Seccion seccion) throws Exception{
        GymManagerDAO.getInstance().registrarInscripcion(socio.getIdSocio(), seccion.getIdSeccion());
    }
    
    /**
    * Envia los datos del socio seleccionado y la seccion seleccionada a la
    * base de datos para retirar al socio de la seccion seleccionada. 
    * <p>
    * @param socio    el dato actual por el que va a ser buscado el empleado.
    * @param seccion  el tipo de datos por el cual va a ser buscado.
    *
    * @return los datos basicos del empleado que se esta buscando.
    */
    public static void retirarSocio(Socio socio, Seccion seccion) throws Exception{
        GymManagerDAO.getInstance().retirarSocio(socio.getIdSocio(), seccion.getIdSeccion());
    }
    
    /**
    * Registra la asistencia de un socio del gimnasio basado en la
    * identificacion del socio (id). 
    * <p>
    * @param socio    el dato actual por el que va a ser buscado el empleado.
    *
    */
    public static void registrarAsistencia(int idSocio) throws Exception{
        GymManagerDAO.getInstance().registrarAsistencia(idSocio);
    }
}
