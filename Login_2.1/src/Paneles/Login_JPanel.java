package Paneles;

import CodificarMD5.Md5;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jonathan de Jesus Perez Becerra
 */
public class Login_JPanel extends JPanel {

    private JFrame myFrame;

    private String directorioRaiz;
    private final JTextField usuariotextarea;
    private final JButton BotonEntrar;
    private final JPasswordField passwordField;//campo de texto para contraseñas

    public Login_JPanel(JFrame frame) {
        myFrame = frame;

        this.setLayout(null);

        String cad = "";
        char temp4;
        directorioRaiz = System.getProperty("user.dir");
        for (int i = 0; i < directorioRaiz.length(); i++) {
            temp4 = directorioRaiz.charAt(i);
            if (temp4 == 92) {
                temp4 = 47;
                cad = cad + temp4;
            } else {
                cad = cad + temp4;
            }
        }
        directorioRaiz += "/Texto_Cifrado.vge";

        JLabel etiq1 = new JLabel();
        etiq1.setText("Usuario");
        etiq1.setForeground(Color.WHITE);
        add(etiq1);
        etiq1.setBounds(40, 10, 200, 20);

        usuariotextarea = new JTextField(23);
        add(usuariotextarea);
        usuariotextarea.setBounds(40, 30, 200, 20);

        JLabel etiq2 = new JLabel();
        etiq2.setText("Contraseña");
        etiq2.setForeground(Color.WHITE);
        add(etiq2);
        etiq2.setBounds(40, 60, 200, 20);

        passwordField = new JPasswordField(23);//creamos un objeto tipo cuadro de tipo contraseña
        add(passwordField);//lo añadimos a la ventana
        passwordField.setBounds(40, 80, 200, 20);

        BotonEntrar = new JButton("Entrar");
        add(BotonEntrar);
        BotonEntrar.setBounds(95, 120, 93, 20);

        BotonEntrar.addActionListener(new HandlerEntrar());
        BotonEntrar.addKeyListener(new HandlerEntrar());

    }

    @Override
    public void paint(Graphics g) {
        Dimension dimension = this.getSize();
        ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/HACK.jpg"));
        g.drawImage(icon.getImage(), 0, 0, dimension.width, dimension.height, null);
        setOpaque(false);
        super.paintChildren(g);
    }

    private class HandlerEntrar extends KeyAdapter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            String usuario = usuariotextarea.getText();
            String contra = passwordField.getText();
            File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;
            JFileChooser fileChooser = new JFileChooser();

            try {
                File archi = new File(directorioRaiz);
                if (!archi.exists()) {
                    
                    JOptionPane.showMessageDialog(null, "OJO: ¡¡No existe el archivo de texto en la ubicacion establecida\n"
                            + "favor de seleccionarlo desde el explorador de archivos\n"
                            + "En caso de no contar con una base le sugerimos seleccionar la nuestra");
                    
                    fileChooser.showOpenDialog(fileChooser);
                    try {
                        String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                        directorioRaiz = ruta;
                    } catch (NullPointerException e) {
                        System.out.println("No se ha seleccionado ningún fichero");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        
                    }

                } 

                    // Apertura del fichero y creacion de BufferedReader para poder
                    // hacer una lectura comoda (disponer del metodo readLine()).
                    archivo = new File(directorioRaiz);
                    fr = new FileReader(archivo);
                    br = new BufferedReader(fr);

                    // Lectura del ficheros
                    String[] parts = null;
                    String guardar = "";
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        guardar += linea + "JHONI";
                    }
                    
                    System.out.println();
                    
                    parts = guardar.split("JHONI");
                    for (int i = 0; i < parts.length; i++) {
                        if (usuario.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No ingresaste un usuario");
                            break;
                        }
                        if (usuario.equals(parts[i])) {
                            String x = Md5.getMD5(contra);
                            if (parts[i+1].equals(x)) {
                                JOptionPane.showMessageDialog(null, "Acceso permitido");
                                PopupFrame prueba = new PopupFrame();
                                prueba.setBounds(670, 270, 300, 200);
                                prueba.setVisible(true);
                                prueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                myFrame.dispose();

                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                                System.exit(0);
                                break;
                            }

                        }
                        if (i == parts.length - 1) {
                            JOptionPane.showMessageDialog(null, "No existe este usuario en nuestro sistema");
                            System.exit(0);
                        }
                    }
                

            } catch (Exception e) {
                System.out.println("No se encontro la ruta especificada");
                System.exit(0);
            } finally {
                // En el finally cerramos el fichero, para asegurarnos
                // que se cierra tanto si todo va bien como si salta 
                // una excepcion.
                try {
                    if (null != fr) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    System.out.println("error al cerrar el acrchivo");
                    System.exit(0);
                }
            }

        }
    }
}
