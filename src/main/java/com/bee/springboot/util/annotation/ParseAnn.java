package com.bee.springboot.util.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 解析注解，一般操作都是在这里进行
 */
public class ParseAnn {

    public static void main(String[] args){
        //1.加载类
        try{
            Class c = Class.forName("com.bee.springboot.util.annotation.Child");
            //2.找到类上面的注解
            boolean isExist = c.isAnnotationPresent(Description.class);
            if(isExist){
                //3.拿到注解实例
                Description d = (Description) c.getAnnotation(Description.class);
                System.out.println(d.desc());
            }
            //4.找到方法上的注解
            Method[] ms = c.getMethods();
           /* for(Method m:ms){//遍历到两个方法，第二个是空指针
                boolean isMExist = m.isAnnotationPresent(Description.class);
                if(isExist){
                    Description d = m.getAnnotation(Description.class);
                    System.out.println(d.desc());
                }

            }*/

            /**************************另一种解析方法**************************************/
            //获取所有注解 进行解析
            for(Method m:ms){
                Annotation[] as = m.getAnnotations();
                for(Annotation a:as){
                    if(a instanceof Description){
                        Description d = (Description)a;
                        System.out.println(d.desc());
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
