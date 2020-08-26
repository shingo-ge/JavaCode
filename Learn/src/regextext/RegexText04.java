package regextext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @author shingo_ge
 */
public class RegexText04 {
    public static void main(String[] args) {
        Pattern pattern = compile("(\\d{3,4})-(\\d{7,8})");
        Matcher matcher = pattern.matcher("010-12345678");
        if (matcher.matches()){
            String str1 = matcher.group(1);
            String str2 = matcher.group(2);
            System.out.println(str1);
            System.out.println(str2);
        }
    }
}
