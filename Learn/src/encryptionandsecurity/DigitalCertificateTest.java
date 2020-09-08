package encryptionandsecurity;

import com.sun.tools.javac.Main;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author shingo_ge
 */
public class DigitalCertificateTest {
    public static void main(String[] args) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException,
            NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException, SignatureException {
        byte[] message = "大哥，我就是想要二哥的赤兔马，我把他干掉吧：）".getBytes(StandardCharsets.UTF_8);
        // 读取KeyStore:
        KeyStore keyStore = loadKeyStore("my.keystore", "123456");
        // 读取私钥:
        PrivateKey privateKey = (PrivateKey) keyStore.getKey("mycert","123456".toCharArray());
        // 读取证书:
        X509Certificate certificate = (X509Certificate) keyStore.getCertificate("mycert");
        // 加密:
        byte[] encrypted = encrypt(certificate,message);
        System.out.printf("加密文件：%x%n",new BigInteger(1,encrypted));
        // 解密:
        byte[] decrypted = decrypt(privateKey,encrypted);
        System.out.println("解密文件：" + new String(decrypted,StandardCharsets.UTF_8));
        // 签名:
        byte[] signature = sign(privateKey,certificate,message);
        System.out.println("签名文件：" + new BigInteger(1,signature));
        // 验证签名:
        boolean verified = verify(certificate,message,signature);
        System.out.println(verified?"验证通过":"验证失败");
    }

    private static boolean verify(X509Certificate certificate, byte[] message, byte[] signatureData) throws NoSuchAlgorithmException,
            InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(certificate.getSigAlgName());
        signature.initVerify(certificate);
        signature.update(message);
        return signature.verify(signatureData);
    }

    private static byte[] sign(PrivateKey privateKey, X509Certificate certificate, byte[] message) throws NoSuchAlgorithmException,
            InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(certificate.getSigAlgName());
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    private static byte[] decrypt(PrivateKey privateKey, byte[] encrypted) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encrypted);
    }

    private static byte[] encrypt(X509Certificate certificate, byte[] message) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(certificate.getPublicKey().getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,certificate.getPublicKey());
        return cipher.doFinal(message);
    }

    private static KeyStore loadKeyStore(String keyStoreFile, String password) {
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream(keyStoreFile)){
            if (is ==null){
                throw new RuntimeException("file not found in classPath：" + keyStoreFile);
            }
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(is,password.toCharArray());
            return keyStore;
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            throw new RuntimeException(e);
        }
    }
}
