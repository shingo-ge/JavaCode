package collection;

import java.util.List;

/**
 * @author shingo_ge
 */
public class MapTest {
    public static void main(String[] args) {
        List<Student> studentList = List.of(
                new Student("张飞",95.5),
                new Student("关羽",89.5),
                new Student("刘备",99.0)
        );
        Students students = new Students(studentList);
        double score = students.getScore("张飞");
        System.out.println(score);
    }
}
