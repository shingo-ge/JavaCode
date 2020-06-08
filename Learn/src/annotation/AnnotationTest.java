package annotation;

import java.lang.reflect.Field;

/**
 * @author shingo_ge
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Person[] persons = {
                new Person("张飞",200),
                new Person("关羽",28),
                new Person("刘",30)
        };
        for (Person person : persons) {
            try {
                check(person);
                System.out.println("用户：" + person.getName() + "，您数据通过检查！");
            } catch (IllegalArgumentException | IllegalAccessException e) {
                System.out.println("用户：" + person.getName() + "，您数据未通过检查！" + e);
            }
        }
    }

    private static void check(Person person) throws IllegalAccessException,IllegalArgumentException {
        //获取Person类所有字段
        Field[] fields = person.getClass().getDeclaredFields();
        //遍历所有字段
        for (Field field : fields) {
            //设置字段可访问
            field.setAccessible(true);
            //尝试获取该字段的Range注解
            Range range = field.getAnnotation(Range.class);
            //如果该字段的Range注解存在
            if (range != null){
                //获取该该对象此字段的值
                Object value = field.get(person);
                //如果值为字符串类型
                if (value instanceof String){
                    String s = (String) value;
                    //判断是否在Range注解范围之内
                    if (s.length() < range.min() || s.length() > range.max()){
                        throw new IllegalArgumentException("无效的字段："+field.getName());
                    }
                    //如果值为整数类型
                }else if (value instanceof Integer){
                    int i = (int) value;
                    //判断是否在Range注解范围之内
                    if (i < range.min() || i > range.max()){
                        throw new IllegalArgumentException("无效的字段："+field.getName());
                    }
                }
            }
        }
    }
}
