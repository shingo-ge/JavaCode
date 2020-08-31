package encryptionandsecurity;

import java.util.Arrays;
import java.util.Base64;

/**
 * @author shingo_ge
 */
public class Base64Test01 {
    public static void main(String[] args) {
        encode();
        System.out.println("--------------------");
        decode();
        System.out.println("--------------------");
        withoutPaddingTest();
        System.out.println("--------------------");
        byte[] input = { 0x01, 0x02, 0x7f, 0x00 };
        String encode = Base64.getUrlEncoder().encodeToString(input);
        System.out.println(encode);
        byte[] decode = Base64.getUrlDecoder().decode(encode);
        System.out.println(Arrays.toString(decode));

    }

    private static void withoutPaddingTest() {
        byte[] input = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad, 0x21 };
        String encode1 = Base64.getEncoder().encodeToString(input);
        String encode2 = Base64.getEncoder().withoutPadding().encodeToString(input);
        System.out.println(encode1);
        System.out.println(encode2);
        byte[] decode = Base64.getDecoder().decode(encode2);
        System.out.println(Arrays.toString(decode));
    }

    private static void decode() {
        byte[] decode = Base64.getDecoder().decode("5Lit");
        String output = Arrays.toString(decode);
        System.out.println(output);
    }

    private static void encode() {
        byte[] input = {(byte) 0xe4, (byte) 0xb8, (byte) 0xad};
        String encode = Base64.getEncoder().encodeToString(input);
        System.out.println(encode);
    }
}
