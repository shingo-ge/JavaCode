package collection;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author shingo_ge
 */
public class TreeMapTest {
    public static void main(String[] args) {
        Map<Student,Integer> map = new TreeMap<>(Comparator.comparingDouble(Student::getScore));
        map.put(new Student("张飞",79.5),1);
        map.put(new Student("关羽",60.5),2);
        map.put(new Student("刘备",91.0),3);
        for (Student student : map.keySet()) {
            System.out.println(student.getName());
        }
        System.out.println(map.get(new Student("张飞",79.5)));
    }
}
