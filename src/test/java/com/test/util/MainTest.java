package com.test.util;

import org.junit.Test;

import java.util.Properties;

public class MainTest {

    /**
     * 获取系统变量
     */
    @Test
    public void method1(){
        //获取所有的属性
        Properties properties = System.getProperties();
        for(String key:properties.stringPropertyNames()){
            //输出对应的键和值
            System.out.println(key + "=" + properties.getProperty(key));
        }
        /**
         * java.runtime.name=Java(TM) SE Runtime Environment
         * sun.boot.library.path=C:\Program Files\jdk\java8\jre\bin
         * java.vm.version=25.74-b02
         * java.vm.vendor=Oracle Corporation
         * ....
         */
    }
}
