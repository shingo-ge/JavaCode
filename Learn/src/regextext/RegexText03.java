package regextext;

/**
 * @author shingo_ge
 */
public class RegexText03 {
    public static void main(String[] args) {
        String regex = "learn\\s([Jj]ava|[Pp]hp|[Gg]o)";
        System.out.println("learn java".matches(regex));
        System.out.println("learn Java".matches(regex));
    }
}
