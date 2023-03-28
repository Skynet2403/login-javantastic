package test_prueba;

import Paneles.Login_JFrame;
import javax.swing.JFrame;

/**
 *
 * @author jonat
 */
public class Test_prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame prueba = new PopupFrame();
        Login_JFrame login = new Login_JFrame(prueba);
        login.setVisible(true);
        login.setBounds(570, 270, 300, 200);
        
        //en caso de funcionar se abre otra ventana
        
        
    }

}
