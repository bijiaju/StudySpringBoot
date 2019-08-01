package com.bee.springboot.util.annotation;

/**
 * @author weichunhe
 * created at 2018/11/30
 */
public class ArgumentResolverException extends RuntimeException {
    public ArgumentResolverException(String message) {
        super(message);
    }

    public static void throwException(String message) {
        throw new ArithmeticException(message);
    }
}
