package encryptionandsecurity;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author shingo_ge
 */
public class AesEcbTest {
    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException,
            NoSuchAlgorithmException, NoSuchPaddingException {
        //原文：
        String message = "Hello, world!";
        System.out.println("原文：" + message);
        //128位密钥 = 16 bytes key:
        byte[] key = "1234567890abcdef".getBytes(StandardCharsets.UTF_8);
        //加密
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] encrypted = encrypt(key,messageBytes);
        System.out.println("密文：" + Base64.getEncoder().encodeToString(encrypted));
        //解密
        byte[] decrypted = decrypt(key,encrypted);
        System.out.println("解密：" + new String(decrypted,StandardCharsets.UTF_8));
    }

    /**
     * 解密
     * */
    private static byte[] decrypt(byte[] key, byte[] encrypted) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        return cipher.doFinal(encrypted);
    }

    /**
     * 加密
     * */
    private static byte[] encrypt(byte[] key, byte[] messageBytes) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        return cipher.doFinal(messageBytes);
    }
}
