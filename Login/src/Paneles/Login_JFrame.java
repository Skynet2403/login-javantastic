package Paneles;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Jonathan de Jesus Perez Becerra
 */
public class Login_JFrame extends JFrame{
Login_JPanel login = new Login_JPanel();
    
    public Login_JFrame() {
        super("Login");
        this.add(login,BorderLayout.CENTER);
        this.setLocationRelativeTo(null);    
    }    
}
