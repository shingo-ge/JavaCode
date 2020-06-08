package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shingo_ge
 * 使用@Range注解来检查Java Bean的字段。如果字段类型是String，就检查String的长度，如果字段是int，就检查int的范围。
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
 @interface Range {
    int min() default 0;
    int max() default 255;
}
