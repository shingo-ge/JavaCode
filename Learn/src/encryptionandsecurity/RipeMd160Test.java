package encryptionandsecurity;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @author shingo_ge
 */
public class RipeMd160Test {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 注册BouncyCastle
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest messageDigest = MessageDigest.getInstance("RipeMD160");
        messageDigest.update("Hello World!".getBytes(StandardCharsets.UTF_8));
        byte[] result = messageDigest.digest();
        String string = new BigInteger(1, result).toString(16);
        System.out.println(string);
    }
}
