package Codificar_descodificar;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Jonathan de Jesus Perez Becerra
 */
public class tripleDes {

    private byte[] encryptedMessageBytes = null;

    //paso 2. Generación de clave secreta
    //generar una clave secreta que se utilizará para el proceso de cifrado y descifrado. 
    //En nuestro caso, usaremos una clave de 24 bytes construida a partir de números y letras aleatorios:
    private byte[] secretKey = "9mng65v8jf4lxn93nabf981m".getBytes();

    //Ahora, envolveremos nuestra clave en SecretKeySpec combinándola con un algoritmo elegido:
    SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "TripleDES");

    //Otro elemento que debemos generar de antemano es el vector de inicialización para nuestra clave. 
    //Usaremos una matriz de 8 bytes de números y letras aleatorios:
    byte[] iv = "a76nb5h9".getBytes();

    //Y luego, lo envolveremos en la clase IvParameterSpec:
    IvParameterSpec ivSpec = new IvParameterSpec(iv);

    public String Codificar(String cad) {
        //paso 3. Cifrado de cadenas
        //Primero definamos una Cadena con la que trabajaremos:
        String secretMessage = cad;

        //A continuación, necesitaremos un objeto Cipher inicializado con el modo de cifrado, 
        //la clave secreta y el vector de inicialización que generamos anteriormente:
        Cipher encryptCipher = null;
        try {
            encryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(tripleDes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(tripleDes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(tripleDes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(tripleDes.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Con Cipher , podemos ejecutar el método doFinal para cifrar nuestro mensaje. 
        //Tenga en cuenta que solo funciona con una matriz de bytes , por lo que primero 
        //debemos transformar nuestra Cadena:
        byte[] secretMessagesBytes = secretMessage.getBytes(StandardCharsets.UTF_8);

        String encodedMessage = "";
        try {
            encryptedMessageBytes = encryptCipher.doFinal(secretMessagesBytes);

            //Ahora, nuestro mensaje está encriptado con éxito. 
            //Si quisiéramos almacenarlo en una base de datos o enviarlo a través de una API REST, 
            //sería más conveniente codificarlo con el alfabeto Base64:
            encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(tripleDes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(tripleDes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encodedMessage;
    }

//    public void descodificar(String cad) {
//
//        //Para esto, necesitaremos una nueva instancia de Cipher, 
//        //pero esta vez la inicializaremos en modo de descifrado:
//        Cipher decryptCipher = null;
//        try {
//            decryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(tripleDes.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NoSuchPaddingException ex) {
//            Logger.getLogger(tripleDes.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
//        } catch (InvalidKeyException ex) {
//            Logger.getLogger(tripleDes.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvalidAlgorithmParameterException ex) {
//            Logger.getLogger(tripleDes.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        byte[] cad = null;
//        byte[] decryptedMessageBytes = null;
//        try {
//            decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
//        } catch (IllegalBlockSizeException ex) {
//            Logger.getLogger(tripleDes.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (BadPaddingException ex) {
//            Logger.getLogger(tripleDes.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        //Ahora, decodificaremos el resultado en una variable de cadena:
//        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
//        
//        System.out.println("decryptedMessage");
//    }

}
