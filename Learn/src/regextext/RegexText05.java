package regextext;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @author shingo_ge
 * 利用分组匹配，从字符串"23:01:59"提取时、分、秒。
 */
public class RegexText05 {
    public static void main(String[] args) {
        System.out.println("请输入一组时间（格式如：23:01:59）");
        Scanner scanner = new Scanner(System.in);
        String timeStr = scanner.nextLine();
        Pattern pattern = compile("([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])");
        Matcher matcher = pattern.matcher(timeStr);
        if (matcher.matches()){
            String time = matcher.group(0);
            String hour = matcher.group(1);
            String minute = matcher.group(2);
            String second = matcher.group(3);
            System.out.println(time);
            System.out.println(hour);
            System.out.println(minute);
            System.out.println(second);
        }else {
            System.out.println("输入错误");
        }
    }
}
