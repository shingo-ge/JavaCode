package iolearn;

import java.io.File;

/**
 * @author shingo_ge
 */
public class FileTest {
    public static void main(String[] args) {
        File file = new File("D:\\test");
        int level = 0;
        System.out.println(file.getName());
        printFile(file,level);
    }

    private static void printFile(File file, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= level; i++) {
            sb.append("  ");
        }
        sb.append("├-");
        File[] files = file.listFiles();
        assert files != null;
        for (File file1 : files) {
            System.out.print(sb);
            System.out.println(file1.getName());
            if (file1.isDirectory()){
                printFile(file1,++level);
            }
        }

    }
}
