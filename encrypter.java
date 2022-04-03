


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class encrypter{

    encrypter(){}


    public void encrypt(File logFile, String password)throws Exception{

        SecretKeySpec key = new SecretKeySpec(password.getBytes(), "Blowfish");
        try{
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            FileInputStream inputStream = new FileInputStream(logFile);
            byte[] inputBytes = new byte[(int) logFile.length()];
            inputStream.read(inputBytes);
            byte[] encryptedLog = cipher.doFinal(inputBytes);
            FileOutputStream outputStream = new FileOutputStream(logFile);
            outputStream.write(encryptedLog);
            outputStream.close();
            inputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    public void decrypt(File logFile, String password)throws Exception{

        SecretKeySpec key = new SecretKeySpec(password.getBytes(), "Blowfish");
        try{
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, key);
            FileInputStream inputStream = new FileInputStream(logFile);
            byte[] inputBytes = new byte[(int) logFile.length()];
            inputStream.read(inputBytes);
            byte[] decryptLog = cipher.doFinal(inputBytes);
            FileOutputStream outputStream = new FileOutputStream(logFile);
            outputStream.write(decryptLog);
            outputStream.close();
            inputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }




}
