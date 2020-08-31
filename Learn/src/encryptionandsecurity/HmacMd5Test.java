package encryptionandsecurity;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author shingo_ge
 */
public class HmacMd5Test {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = keyGenerator.generateKey();
        //打印随机key
        byte[] keyEncoded = secretKey.getEncoded();
        System.out.println(new BigInteger(1,keyEncoded).toString(16));

        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(secretKey);
        mac.update("Hello World!".getBytes(StandardCharsets.UTF_8));
        byte[] bytes = mac.doFinal();
        String result = new BigInteger(1, bytes).toString(16);
        System.out.println(result);
    }
}
