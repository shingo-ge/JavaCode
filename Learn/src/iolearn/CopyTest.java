package iolearn;

import java.io.*;

/**
 * @author shingo_ge
 * 请利用InputStream和OutputStream，编写一个复制文件的程序，它可以带参数运行：
 */
public class CopyTest {
    public static void main(String[] args) {
        String sourcePath = "D:\\CoderLife\\JavaCode\\Learn\\src\\collection\\StackTest.java";
        String targetPath = "D:\\测试.java";
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);
        try {
            try (InputStream is = new FileInputStream(sourceFile);
                 OutputStream os = new FileOutputStream(targetFile)){
                is.transferTo(os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
