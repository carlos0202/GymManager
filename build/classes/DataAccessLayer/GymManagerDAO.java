/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */

package DataAccessLayer;

import DomainModelLayer.Entidades.*;
import java.sql.*;
import java.util.Vector;

/**
* Clase que almacena los datos de la conexion a la base de datos
* y todos sus metodos . 
*/
public class GymManagerDAO {
    /**
    * Objeto que mantiene una instancia de la clase usando el patron
    * singleton. 
    */
    private static GymManagerDAO instance;
    /**
    * Objeto utilizado para mantener la consistencia de los datos
    * al trabajar con las transacciones. 
    */
    private Savepoint previousState;
    /**
    * Objeto generico que encapsula las operaciones basicas a 
    * realizar para la base datos. 
    */
    GenericDAO genericDAO;
      
    /**
    * Constructor privado para la clase GymManagerDAO.
    */
    private GymManagerDAO() throws Exception{
        genericDAO = new GenericDAO();
    }
        
    /**
    * Metodo para registrar un empleado en la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param emp un objeto de la clase Empleado.
    * @throws Exception lanza una excepcion si se produce un error al registrar
    *                   al empleado o alguno de sus datos basicos.
    */
    public void registrarEmpleado(Empleado emp) throws Exception{
        previousState = null;
        int idEmpleado;
        int idPersona;
        
        try{
            genericDAO.getConnection().setAutoCommit(false);

            previousState = genericDAO.getConnection().setSavepoint();

            int idDireccion = registrarDireccion(emp.getDireccion());
            idPersona = registrarPersona(emp, idDireccion);

            for(Telefono obj: emp.getTelefonos()) {
                registrarTelefono(obj, idPersona);
            }
            
            String insertEmpleado = "Insert into Empleados(puesto, salario, idPersona) "+
                                    "Values(?, ?, ?);";
            
            Object [] params = {
                emp.getPuesto(),
                emp.getSalario(),
                idPersona
            };
            
            idEmpleado = genericDAO.executeInsertIdent(insertEmpleado, params);
            
            if( emp instanceof PersAdministrativo){
                String insertPersAdm = "Insert Into PersAdministrativo "+
                                          "Values (?, ?, ?);";
                
                Object [] values = {
                  idEmpleado,
                  ((PersAdministrativo)emp).getUserName(),
                  ((PersAdministrativo)emp).getPassword()
                };
                
                genericDAO.executeUpdate(insertPersAdm, values);
            }
            else{
                String insertInstructor = "Insert into Instructores(especialidad, idEmpleado) "+
                                          "Values(?, ?);";
                
                Object [] values = {
                    ((Instructor)emp).getEspecialidad(),
                    idEmpleado
                };
                
                genericDAO.executeUpdate(insertInstructor, values);
            }
            
            genericDAO.getConnection().commit();
            genericDAO.closeConneciton();
        }catch( Exception ex){
            genericDAO.getConnection().rollback(previousState);
            throw ex;
        }
        finally{
            genericDAO.getConnection().setAutoCommit(true);
        }
    }
        
    /**
    * Metodo para registrar el telefono de una persona en la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param telefono un objeto de la clase Telefono.
    * @param idPersona el id de una persona.
    * @throws Exception lanza una excepcion si se produce un error al registrar
    *                   los datos del telefono.
    */
    private void registrarTelefono(Telefono telefono, int idPersona) throws Exception{
        String insertTelefono = "Insert into Telefonos(numero, tipo, idPersona) "+
                                 "values(?, ?, ?);";
        
        Object [] params = {
          telefono.getNumero(),
          telefono.getTipo(),
          idPersona
        };
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(insertTelefono, params);
        
    }
        
    /**
    * Metodo para registrar la direccion de una persona en la base de datos. 
    * <p>
    * @param direccion un objeto de la clase Direccion.
    * @return el id de la direccion.
    * @throws Exception lanza una excepcion si se produce un error al registrar
    *                   la direccion.
    * 
    */
    private int registrarDireccion(Direccion direccion) throws Exception{
        int idGenerado = 0;
        String insertDireccion = "Insert into Direcciones(municipio, sector, calle, numero) "+
                                 "values(?, ?, ?, ?);";

        Object [] params = {
          direccion.getMunicipio(),
          direccion.getSector(),
          direccion.getCalle(),
          direccion.getNumero()
        };
        genericDAO.prepareConnection();
        idGenerado = genericDAO.executeInsertIdent(insertDireccion, params);

        return idGenerado;
    }
        
    /**
    * Metodo para registrar una persona en la base de datos. 
    * <p>
    * @param persona un objeto de la clase Persona.
    * @param idDireccion el id de la direccion.
    * @return el id de la persona.
    * @throws Exception lanza una excepcion si se produce un error al registrar
    *                   los datos basicos de la persona.
    */
    private int registrarPersona(Persona persona, int idDireccion) throws Exception{
        int idPersona;
        String insertPersona = "Insert into Personas(cedula, nombre, apellido, "+
                                 " sexo, fechaNacimiento, idDireccion) " +
                                 "Values(?, ?, ?, ?, ?, ?);";

        Object [] params = {
          persona.getCedula(), persona.getNombre(),
          persona.getApellido(), String.valueOf(persona.getSexo()),
          new Date(persona.getFechaNacimiento().getTime()),
          idDireccion
        };
        
        genericDAO.prepareConnection();
        idPersona = genericDAO.executeInsertIdent(insertPersona, params);
        
        return idPersona;
    }
       
    /**
    * Metodo para registrar un socio en la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno
    * @param socio un objeto de la clase Socio.
    * @throws Exception lanza una excepcion si se produce un error al registrar
    *                   los datos basicos del socio.
    */
    public void registrarSocio(Socio socio) throws Exception{
        String insertSocio = "insert into socios "+
                             "values(null, null, ?, ?, ?, ?, 'V');";
        previousState = null;
        int idPersona;
        
        genericDAO.openConnection();
        try{
            genericDAO.getConnection().setAutoCommit(false);
            previousState = genericDAO.getConnection().setSavepoint();
            
            int idDireccion = registrarDireccion(socio.getDireccion());
            idPersona = registrarPersona(socio, idDireccion);

            for(Telefono obj: socio.getTelefonos()) {
                registrarTelefono(obj, idPersona);
            }
            
            Object [] params = {
                socio.getPeso(),
                socio.getEstatura(),
                socio.getMembresia().getIdMembresia(),
                idPersona
            };
            
            genericDAO.executeUpdate(insertSocio, params);
            genericDAO.getConnection().commit();
        }catch(Exception ex){
            genericDAO.getConnection().rollback(previousState);
            throw ex;
        }finally{
            genericDAO.getConnection().setAutoCommit(true);
        }
    }
        
    /**
    * Metodo para registrar una seccion en la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param seccion un objeto de la clase seccion.
    * @throws Exception lanza una excepcion si se produce un error al registrar
    *                   los datos basicos de la seccion.
    */
    public void registrarSeccion(Seccion seccion) throws Exception{
        String insertSeccion = "insert into Secciones "+
                                "(nombre, dia, horaInicio, horaFin, " +
                                "capacidad, idClase, idSalon, idInstructor, " +
                                "fechaInicio, fechaFin) " +
                           "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        Object [] params = {
          seccion.getNombre(), seccion.getDia(),
          seccion.getHoraInicio(), seccion.getHoraFin(),
          seccion.getCapacidad(), seccion.getClaseImpartida().getIdClase(),
          seccion.getSalon().getIdSalon(),
          seccion.getInstrctAsignado().getIdInstructor(),
          new Date(seccion.getFechaInicio().getTime()),
          new Date(seccion.getFechaFin().getTime())
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(insertSeccion, params);

    }
        
    /**
    * Metodo para registrar una membresia en la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param membresia un objeto de la clase Membresia.
    * @throws Exception lanza una excepcion si se produce un error al registrar
    *                   los datos basicos de la membresia.
    */
    public void registrarMembresia(Membresia membresia) throws Exception{
        String query = "insert into membresias "+
                       "values(null, ?, ?, ?, ?);";
        
        Object [] params = {
          membresia.getNombre(),
          membresia.getDescripcion(),
          membresia.getTipo(),
          membresia.getPrecio()
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(query, params);
    }
        
    /**
    * Metodo para registrar la asistencia del socio en la base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param IdSocio el id del socio.
    * @throws Exception lanza una excepcion si se produce un error al registrar
    *                   la asistencia del socio o si ya ha sido registrada.
    */
    public void registrarAsistencia(int idSocio) throws Exception{
        String query = "insert into asistencia_socios "+
                       "values(?, ?)";
        
        Object [] params = {
          idSocio,
          new Date(new java.util.Date().getTime())
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(query, params);
    }
        
    /**
    * Metodo para buscar un empleado en la base de datos. 
    * <p>
    * @param filtro tipo de busqueda que toma como parametro real el id.
    * @param indiceCampo tipo de busqueda que toma como parametro real el nombre
    * ,apellido o cedula.
    * @return un objeto del empleado que fue seleccionado.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   los datos del empleado.
    * 
    */
    public Vector<Empleado> buscarEmpleado(String filtro, int indiceCampo) throws Exception{
        ResultSet queryResult;
        Vector<Empleado> empleados = new Vector();
        String selectEmpleado;
        Empleado emp;
        Object [] params;
                
        if( indiceCampo == 1){
            selectEmpleado = "select * from vEmpleados "+
                             "where idEmpleado = ?;";
            
            params = new Object[]{
                Integer.valueOf(filtro)
            };
        }
        else{
            selectEmpleado = "exec usp_buscarEmpleado ?, ?;";
            
            params = new Object[]{
                filtro,
                indiceCampo
            };
        }
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(selectEmpleado, params);

        while(queryResult.next()){

            emp = new Empleado( queryResult.getInt("idPersona"),
                    queryResult.getString("cedula"),
                    queryResult.getString("nombre"),
                    queryResult.getString("apellido"),
                    queryResult.getString("sexo").charAt(0),
                    queryResult.getDate("fechaNacimiento"),
                    this.buscarTelefonos(queryResult.getInt("idPersona")),
                    this.buscarDireccion(queryResult.getInt("idDireccion")),
                    queryResult.getInt("idEmpleado"),
                    queryResult.getString("puesto"),
                    queryResult.getDouble("salario"),
                    queryResult.getDate("fechaIngreso")
            );
            empleados.add(emp);
        }
        queryResult.close();
        
        return empleados;
    }
        
    /**
    * Metodo para registrar una inscripcion de un socio en el gimnasio y
    * guardarla base de datos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param idSocio el id del socio.
    * @param idSeccion el id de la seccion.
    * @throws Exception lanza una excepcion si se produce un error al registrar
    *                   la inscripcion de la seccion.
    * 
    */
    public void registrarInscripcion(int idSocio, int idSeccion) throws Exception{
        String query = "insert into secciones_socio "+
                       "values(?, ?, null);";
        Object [] params = {
          idSocio,
          idSeccion
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(query, params);

    }
       
    /**
    * Metodo para registrar los pagos hechos por un socio. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param factura un objeto de la clase Factura.
    * @param empleado un objeto de la clase Empleado.
    * @param monto el monto de la factura.
    * @throws Exception lanza una excepcion si se produce un error al registrar
    *                   el pago de la factura.
    * 
    */
    public void registrarPago(Factura factura, Empleado empleado, double monto) throws Exception{
        String query = "exec usp_registrarPago ?, ?, ?;";
                
        Object [] params = {
          factura.getIdFactura(),
          monto,
          empleado.getIdEmpleado()
        };

        genericDAO.prepareConnection();
        genericDAO.executeUpdate(query, params);
    }
        
    /**
    * Metodo para buscar un empleado en la base de datos. 
    * <p>
    * @param idEmpleado el id del empleado.
    * @param tipo el tipo del empleado.
    * @return un objeto del empleado que fue seleccionado.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   el empleado.
    * 
    */
    public Empleado buscarEmpleado(int idEmpleado, String tipo) throws Exception{
        ResultSet queryResult;
        String selectEmpleado;
        Empleado empleado = null;
        Object [] params = { idEmpleado };
        
        if(tipo.equals("Instructor") ){
            selectEmpleado = "select * from vInstructores "+
                             "where idInstructor = ?;";
        }
        else{
            selectEmpleado = "select * from vPersAdministrativo "+
                             "where idEmpleado = ?;";
        }
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(selectEmpleado, params);

        while(queryResult.next()){
             if(tipo.equals("Instructor")){
                 Instructor emp = new Instructor(queryResult.getInt("idPersona"),
                    queryResult.getString("cedula"),
                    queryResult.getString("nombre"),
                    queryResult.getString("apellido"),
                    queryResult.getString("sexo").charAt(0),
                    queryResult.getDate("fechaNacimiento"),
                    buscarTelefonos(queryResult.getInt("idPersona")),
                    buscarDireccion(queryResult.getInt("idDireccion")),
                    queryResult.getInt("idEmpleado"),
                    queryResult.getString("puesto"),
                    queryResult.getDouble("salario"),
                    queryResult.getDate("fechaIngreso"),
                    queryResult.getInt("idInstructor"),
                    queryResult.getString("especialidad")
                );
                emp.setSecciones(buscarSecciones(3, emp.getIdInstructor()));
                for(int i = 0; i < emp.getSecciones().size(); i++){
                    emp.getSecciones().get(i).setInstrctAsignado(emp);
                }
                    
                empleado = emp;
            }
            else{     
              PersAdministrativo  emp = new PersAdministrativo(queryResult.getInt("idPersona"),
                        queryResult.getString("cedula"),
                        queryResult.getString("nombre"),
                        queryResult.getString("apellido"),
                        queryResult.getString("sexo").charAt(0),
                        queryResult.getDate("fechaNacimiento"),
                        buscarTelefonos(queryResult.getInt("idPersona")),
                        buscarDireccion(queryResult.getInt("idDireccion")),
                        queryResult.getInt("idEmpleado"),
                        queryResult.getString("puesto"),
                        queryResult.getDouble("salario"),
                        queryResult.getDate("fechaIngreso"),
                        queryResult.getString("userName"),
                        queryResult.getString("password")
                );
              empleado = emp;
            }

        }
        queryResult.close();        
        
        return empleado;
    }
        
    /**
    * Metodo para buscar un empleado en la base de datos. 
    * <p>
    * @param userName el nombre de usuario del empleado administrativo.
    * @param password la clave de inicio de session de del empleado administrativo.
    * @param puesto el puesto del del empleado administrativo.
    * @return un objeto del empleado que fue seleccionado.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   el empleado.
    * 
    */
    public PersAdministrativo buscarEmpleado(String userName, String password, String puesto) throws Exception{
        PersAdministrativo empleado = null;
        String query = "select * from vPersAdministrativo "+
                       "where userName = ? and password = ? and puesto = ?;";
        ResultSet queryResult;
        
        Object [] params = {
            userName,
            password,
            puesto
        };
        
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(query, params);
        
        while(queryResult.next()){
            empleado = new PersAdministrativo(queryResult.getInt("idPersona"),
                        queryResult.getString("cedula"),
                        queryResult.getString("nombre"),
                        queryResult.getString("apellido"),
                        queryResult.getString("sexo").charAt(0),
                        queryResult.getDate("fechaNacimiento"),
                        buscarTelefonos(queryResult.getInt("idPersona")),
                        buscarDireccion(queryResult.getInt("idDireccion")),
                        queryResult.getInt("idEmpleado"),
                        queryResult.getString("puesto"),
                        queryResult.getDouble("salario"),
                        queryResult.getDate("fechaIngreso"),
                        queryResult.getString("userName"),
                        queryResult.getString("password")
            );
        }
        
        return empleado;
    }
        
    /**
    * Metodo para buscar la direccion de una persona en la base de datos. 
    * <p>
    * @param idDireccion el id de la direccion.
    * @return un objeto de la clase Direccion.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   la direccion.
    */
    public Direccion buscarDireccion(int idDireccion) throws Exception{
        Direccion direccion = null;
        ResultSet dataDir;
        String selectDireccion = "Select * from direcciones "+
                                 "Where idDireccion = ?;";
        Object [] params = { idDireccion };
        
        genericDAO.prepareConnection();
        dataDir = genericDAO.executeQuery(selectDireccion, params);
        
        while(dataDir.next()){
            direccion = new Direccion(dataDir.getInt("idDireccion"),
                  dataDir.getString("municipio"),
                  dataDir.getString("sector"),
                  dataDir.getString("calle"),
                  dataDir.getInt("numero")                    
            );
        }
        
        dataDir.close();
        
        return direccion;
    }
        
    /**
    * Metodo para buscar el o los telefonos de una persona en la base de datos. 
    * <p>
    * @param idPerosona el id de la persona.
    * @return un objeto de la clase Telefono.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   los telefonos.
    * 
    */
    public Vector<Telefono> buscarTelefonos(int idPersona) throws Exception{
        String selectTelefonos = "exec uspBuscarTelefonos ?;";
        ResultSet dataTels;
        Vector<Telefono> telefonos = new Vector();
        Object [] params = { idPersona };
        
        genericDAO.prepareConnection();
        dataTels = genericDAO.executeQuery(selectTelefonos, params);
        
        while(dataTels.next()){
            telefonos.add( new Telefono(dataTels.getInt("idTelefono"),
                    dataTels.getString("numero"),
                    dataTels.getString("tipo"))
            );    
        }
        dataTels.close();
        
        return telefonos;
    }
       
    /**
    * Metodo para buscar una membresia en la base de datos. 
    * <p>
    * @param idMembresia el id de la membresia.
    * @return un objeto de la clase Membresia.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   la membresia.
    */
    public Membresia buscarMembresia(int idMembresia) throws Exception{
        ResultSet queryResult;
        Membresia membresia = null;
        String findMembresia = "select * from membresias "+
                               "where idMembresia = ?;";
        Object [] params = { idMembresia };
        
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(findMembresia, params);        
        while(queryResult.next()){
            membresia = new Membresia(queryResult.getInt("idMembresia"),
                    queryResult.getString("nombre"),
                    queryResult.getString("descripcion"),
                    queryResult.getString("tipo"),
                    queryResult.getDouble("precio")
            );
        }
        queryResult.close();
        
        return membresia;
    }
        
    /**
    * Metodo para buscar las membresia en la base de datos. 
    * <p>
    * @return un objeto de la clase Membresia.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   las membresias.
    */
    public Vector<Membresia> buscarMembresias() throws Exception{
        ResultSet queryResult;
        Vector<Membresia> membresias = new Vector<Membresia>();
        String selectMembresias = "select * from membresias;";
        
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(selectMembresias, null);        
        while(queryResult.next()){
            membresias.add(new Membresia(queryResult.getInt("idMembresia"),
                    queryResult.getString("nombre"),
                    queryResult.getString("descripcion"),
                    queryResult.getString("tipo"),
                    queryResult.getDouble("precio"))
            );
        }
        
        queryResult.close();
        
        return membresias;
    }
      
    /**
    * Metodo para buscar un socio en la base de datos. 
    * <p>
    * @param filtro tipo de busqueda que toma como parametro real el id.
    * @param campoBusqueda tipo de busqueda que toma como parametro real el nombre
    *                      apellido o cedula.
    * @return un objeto del socio que fue seleccionado.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   los socios que coinciden con los campos de busqueda
    */
    public Vector<Socio> buscarSocios(String filtro, int campoBusqueda) throws Exception{
        ResultSet queryResult;
        String selectSocio = "exec uspBuscarSocio ?, ?;";
        String selectSocioBI = "select * from vSocios "+
                               "where idSocio = ?;";
        Vector<Socio> socios = new Vector();
        Object [] params;
        
        genericDAO.prepareConnection();
        if(campoBusqueda == 1){
            params = new Object[]{ Integer.valueOf(filtro) };
            queryResult = genericDAO.executeQuery(selectSocioBI, params);
        }
        else{
            params = new Object[]{
                Integer.valueOf(filtro),
                campoBusqueda
            };
            queryResult = genericDAO.executeQuery(selectSocio, params);
        }

        while(queryResult.next()){
            Socio socio = new Socio(queryResult.getInt("idPersona"),
                    queryResult.getString("cedula"),
                    queryResult.getString("nombre"),
                    queryResult.getString("apellido"),
                    queryResult.getString("sexo").charAt(0),
                    queryResult.getDate("fechaNacimiento"),
                    buscarTelefonos(queryResult.getInt("idPersona")),
                    buscarDireccion(queryResult.getInt("idDireccion")),
                    queryResult.getInt("idSocio"),
                    queryResult.getDouble("peso"),
                    queryResult.getDouble("estatura"),
                    queryResult.getDate("fechaIngreso"),
                    buscarMembresia(queryResult.getInt("idMembresia")),
                    queryResult.getString("status").charAt(0)
            );
            socio.setSeccionesInscritas(buscarSeccionesSocio(socio.getIdSocio()));
            
            socios.add(socio);
        }
        
        return socios;
    }
        
    /**
    * Metodo para buscar un socio en la base de datos. 
    * <p>
    * @param idSocio el id del socio.
    * @return un objeto del socio que fue seleccionado.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   el socio.
    */
    public Socio buscarSocio(int idSocio) throws Exception{
        ResultSet queryResult;
        Socio socio = null;
        String selectSocio = "select * from vSocios "+
                               "where idSocio = ?;";
        Object [] params = { idSocio };
        
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(selectSocio, params);
        
        while(queryResult.next()){
            socio = new Socio(queryResult.getInt("idPersona"),
                        queryResult.getString("cedula"),
                        queryResult.getString("nombre"),
                        queryResult.getString("apellido"),
                        queryResult.getString("sexo").charAt(0),
                        queryResult.getDate("fechaNacimiento"),
                        buscarTelefonos(queryResult.getInt("idPersona")),
                        buscarDireccion(queryResult.getInt("idDireccion")),
                        queryResult.getInt("idSocio"),
                        queryResult.getDouble("peso"),
                        queryResult.getDouble("estatura"),
                        queryResult.getDate("fechaIngreso"),
                        buscarMembresia(queryResult.getInt("idMembresia"))
            );
        }

        queryResult.close();
        
        return socio;
    }
        
    /**
    * Metodo para buscar una clase en la base de datos. 
    * <p>
    * @param idClase el id de la clae.
    * @return un objeto de la clase Clase.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   la clase.
    */
    public Clase buscarClase(int idClase) throws Exception{
        ResultSet queryResult;
        String selectClase = "select * from clases "+
                             "where idClase = ?;";
        Clase clase = null;
        Object [] params = { idClase };
        
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(selectClase, params);
        while(queryResult.next()){
            clase = new Clase(queryResult.getInt("idClase"),
                    queryResult.getString("nombre"),
                    queryResult.getString("descripcion"),
                    queryResult.getDouble("precio")
            );
            clase.setSecciones(buscarSecciones(4, clase.getIdClase()));              
        }
        queryResult.close();
        
        return clase;
    }
        
    /**
    * Metodo para buscar los detalles de una factura en la base de datos. 
    * <p>
    * @param idFactura el id de la factura.
    * @return un objeto de la clase DetalleFactura.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   el detalle de la factura.
    */
    public Vector<DetalleFactura> buscarDetallesFactura(int idFactura) throws Exception{
        Vector<DetalleFactura> detalles = new Vector();
        String query = "select * from detallefactura where idFactura = ?;";
        Object [] params = { idFactura };
        ResultSet queryResult;
        
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(query, params);
        while(queryResult.next()){
            detalles.add(new DetalleFactura(
                    queryResult.getInt("idDetalleFactura"),
                    queryResult.getString("detalle"),
                    queryResult.getDouble("precio")
                    )                   
            );
        }
        queryResult.close();
        
        return detalles;
    }
        
    /**
    * Metodo para buscar las facturas pendientes de los socios en la base de datos. 
    * <p>
    * @param idSocio el id del socio.
    * @return un objeto de la clase Factura.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   las facturas pendientes.
    */
    public Vector<Factura> buscarFacturasPendientes(int idSocio) throws Exception{
        String query = "exec usp_obtenerFacturasPend ?;";
        ResultSet queryResult;
        Vector<Factura> facturas = new Vector();
        Object [] params = { idSocio };
        
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(query, params);
        while(queryResult.next()){
            facturas.add(new Factura(
                queryResult.getInt("idFactura"),
                queryResult.getDate("fechaCreacion"),
                buscarDetallesFactura(queryResult.getInt("idFactura")),
                queryResult.getString("nfc"),
                null,
                null
                        
            ));
        }
        queryResult.close();
        
        return facturas;
    }
         
    /**
    * Metodo para buscar las clases que se imparten en la base de datos. 
    * <p>
    * @param filtro tipo de busqueda que toma como parametro real el id.
    * @param campoBusqueda tipo de busqueda que toma como parametro real el nombre
    * ,salon, instructor o dia.
    * @return un objeto de la clase Clase.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   las clases.
    */  
    public Vector<Clase> buscarClase(String filtro, int campoBusqueda) throws Exception{
        ResultSet queryResult;
        Vector<Clase> clases = new Vector();
        String selectClases = "select * from clases ";
        Object [] params;
        
        if(campoBusqueda == 2){
            params = new Object[]{ filtro };
            selectClases += "where nombre = ?;";
        }
        else{
            selectClases += "where precio = ?;";
            params = new Object[]{1, Double.valueOf(filtro)};
        }
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(selectClases, params);
        
        while(queryResult.next()){
            Clase clase = new Clase(queryResult.getInt("idClase"),
                    queryResult.getString("nombre"),
                    queryResult.getString("descripcion"),
                    queryResult.getDouble("precio")        
            );
            clase.setSecciones(buscarSecciones(4, clase.getIdClase()));
            
            clases.add(clase);
        }
        
        queryResult.close();
        
        return clases;
    }
    
    /**
    * Metodo para buscar las clases que se imparten en la base de datos. 
    * <p>
    * @return un objeto de la clase Clase.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   las clases.
    */ 
    public Vector<Clase> buscarClases() throws Exception{
        ResultSet queryResult;
        Vector<Clase> clases = new Vector();
        String selectClases = "select * from clases;";
        
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(selectClases, null);
        while(queryResult.next()){
            Clase clase = new Clase(queryResult.getInt("idClase"),
                    queryResult.getString("nombre"),
                    queryResult.getString("descripcion"),
                    queryResult.getDouble("precio")        
            );
            clase.setSecciones(buscarSecciones(4, clase.getIdClase()));
            
            clases.add(clase);
        }
        queryResult.close();
        
        return clases;
    }
       
    /**
    * Metodo para buscar las secciones que se imparten en la base de datos. 
    * <p>
    * @param filtro tipo de busqueda que toma como parametro real el id.
    * @param columna tipo de busqueda.
    * @return un objeto de la clase Secciones.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *         las seccinoes.
    */ 
    public Vector<Seccion> buscarSecciones(int columna, int filtro) throws Exception{
        ResultSet queryResult;
        Vector<Seccion> secciones = new Vector();
        String selectSecciones = null;

        Object [] params = { filtro };
        
        if( columna == 1){
            selectSecciones = "select * from vSecciones where idSeccion = ?;";
        }
        else if(columna == 2){
            selectSecciones = "select * from vSecciones where idSalon = ?;";
        }   
        else if(columna == 3){
            selectSecciones = "select * from vSecciones where idInstructor = ?;";
        }
        else if(columna == 4){
            selectSecciones = "select * from vSecciones where idClase = ?;";
        }

        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(selectSecciones, params);
        while(queryResult.next()){
            Seccion seccion = new Seccion(queryResult.getInt("idSeccion"),
                    queryResult.getString("nombreSeccion"),
                    queryResult.getInt("horaInicio"),
                    queryResult.getInt("horaFin"),
                    queryResult.getInt("dia"),
                    queryResult.getInt("capacidad")
           
            );
            if(columna != 3){
                seccion.setInstrctAsignado(
                    (Instructor)buscarEmpleado(
                        queryResult.getInt("idInstructor"),
                        "Instructor"
                    )
                );
            }            
            
            seccion.setSalon( new Salon(
                    queryResult.getInt("idSalon"),
                    queryResult.getString("nombreSalon")
            ));

            seccion.setClaseImpartida( new Clase(
                    queryResult.getInt("idClase"),
                    queryResult.getString("nombreClase"),
                    queryResult.getString("descripcion"),
                    queryResult.getDouble("precio")
            ));
            seccion.setSociosInscritos(
                    buscarSocios(seccion.getIdSeccion())
            );

            secciones.add(seccion);
        }
        queryResult.close();
    
        return secciones;
    }
        
    /**
    * Metodo para buscar las secciones que estan disponibles en la base de datos. 
    * <p>
    * @param filtro tipo de busqueda que toma como parametro real el id.
    * @param campoBusqueda tipo de busqueda.
    * @param disponibles disponibilodad de las secciones
    * @return un objeto de la clase Secciones.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   las secciones.
    */ 
    public Vector<Seccion> buscarSecciones(int campoBusqueda, String filtro, boolean disponibles) throws Exception{
        Vector<Seccion> secciones = new Vector();
        ResultSet queryResult;
        String selectSecs = "select * from vSecciones where ";
        Object [] params;
        
        if(disponibles){
            selectSecs += "capacidad > 0 and ";
        }
        
        switch(campoBusqueda){
            case 1: selectSecs += "idSeccion = ?;";
                break;
            case 2: selectSecs += "nombreClase = ?;";
                break;
            case 3: selectSecs += "nombreSalon = ?;";
                break;
            case 4: selectSecs = "exec usp_findSecsByInstructor ?;";
                break;
            case 5: selectSecs += "dia = ?;";
                break;
            default: selectSecs += "idSeccion = ?;";
                break;
        }

        if( campoBusqueda == 1 || campoBusqueda == 5){
            params = new Object[]{ Integer.valueOf(filtro) };
        }
        else{
            params = new Object[]{ filtro };
        }
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(selectSecs, params);
        while(queryResult.next()){
            Seccion seccion = new Seccion(queryResult.getInt("idSeccion"),
                    queryResult.getString("nombreSeccion"),
                    queryResult.getInt("horaInicio"),
                    queryResult.getInt("horaFin"),
                    queryResult.getInt("dia"),
                    queryResult.getInt("capacidad")
           
            );

            seccion.setInstrctAsignado(
                (Instructor)buscarEmpleado(
                    queryResult.getInt("idInstructor"),
                    "Instructor"
                )
            );
           
            seccion.setSalon( new Salon(
                    queryResult.getInt("idSalon"),
                    queryResult.getString("nombreSalon")
            ));

            seccion.setClaseImpartida( new Clase(
                    queryResult.getInt("idClase"),
                    queryResult.getString("nombreClase"),
                    queryResult.getString("descripcion"),
                    queryResult.getDouble("precio")
            ));
            seccion.setSociosInscritos(
                    buscarSocios(seccion.getIdSeccion())
            );

            secciones.add(seccion);
        }
        queryResult.close();

        return secciones;
    }
        
    /**
    * Metodo para buscar las secciones que tiene un socio en la base de datos. 
    * <p>
    * @param idSocio el id del socio.
    * @return un objeto de la clase Secciones.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   al buscar las secciones de clase del socio.
    */ 
    public Vector<Seccion> buscarSeccionesSocio(int idSocio) throws Exception{
        Vector<Seccion> secciones = new Vector();
        String query = "exec uspBuscarSeccionesSocio ?;";
        ResultSet queryResult;
        Object [] params = { idSocio };
        
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(query, params);
        while(queryResult.next()){
            Seccion seccion = new Seccion(queryResult.getInt("idSeccion"),
                    queryResult.getString("nombreSeccion"),
                    queryResult.getInt("horaInicio"),
                    queryResult.getInt("horaFin"),
                    queryResult.getInt("dia"),
                    queryResult.getInt("capacidad")
           
            );

            seccion.setInstrctAsignado(
                (Instructor)buscarEmpleado(
                    queryResult.getInt("idInstructor"),
                    "Instructor"
                )
            );
           
            seccion.setSalon( new Salon(
                    queryResult.getInt("idSalon"),
                    queryResult.getString("nombreSalon")
            ));

            seccion.setClaseImpartida( new Clase(
                    queryResult.getInt("idClase"),
                    queryResult.getString("nombreClase"),
                    queryResult.getString("descripcion"),
                    queryResult.getDouble("precio")
            ));
            
            secciones.add(seccion);
        }
        queryResult.close();
        
        return secciones;
    }
    
    /**
    * Metodo para buscar los socios en la base de datos. 
    * <p>
    * @param idSocio el id del socio.
    * @return un objeto de la clase Socios.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   los socios.
    */ 
    public Vector<Socio> buscarSocios(int idSeccion) throws Exception{
        String selecSocios = "select idSocio from Secciones_Socio "+
                             "where idSeccion = ?;";
        Vector<Socio> socios = new Vector();
        ResultSet queryResult;
        Object [] params = { idSeccion };
        
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(selecSocios, params);
        while(queryResult.next()){
            socios.add(
                    buscarSocio(queryResult.getInt("idSocio"))
            );
        }
        queryResult.close();
        
        return socios;
    }
       
    /**
    * Metodo para buscar los salones en la base de datos. 
    * <p>
    * @param idSalon el id del salon.
    * @return un objeto de la clase Salon.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   el salon.
    */
    public Salon buscarSalon(int idSalon) throws Exception{
        ResultSet queryResult;
        Salon salon = null;
        String selectSalon = "select * from salones "+
                             "where idSalon = ?;";
        Object [] paramss = { idSalon };
        
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(selectSalon, paramss);
        while(queryResult.next()){
            salon = new Salon(
                    queryResult.getInt("idSalon"),
                    queryResult.getString("nombre"),
                    buscarSecciones(2, idSalon)
            );
        }
        queryResult.close();
        
        return salon;
    }
        
    /**
    * Metodo para buscar los socios en la base de datos. 
    * <p>
    * @param idSocio el id del socio.
    * @return un objeto de la clase Socios.
    * @throws Exception lanza una excepcion si se produce un error al buscar
    *                   los salones.
    */
    public Vector<Salon> buscarSalones() throws Exception{
        ResultSet queryResult;
        Vector <Salon> salones = new Vector();
        String selectSalones = "select * from salones;";
        
        genericDAO.prepareConnection();
        queryResult = genericDAO.executeQuery(selectSalones, null);
        while(queryResult.next()){
           Salon salon = new Salon(
                    queryResult.getInt("idSalon"),
                    queryResult.getString("nombre"),
                    buscarSecciones(2, queryResult.getInt("idSalon"))
            );
           salones.add(salon);
        }
        queryResult.close();
        
        return salones;
    }
        
    /**
    * Metodo para actualizar los datos de un empleado. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param emp un objeto de la clase Empleado.
    * @throws Exception lanza una excepcion si se produce un error al actualizar
    *                   el empleado.
    */
    public void actualizarEmpleado(Empleado  emp) throws Exception{
        String updateEmpleado = "Update Empleados "+
                                "set puesto = ?, salario = ? "+
                                "where idEmpleado = ?;";
        previousState = null;
        Object [] params = {
          emp.getPuesto(),
          emp.getSalario(),
          emp.getIdEmpleado()
        };
        
        genericDAO.prepareConnection();
        genericDAO.getConnection().setAutoCommit(false);
        try{
            previousState = genericDAO.getConnection().setSavepoint();
           
            actualizarTelefonos(emp.getTelefonos(), emp.getIdPersona());
            actualizarDireccion(emp.getDireccion());
            actualizarPersona(emp);
            
            genericDAO.executeUpdate(updateEmpleado, params);
            
            if( emp instanceof Instructor) {
                actualizarInstructor((Instructor)emp);
            }
            else {
                actualizarPersAdm((PersAdministrativo)emp);
            }
            
            genericDAO.getConnection().commit();
        }catch(Exception ex){
            genericDAO.getConnection().rollback(previousState);
            throw ex;
        }finally{
            genericDAO.getConnection().setAutoCommit(true);
        }       
        
    }
       
    /**
    * Metodo para actualizar los datos de un socio. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param socio un objeto de la clase Socio.
    * @throws Exception lanza una excepcion si se produce un error al actualizar
    *                   los datos del socio.
    */
    public void actualizarSocio(Socio socio) throws Exception{
        String updateSocio = "update socios "+
                             "set peso = ?, "+
                             "estatura = ?, "+
                             "idMembresia = ? "+
                             "where idSocio = ?;";
        previousState = null;
        Object [] params = {
            socio.getPeso(), socio.getEstatura(),
            socio.getMembresia().getIdMembresia(),
            socio.getIdSocio()
        };
        
        genericDAO.prepareConnection();
        genericDAO.getConnection().setAutoCommit(false);
        try{
            previousState = genericDAO.getConnection().setSavepoint();
           
            actualizarTelefonos(socio.getTelefonos(), socio.getIdPersona());
            actualizarDireccion(socio.getDireccion());
            actualizarPersona(socio);
            genericDAO.executeUpdate(updateSocio, params);
            
            genericDAO.getConnection().commit();
        }catch(Exception ex){
            genericDAO.getConnection().rollback(previousState);
            throw ex;
        }finally{
            genericDAO.getConnection().setAutoCommit(true);
        }
    }
        
    /**
    * Metodo para actualizar el estado de la membresia de un socio. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param socio un objeto de la clase Socio.
    * @throws Exception lanza una excepcion si se produce un error al actualizar
    *                   el estado de la membresia del socio.
    */
    public void actualizarEstadoMembresia(Socio socio) throws Exception{
        String query = "update socios "+
                             "set status = ? "+
                             "where idSocio = ?;";
        Object [] params = {
          socio.getStatus(),
          socio.getIdSocio()
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(query, params);
        
    }
       
    /**
    * Metodo para actualizar los datos de un instructor. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param inst un objeto de la clase Instructor.
    * @throws Exception lanza una excepcion si se produce un error al actualizar
    *                   los datos del instructor.
    */
    private void actualizarInstructor(Instructor inst) throws Exception{
        String updInstructor = "Update Instructores "+
                               "set especialidad = ? " +
                               "where idInstructor = ?;";
        Object [] params = {
          inst.getEspecialidad(),
          inst.getIdInstructor()
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(updInstructor, params);

    }
        
    /**
    * Metodo para actualizar los datos de un empleado administrativo. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param pa un objeto de la clase PersAdministrativo.
    * @throws Exception lanza una excepcion si se produce un error al actualizar
    *                   los datos del empleado administrativo.
    */
    private void actualizarPersAdm(PersAdministrativo pa) throws Exception{
        String updPersAdm = "Update PersAdministrativo "+
                            "set userName = ?, password = ? "+
                            "where idEmpleado = ?;";
        Object [] params = {
          pa.getUserName(),
          pa.getPassword(),
          pa.getIdEmpleado()
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(updPersAdm, params);

    }
        
    /**
    * Metodo para actualizar un telefono. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param tel un objeto de la clase Telefono.
    * @throws Exception lanza una excepcion si se produce un error al actualizar
    *                   los datos del telefono.
    */
    public void actualizarTelefono(Telefono tel) throws Exception{
        String updateTelefono = "update telefonos "+
                                "set numero = ?, "+
                                "tipo = ? "+
                                "where idTelefono = ?;";
        Object [] params = {
            tel.getNumero(),
            tel.getTipo(),
            tel.getIdTelefono()
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(updateTelefono, params);

    }
        
    /**
    * Metodo para actualizar los telefonos de una persona. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param telefonos un objeto de la clase Telefono.
    * @param  idPersona el id de la persona. 
    * @throws Exception lanza una excepcion si se produce un error al actualizar
    *                   los datos de los telefonos.
    */
    public void actualizarTelefonos(Vector<Telefono> telefonos, int idPersona) throws Exception{
        boolean flag = false;
        Vector<Telefono> viejos = buscarTelefonos(idPersona);
        previousState = null;
        
        genericDAO.prepareConnection();
        genericDAO.getConnection().setAutoCommit(false);
        
        try{
            previousState = genericDAO.getConnection().setSavepoint();
            for(Telefono obj: viejos){
                for( int i = 0; i < telefonos.size(); i++){
                    if(obj.getIdTelefono() == telefonos.get(i).getIdTelefono()){
                        flag = false;
                        break;
                    }
                    else {
                        flag = true;
                    }

                }
                if(flag){
                    eliminarTelefono(obj);
                }
            }

            for( Telefono tel: telefonos){
                for(int i = 0; i < viejos.size(); i++){
                    if(tel.getIdTelefono()==viejos.get(i).getIdTelefono()){
                            actualizarTelefono(tel);
                            flag = true;
                            break;
                    }
                    else{
                        flag = false;
                    }
                }
                if(!flag){
                    registrarTelefono(tel, idPersona);
                }
            }
            genericDAO.getConnection().commit();
        }catch(Exception ex){
            genericDAO.getConnection().rollback(previousState);
            throw ex;
        }finally{
            genericDAO.getConnection().setAutoCommit(true);
        }
        
    }
          
    /**
    * Metodo para actualizar una direccion. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param direccion un objeto de la clase direccion.
    * @throws Exception lanza una excepcion si se produce un error al actualizar
    *                   los datos de la direccion.
    */
    public void actualizarDireccion(Direccion direccion) throws Exception{
        String updateDireccion = "update direcciones "+
                                 "set municipio = ?,  sector = ?, "+
                                 "calle = ?,  numero = ? "+
                                 "where idDireccion = ?;";
        Object [] params = {
            direccion.getMunicipio(), direccion.getSector(),
            direccion.getCalle(), direccion.getNumero(),
            direccion.getIdDireccion()
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(updateDireccion, params);
    }
       
    /**
    * Metodo para actualizar los datos de una persona. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param persona un objeto de la clase Persona.
    * @throws Exception lanza una excepcion si se produce un error al actualizar
    *                   los datos de la persona.
    */
    public void actualizarPersona(Persona persona) throws Exception{
        String updatePersona = "update personas "+
                               "set cedula = ?, "+
                               "nombre = ?, "+
                               "apellido = ?,  sexo = ?, "+
                               "fechaNacimiento = ? "+
                               "where idPersona = ?;";
        Object [] params = {
            persona.getCedula(), persona.getNombre(),
            persona.getApellido(), 
            String.valueOf(persona.getSexo()),
            new Date(persona.getFechaNacimiento().getTime()),
            persona.getIdPersona()
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(updatePersona, params);
    }
       
    /**
    * Metodo para eliminar un telefono. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param tel un objeto de la clase Telefono.
    * @throws Exception lanza una excepcion si se produce un error al eliminar
    *                   el telefono
    */
    public void eliminarTelefono(Telefono tel) throws Exception{
        String deleteTel = "delete from telefonos "+
                           "where idTelefono = ?;";
        Object [] params = { tel.getIdTelefono()  };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(deleteTel, params);
    }
       
    /**
    * Metodo para eliminar una membresia. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param idMembresia el id de la membresia.
    * @throws Exception lanza una excepcion si se produce un error al eliminar
    *                   la membresia.
    */
    public void eliminarMembresia(int idMembresia) throws Exception{
        int sociosInscr;
        String delQuery = "delete from Membresias "+
                          "where idMembresia = ?;";
        String query = "select count(*) from Socios "+
                       "where idMembresia = ?;";
        Object [] params = { idMembresia };
        
        genericDAO.prepareConnection();       
        sociosInscr = (Integer)genericDAO.executeScalar(query, params);
        
        if(sociosInscr == 0){
            genericDAO.executeUpdate(delQuery, params);
        }
        else{
            throw new Exception("Esta membresia esta en uso.");
        }
        
    }
       
    /**
    * Metodo para actualizar una seccion. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param seccion un objeto de la clase Seccion.
    * @throws Exception lanza una excepcion si se produce un error al actualizar
    *                   los datos de la seccion.
    */
    public void actualizarSeccion(Seccion seccion) throws Exception{
        String updateSeccion = "update Secciones "+
                               "set nombre = ?, horaInicio = ?, "+
                               "horaFin = ?, capacidad = ?, "+
                               "dia = ?, idClase = ?, "+
                               "idSalon = ?, idInstructor = ? "+
                               "where idSeccion = ?;";
        Object [] params = {
            seccion.getNombre(), seccion.getHoraInicio(),
            seccion.getHoraFin(), seccion.getCapacidad(),
            seccion.getDia(),
            seccion.getClaseImpartida().getIdClase(),
            seccion.getSalon().getIdSalon(),
            seccion.getInstrctAsignado().getIdInstructor(),
            seccion.getIdSeccion()
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(updateSeccion, params);
    }
       
    /**
    * Metodo para actualizar una clase. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param clase un objeto de la clase Clase.
    * @throws Exception lanza una excepcion si se produce un error al actualizar
    *                   los datos de la clase.
    */
    public void actualizarclase(Clase clase) throws Exception{
        String query = "update Clases "+
                       "set nombre = ?, descripcion = ?, "+
                       " precio = ? "+
                       "where idClase = ?;";
        Object [] params = {
            clase.getNombre(), clase.getDescripcion(),
            clase.getPrecio(), clase.getIdClase()
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(query, params); 
    }
       
    /**
    * Metodo para actualizar una membresia. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param membresia un objeto de la clase Membresia.
    * @throws Exception lanza una excepcion si se produce un error al actualizar
    *                   la membresia.
    */
    public void actualizarMembresia(Membresia membresia) throws Exception{
        String query = "update membresias "+
                       "set nombre = ?, descripcion = ?, "+
                       "tipo = ?, precio = ? "+
                       "where idMembresia = ?;";
        Object [] params = {
            membresia.getNombre(), membresia.getDescripcion(),
            membresia.getTipo(), membresia.getPrecio(),
            membresia.getIdMembresia()
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(query, params);
    }
    
    /**
    * Metodo para eliminar los telefonos. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param idPersona el id de la persona.
    * @throws Exception lanza una excepcion si se produce un error al eliminar
    *         los telefonos.
    */
    private void clearTels(int idPersona) throws Exception{
        String query = "delete from telefonos "+
                       "where idPersona = ?;";
        Object [] params = { idPersona };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(query, params);
    }
       
    /**
    * Metodo para cancelar un empleado. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param emp un objeto de la clase Empleado.
    * @throws Exception lanza una excepcion si se produce un error al eliminar
    *                   el empleado.
    */
    public void cancelarEmpleado(Empleado emp) throws Exception{
        previousState  = null;
        String canInst = "delete from instructores "+
                         "where idInstructor = ?;";
        String canPAdm = "delete from persadministrativo "+
                         "where idEmpleado = ?;";
        Object [] params;
        
        genericDAO.prepareConnection();
        genericDAO.getConnection().setAutoCommit(false);
        try{
            previousState = genericDAO.getConnection().setSavepoint();

            if(emp instanceof Instructor){
                params = new Object[]{ ((Instructor)emp).getIdInstructor() };
                eliminarEmpleado(emp);
                genericDAO.executeUpdate(canInst, params);
            }
            else{
                params = new Object[]{ emp.getIdEmpleado() };
                genericDAO.executeUpdate(canPAdm, params);
                eliminarEmpleado(emp);
            }

            clearTels(emp.getIdPersona());
            eliminarPersona(emp.getIdPersona());
            eliminarDireccion(emp.getDireccion());
            
            genericDAO.getConnection().commit();
        }catch(Exception ex){
            genericDAO.getConnection().rollback(previousState);
            throw ex;
        }finally{
            genericDAO.getConnection().setAutoCommit(true);
        }
    }
   
    /**
    * Metodo para eliminar un empleado. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param emp un objeto de la clase Empleado.
    * @throws Exception lanza una excepcion si se produce un error al eliminar
    *                   el empleado.
    */
    public void eliminarEmpleado(Empleado emp) throws Exception{
        String deleteEmp = "delete from empleados "+
                           "where idEmpleado = ?;";
        Object [] params = { emp.getIdEmpleado() };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(deleteEmp, params);
    }
       
    /**
    * Metodo para eliminar una persona. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param idPersona el id de la persona.
    * @throws Exception lanza una excepcion si se produce un error al eliminar
    *                   los datos de la persona.
    */
    public void eliminarPersona(int idPersona) throws Exception{
        String delPersona = "delete from personas "+
                            "where idPersona = ?;";
        Object [] params = { idPersona };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(delPersona, params);
    }
        
    /**
    * Metodo para eliminar una direccion. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param dir un objeto de la clase direccion.
    * @throws Exception lanza una excepcion si se produce un error al eliminar
    *                   los datos de la direccion.
    */
    public void eliminarDireccion(Direccion dir) throws Exception{
        String delDireccion = "delete from direcciones "+
                              "where idDireccion = ?;";
        Object [] params = { dir.getIdDireccion() };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(delDireccion, params);
    }
       
    /**
    * Metodo para eliminar una seccion. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param idSeccion el id de la seccion.
    * @throws Exception lanza una excepcion si se produce un error al eliminar
    *                   los datos de la seccion.
    */
    public void eliminarSeccion(int idSeccion) throws Exception{
        String delSeccion = "delete from Secciones "+
                            "where idSeccion = ?;";
        Object [] params = { idSeccion };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(delSeccion, params);
    }
    
    /**
    * Metodo para retirar a un socio de una seccion. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param idSocio el id del socio.
    * @param idSeccion el id de la seccion.
    * @throws Exception lanza una excepcion si se produce un error al eliminar
    *                   los datos del socio.
    */
    public void retirarSocio(int idSocio, int idSeccion) throws Exception{
        String query = "delete from Secciones_Socio "+
                       "where idSocio = ? and idSeccion = ?;";
        Object [] params = {
            idSocio, idSeccion
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(query, params);
    }
    
    /**
    * Metodo para eliminar un socio. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param soc un objeto de la clase Socio.
    * @throws Exception lanza una excepcion si se produce un error al eliminar
    *                   los datos del socio.
    */
    public void eliminarSocio(Socio soc) throws Exception{
        previousState = null;
        String deleteSoc = "delete from socios "+
                           "where idSocio = ?;";
        Object [] params = { soc.getIdSocio() };
        
        genericDAO.prepareConnection();        
        genericDAO.getConnection().setAutoCommit(false);
        try{
            previousState = genericDAO.getConnection().setSavepoint();
            
            genericDAO.executeUpdate(deleteSoc, params);
            clearTels(soc.getIdPersona());
            eliminarPersona(soc.getIdPersona());
            eliminarDireccion(soc.getDireccion());
            
            genericDAO.getConnection().commit();
        }catch(Exception ex){
            genericDAO.getConnection().rollback(previousState);
            throw ex;
        }finally{
            genericDAO.getConnection().setAutoCommit(true);
        }
        
    }
        /**
    * Metodo para crear una clase. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param clase un objeto de la clase Clase.
    * @throws Exception lanza una excepcion si se produce un error al registrar
    *                   los datos de la nueva clase.
    */
    public void crearClase(Clase clase) throws Exception{
        String insertClas = "Insert into Clases(nombre, descripcion, precio) "+
                                 "values(?, ?, ?);";
        Object [] params = {
            clase.getNombre(), clase.getDescripcion(),
            clase.getPrecio()
        };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(insertClas, params);
    }
        
    /**
    * Metodo para eliminar una clase. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param clase un objeto de la clase Clase.
    * @throws Exception lanza una excepcion si se produce un error al eliminar
    *                   los datos de la clase.
    */
    public void eliminarClase(Clase clase) throws Exception{
        String deleteClas = "delete from clases "+
                           "where idClase = ?;";
        Object [] params = { clase.getIdClase() };
        
        genericDAO.prepareConnection();
        genericDAO.executeUpdate(deleteClas, params);
    }
    
    public void prepareClose() throws Exception{
        if(genericDAO.getConnection().getAutoCommit()){
            genericDAO.closeConneciton();
        }
    }
    
    /**
    * Implementa el patron de diseño singleton para manejar la instanciacion
    * de los objetos de la clase y solo permitir una desde el mismo entorno
    * de trabajo. 
    * <p>
    * 
    * @return la instancia actual de la clase GymManagerDAO y si no
    *         existe ninguna la crea y luego la retorna
    * @throws Exception lanza una excepcion si se produce un error al crear
    *                   los componentes de la base de datos o al obtener los
    *                   credenciales necesarios para conectarse a la misma.
    * 
    */
    public static GymManagerDAO getInstance() throws Exception{
        if( instance == null){
            instance = new GymManagerDAO();
        }
        return instance;
    }
}
