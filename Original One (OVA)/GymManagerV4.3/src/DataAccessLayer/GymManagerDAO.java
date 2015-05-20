/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */

package DataAccessLayer;

import DomainModelLayer.Entidades.*;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Vector;

/**
* Clase que almacena los datos de la conexion a la base de datos
* y todos sus metodos . 
*/
public class GymManagerDAO {
    private static GymManagerDAO instance;
    private String dbUser;
    private String dbPass;
    private static String url = "jdbc:mysql://localhost:3306/gymmanagerV5";
    private static String driver = "com.mysql.jdbc.Driver";
    private Connection conexionBD;
    private Savepoint previousState;
    private Properties dbProps;
      
    /**
    * Constructor privado para la clase GymManagerDAO.
    */
    private GymManagerDAO() throws Exception{
        dbProps = new Properties();
        String path = System.getProperty("user.home")+"\\GymManager\\DB.properties";
        dbProps.load(new FileInputStream(path));
        dbUser = dbProps.getProperty("DBuser");
        dbPass = dbProps.getProperty("DBpass");
        
        Class.forName(driver).newInstance();
        conexionBD = DriverManager.getConnection(url, dbUser, dbPass);
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
        ResultSet queryResult = null;
        PreparedStatement insertEmp =  null;
        PreparedStatement insertPAdm = null;
        PreparedStatement insertInst = null;
        previousState = null;
        int idEmpleado = 0;
        int idPersona = 0;
        
        try{
            conexionBD.setAutoCommit(false);

            previousState = conexionBD.setSavepoint();

            int idDireccion = registrarDireccion(emp.getDireccion());
            idPersona = registrarPersona(emp, idDireccion);

            for(Telefono obj: emp.getTelefonos())
                registrarTelefono(obj, idPersona);
            
            String insertEmpleado = "Insert into Empleados(idEmpleado, puesto, salario, idPersona) "+
                                    "Values(null, ?, ?, ?);";
            insertEmp = conexionBD.prepareStatement(insertEmpleado,
                                                    PreparedStatement.RETURN_GENERATED_KEYS);
            
            insertEmp.setString(1, emp.getPuesto());
            insertEmp.setDouble(2, emp.getSalario());
            insertEmp.setInt(3, idPersona);

            insertEmp.executeUpdate();
            queryResult = insertEmp.getGeneratedKeys();
            
            while(queryResult.next())
                idEmpleado = queryResult.getInt(1);
            
            insertEmp.close();
            
            if( emp instanceof PersAdministrativo){
                String insertPersAdm = "Insert Into PersAdministrativo "+
                                          "Values (?, ?, ?);";

                insertPAdm = conexionBD.prepareStatement(insertPersAdm);
                insertPAdm.setInt(1, idEmpleado);
                insertPAdm.setString(2, ((PersAdministrativo)emp).getUserName());
                insertPAdm.setString(3, ((PersAdministrativo)emp).getPassword());

                insertPAdm.executeUpdate();
                insertPAdm.close();
            }
            else{
                String insertInstructor = "Insert into Instructores "+
                                          "Values(null, ?, ?);";
                insertInst = conexionBD.prepareStatement(insertInstructor);
                insertInst.setString( 1, ((Instructor)emp).getEspecialidad() );
                insertInst.setInt(2, idEmpleado);
                
                insertInst.executeUpdate();
                insertInst.close();
            }
            
            conexionBD.commit();
        }catch( Exception ex){
            conexionBD.rollback(previousState);
            throw ex;
        }
        finally{
            conexionBD.setAutoCommit(true);
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
        PreparedStatement insertTel = null;
        
        String insertTelefono = "Insert into Telefonos "+
                                 "values(null, ?, ?, ?);";
        
        insertTel = conexionBD.prepareStatement(insertTelefono);

        insertTel.setString(1, telefono.getNumero());
        insertTel.setString(2, telefono.getTipo());
        insertTel.setInt(3, idPersona);

        insertTel.executeUpdate();  
        insertTel.close();
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
        ResultSet queryResult = null;
        PreparedStatement insertDir = null;
        int idGenerado = 0;
        
        String insertDireccion = "Insert into Direcciones "+
                                 "values(null, ?, ?, ?, ?);";
        
        insertDir = conexionBD.prepareStatement(insertDireccion,
                                               PreparedStatement.RETURN_GENERATED_KEYS);

        insertDir.setString(1, direccion.getMunicipio());
        insertDir.setString(2, direccion.getSector());
        insertDir.setString(3, direccion.getCalle());
        insertDir.setInt(4, direccion.getNumero());

        insertDir.executeUpdate();
        queryResult = insertDir.getGeneratedKeys();

        while(queryResult.next())
            idGenerado = queryResult.getInt(1);

        insertDir.close();

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
        ResultSet queryResult = null;
        PreparedStatement insertPer = null;
        int idPersona = 0;
        String insertPersona = "Insert into Personas "+
                                 "Values(null, ?, ?, ?, ?, ?, ?);";
        
        insertPer = conexionBD.prepareStatement(insertPersona,
                                               PreparedStatement.RETURN_GENERATED_KEYS); 

        insertPer.setString(1, persona.getCedula());
        insertPer.setString(2, persona.getNombre());
        insertPer.setString(3, persona.getApellido());
        insertPer.setString(4, String.valueOf(persona.getSexo() ) ); 
        insertPer.setDate(5, new Date(persona.getFechaNacimiento().getTime()));
        insertPer.setInt(6, idDireccion);
        
        insertPer.executeUpdate();
        queryResult = insertPer.getGeneratedKeys();
        while(queryResult.next())
            idPersona = queryResult.getInt(1);
        
        insertPer.close();
        
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
        PreparedStatement insertSoc = null;
        String insertSocio = "insert into socios "+
                             "values(null, null, ?, ?, ?, ?, 'V');";
        previousState = null;
        int idPersona = 0;
        
        try{
            conexionBD.setAutoCommit(false);
            previousState = conexionBD.setSavepoint();
            
            int idDireccion = registrarDireccion(socio.getDireccion());
            idPersona = registrarPersona(socio, idDireccion);

            for(Telefono obj: socio.getTelefonos())
                registrarTelefono(obj, idPersona);
            
            insertSoc = conexionBD.prepareStatement(insertSocio);
            insertSoc.setDouble(1, socio.getPeso());
            insertSoc.setDouble(2, socio.getEstatura());
            insertSoc.setInt(3, socio.getMembresia().getIdMembresia());
            insertSoc.setInt(4, idPersona);
            insertSoc.executeUpdate();
            
            conexionBD.commit();
        }catch(Exception ex){
            conexionBD.rollback(previousState);
            ex.printStackTrace();
            throw ex;
        }
        
        insertSoc.close();

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
                           "values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement insertSec = null; 
        insertSec = conexionBD.prepareStatement(insertSeccion);

        insertSec.setString(1, seccion.getNombre());
        insertSec.setInt(2, seccion.getDia());
        insertSec.setInt(3, seccion.getHoraInicio());
        insertSec.setInt(4, seccion.getHoraFin());
        insertSec.setInt(5, seccion.getCapacidad());
        insertSec.setInt(6, seccion.getClaseImpartida().getIdClase());
        insertSec.setInt(7, seccion.getSalon().getIdSalon());
        insertSec.setInt(8, seccion.getInstrctAsignado().getIdInstructor());
        insertSec.setDate(9, new Date(seccion.getFechaInicio().getTime()));
        insertSec.setDate(10, new Date(seccion.getFechaFin().getTime()));
        
        insertSec.executeUpdate();
        
        insertSec.close();
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
        PreparedStatement insertMemb = conexionBD.prepareStatement(query);
        
        insertMemb.setString(1, membresia.getNombre());
        insertMemb.setString(2, membresia.getDescripcion());
        insertMemb.setString(3, membresia.getTipo());
        insertMemb.setDouble(4, membresia.getPrecio());
        
        insertMemb.executeUpdate();
        
        insertMemb.close();
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
        PreparedStatement regAsist = conexionBD.prepareStatement(query);
        
        regAsist.setInt(1, idSocio);
        regAsist.setDate(2, new Date(new java.util.Date().getTime()));
        regAsist.executeUpdate();
        
        regAsist.close();
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
        ResultSet queryResult = null;
        Vector<Empleado> empleados = new Vector();
        PreparedStatement selectEmp = null;
        String selectEmpleado = null;
        Empleado emp = null;
        
        if( indiceCampo == 1){
            selectEmpleado = "select * from vEmpleados "+
                             "where idEmpleado = ?;";
            selectEmp = conexionBD.prepareStatement(selectEmpleado);
            selectEmp.setInt(1, Integer.valueOf(filtro));
        }
        else{
            selectEmpleado = "call usp_buscarEmpleado(?, ?);";
            selectEmp = conexionBD.prepareStatement(selectEmpleado);
            selectEmp.setString(1, filtro);
            selectEmp.setInt(2, indiceCampo);
        }

        queryResult = selectEmp.executeQuery();

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
        PreparedStatement insertInscr = conexionBD.prepareStatement(query);
        insertInscr.setInt(1, idSocio);
        insertInscr.setInt(2, idSeccion);
        
        insertInscr.executeUpdate();
        
        insertInscr.close();
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
        String query = "call usp_registrarPago(?, ?);";
        String updFact = "update facturas set idEmpleado = ? where idFactura = ?;";
        previousState = null;
        PreparedStatement regPago = null;
        PreparedStatement updFactura = null;
                
        try{
            conexionBD.setAutoCommit(false);
            previousState = conexionBD.setSavepoint();
            
            regPago = conexionBD.prepareStatement(query);

            regPago.setInt(1, factura.getIdFactura());
            regPago.setDouble(2, monto);
            regPago.executeUpdate();
            
            updFactura = conexionBD.prepareStatement(updFact);
            updFactura.setInt(1, empleado.getIdEmpleado());
            updFactura.setInt(2, factura.getIdFactura());
            updFactura.executeUpdate();
            
            conexionBD.commit();
        }catch(SQLException ex){
            ex.printStackTrace();
            throw ex;
        }finally{
            regPago.close();
            updFactura.close();
        }
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
        ResultSet queryResult = null;
        PreparedStatement selectEmp = null;
        String selectEmpleado = null;
        Empleado empleado = null;
        queryResult = null;
        
        if(tipo.equals("Instructor") ){
            selectEmpleado = "select * from vInstructores "+
                             "where idInstructor = ?;";
        }
        else{
            selectEmpleado = "select * from vAdministrativos "+
                             "where idEmpleado = ?;";
        }
        
        selectEmp = conexionBD.prepareStatement(selectEmpleado);
        selectEmp.setInt(1, idEmpleado);
        queryResult = selectEmp.executeQuery();

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
        String query = "select * from vAdministrativos "+
                       "where userName = ? and password = ? and puesto = ?;";
        PreparedStatement selEmp = conexionBD.prepareStatement(query);
        String conf = "SET GLOBAL event_scheduler = ON;";
        Statement runConf = null;
        ResultSet queryResult = null;
        
        selEmp.setString(1, userName);
        selEmp.setString(2, password);
        selEmp.setString(3, puesto);
        
        queryResult = selEmp.executeQuery();
        
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
        
        selEmp.close();
        
        runConf = conexionBD.createStatement();
        runConf.execute(conf);
        runConf.close();
        
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
        PreparedStatement selectDir = null;
        Direccion direccion = null;
        ResultSet dataDir = null;
        String selectDireccion = "Select * from direcciones "+
                                 "Where idDireccion = ?;";
        
        selectDir = conexionBD.prepareStatement(selectDireccion);
        selectDir.setInt(1, idDireccion);
        dataDir = selectDir.executeQuery();
        
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
        PreparedStatement selectTel = null;
        String selectTelefonos = "call usp_buscarTelefonos(?);";
        ResultSet dataTels = null;
        Vector<Telefono> telefonos = new Vector();
        
        selectTel = conexionBD.prepareStatement(selectTelefonos);
        selectTel.setInt(1, idPersona);
        dataTels = selectTel.executeQuery();
        
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
        ResultSet queryResult = null;
        Membresia membresia = null;
        PreparedStatement findMem = null;
        String findMembresia = "select * from membresias "+
                               "where idMembresia = ?;";
        findMem = conexionBD.prepareStatement(findMembresia);
        findMem.setInt(1, idMembresia);
        queryResult = findMem.executeQuery();
        
        while(queryResult.next()){
            membresia = new Membresia(queryResult.getInt("idMembresia"),
                    queryResult.getString("nombre"),
                    queryResult.getString("descripcion"),
                    queryResult.getString("tipo"),
                    queryResult.getDouble("precio")
            );
        }
        
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
        ResultSet queryResult = null;
        Vector<Membresia> membresias = new Vector<Membresia>();
        String selectMembresias = "select * from membresias;";
        PreparedStatement selectMembs = conexionBD.prepareStatement(selectMembresias);
        
        queryResult = selectMembs.executeQuery();
        
        while(queryResult.next()){
            membresias.add(new Membresia(queryResult.getInt("idMembresia"),
                    queryResult.getString("nombre"),
                    queryResult.getString("descripcion"),
                    queryResult.getString("tipo"),
                    queryResult.getDouble("precio"))
            );
        }
        
        selectMembs.close();
        
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
        ResultSet queryResult = null;
        PreparedStatement selectSoc = null;
        String selectSocio = "call usp_buscarSocio(?, ?);";
        String selectSocioBI = "select * from vSocios "+
                               "where idSocio = ?;";
        Vector<Socio> socios = new Vector();
        
        if(campoBusqueda == 1){
            selectSoc = conexionBD.prepareStatement(selectSocioBI);
            selectSoc.setInt(1, Integer.valueOf(filtro));
        }
        else{
            selectSoc = conexionBD.prepareStatement(selectSocio);
            selectSoc.setString(1, filtro);
            selectSoc.setInt(2, campoBusqueda);
        }
        queryResult = selectSoc.executeQuery();
        
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
        ResultSet queryResult = null;
        Socio socio = null;
        PreparedStatement selectSoc = null;
        String selectSocio = "select * from vSocios "+
                               "where idSocio = ?;";
        
        selectSoc = conexionBD.prepareStatement(selectSocio);
        selectSoc.setInt(1, idSocio);
        queryResult = selectSoc.executeQuery();
        
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

        selectSoc.close();
        
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
        ResultSet queryResult = null;
        String selectClase = "select * from clases "+
                             "where idClase = ?;";
        Clase clase = null;
        PreparedStatement selectClas = conexionBD.prepareStatement(selectClase);
        selectClas.setInt(1, idClase);
        queryResult = selectClas.executeQuery();
        
        while(queryResult.next()){
            clase = new Clase(queryResult.getInt("idClase"),
                    queryResult.getString("nombre"),
                    queryResult.getString("descripcion"),
                    queryResult.getDouble("precio")
            );
            clase.setSecciones(buscarSecciones(4, clase.getIdClase()));              
        }
        selectClas.close();
        
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
        PreparedStatement selectDets = conexionBD.prepareStatement(query);
        ResultSet queryResult = null;
        
        selectDets.setInt(1, idFactura);
        queryResult = selectDets.executeQuery();
        
        while(queryResult.next()){
            detalles.add(new DetalleFactura(
                    queryResult.getInt("idDetalleFactura"),
                    queryResult.getString("detalle"),
                    queryResult.getDouble("precio")
                    )                   
            );
        }
        selectDets.close();
        
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
        String query = "call usp_obtenerFacturasPend(?);";
        ResultSet queryResult = null;
        Vector<Factura> facturas = new Vector();
        PreparedStatement selectFacts = conexionBD.prepareStatement(query);
        
        selectFacts.setInt(1, idSocio);
        queryResult = selectFacts.executeQuery();
        
        while(queryResult.next()){
            facturas.add(new Factura(
                    queryResult.getInt("idFactura"),
                    queryResult.getDate("fechaCreacion"),
                    buscarDetallesFactura(queryResult.getInt("idFactura")),
                    queryResult.getString("nfc"),
                    null,
                    null
                    )    
            );
        }
        
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
        ResultSet queryResult = null;
        Vector<Clase> clases = new Vector();
        String selectClases = "select * from clases ";
        PreparedStatement selectClas = null;
        
        if(campoBusqueda == 2){
            selectClases += "where nombre = ?;";
            selectClas = conexionBD.prepareStatement(selectClases);
            selectClas.setString(1, filtro);
        }
        else{
            selectClases += "where precio = ?;";
            selectClas = conexionBD.prepareStatement(selectClases);
            selectClas.setDouble(1, Double.valueOf(filtro));
        }
        queryResult = selectClas.executeQuery();
        
        while(queryResult.next()){
            Clase clase = new Clase(queryResult.getInt("idClase"),
                    queryResult.getString("nombre"),
                    queryResult.getString("descripcion"),
                    queryResult.getDouble("precio")        
            );
            clase.setSecciones(buscarSecciones(4, clase.getIdClase()));
            
            clases.add(clase);
        }
        
        selectClas.close();
        
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
        ResultSet queryResult = null;
        Vector<Clase> clases = new Vector();
        String selectClases = "select * from clases;";
        PreparedStatement selectClas = conexionBD.prepareStatement(selectClases);

        queryResult = selectClas.executeQuery();
        
        while(queryResult.next()){
            Clase clase = new Clase(queryResult.getInt("idClase"),
                    queryResult.getString("nombre"),
                    queryResult.getString("descripcion"),
                    queryResult.getDouble("precio")        
            );
            clase.setSecciones(buscarSecciones(4, clase.getIdClase()));
            
            clases.add(clase);
        }
        
        selectClas.close();
        
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
        ResultSet queryResult = null;
        Vector<Seccion> secciones = new Vector();
        PreparedStatement selectSecs = null;
        String selectSecciones = null;
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

        selectSecs = conexionBD.prepareStatement(selectSecciones);
        selectSecs.setInt(1, filtro);

        queryResult = selectSecs.executeQuery();
        
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
        PreparedStatement selSecs = null;
        ResultSet queryResult = null;
        String selectSecs = "select * from vSecciones where ";
        
        if(disponibles){
            selectSecs += "capacidad > 0 && ";
        }
        
        if( campoBusqueda == 2){
            selectSecs += "nombreClase = ?;";
        }
        else if(campoBusqueda == 3){
            selectSecs += "nombreSalon = ?;";
        }
        else if(campoBusqueda == 4){
            selectSecs = "call usp_findSecsByInstructor(?);";
        }
        else if(campoBusqueda == 5){
            selectSecs += "dia = ?;";
        }
        else{
            selectSecs += "idSeccion = ?;";
        }
        
        if( campoBusqueda == 1 || campoBusqueda == 5){
            selSecs = conexionBD.prepareStatement(selectSecs);
            selSecs.setInt(1, Integer.valueOf(filtro));
        }
        else{
            selSecs = conexionBD.prepareStatement(selectSecs);
            selSecs.setString(1, filtro);
        }
        queryResult = selSecs.executeQuery();
        
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
        selSecs.close();

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
        String query = "call usp_buscarSeccionesSocio(?);";
        PreparedStatement selSecs = conexionBD.prepareStatement(query);
        ResultSet queryResult = null;
        
        selSecs.setInt(1, idSocio);
        queryResult = selSecs.executeQuery();
        
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
        ResultSet queryResult = null;
        PreparedStatement selSocs = conexionBD.prepareStatement(selecSocios);
        selSocs.setInt(1, idSeccion);
        
        queryResult = selSocs.executeQuery();
        
        while(queryResult.next()){
            socios.add(
                    buscarSocio(queryResult.getInt("idSocio"))
            );
        }
        selSocs.close();
        
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
        ResultSet queryResult = null;
        Salon salon = null;
        String selectSalon = "select * from salones "+
                             "where idSalon = ?;";
        PreparedStatement buscSalon = null;
        
        buscSalon = conexionBD.prepareStatement(selectSalon);
        buscSalon.setInt(1, idSalon);
        queryResult = buscSalon.executeQuery();
        
        while(queryResult.next()){
            salon = new Salon(
                    queryResult.getInt("idSalon"),
                    queryResult.getString("nombre"),
                    buscarSecciones(2, idSalon)
            );
        }
        
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
        ResultSet queryResult = null;
        Vector <Salon> salones = new Vector();
        String selectSalones = "select * from salones;";
        PreparedStatement selectSal = conexionBD.prepareStatement(selectSalones);
        
        queryResult = selectSal.executeQuery();
        
        while(queryResult.next()){
           Salon salon = new Salon(
                    queryResult.getInt("idSalon"),
                    queryResult.getString("nombre"),
                    buscarSecciones(2, queryResult.getInt("idSalon"))
            );
           salones.add(salon);
        }
        
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
        PreparedStatement updateEmp = null;
        String updateEmpleado = "Update Empleados "+
                                "set puesto = ?, salario = ? "+
                                "where idEmpleado = ?;";
        previousState = null;
        conexionBD.setAutoCommit(false);
        
        try{
            previousState = conexionBD.setSavepoint();
           
            actualizarTelefonos(emp.getTelefonos(), emp.getIdPersona());
            actualizarDireccion(emp.getDireccion());
            actualizarPersona(emp);
            
            updateEmp = conexionBD.prepareStatement(updateEmpleado);
            updateEmp.setString(1, emp.getPuesto());
            updateEmp.setDouble(2, emp.getSalario());
            updateEmp.setInt(3, emp.getIdEmpleado());
            updateEmp.executeUpdate();
            updateEmp.close();
            
            if( emp instanceof Instructor)
                actualizarInstructor((Instructor)emp);
            else
                actualizarPersAdm((PersAdministrativo)emp);
            
            conexionBD.commit();
        }catch(Exception ex){
            conexionBD.rollback(previousState);
            throw ex;
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
        PreparedStatement updateSoc = null;
        String updateSocio = "update socios "+
                             "set peso = ?, "+
                             "estatura = ?, "+
                             "idMembresia = ? "+
                             "where idSocio = ?;";
        previousState = null;
        conexionBD.setAutoCommit(false);
        try{
            previousState = conexionBD.setSavepoint();
           
            actualizarTelefonos(socio.getTelefonos(), socio.getIdPersona());
            actualizarDireccion(socio.getDireccion());
            actualizarPersona(socio);

            updateSoc = conexionBD.prepareStatement(updateSocio);
            updateSoc.setDouble(1, socio.getPeso());
            updateSoc.setDouble(2, socio.getEstatura());
            updateSoc.setInt(3, socio.getMembresia().getIdMembresia());
            updateSoc.setInt(4, socio.getIdSocio());
            updateSoc.executeUpdate();

            updateSoc.close();
            
            conexionBD.commit();
        }catch(Exception ex){
            conexionBD.rollback(previousState);
            throw ex;
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
        PreparedStatement updateSoc = conexionBD.prepareStatement(query);
        
        updateSoc.setString(1, String.valueOf(socio.getStatus()));
        updateSoc.setInt(2, socio.getIdSocio());
        updateSoc.executeUpdate();
        
        updateSoc.close();
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
        
        PreparedStatement updinst = conexionBD.prepareStatement(updInstructor);
        
        updinst.setString(1, inst.getEspecialidad());
        updinst.executeUpdate();
        
        updinst.close();
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
        
        PreparedStatement updPAdm = conexionBD.prepareStatement(updPersAdm);
        
        updPAdm.setString(1, pa.getUserName());
        updPAdm.setString(2, pa.getPassword());
        updPAdm.setInt(3, pa.getIdEmpleado());

        updPAdm.executeUpdate();
        
        updPAdm.close();
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
        PreparedStatement updateTel = null;
        String updateTelefono = "update telefonos "+
                                "set numero = ?, "+
                                "tipo = ? "+
                                "where idTelefono = ?;";
        
        updateTel = conexionBD.prepareStatement(updateTelefono);
        updateTel.setString(1, tel.getNumero());
        updateTel.setString(2, tel.getTipo());
        updateTel.setInt(3, tel.getIdTelefono());
        updateTel.executeUpdate();
        
        updateTel.close();
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

        for(Telefono obj: viejos){
            for( int i = 0; i < telefonos.size(); i++){
                if(obj.getIdTelefono() == telefonos.get(i).getIdTelefono()){
                    flag = false;
                    break;
                }
                else
                    flag = true;

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
        PreparedStatement updateDir = null;
        String updateDireccion = "update direcciones "+
                                 "set municipio = ?,  sector = ?, "+
                                 "calle = ?,  numero = ? "+
                                 "where idDireccion = ?;";
        
        updateDir = conexionBD.prepareStatement(updateDireccion);
        updateDir.setString(1, direccion.getMunicipio());
        updateDir.setString(2, direccion.getSector());
        updateDir.setString(3, direccion.getCalle());
        updateDir.setInt(4, direccion.getNumero());
        updateDir.setLong(5, direccion.getIdDireccion());

        updateDir.executeUpdate();
        
        updateDir.close();
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
        PreparedStatement updatePers = null;
        String updatePersona = "update personas "+
                               "set cedula = ?, "+
                               "nombre = ?, "+
                               "apellido = ?,  sexo = ?, "+
                               "fechaNacimiento = ? "+
                               "where idPersona = ?;";

        updatePers = conexionBD.prepareStatement(updatePersona);
        updatePers.setString(1, persona.getCedula());
        updatePers.setString(2, persona.getNombre());
        updatePers.setString(3, persona.getApellido());
        updatePers.setString(4, String.valueOf(persona.getSexo()));
        updatePers.setDate(5, new Date(persona.getFechaNacimiento().getTime()));        
        updatePers.setInt(6, persona.getIdPersona());

        updatePers.executeUpdate();
        
        updatePers.close();
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
        PreparedStatement delTel = null;
        String deleteTel = "delete from telefonos "+
                           "where idTelefono = ?;";
        
        delTel = conexionBD.prepareStatement(deleteTel);
        delTel.setInt(1, tel.getIdTelefono());
        delTel.executeUpdate();
        
        delTel.close();
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
        int sociosInscr = 0;
        ResultSet numSocios = null;
        String delQuery = "delete from Membresias "+
                          "where idMembresia = ?;";
        String query = "select count(idMembresia) from Socios "+
                       "where idMembresia = ?;";
        PreparedStatement sociosRegs = conexionBD.prepareStatement(query);
        sociosRegs.setInt(1, idMembresia);
        numSocios = sociosRegs.executeQuery();
        
        while(numSocios.next()){
            sociosInscr = numSocios.getInt(1);
        }
        
        if(sociosInscr == 0){
            PreparedStatement delMemb = conexionBD.prepareStatement(delQuery);
            delMemb.setInt(1, idMembresia);
            delMemb.executeUpdate();
            delMemb.close();
        }
        else{
            throw new Exception("Esta membresia esta en uso.");
        }
        
        sociosRegs.close();
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
        PreparedStatement updateSec = conexionBD.prepareStatement(updateSeccion);
        
        updateSec.setString(1, seccion.getNombre());
        updateSec.setInt(2, seccion.getHoraInicio());
        updateSec.setInt(3, seccion.getHoraFin());
        updateSec.setInt(4, seccion.getCapacidad());
        updateSec.setInt(5, seccion.getDia());
        updateSec.setInt(6, seccion.getClaseImpartida().getIdClase());
        updateSec.setInt(7, seccion.getSalon().getIdSalon());
        updateSec.setInt(8, seccion.getInstrctAsignado().getIdInstructor());
        updateSec.setInt(9, seccion.getIdSeccion());
        
        updateSec.executeUpdate();
        
        updateSec.close();
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
        PreparedStatement updateClase = conexionBD.prepareStatement(query);
        updateClase.setString(1, clase.getNombre());
        updateClase.setString(2, clase.getDescripcion());
        updateClase.setDouble(3, clase.getPrecio());
        updateClase.setInt(4, clase.getIdClase());
        updateClase.executeUpdate();
        
        updateClase.close();        
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
        PreparedStatement updateMemb = conexionBD.prepareStatement(query);
        
        updateMemb.setString(1, membresia.getNombre());
        updateMemb.setString(2, membresia.getDescripcion());
        updateMemb.setString(3, membresia.getTipo());
        updateMemb.setDouble(4, membresia.getPrecio());
        updateMemb.setInt(5, membresia.getIdMembresia());
        
        updateMemb.executeUpdate();
        
        updateMemb.close();
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
        PreparedStatement stm = conexionBD.prepareStatement(query);
        stm.setInt(1, idPersona);
        stm.executeUpdate();
        
        stm.close();
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
        PreparedStatement canEmp = null;
        previousState  = null;
        String canInst = "delete from instructores "+
                         "where idInstructor = ?;";
        String canPAdm = "delete from persadministrativo "+
                         "where idEmpleado = ?;";
        
        conexionBD.setAutoCommit(false);
        try{
            previousState = conexionBD.setSavepoint();

            if(emp instanceof Instructor){
                eliminarEmpleado(emp);
                canEmp = conexionBD.prepareStatement(canInst);
                canEmp.setInt(1, ((Instructor)emp).getIdInstructor());
                canEmp.executeUpdate();
            }
            else{
                canEmp = conexionBD.prepareStatement(canPAdm);
                canEmp.setInt(1, emp.getIdEmpleado());
                canEmp.executeUpdate();
                canEmp.close();
                eliminarEmpleado(emp);
            }
            
            clearTels(emp.getIdPersona());
            eliminarPersona(emp.getIdPersona());
            eliminarDireccion(emp.getDireccion());
            
            conexionBD.commit();
        }catch(Exception ex){
            conexionBD.rollback(previousState);
            throw ex;
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
        PreparedStatement delEmp = null;
        String deleteEmp = "delete from empleados "+
                           "where idEmpleado = ?;";
        delEmp = conexionBD.prepareStatement(deleteEmp);
        delEmp.setInt(1, emp.getIdEmpleado());
        delEmp.executeUpdate();
        
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
        PreparedStatement delPers = null;
        String delPersona = "delete from personas "+
                            "where idPersona = ?;";
        delPers = conexionBD.prepareStatement(delPersona);
        delPers.setInt(1, idPersona);
        delPers.executeUpdate();
        
        delPers.close();
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
        PreparedStatement delDir = null;
        String delDireccion = "delete from direcciones "+
                              "where idDireccion = ?;";
        delDir = conexionBD.prepareStatement(delDireccion);
        delDir.setInt(1, dir.getIdDireccion());
        delDir.executeUpdate();
        
        delDir.close();
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
        PreparedStatement delSec = conexionBD.prepareStatement(delSeccion);
        
        delSec.setInt(1, idSeccion);
        delSec.executeUpdate();
        
        delSec.close();
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
        PreparedStatement delInsc = conexionBD.prepareStatement(query);
        
        delInsc.setInt(1, idSocio);
        delInsc.setInt(2, idSeccion);
        delInsc.executeUpdate();
        
        delInsc.close();
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
        PreparedStatement delSoc = null;
        previousState = null;
        String deleteSoc = "delete from socios "+
                           "where idSocio = ?;";
        
        conexionBD.setAutoCommit(false);
        try{
            previousState = conexionBD.setSavepoint();
            
            delSoc = conexionBD.prepareStatement(deleteSoc);
            delSoc.setInt(1, soc.getIdSocio());
            delSoc.executeUpdate();
            delSoc.close();
            
            clearTels(soc.getIdPersona());
            eliminarPersona(soc.getIdPersona());
            eliminarDireccion(soc.getDireccion());
            
            conexionBD.commit();
        }catch(Exception ex){
            conexionBD.rollback(previousState);
            throw ex;
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
        PreparedStatement insertCla = null;
        
        String insertClas = "Insert into Clases "+
                                 "values(null, ?, ?, ?);";
        
        insertCla = conexionBD.prepareStatement(insertClas);

        insertCla.setString(1, clase.getNombre());
        insertCla.setString(2, clase.getDescripcion());
        insertCla.setDouble(3, clase.getPrecio());

        insertCla.executeUpdate();  
        insertCla.close();
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
        PreparedStatement delCla = null;
        String deleteClas = "delete from clases "+
                           "where idClase = ?;";
        
        delCla = conexionBD.prepareStatement(deleteClas);
        delCla.setInt(1, clase.getIdClase());
        delCla.executeUpdate();
        
        delCla.close();
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
