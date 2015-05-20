/*
 * Responsable:
 *      Carlos Antonio Gonzalez Canario
 */
package Utilidades;

/**
* Clase utilitaria para verificar un numero de telefono. 
*/
public class CheckField {
    
    /**
    * verifica que el telefono enviado por parametro no tiene caracteres invalidos. 
    * <p>
    * @param tel el numero de telefono a verificar.
    *
    */
    public static boolean checkTel(String tel){
        for(int i = 0; i < tel.length(); i++){
            if(tel.charAt(i) != ' ')
                continue;
            else
                return false;
        }
        
        return true;
    }
}
