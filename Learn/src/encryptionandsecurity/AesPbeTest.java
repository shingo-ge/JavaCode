package encryptionandsecurity;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * @author shingo_ge
 */
public class AesPbeTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException {
        //把BouncyCastle作为Provider添加到java.security
        Security.addProvider(new BouncyCastleProvider());
        //原文：
        String message = "Hello, world!";
        System.out.println("原文：" + message);
        //用户提供的加密口令：
        String password = "hello12345";
        System.out.println("用户提供的加密口令：" + password);
        // 16 bytes随机Salt:
        byte[] salt = SecureRandom.getInstanceStrong().generateSeed(16);
        System.out.println("随机Salt：" + new BigInteger(1,salt).toString(16));
        //加密
        byte[] data = message.getBytes(StandardCharsets.UTF_8);
        byte[] encrypted = encrypt(password,salt,data);
        System.out.println("密文：" + Base64.getEncoder().encodeToString(encrypted));
        //解密
        byte[] decrypted = decrypt(password,salt,encrypted);
        System.out.println("解密：" + new String(decrypted,StandardCharsets.UTF_8));
    }

    private static byte[] decrypt(String password, byte[] salt, byte[] encrypted) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 1000);
        Cipher cipher = Cipher.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        cipher.init(Cipher.DECRYPT_MODE,secretKey,pbeParameterSpec);
        return cipher.doFinal(encrypted);
    }

    private static byte[] encrypt(String password, byte[] salt, byte[] message) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 1000);
        Cipher cipher = Cipher.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,pbeParameterSpec);
        return cipher.doFinal(message);
    }
}
