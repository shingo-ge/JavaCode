package encryptionandsecurity;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author shingo_ge
 */
public class UrlTest01 {
    public static void main(String[] args) {
        encode();
        String decode = URLDecoder.decode("%E4%B8%AD%E6%96%87%EF%BC%81", StandardCharsets.UTF_8);
        System.out.println(decode);
    }

    private static void encode() {
        String encode = URLEncoder.encode("中文！", StandardCharsets.UTF_8);
        System.out.println(encode);
    }
}
