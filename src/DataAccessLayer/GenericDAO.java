/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Clase utilizada para manejar las operaciones con la base
 * de datos de una manera generica.
 * 
 * @author C007
 */
public class GenericDAO {
    private static String url;
    private static String driver;
    private Connection conexionBD;
    private Properties dbProps;
    private PreparedStatement stm;
    private ResultSet result;
    
    /**
    * Constructor privado para la clase GymManagerDAO.
    */
    public GenericDAO() throws Exception{
        dbProps = new Properties();
        dbProps.load(getClass().getResourceAsStream("DB.properties"));
        url = dbProps.getProperty("SqlServerConn");
        driver = dbProps.getProperty("SqlServerDriver");
        Class.forName(driver).newInstance();
        conexionBD = DriverManager.getConnection(url);
    }
    
    /**
    * Metodo utilizado para abrir la conexion a la base de datos.
    * <p>
    * @throws Exception lanza una excepcion si se produce un error al 
    *                   abrir la conexion a la base de datos.
    */
    public void openConnection() throws Exception{
        if(conexionBD.isClosed()) {
            conexionBD = DriverManager.getConnection(url);
        }
    }
    
    /**
    * Metodo utilizado para ejecutar una consulta en la base de datos
    * que no retorna nigun valor. 
    * <p>
    * Este metodo no tiene ningun valor de retorno.
    * @param query  consulta sql a ejecutar.
    * @param params valores de los parametros de la consulta.
    * @throws Exception lanza una excepcion si se produce un error al 
    *                   ejecutar la instruccion.
    */
    public void executeUpdate(String query, Object [] params) throws Exception{
        stm = conexionBD.prepareStatement(query);
        
        if(params != null) {
            for(int i = 0; i < params.length; i++){
                stm.setObject(i+1, params[i]);
            }
        }
        
        stm.executeUpdate();
        stm.close();
        
    }
    
    /**
    * Metodo utilizado para ejecutar una consulta en la base de datos
    * que retorne 1 o mas filas de datos. 
    * <p>
    * @return un objeto ResultSet con el conjunto de resultados.
    * @param query  consulta sql a ejecutar.
    * @param params valores de los parametros de la consulta.
    * @throws Exception lanza una excepcion si se produce un error al 
    *                   ejecutar la instruccion.
    */
    public ResultSet executeQuery(String query, Object [] params) throws Exception{
        stm = conexionBD.prepareStatement(query);
        
        if(params != null) {
            for(int i = 0; i < params.length; i++){
                stm.setObject(i+1, params[i]);
            }
        }
        
        result = stm.executeQuery();
        
        return result;
    }
    
    /**
    * Metodo utilizado para ejecutar una consulta en la base de datos
    * que retorne 1 unico valor. 
    * <p>
    * @return un objeto Object que representa ael resultado escalar
    *         de la ejecucion de la consulta.
    * @param query  consulta sql a ejecutar.
    * @param params valores de los parametros de la consulta.
    * @throws Exception lanza una excepcion si se produce un error al 
    *                   ejecutar la instruccion.
    */
    public Object executeScalar(String query, Object [] params) throws Exception{
        Object returnValue = null;
        stm = conexionBD.prepareStatement(query);
        
        if(params != null) {
            for(int i = 0; i < params.length; i++){
                stm.setObject(i+1, params[i]);
            }
        }
        
        result = stm.executeQuery();
        
        while(result.next()){
            returnValue = result.getObject(1);
        }
        return returnValue;
    }
    
    /**
    * Metodo utilizado para ejecutar una consulta en la base de datos
    * que retorne 1 valor entero que representa el valor del campo
    * autogenerado de la consulta.
    * 
    * Este metodo se utiliza para retornar los campos autogenerados
    * que se producen de las sentencias insert en la base de datos.
    * <p>
    * @return un objeto ResultSet con el conjunto de resultados.
    * @param query  consulta sql a ejecutar.
    * @param params valores de los parametros de la consulta.
    * @throws Exception lanza una excepcion si se produce un error al 
    *                   ejecutar la instruccion.
    */
    public int executeInsertIdent(String query, Object [] params) throws Exception{
        int generatedKey = 0;
        
        stm = conexionBD.prepareStatement(query, 
                                 PreparedStatement.RETURN_GENERATED_KEYS);
        
        if(params != null) {
            for(int i = 0; i < params.length; i++){
                stm.setObject(i+1, params[i]);
            }
        }
        
        stm.executeUpdate();
        
        result = stm.getGeneratedKeys();
        
        while(result.next()){
            generatedKey = result.getInt(1);
        }
        stm.close();
        
        return generatedKey;
    }
    
    /**
    * Metodo utilizado para obtener la conexion a la base de datos.
    * <p>
    * @return un objeto Connection que representa la conexion a la
    *         base de datos.
    * @throws Exception lanza una excepcion si se produce un error al 
    *                   establecer la conexion a la base de datos.
    */
    public Connection getConnection() throws Exception{
        prepareConnection();
        
        return conexionBD;
    }
    
    /**
    * Metodo utilizado para verificar el estado de la conexion antes
    * de utilizarla, y abrirla en caso de ser necesraio.
    * <p>
    * @throws Exception lanza una excepcion si se produce un error al 
    *                   manejar el estado de la conexion.
    */
    public void prepareConnection() throws Exception{
        if(conexionBD.isClosed()){
            openConnection();
        }
    }
    
    /**
    * Metodo utilizado para cerrar la conexion a la base de datos.
    * <p>
    *         base de datos.
    * @throws Exception lanza una excepcion si se produce un error al 
    *                   cerrar la conexion.
    */
    public void closeConneciton() throws Exception{
        if(!conexionBD.isClosed()){
            conexionBD.close();
        }
    }
}
