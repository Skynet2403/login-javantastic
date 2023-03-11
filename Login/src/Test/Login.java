package Test;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Jonathan de Jesus Perez Becerra
 */
public class Login {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Paneles.Login_JFrame cft = new Paneles.Login_JFrame();
        cft.setVisible(true);
        cft.setBounds(670, 270, 300, 250);
        cft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
