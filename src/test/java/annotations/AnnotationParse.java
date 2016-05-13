package annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/2/16 0016.
 */
public class AnnotationParse {

    public static void main(String[] args) throws Exception{
        for (Method method : AnnotationParse.class.getClassLoader().loadClass(("annotations.AnnotationExample")).getMethods()) {
            if (method.isAnnotationPresent(MethodInfo.class)) {
                for (Annotation anno : method.getDeclaredAnnotations()) {
                    System.out.println("Annotation in Method '"
                            + method + "' : " + anno);
                }
                MethodInfo methodAnno = method
                        .getAnnotation(MethodInfo.class);
                if (methodAnno.revision() == 1) {
                    System.out.println("Method with revision no 1 = "
                            + method);
                }
            }
        }
    }
}

