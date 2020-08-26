package regextext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @author shingo_ge
 */
public class RegexText06 {
    public static void main(String[] args) {
        String str = "the quick brown fox jumps over the lazy dog.";
        Pattern pattern = compile("\\wo\\w");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            String sub = str.substring(matcher.start(),matcher.end());
            System.out.println(sub);
        }
    }
}
