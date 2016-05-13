package annotations;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2016/2/16 0016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.METHOD)
public @interface MethodInfo {

    String author() default "admin";

    String date();

    int revision() default 1;

    String comments();


}
