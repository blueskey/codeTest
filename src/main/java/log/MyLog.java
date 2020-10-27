package log;

/**
 * @author ju
 */
public @interface MyLog {

    String module() default "";

    String operator() default "";

}
