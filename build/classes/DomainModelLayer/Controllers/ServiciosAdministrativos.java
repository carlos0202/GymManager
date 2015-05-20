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
* Clase controlador que sirve de fachada al subsistema de ServiciosAdministrativos. 
*/
public class ServiciosAdministrativos {
    
    /**
    * Recibe los datos de una clase que sera creada en el gimnasio. 
    * <p>
    * @param nombre      el nombre de la clase a crear.
    * @param descripcion la la descripcion de la clase a crear.
    * @param precio      el costo de inscripcion de la clase a crear.
    *
    */
    public static void crearClase(String nombre, String descripcion, double precio) throws Exception{
        Clase clase = new Clase(nombre, descripcion, precio);
        
        GymManagerDAO.getInstance().crearClase(clase);
    }
    
    /**
    * Recibe los datos de una seccion de clase que va a ser creada en el
    * gimnasio.
    * <p>
    * @param nombre     el nombre de la seccion de clases a crer.
    * @param capacidad  la capacidad maxima de socios que se podran inscribir.
    * @param horaI      la hora a la que va a iniciar la seccion de clases.
    * @param horaF      la hora a la que va a finalizar la seccion de clases.
    * @param dia        el dia en que va a impartirse la seccion de clases.
    * @param clase      la clase de la cual se esta creando la seccion.
    * @param salon      el salon en que se impartira la seccion de clases.
    * @param instructor el instructor que va a impartir la seccion de clases.
    * @param fechaI     la fecha en que va a empezar a impartirse la seccion.
    * @param fechaF     la fecha en que va a finalizar de impartirse la seccion.
    *
    */
    public static void crearSeccion(String nombre, int capacidad, int horaI, int horaF, int dia, Clase clase,
            Salon salon, Instructor instructor, Date fechaI, Date fechaF) throws Exception{
        
        Seccion seccion = new Seccion(nombre, clase, horaI, horaF, dia, salon,
                capacidad, instructor,  fechaI, fechaF
        );
        
        for(Seccion secc: salon.getSecciones()){
            secc.compararHorario(seccion);
        }
        
        for(Seccion sec: instructor.getSecciones()){
            sec.compararInstructor(seccion);
        }
        
        GymManagerDAO.getInstance().registrarSeccion(seccion);
    }
    
    /**
    * Envia a la base de datos los parametros de inicio de sesion de un empleado. 
    * <p>
    * @param userName El nombre de usuario del empleado.
    * @param password El password el empleado.
    *
    * @return los datos basicos del empleado que intenta iniciar sesion.
    */
    public static PersAdministrativo registrarLogin(String userName, String password) throws Exception{
        return GymManagerDAO.getInstance().buscarEmpleado(userName, password, "Gerente");
    }
    
    /**
    * Recibe los datos de una membresia que sera creada en el gimnasio. 
    * <p>
    * @param nombre      el nombre de la membresia a crear.
    * @param descripcion la la descripcion de la membresia a crear.
    * @param tipo        el tipo de pago de membresia que sera creada.
    * @param precio      el costo de inscripcion de la membresia a crear.
    *
    */
    public static void crearMembresia(String nombre, String descripcion, String tipo, double precio) throws Exception{
        Membresia membresia = new Membresia(nombre, descripcion, tipo, precio);
        
        GymManagerDAO.getInstance().registrarMembresia(membresia);
    }
    
    /**
    * Busca una clase en la base de datos basado en su identificacion (id)
    * <p>
    * @param idClase el dato actual por el que va a ser buscado el empleado.
    * 
    * @return los datos del socio si ha sido encontrado.
    */
    public static Clase buscarClase(int idClase) throws Exception{
        return GymManagerDAO.getInstance().buscarClase(idClase);
    }
    
    /**
    * Busca los datos de clases basado en uno de sus datos basicos. 
    * <p>
    * @param filtro el dato actual por el que va a ser buscado la clase.
    * @param campo  el tipo de datos por el cual va a ser buscado.
    *
    * @return los datos basicos de la clases que coincidad con la busqueda.
    */
    public static Vector<Clase> buscarClases(String filtro, int campo) throws Exception{
        return GymManagerDAO.getInstance().buscarClase(filtro, campo);
    }
    
    /**
    * Busca los datos de las clases que se imparten en el gimnasio. 
    * <p>
    *
    * @return los datos de las clases que se imparten.
    */
    public static Vector<Clase> buscarClases() throws Exception{
        return GymManagerDAO.getInstance().buscarClases();
    }
    
    /**
    * Busca un instructor en la base de datos basado en su identificacion (id)
    * <p>
    * @param idInstructor el dato actual por el que va a ser buscado el empleado.
    * 
    * @return los datos del instructor si ha sido encontrado.
    */
    public static Instructor buscarInstructor(int idInstructor) throws Exception{
        return (Instructor)GymManagerDAO.getInstance().buscarEmpleado(idInstructor, "Instructor");
    }
    
    /**
    * Busca los datos de los salones donde se imparten las clases en el gimnasio. 
    * <p>
    *
    * @return los datos de los salones de clases.
    */
    public static Vector<Salon> buscarSalones() throws Exception{
        return GymManagerDAO.getInstance().buscarSalones();
    }

    /**
    * Busca una seccion de clases basado en su identificacion (id)
    * <p>
    * @param idSeccion   el dato actual por el que va a ser buscada la seccion.
    * 
    * @return los datos del instructor si ha sido encontrado.
    */
    public static Seccion buscarSeccion(int idSeccion) throws Exception{
        return GymManagerDAO.getInstance().buscarSecciones(1, idSeccion).get(0);
    }
    
    /**
    * Envia los datos de una seccion de clases para actualizar sus datos.
    * <p>
    * @param seccion  los datos de la seccion que van a ser actualizados.
    * 
    */
    public static void actualizarSeccion(Seccion seccion) throws Exception{
        for(Seccion secc: seccion.getSalon().getSecciones()){
            secc.compararHorario(seccion);
        }
        
        for(Seccion sec: seccion.getInstrctAsignado().getSecciones()){
            sec.compararInstructor(seccion);
        }
        
        GymManagerDAO.getInstance().actualizarSeccion(seccion);
    }
    
    /**
    * Envia los datos de una clase para actualizar sus datos.
    * <p>
    * @param clase los datos de la clase que va a ser actualizada.
    * 
    */
    public static void actualizarClase(Clase clase) throws Exception{
        GymManagerDAO.getInstance().actualizarclase(clase);
    }
    /**
    * Envia los datos de una membresia para actualizar sus datos.
    * <p>
    * @param membresia  los datos de la membresia que va a ser actualizada.
    * 
    */
    public static void actualizarMembresia(Membresia membresia) throws Exception{
        
        GymManagerDAO.getInstance().actualizarMembresia(membresia);
    }
    
    /**
    * Busca los datos de clases basado en uno de sus datos basicos. 
    * <p>
    * @param filtro        el dato actual por el que va a ser buscado la clase.
    * @param campoBusqueda el tipo de datos por el cual va a ser buscado.
    *
    * @return los datos basicos de la clases que coincidad con la busqueda.
    */
    public static Vector<Seccion> buscarSecciones(int campoBusqueda, String filtro) throws Exception{
        
        return GymManagerDAO.getInstance().buscarSecciones(campoBusqueda, filtro, false);
    }
    
    /**
    * Busca los datos de las membresias del gimnasio. 
    * <p>
    *
    * @return los datos basicos de las membresias del gimnasio.
    */
    public static Vector<Membresia> buscarMembresias() throws Exception{
        return GymManagerDAO.getInstance().buscarMembresias();
    }
    
    /**
    * Busca los datos de una membresia basado en su identificacion personal (id). 
    * <p>
    * @param idMembresia el id de la membresia a buscar.
    *
    * @return los datos basicos de la membresia.
    */
    public static Membresia buscarMembresia(int idMembresia) throws Exception{
        
        return GymManagerDAO.getInstance().buscarMembresia(idMembresia);
    }
    
    /**
    * Recibe los datos de una seccion que va a ser eliminada. 
    * <p>
    * @param seccion los datos de la seccion que va a ser eliminada.
    *
    */
    public static void eliminarSeccion(Seccion seccion) throws Exception{
        GymManagerDAO.getInstance().eliminarSeccion(seccion.getIdSeccion());
    }
    
    /**
    * Recibe los datos de una clase que va a ser eliminada. 
    * <p>
    * @param clase los datos de la clase que va a ser eliminada.
    *
    */
    public static void eliminarClase(Clase clase) throws Exception{
        GymManagerDAO.getInstance().eliminarClase(clase);
    }
    
    /**
    * Recibe los datos de una membresia que va a ser eliminada. 
    * <p>
    * @param clase los datos de la membresia que va a ser eliminada.
    *
    */
    public static void eliminarMembresia(Membresia membresia) throws Exception{
        GymManagerDAO.getInstance().eliminarMembresia(membresia.getIdMembresia());
    }
    
}
