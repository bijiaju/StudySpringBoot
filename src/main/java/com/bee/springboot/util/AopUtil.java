package com.bee.springboot.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;

/**
 * @author bee
 * created at 2018/12/5
 */
public class AopUtil {

    private static String DELIMIT = "={}, ";

    /**
     * It's useful when enter a method
     *
     * @param logger
     * @param joinPoint
     */
    public static void logEnter(Logger logger, JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String method = getMethodName(joinPoint);
        StringBuilder format = new StringBuilder(">>> " + method + " ");
        String[] names = signature.getParameterNames();
        Class[] types = signature.getParameterTypes();
        for (int i = 0; i < types.length; i++) {
            format.append(names[i]);
            format.append(DELIMIT);
        }
        logger.info(format.toString(), joinPoint.getArgs());
    }

    /**
     * It's useful when exit a method
     *
     * @param logger
     * @param joinPoint
     */
    public static void logExit(Logger logger, JoinPoint joinPoint, Object result) {
        String method = getMethodName(joinPoint);
        logger.info("<<< {} ,result is {}", method, result);
    }

    public static String getMethodName(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return joinPoint.getTarget().getClass().getSimpleName() + "." + signature.getName();
    }

    public static String getFullMethodName(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return joinPoint.getTarget().getClass().getName() + "." + signature.getName();
    }
}
