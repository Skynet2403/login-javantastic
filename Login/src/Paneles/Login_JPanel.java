package Paneles;

import Codificar_descodificar.AES256;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import Codificar_descodificar.tripleDes;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jonathan de Jesus Perez Becerra
 */
public class Login_JPanel extends JPanel {

    private String directorioRaiz;
    private final JTextField usuariotextarea;
    private final JButton BotonEntrar;
    private final JButton BotonRegistrarse;
    private final JPasswordField passwordField;//campo de texto para contraseñas

    public Login_JPanel() {

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
        BotonEntrar.setBounds(40, 120, 93, 20);

        BotonRegistrarse = new JButton("Registrarse");
        add(BotonRegistrarse);
        BotonRegistrarse.setBounds(138, 120, 103, 20);

//        BotonEntrar.addActionListener(new HandlerEncriptar());
        BotonEntrar.addActionListener(new HandlerEntrar());
        BotonRegistrarse.addActionListener(new HandlerRegistrarse());

    }

    @Override
    public void paint(Graphics g) {
        Dimension dimension = this.getSize();
        ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/HACK.jpg"));
        g.drawImage(icon.getImage(), 0, 0, dimension.width, dimension.height, null);
        setOpaque(false);
        super.paintChildren(g);
    }

    private class HandlerRegistrarse implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            String usuario = "";
            usuario = usuariotextarea.getText();
            if (usuario.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No ingresaste un usuario");
            } else {
                try {

                    File archivo = null;
                    FileReader fr = null;
                    BufferedReader br = null;
                    // Apertura del fichero y creacion de BufferedReader para poder
                    // hacer una lectura comoda (disponer del metodo readLine()).
                    archivo = new File(directorioRaiz + "/Texto_Cifrado.vge");
                    fr = new FileReader(archivo);
                    br = new BufferedReader(fr);

                    // Lectura del ficheros
                    String[] parts = null;
                    String guardar = "";
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        guardar += linea + "JHONI";
                    }

                    parts = guardar.split("JHONI");
                    for (int i = 0; i < parts.length; i++) {
                        if (usuario.equals(parts[i])) {
                            JOptionPane.showMessageDialog(null, "El usuario ya existe");
                            break;
                        } else {
                            if (i == parts.length - 1) {
                                String str = "", temp = "", temp2 = "";
                                str = passwordField.getText();
                                if (str.length() > 6) {
                                    AES256 pru = new AES256();
                                    temp2 = pru.encrypt(str);
                                    temp = String.format(usuario + "JHONI%s", temp2 + "\n");

                                    try {
                                        String contenido = temp;

                                        File file = new File(directorioRaiz + "/Texto_Cifrado.vge");
                                        // Si el archivo no existe es creado
                                        if (!file.exists()) {
                                            file.createNewFile();
                                        }
                                        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                                        BufferedWriter bw = new BufferedWriter(fw);
                                        bw.write(contenido);
                                        bw.close();
                                        JOptionPane.showMessageDialog(null, "Se registro con exito");
                                        usuariotextarea.setText(null);
                                        passwordField.setText(null);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "La contraseña es demasiado corta");
                                }
                            }
                        }
                    }

                    try {
                        if (null != fr) {
                            fr.close();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // En el finally cerramos el fichero, para asegurarnos
                    // que se cierra tanto si todo va bien como si salta 
                    // una excepcion.

                }

            }
        }
    }

    private class HandlerEntrar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            String usuario = usuariotextarea.getText();
            AES256 aes = new AES256();
            String contra = passwordField.getText();
            File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;

            try {
                // Apertura del fichero y creacion de BufferedReader para poder
                // hacer una lectura comoda (disponer del metodo readLine()).
                archivo = new File(directorioRaiz + "/Texto_Cifrado.vge");
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);

                // Lectura del ficheros
                String[] parts = null;
                String guardar = "";
                String linea;
                while ((linea = br.readLine()) != null) {
                    guardar += linea + "JHONI";
                }

                parts = guardar.split("JHONI");
                for (int i = 0; i < parts.length; i++) {
                    if (usuario.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No ingresaste un usuario");
                        break;
                    }
                    if (usuario.equals(parts[i])) {
                        System.out.println(parts[i + 1]);
                        String x = aes.decrypt(parts[i + 1]);
                        System.out.println(x);
                        if (contra.equals(x)) {
                            System.out.println("Acceso correcto");
                            JOptionPane.showMessageDialog(null, "Acceso permitido");
                            break;
                        } else {
                            System.out.println("Usuario o Contrasenna incorrecta");
                            JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                            break;
                        }

                    }
                    if (i == parts.length - 1) {
                        JOptionPane.showMessageDialog(null, "No existe este usuario en nuestro sistema, \n"
                                + "favor de regristrase");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // En el finally cerramos el fichero, para asegurarnos
                // que se cierra tanto si todo va bien como si salta 
                // una excepcion.
                try {
                    if (null != fr) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

        }
    }
}
