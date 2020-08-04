package collection;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author shingo_ge
 */
public class PropertiesTest {
    public static void main(String[] args) {
        String propertiesPath = "D:\\CoderLife\\JavaCode\\Learn\\src\\collection\\FileTest.properties";
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(propertiesPath, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties.getProperty("last_open_file"));
        properties.setProperty("测试","正常");
        try {

            properties.store(new FileWriter(propertiesPath,StandardCharsets.UTF_8),"test_Properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
