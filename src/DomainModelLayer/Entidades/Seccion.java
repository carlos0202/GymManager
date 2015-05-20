/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package DomainModelLayer.Entidades;

import java.util.Date;
import java.util.Vector;

/**
* Clase que almacena los datos de una Seccion de clases del gimnasio. 
*/
public class Seccion {
    private int idSeccion;
    private String nombre;
    private Clase claseImpartida;
    private Date fechaInicio;
    private Date fechaFin;
    private int horaInicio;
    private int horaFin;
    private int dia;
    private Salon salon;
    private int capacidad;
    private Vector<Socio> sociosInscritos;
    private Instructor instrctAsignado;

    /**
    * Instancia una nueva Seccion de clases usando los parametros recibidos. 
    * <p>
    * @param idSeccion       el identicador unico de la seccion (id).
    * @param nombre          el nombre de la seccion de clase.
    * @param claseImpartida  la clase que se imparte en esta seccion.
    * @param horaInicio      la hora a la que inician las clases en esta seccion.
    * @param horaFin         la hora a la que finalizan las clases en esta seccion.
    * @param dia             el dia en que se imparte esta seccion de clase.
    * @param salon           el salon donde se imparte esta seccion de clase.
    * @param capacidad       la capacidad maxima de socios que pueden inscribirse
    *                        a esta seccion.
    * @param sociosInscritos los socios actualmente inscritos en esta seccion.
    * @param instructor      el instructor asignado a esta secccion de clases.
    * 
    */
    public Seccion(int idSeccion, String nombre, Clase claseImpartida, int horaInicio, int horaFin,
                   int dia, Salon salon, int capacidad, Vector<Socio> sociosInscritos, Instructor instructor) {
        this.idSeccion = idSeccion;
        this.nombre = nombre;
        this.claseImpartida = claseImpartida;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dia = dia;
        this.salon = salon;
        this.capacidad = capacidad;
        this.sociosInscritos = sociosInscritos;
        this.instrctAsignado = instructor;
    }
    
    /**
    * Instancia una nueva Seccion de clases usando los parametros recibidos. 
    * <p>
    * @param idSeccion       el identicador unico de la seccion (id).
    * @param nombre          el nombre de la seccion de clase.
    * @param horaInicio      la hora a la que inician las clases en esta seccion.
    * @param horaFin         la hora a la que finalizan las clases en esta seccion.
    * @param dia             el dia en que se imparte esta seccion de clase.
    * @param capacidad       la capacidad maxima de socios que pueden inscribirse
    *                        a esta seccion.
    * 
    */
    public Seccion(int idSeccion, String nombre, int horaInicio, int horaFin,
            int dia, int capacidad) {
        this.idSeccion = idSeccion;
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dia = dia;
        this.capacidad = capacidad;
    }

    /**
    * Instancia una nueva Seccion de clases usando los parametros recibidos. 
    * <p>
    * @param nombre          el nombre de la seccion de clase.
    * @param claseImpartida  la clase que se imparte en esta seccion.
    * @param horaInicio      la hora a la que inician las clases en esta seccion.
    * @param horaFin         la hora a la que finalizan las clases en esta seccion.
    * @param dia             el dia en que se imparte esta seccion de clase.
    * @param salon           el salon donde se imparte esta seccion de clase.
    * @param capacidad       la capacidad maxima de socios que pueden inscribirse
    *                        a esta seccion.
    * @param fechaI          la fecha en que comienza a impartirse esta seccion
    *                        de clases.
    * @param fechaF          la fecha en la que deja de impartirse esta seccion
    *                        de clases.
    */
    public Seccion(String nombre, Clase claseImpartida, int horaInicio, int horaFin, int dia, Salon salon,
                   int capacidad, Date fechaI, Date fechaF) {
        this.nombre = nombre;
        this.claseImpartida = claseImpartida;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dia = dia;
        this.salon = salon;
        this.capacidad = capacidad;
        this.sociosInscritos = new Vector();
        this.fechaInicio = fechaI;
        this.fechaFin = fechaF;
    }
    
    /**
    * Instancia una nueva Seccion de clases usando los parametros recibidos. 
    * <p>
    * @param nombre          el nombre de la seccion de clase.
    * @param claseImpartida  la clase que se imparte en esta seccion.
    * @param horaInicio      la hora a la que inician las clases en esta seccion.
    * @param horaFin         la hora a la que finalizan las clases en esta seccion.
    * @param dia             el dia en que se imparte esta seccion de clase.
    * @param salon           el salon donde se imparte esta seccion de clase.
    * @param capacidad       la capacidad maxima de socios que pueden inscribirse
    *                        a esta seccion.
    * @param instructor      el instructor asignado a esta secccion de clases.
    * @param fechaI          la fecha en que comienza a impartirse esta seccion
    *                        de clases.
    * @param fechaF          la fecha en la que deja de impartirse esta seccion
    *                        de clases.
    */
    public Seccion(String nombre, Clase claseImpartida, int horaInicio, int horaFin, int dia, Salon salon,
                int capacidad, Instructor instructor, Date fechaI, Date fechaF) {
        this.nombre = nombre;
        this.claseImpartida = claseImpartida;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dia = dia;
        this.salon = salon;
        this.capacidad = capacidad;
        this.sociosInscritos = new Vector<Socio>();
        this.instrctAsignado = instructor;
        this.fechaInicio = fechaI;
        this.fechaFin = fechaF;
    }

    /**
    * obtiene los datos de la capacidad maxima de socios que pueden inscribirse. 
    * <p>
    * @return  la maxima capacidad que puede inscribir esta seccion.
    *
    */ 
    public int getCapacidad() {
        return capacidad;
    }

    /**
    * establece la capacidad maxima de socios que se pueden inscribir en esta
    * seccion. 
    * <p>
    * @param capacidad la capacidad maxima que va a tener esta seccion.
    *
    */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
    * obtiene un valor entero que representa el dia en que se imparte esta
    * seccion, siendo el rango [1 - 7] = [Lunes - Domingo]. 
    * <p>
    * @return  el dia en que se imparte esta seccion de clases.
    *
    */ 
    public int getDia() {
        return dia;
    }

    /**
    * establece el dia en que se va a impartir este dia de clases
    * en el rango [1-7] = [Lunes-Domingo]. 
    * <p>
    * @param dia el dia en que se va a impartir esta seccion de clases.
    *
    */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
    * obtiene un valor entero que representa la hora en la que termina de
    * impartirse esta seccion de clases, siendo el rango [6-20] = [6 am - 8 pm]. 
    * <p>
    * @return  la hora en que acaba de impartirse esta seccin de clases.
    *
    */ 
    public int getHoraFin() {
        return horaFin;
    }

    /**
    * establece la hora en que finaliza de impartirse esta seccion de clases
    * en el rango [7-21] =  [7 am - 9 pm]. 
    * <p>
    * @param horaFin la hora a la que termina de impartirse esta seccion de clases.
    *
    */
    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    /**
    * obtiene un valor entero que representa la hora en la que empieza de
    * impartirse esta seccion de clases, siendo el rango [7-21] = [7 am - 9 pm]. 
    * <p>
    * @return  la hora en que empieza de impartirse esta seccin de clases.
    *
    */ 
    public int getHoraInicio() {
        return horaInicio;
    }

    /**
    * establece la hora en que finaliza de impartirse esta seccion de clases
    * en el rango [6-20] =  [6 am - 8 pm]. 
    * <p>
    * @param horaInicio la hora a la que termina de impartirse esta seccion de clases.
    *
    */
    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
    * obtiene los datos del nombre de la seccion. 
    * <p>
    * @return  el nombre de esta seccion de clases.
    *
    */ 
    public String getNombre() {
        return nombre;
    }

    /**
    * establece el nombre de esta seccion de clases. 
    * <p>
    * @param nombre el nombre que sera asignado a esta seccion de clase.
    *
    */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
    * obtiene los datos del salon donde se imparte esta seccion de clases. 
    * <p>
    * @return  el salon donde se imparte esta seccion de clases.
    *
    */ 
    public Salon getSalon() {
        return salon;
    }

    /**
    * establece el salon donde se imparte esta seccion de clases. 
    * <p>
    * @param salon el salon donde se va a impartir esta clase.
    *
    */
    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    /**
    * obtiene los datos de los socios inscritos a esta seccion de clases. 
    * <p>
    * @return  los socios inscritos en esta clase.
    *
    */ 
    public Vector<Socio> getSociosInscritos() {
        return sociosInscritos;
    }

    /**
    * establece el los socios inscritos a esta seccion de clases. 
    * <p>
    * @param sociosInscritos los socios inscritos a esta seccion de clases.
    *
    */
    public void setSociosInscritos(Vector<Socio> sociosInscritos) {
        this.sociosInscritos = sociosInscritos;
    }

    /**
    * obtiene los datos del identificador unico de esta seccion (id). 
    * <p>
    * @return  el id de esta seccion de clases.
    *
    */ 
    public int getIdSeccion() {
        return idSeccion;
    }
    
    /**
    * Inscribe un socio a esta seccion de clases. 
    * <p>
    * @param socio el socio que sera inscrito a esta seccion de clases.
    *
    */
    public void inscribirSocio(Socio socio){
        this.sociosInscritos.add(socio);
        this.capacidad--;
    }

    /**
    * obtiene los datos de la clase que se imparte en esta seccion de clases. 
    * <p>
    * @return  la clase que se imparte en esta secion.
    *
    */ 
    public Clase getClaseImpartida() {
        return claseImpartida;
    }

    /**
    * establece la clase que sera impartida en esta seccion. 
    * <p>
    * @param claseImpartida la clase que sera impartida en esta seccion.
    *
    */
    public void setClaseImpartida(Clase claseImpartida) {
        this.claseImpartida = claseImpartida;
    }
    
    /**
    * Borra de la lista de socios inscritos a esta seccion a un socio basado
    * en su identifiacion unica (id). 
    * <p>
    * @param idSocio el id del socio que se desea retirar de la seccion.
    * @return verdadero si el socio ha sido retirado, de lo contrario retora falso.
    */ 
    public boolean retirarSocio(int idSocio){
        for( int i = 0; i < sociosInscritos.size(); i++ ){
            if( sociosInscritos.elementAt(i).getIdSocio() == idSocio){
                sociosInscritos.removeElementAt(i);
                return true;
            }
        }
        return false;
    }

    /**
    * obtiene los datos del instructor que imparte clase en esta seccion. 
    * <p>
    * @return  el instructor que imparte clase en esta seccion.
    *
    */ 
    public Instructor getInstrctAsignado() {
        return instrctAsignado;
    }

    /**
    * establece el instructor que impartira clase en esta seccion. 
    * <p>
    * @param instrctAsignado el instructor que va a impartir clases en esta seccion.
    *
    */
    public void setInstrctAsignado(Instructor instrctAsignado) {
        this.instrctAsignado = instrctAsignado;
    }

    /**
    * obtiene los datos de la fecha en que termina de impartirse esta seccion. 
    * <p>
    * @return  la fecha en que termina de impartirse esta seccion.
    *
    */ 
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
    * establece la fecha en que finaliza de impartirse esta clase. 
    * <p>
    * @param fechaFin la fecha en que terminara de impartirse la seccion de clases.
    *
    */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
    * obtiene los datos de la fecha en que comienza a impartirse esta seccion. 
    * <p>
    * @return  la fecha en que comienza a impartirse esta seccion.
    *
    */ 
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
    * establece la fecha en que comenzara a impartirse esta clase. 
    * <p>
    * @param fechaInicio la fecha en que terminara de impartirse la seccion de clases.
    *
    */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
    * Comparar los datos basicos de una seccion con otra a fin de verificar si
    * esta seccion tiene choque de horario con la seccion que se recibe por 
    * parametro. 
    * <p>
    * @param obj la seccion con la que se va a comparar el horario.
    * @throws Exception si existe algun choque de horario.
    *
    */ 
    public void compararHorario(Seccion obj) throws Exception{
        if( this.dia == obj.getDia() && this.idSeccion != obj.getIdSeccion() &&
            this.salon.getIdSalon() == obj.getSalon().getIdSalon() ){
            
            if(  (this.horaInicio < obj.getHoraFin() &&
                  this.horaFin > obj.getHoraInicio() ) ||
                 (this.horaInicio > obj.getHoraFin() &&
                  this.horaFin < obj.getHoraInicio()  )    ){
                throw new Exception(
                        "Existe un choque de horario con la seccion "+
                        this.nombre + ", " + this.horaInicio+":00 - "+
                        this.horaFin+":00."
                );
            }
            
        }

    }
    
    /**
    * Comparar los datos basicos de una seccion con otra a fin de verificar si
    * esta seccion tiene choque de Instructor con la seccion que se recibe por 
    * parametro. 
    * <p>
    * @param obj la seccion con la que se va a comparar el horario.
    * @throws Exception si existe algun choque de Instructor.
    *
    */ 
    public void compararInstructor(Seccion obj) throws Exception{
        if( this.dia == obj.getDia() && this.idSeccion != obj.getIdSeccion() &&
            this.instrctAsignado.getIdInstructor() == obj.getInstrctAsignado().getIdInstructor() ){
            
            if(  (this.horaInicio < obj.getHoraFin() &&
                  this.horaFin > obj.getHoraInicio() ) ||
                 (this.horaInicio > obj.getHoraFin() &&
                  this.horaFin < obj.getHoraInicio()  )    ){
                throw new Exception(
                        "Existe un choque de horario con la seccion "+
                        this.nombre + ", " + this.horaInicio+":00 - "+
                        this.horaFin+":00."+ " Impartida por el instructor "+
                        this.instrctAsignado.getNombre() + " "+
                        this.instrctAsignado.getApellido()
                );
            }
        }
    }
}
