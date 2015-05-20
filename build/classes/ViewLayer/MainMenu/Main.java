/*
 * Responsable:
 * Andres de Jesus Frias Guzman
 */


package ViewLayer.MainMenu;

import Utilidades.SystemUtils;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String []agrs){
        System.out.println(System.getProperty("os.arch"));
        try {
            if(System.getProperty("os.arch").equals("amd64")) {
                SystemUtils.addLibraryPath("C:\\sqljdbc_4.0\\enu\\auth\\x64");
            }
            else {
                SystemUtils.addLibraryPath("C:\\sqljdbc_4.0\\enu\\auth\\x86");
            }    
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar el dll");
        }
        MenuPrincipal.getInstance().setVisible(true);
    }
}
