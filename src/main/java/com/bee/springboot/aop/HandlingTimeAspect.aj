package com.bee.springboot.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect// 指定当前类为切面类
@Component
public class  HandlingTimeAspect {
    @Pointcut("execution(* com.bee.springboot.controller.*.*(..))")
    public void handlingTimePointcut() {}

    @Around("handlingTimePointcut()")
    public void handlingTimeAround(ProceedingJoinPoint joinPoint){
        try {
            long startTime = System.currentTimeMillis();
            joinPoint.proceed();
            //System.out.println(proceed);
            System.out.println("方法执行时间：" + (System.currentTimeMillis() - startTime));
            //return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //return null;
    }


}
