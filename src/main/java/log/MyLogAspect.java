package log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author ju
 */
@Aspect
@Component
public class MyLogAspect {

    @Around("@annotation(log.MyLog))")
    public void addMyLog(JoinPoint joinPoint) {

        MyLog myLog = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(MyLog.class);


        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getSignature().getName());

        System.out.println(joinPoint.getArgs());

        System.out.println(myLog.module());

    }
}
