package regextext;

/**
 * @author shingo_ge
 */
public class RegexText01 {
    public static void main(String[] args) {
        String regex = "20\\d\\d";
        System.out.println("2019".matches(regex));
        System.out.println("2120".matches(regex));
    }
}
