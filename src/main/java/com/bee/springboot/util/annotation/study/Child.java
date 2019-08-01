package com.bee.springboot.util.annotation.study;

@Description(desc = "i am class annotation", author = "bee")
public class Child implements Person {

    @Override
    @Description(desc = "i am method annotation", author = "bee1")
    public String name() {
        return null;
    }

    @Override
    public int age() {
        return 0;
    }

    @Override
    public void sing() {

    }
}
