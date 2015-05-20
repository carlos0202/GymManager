/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Entidades;

import java.util.Date;
import java.util.Vector;

/**
* Clase que almacena los datos de los socios. 
*/
public class Socio extends Persona{
    private int idSocio;
    private double peso;
    private double estatura;
    private Date fechaIngreso;
    private Membresia membresia;
    private Vector<Factura> facturas;
    private Vector<Seccion> seccionesInscritas;
    private char status;
    
    /**
    * Constructor publico de la clase Socio. 
    * <p>
    * @param idPersona          el id de la persona.
    * @param cedula             la cedula del socio.
    * @param nombre             el nombre del socio.
    * @param apellido           el apellido del socio.
    * @param sexo               el sexo del socio.
    * @param fechaNacimiento    la fecha de nacimiento del socio.
    * @param telefonos          los telelefonos socio.
    * @param direccion          la direccion del socio.
    * @param idSocio            el id del socio.
    * @param peso               el peso del socio.
    * @param estatura           la estatura del socio.
    * @param fechaIngreso       la fecha de ingresos del socio
    * @param membresia          la membresia del socio
    * 
    */
    public Socio(int idPersona, String cedula, String nombre, String apellido, char sexo,
                 Date fechaNacimiento, Vector<Telefono> telefonos,
                 Direccion direccion, int idSocio, double peso, double estatura, Date fechaIngreso,
                 Membresia membresia) {
        super(idPersona, cedula, nombre, apellido, sexo, fechaNacimiento, telefonos, direccion);
        this.idSocio = idSocio;
        this.peso = peso;
        this.estatura = estatura;
        this.fechaIngreso = fechaIngreso;
        this.membresia = membresia; 
        this.fechaIngreso = new Date();
        this.facturas = new Vector<Factura>();
        this.seccionesInscritas = new Vector<Seccion>();
    }
     
    /**
    * Constructor publico de la clase Socio. 
    * <p>
    * @param idPersona          el id de la persona.
    * @param cedula             la cedula del socio.
    * @param nombre             el nombre del socio.
    * @param apellido           el apellido del socio.
    * @param sexo               el sexo del socio.
    * @param fechaNacimiento    la fecha de nacimiento del socio.
    * @param telefonos          los telelefonos socio.
    * @param direccion          la direccion del socio.
    * @param idSocio            el id del socio.
    * @param peso               el peso del socio.
    * @param estatura           la estatura del socio.
    * @param fechaIngreso       la fecha de ingresos del socio
    * @param membresia          la membresia del socio
    * @param status             el status del socio
    * 
    */
    public Socio(int idPersona, String cedula, String nombre, String apellido, char sexo,
                 Date fechaNacimiento, Vector<Telefono> telefonos,
                 Direccion direccion, int idSocio, double peso, double estatura, Date fechaIngreso,
                 Membresia membresia, char status) {
        super(idPersona, cedula, nombre, apellido, sexo, fechaNacimiento, telefonos, direccion);
        this.idSocio = idSocio;
        this.peso = peso;
        this.estatura = estatura;
        this.fechaIngreso = fechaIngreso;
        this.membresia = membresia; 
        this.fechaIngreso = new Date();
        this.facturas = new Vector<Factura>();
        this.seccionesInscritas = new Vector<Seccion>();
        this.status = status;
    }
        
    /**
    * Constructor publico de la clase Socio. 
    * <p>
    * @param cedula             la cedula del socio.
    * @param nombre             el nombre del socio.
    * @param apellido           el apellido del socio.
    * @param sexo               el sexo del socio.
    * @param fechaNacimiento    la fecha de nacimiento del socio.
    * @param telefonos          los telelefonos socio.
    * @param direccion          la direccion del socio.
    * @param idSocio            el id del socio.
    * @param peso               el peso del socio.
    * @param estatura           la estatura del socio.
    * @param fechaIngreso       la fecha de ingresos del socio
    * @param membresia          la membresia del socio
    * 
    */
    public Socio(String cedula, String nombre, String apellido, char sexo,
                 Date fechaNacimiento, Vector<Telefono> telefonos,
                 Direccion direccion, double peso, double estatura, Membresia membresia) {
        super(cedula, nombre, apellido, sexo, fechaNacimiento, telefonos, direccion);
        this.peso = peso;
        this.estatura = estatura;
        this.membresia = membresia; 
    }
       
    /**
    * Constructor publico de la clase Socio. 
    * <p>
    * @param cedula             la cedula del socio.
    * @param nombre             el nombre del socio.
    * @param apellido           el apellido del socio.
    * @param sexo               el sexo del socio.
    * @param fechaNacimiento    la fecha de nacimiento del socio.
    * @param telefonos          los telelefonos socio.
    * @param direccion          la direccion del socio.
    * @param idSocio            el id del socio.
    * @param peso               el peso del socio.
    * @param estatura           la estatura del socio.
    * @param fechaIngreso       la fecha de ingresos del socio
    * @param membresia          la membresia del socio
    * 
    */
    public Socio(int idSocio, int idPersona, String cedula, String nombre, String apellido, char sexo,
                 Date fechaNacimiento, Vector<Telefono> telefonos,
                 Direccion direccion, double peso, double estatura, Membresia membresia) {
        super(idPersona, cedula, nombre, apellido, sexo, fechaNacimiento, telefonos, direccion);
        this.idSocio = idSocio;
        this.peso = peso;
        this.estatura = estatura;
        this.membresia = membresia; 
        this.fechaIngreso = new Date();
        this.facturas = new Vector<Factura>();
        this.seccionesInscritas = new Vector<Seccion>();
    }
   
    /**
    * obtiene los datos de la estatura del socio. 
    * <p>
    * @return la estatura del socio.
    *
    */
    public double getEstatura() {
        return estatura;
    }
    
    /**
    * establece los datos de la estatura del socio. 
    * <p>
    * @param estatura de la estatura del socio.
    *
    */
    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }
    
    /**
    * obtiene los datos de la fecha de ingreso del socio. 
    * <p>
    * @return la fecha de ingreso del socio.
    *
    */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }
    
    /**
    * establece los datos de la fecha de ingreso del socio. 
    * <p>
    * @param fechaIngreso de la fecha de ingreso del socio.
    *
    */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    /**
    * obtiene los datos de las facturas del socio. 
    * <p>
    * @return las facturas del socio.
    *
    */
    public Vector<Factura> getFacturas() {
        return facturas;
    }
    
    /**
    * establece los datos de las facturas del socio. 
    * <p>
    * @param fechaIngreso de las facturas del socio.
    *
    */
    public void setFacturas(Vector<Factura> facturas) {
        this.facturas = facturas;
    }
    
    /**
    * obtiene los datos del peso del socio. 
    * <p>
    * @return  peso del socio.
    *
    */
    public double getPeso() {
        return peso;
    }
    
    /**
    * establece los datos de las facturas del socio. 
    * <p>
    * @param fechaIngreso de las facturas del socio.
    *
    */
    public void setPeso(double peso) {
        this.peso = peso;
    }
            
    /**
    * obtiene los datos de la membresia del socio. 
    * <p>
    * @return  membresia del socio.
    *
    */
    public Membresia getMembresia() {
        return membresia;
    }
    
    /**
    * establece los datos de la membresia del socio. 
    * <p>
    * @param membresia de la membresia del socio.
    *
    */
    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }
       
    /**
    * obtiene los datos de la factura del socio. 
    * <p>
    * @return  facturas del socio.
    *
    */
    public Factura getFactura(int idFactura){
        for( int i = 0; i < facturas.size(); i++){
            if( facturas.elementAt(i).getIdFactura() == idFactura)
                return facturas.elementAt(i);
        }
        return null;
    }
    
    /**
    * obtiene los datos del id del socio. 
    * <p>
    * @return  id del socio.
    *
    */
    public int getIdSocio() {
        return idSocio;
    }
    
    /**
    * obtiene los datos de las seciones inscritas del socio. 
    * <p>
    * @return  seccionesInscritas del socio.
    *
    */
    public Vector<Seccion> getSeccionesInscritas() {
        return seccionesInscritas;
    }
    
    /**
    * establece los datos de las seciones inscritas del socio. 
    * <p>
    * @param seccionesInscritas de las seciones inscritas socio.
    *
    */
    public void setSeccionesInscritas(Vector<Seccion> seccionesInscritas) {
        this.seccionesInscritas = seccionesInscritas;
    }
    
    /**
    * obtiene los datos del status del socio. 
    * <p>
    * @return  status del socio.
    *
    */
    public char getStatus() {
        return status;
    }
    
    /**
    * establece los datos del status del socio. 
    * <p>
    * @param status del status socio.
    *
    */
    public void setStatus(char status) {
        this.status = status;
    }

}
