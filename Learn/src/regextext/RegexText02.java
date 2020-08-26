package regextext;

import java.util.Scanner;

/**
 * @author shingo_ge
 * 请编写一个正则表达式匹配国内的电话号码规则：3~4位区号加7~8位电话，中间用-连接，例如：010-12345678。
 */
public class RegexText02 {
    public static void main(String[] args) {
        System.out.println("请输入一组电话号码：");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String regex = "\\d{3,4}-\\d{7,8}";
        if (str.matches(regex)){
            System.out.println("输入正确");
        }else {
            System.out.println("输入错误");
        }
    }
}
