package encryptionandsecurity;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * @author shingo_ge
 */
public class RsaTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, NoSuchPaddingException {
        // 明文:
        byte[] plainText = "三弟，三弟，我们反了大哥吧！".getBytes(StandardCharsets.UTF_8);
        // 创建人物及公钥／私钥对
        Person1 zf = new Person1("张飞");
        String name = zf.getName();
        // 用张飞的公钥加密:
        byte[] publicKeyBytes = zf.getPublicKeyBytes();
        System.out.printf("%s的公钥：%x\n",name,new BigInteger(1,publicKeyBytes));
        byte[] encrypted = zf.encrypt(plainText);
        System.out.printf("加密后的密文：%x\n",new BigInteger(1,encrypted));
        // 用张飞的私钥解密:
        byte[] privateKeyBytes = zf.getPrivateKeyBytes();
        System.out.printf("%s的私钥：%x\n",name,new BigInteger(1,privateKeyBytes));
        byte[] decrypted = zf.decrypt(encrypted);
        System.out.printf("%s解密后得到的明文：%s\n",name,new String(decrypted,StandardCharsets.UTF_8));
    }
}
class Person1{
    private final String name;
    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public Person1(String name) throws NoSuchAlgorithmException {
        this.name = name;
        // 生成公钥／私钥对:
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair keyPair = kpg.generateKeyPair();
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
    }

    public String getName() {
        return name;
    }

    /**
     * 把公钥导出为字节:
     */
    public byte[] getPublicKeyBytes() {
        return this.publicKey.getEncoded();
    }

    /**
     * 用公钥加密:
     */
    public byte[] encrypt(byte[] plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,this.publicKey);
        return cipher.doFinal(plainText);
    }

    /**
     * 把私钥导出为字节:
     */
    public byte[] getPrivateKeyBytes() {
        return this.privateKey.getEncoded();
    }

    /**
     * 用私钥解密:
     */
    public byte[] decrypt(byte[] encrypted) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,this.privateKey);
        return cipher.doFinal(encrypted);
    }
}
