/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Controllers;

import DataAccessLayer.GymManagerDAO;
import DomainModelLayer.Entidades.Empleado;
import DomainModelLayer.Entidades.Factura;
import DomainModelLayer.Entidades.PersAdministrativo;
import DomainModelLayer.Entidades.Socio;

/**
* Clase controlador que sirve de fachada al subsistema de pago. 
*/
public class Pago {
    
    /**
    * Envia  a el data access los parametros de inicio de sesion de un empleado. 
    * <p>
    * @param userName El nombre de usuario del empleado.
    * @param password El password el empleado.
    *
    * @return los datos basicos del empleado que intenta iniciar sesion.
    */
    public static PersAdministrativo registrarLogin(String userName, String password) throws Exception{
        return GymManagerDAO.getInstance().buscarEmpleado(userName, password, "Cajero");
    }
    
    /**
    * Busca los datos de un socio basado en su numeo de identificacion
    * (id).
    * 
    * @param idSocio El id del socio.
    * @return los datos del socio buscado si es encontrado.
    * 
    */
    public static Socio buscarDatosSocio(int idSocio) throws Exception{
        Socio socio = GymManagerDAO.getInstance().buscarSocio(idSocio);
        
        if (socio == null)
            return socio;
        else
            socio.setFacturas(
                GymManagerDAO.getInstance().buscarFacturasPendientes(socio.getIdSocio())
        );        
        
        return socio;
    }
    
    /**
    * Registra el pago de una factura perteneciente a un socio.
    * <p>
    * @param factura   la factura que el socio esta pagando.
    * @param empleado  el empleado que esta registrando el pago.
    * @param monto     el monto con el cual se esta pagando la factura.
    * @param socio     el socio que esta realizando el pago de la factura.
    * @return El monto del cambio, si el monto de pago es mayor que el de
    *         la deuda de la factura.
    */
    public static double registrarPago(Factura factura, Empleado empleado, double monto, Socio socio) throws Exception{
        double cambio = monto - factura.getTotal();        
        
        if(socio.getFacturas().size() == 1){
            socio.setStatus('A');
        }
        
        GymManagerDAO.getInstance().registrarPago(factura, empleado, monto);
        
        if(GymManagerDAO.getInstance().buscarFacturasPendientes(socio.getIdSocio()).isEmpty())
            GymManagerDAO.getInstance().actualizarEstadoMembresia(socio);
        
        
        return cambio;
    }
    
}
