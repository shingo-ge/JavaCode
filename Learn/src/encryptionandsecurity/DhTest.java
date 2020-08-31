package encryptionandsecurity;

import javax.crypto.KeyAgreement;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author shingo_ge
 */
public class DhTest {
    public static void main(String[] args) {
        Person p1 = new Person("张飞");
        Person p2 = new Person("关羽");
        // 各自生成KeyPair:
        p1.generateKeyPair();
        p2.generateKeyPair();
        // 双方交换各自的PublicKey:
        // 张飞根据关羽的PublicKey生成自己的本地密钥:
        p1.generateSecretKey(p2.getPublicKey().getEncoded());
        // 关羽根据张飞的PublicKey生成自己的本地密钥:
        p2.generateSecretKey(p1.getPublicKey().getEncoded());
        // 检查双方的本地密钥是否相同:
        p1.printKeys();
        p2.printKeys();
    }
}

class Person{
    private final String name;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private byte[] secretKey;

    public Person(String name) {
        this.name = name;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * 生成本地KeyPair:
     */
    public void generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
            keyPairGenerator.initialize(512);
            KeyPair kp = keyPairGenerator.generateKeyPair();
            this.publicKey = kp.getPublic();
            this.privateKey = kp.getPrivate();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void generateSecretKey(byte[] receivedPublicKeyBytes) {
        try {
            // 从byte[]恢复PublicKey:
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(receivedPublicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("DH");
            PublicKey receivedPublicKey = keyFactory.generatePublic(keySpec);
            // 生成本地密钥:
            KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
            // 自己的PrivateKey:
            keyAgreement.init(this.privateKey);
            // 对方的PublicKey:
            keyAgreement.doPhase(receivedPublicKey,true);
            // 生成SecretKey密钥:
            this.secretKey = keyAgreement.generateSecret();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public void printKeys() {
        System.out.printf("name：%s\n", this.name);
        System.out.printf("Private key: %x\n", new BigInteger(1, this.privateKey.getEncoded()));
        System.out.printf("Public key: %x\n", new BigInteger(1, this.publicKey.getEncoded()));
        System.out.printf("Secret key: %x\n", new BigInteger(1, this.secretKey));
    }
}