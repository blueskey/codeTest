/**
 * 360hqb.com.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package sign;


import org.aspectj.lang.ProceedingJoinPoint;

import javax.servlet.http.HttpServletRequest;

/**
 * 签名校验拦截器
 *
 */
public class SignCheckAdvice {

    /** 签名校验管理器 */
    private SignManager signManager;

    /**
     * 签名校验
     * 
     * @param joinPoint 织入点
     * @return 织入点处理结果
     * @throws Throwable 签名校验失败则抛出
     */
    public Object doCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = HttpServletHolder.getCurrentRequest();
        signManager.check(request);
        return joinPoint.proceed();
    }

    public void setSignManager(SignManager signManager) {
        this.signManager = signManager;
    }
}
