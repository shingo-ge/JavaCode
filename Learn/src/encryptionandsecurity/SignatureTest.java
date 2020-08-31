package encryptionandsecurity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * @author shingo_ge
 */
public class SignatureTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // 生成RSA公钥/私钥:
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        // 待签名的消息:
        byte[] message = "大哥，我看上二哥的赤兔马了。".getBytes(StandardCharsets.UTF_8);
        // 用私钥签名:
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKey);
        signature.update(message);
        byte[] signed = signature.sign();
        System.out.println("签名后信息：" + new BigInteger(1,signed));
        // 用公钥验证:
        signature.initVerify(publicKey);
        signature.update(message);
        boolean verify = signature.verify(signed);
        System.out.println("验证是否通过：" + verify);
    }
}
