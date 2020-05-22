package com.mood.utils.des;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;


public class DESUtil {

    private static final String DEFAULT_KEY = "CCC47AB111FD437F962AF80026796655";
    private static final String CIPHER_INSTANCE = "DES/ECB/PKCS5Padding";
    private static final String ENCODING = "utf-8";

    public static byte[] encryptBytes(byte[] datasource, String key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes(ENCODING));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            return cipher.doFinal(datasource);
        } catch (Exception e) {

        }
        return new byte[0];
    }

    public static byte[] decryptBytes(byte[] datasource, String key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes(ENCODING));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            return cipher.doFinal(datasource);
        } catch (Exception e) {

        }
        return new byte[0];
    }

    public static String encrypt(String datasource) {
        try {
            return new String(encryptBASE64(encryptBytes(datasource.getBytes(ENCODING), DEFAULT_KEY)));
        } catch (Exception e) {
        }
        return null;
    }

    public static String decrypt(String datasource) {
        try {
            return new String(decryptBytes(decryptBASE64(datasource), DEFAULT_KEY));
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     */
    public static byte[] decryptBASE64(String key){
        return new Base64().decode(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     */
    public static byte[] encryptBASE64(byte[] key){
        return new Base64().encode(key);
    }


}
