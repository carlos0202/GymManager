/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Entidades;

/**
* Clase que almacena los datos de un detalle de una factura. 
*/
public class Direccion {
    private int idDireccion;
    private String municipio;
    private String sector;
    private String calle;
    private int numero;

    /**
    * Instancia una nueva direccion usando los parametros recibidos. 
    * <p>
    * @param municipio el municipio de la direccion.
    * @param sector    el sector de la direccion.
    * @param calle     la calle de la direccion.
    * @param numero    el numero de la direccion
    *
    */
    public Direccion(String municipio, String sector, String calle, int numero) {
        this.municipio = municipio;
        this.sector = sector;
        this.calle = calle;
        this.numero = numero;
    }

    /**
    * Instancia una nueva direccion usando los parametros recibidos. 
    * <p>
    * @param idDireccion el identificador de la direccion (id).
    * @param municipio   el municipio de la direccion.
    * @param sector      el sector de la direccion.
    * @param calle       la calle de la direccion.
    * @param numero      el numero de la direccion
    *
    */
    public Direccion(int idDireccion, String municipio, String sector,
                     String calle, int numero) {
        this.idDireccion = idDireccion;
        this.municipio = municipio;
        this.sector = sector;
        this.calle = calle;
        this.numero = numero;
    }

    /**
    * obtiene los datos de la calle. 
    * <p>
    * @return los datos de la calle de la direccion.
    *
    */
    public String getCalle() {
        return calle;
    }

    /**
    * establece los datos de la calle. 
    * <p>
    * @param calle los datos de la calle.
    *
    */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
    * obtiene los datos del municipio donde esta ubicada la direccion. 
    * <p>
    * @return los datos del municipio.
    *
    */
    public String getMunicipio() {
        return municipio;
    }

    /**
    * establece los datos del municipio. 
    * <p>
    * @param municipio los datos del municipio.
    *
    */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
    * obtiene los datos del numero de casa. 
    * <p>
    * @return los datos del numero de casa.
    *
    */
    public int getNumero() {
        return numero;
    }

    /**
    * establece los datos del numero de casa. 
    * <p>
    * @param numero el numero de casa.
    *
    */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
    * obtiene los datos del sector donde esta ubicada la direccion. 
    * <p>
    * @return los datos del sector.
    *
    */
    public String getSector() {
        return sector;
    }

    /**
    * establece los datos del sector. 
    * <p>
    * @param sector el sector a establecer.
    *
    */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
    * obtiene los datos de la identificacion unica de la direccion (id). 
    * <p>
    * @return los datos de la calle de la direccion.
    *
    */
    public int getIdDireccion() {
        return idDireccion;
    }
       
}
