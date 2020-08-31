package encryptionandsecurity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author shingo_ge
 */
public class Md5Test {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update("Hello".getBytes(StandardCharsets.UTF_8));
        messageDigest.update("World".getBytes(StandardCharsets.UTF_8));
        byte[] result = messageDigest.digest();
        String bigInteger = new BigInteger(1,result).toString(16);
        System.out.println(bigInteger);
    }
}
