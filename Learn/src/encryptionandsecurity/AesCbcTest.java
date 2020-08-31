package encryptionandsecurity;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author shingo_ge
 */
public class AesCbcTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        //原文：
        String message = "Hello, world!";
        System.out.println("原文：" + message);
        //256位密钥 = 32 bytes key:
        byte[] key = "1234567890abcdef1234567890abcdef".getBytes(StandardCharsets.UTF_8);
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
            InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 把encrypted分割成IV和密文:
        byte[] iv = new byte[16];
        byte[] data = new byte[encrypted.length - 16];
        System.arraycopy(encrypted,0,iv,0,16);
        System.arraycopy(encrypted,16,data,0,data.length);
        // 解密:
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey secretKey =  new SecretKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE,secretKey,ivParameterSpec);
        return cipher.doFinal(data);
    }

    /**
     * 加密
     * */
    private static byte[] encrypt(byte[] key, byte[] messageBytes) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        // CBC模式需要生成一个16 bytes的IV(initialization vector):
        SecureRandom secureRandom = SecureRandom.getInstanceStrong();
        byte[] iv = secureRandom.generateSeed(16);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,ivParameterSpec);
        byte[] data = cipher.doFinal(messageBytes);
        // IV不需要保密，把IV和密文一起返回:
        return join(iv,data);
    }

    private static byte[] join(byte[] iv, byte[] data) {
        byte[] result = new byte[iv.length + data.length];
        System.arraycopy(iv,0,result,0,iv.length);
        System.arraycopy(data,0,result,iv.length,data.length);
        return result;
    }
}
