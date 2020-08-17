package junitlearn;

import java.util.Scanner;

/**
 * @author shingo_ge
 */
public class MyFactorial {
    public static void main(String[] args) {
        System.out.println("请输入一个正整数：");
        Scanner scanner = new Scanner(System.in);
        long l = scanner.nextLong();
        long result = factorialCalculation(l);
        System.out.println(result);
    }

    public static long factorialCalculation(long l) {
        long n = 1;
        for (int i = 1; i <= l; i++) {
            n = n *i;
        }
        return n;
    }
}
