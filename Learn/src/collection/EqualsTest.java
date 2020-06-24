package collection;

import java.util.List;

/**
 * @author shingo_ge
 */
public class EqualsTest {
    public static void main(String[] args) {
        List<Person> personList = List.of(
                new Person("张","飞",23),
                new Person("关","羽",25),
                new Person("刘","备",26));

        boolean exist = personList.contains(new Person("刘","备",26));
        System.out.println(exist?"测试通过！":"测试失败！");
        int indexOf = personList.indexOf(new Person("刘", "备", 26));
        System.out.println(indexOf);
    }
}
