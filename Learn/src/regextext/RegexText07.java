package regextext;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @author shingo_ge
 */
public class RegexText07 {
    public static void main(String[] args) {
        String model = "Hello, ${name}! You are learning ${lang}!";
        Map<String, String> map = new HashMap<>(10);
        map.put("name","张飞");
        map.put("lang","Java");
        System.out.println(replaceModule(model,map));
    }

    private static String replaceModule(String model, Map<String, String> map) {
        var sb = new StringBuilder();
        Pattern pattern = compile("\\$\\{(\\w+)}");
        Matcher matcher = pattern.matcher(model);
        while (matcher.find()){
            matcher.appendReplacement(sb,map.get(matcher.group(1)));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
