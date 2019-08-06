package com.bee.springboot.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Aspect// 指定当前类为切面类
public class  HandlingTimeAspect {

    @PostConstruct
    public void init(){
        System.out.println("auto record time init");
    }

    // @Pointcut("execution(* com.bee.springboot.controller.*.*(..))")
    @Pointcut("@annotation(com.bee.springboot.annotation.HandlingTime)")
    public void handlingTimePointcut() {}

    @Around("handlingTimePointcut()")
    public void handlingTimeAround(ProceedingJoinPoint joinPoint){
        try {
            long startTime = System.currentTimeMillis();
            joinPoint.proceed();
            //System.out.println(proceed);
            String methodName = joinPoint.getSignature().getName();
            String className = joinPoint.getSignature().getDeclaringTypeName();
            System.out.println(className + "." + methodName +"方法执行时间：" + (System.currentTimeMillis() - startTime)+"ms");
            //return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //return null;
    }


}
