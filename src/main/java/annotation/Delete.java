package annotation;

import java.lang.annotation.*;

/**
 * @Author Martin Ma
 * @Date 2018/12/29
 **/


@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Delete {

    String value() default "";

}
