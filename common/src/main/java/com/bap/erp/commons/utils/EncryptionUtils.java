package com.bap.erp.commons.utils;

import com.bap.erp.commons.exceptions.EncodingPasswordException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtils {

    public static String encryptPassword(String login, String pass) throws EncodingPasswordException {

        try {
            String symetricKey = divideKey(encryptPasswordWithMD5(login));

            //System.out.println("MD/2 : " + symetricKey);

            /*Encrypt with AES - La llave es una parte del password en MD5**/
            byte[] aError = null;
            SecretKeySpec key = new SecretKeySpec(symetricKey.getBytes(), "AES");

            Cipher cipher;
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] datosCifrados = cipher.doFinal(pass.getBytes()); //cadena = a texto a cifrar

            //Apache commons encoder
            String encoded = Base64.encode(datosCifrados);
            //System.out.println("encoded::: "+encoded);
            return encoded;
            //return new sun.misc.BASE64Encoder().encode(datosCifrados);
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(EncryptionUtils.class.getName()).log(Level.SEVERE, null, e);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(EncryptionUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(EncryptionUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(EncryptionUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(EncryptionUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new EncodingPasswordException("Error while encrypting password");

    }

    private static String divideKey(String md5Password) {

        System.out.println("MD5 key: " + md5Password);

        String newKey = "";
        for (int i = 0; i < md5Password.substring(0, 16).length(); i++) {
            newKey = newKey + md5Password.charAt(i);
        }
        return newKey;
    }

    private static String encryptPasswordWithMD5(String contrasena) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(contrasena.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return number.toString(16);
    }

}
