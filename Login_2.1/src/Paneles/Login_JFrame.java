package Paneles;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Jonathan de Jesus Perez Becerra
 */
public class Login_JFrame extends JFrame {
//Login_JPanel login = new Login_JPanel();

    private JPanel myPanel;

    public Login_JFrame() {
        super("Login");
        myPanel = new Login_JPanel(this);
        this.add(myPanel, BorderLayout.CENTER);

        setVisible(true);
        setBounds(670, 270, 300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
