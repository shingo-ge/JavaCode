package collection;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author shingo_ge
 * 请利用Stack把一个给定的整数转换为十六进制
 */
public class StackTest {
    public static void main(String[] args) {
        System.out.println("请输入一个整数：");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        String result = toHex(i);
        String standard = Integer.toHexString(i);
        System.out.println(result.equalsIgnoreCase(standard)? "测试成功":"测试失败");
    }

    private static String toHex(int i) {
        Deque<String> stack = new LinkedList<>();
        while (i!=0){
            int remain = i % 16;
            System.out.println(remain);
            stack.push(Integer.toHexString(remain));
            i = i/16;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : stack) {
            sb.append(s);
        }
        return sb.toString();

    }
}
