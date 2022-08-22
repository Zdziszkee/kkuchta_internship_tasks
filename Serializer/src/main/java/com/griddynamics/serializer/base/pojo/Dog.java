package com.griddynamics.serializer.base.pojo;

import com.griddynamics.dynamicproxy.api.Printable;
import com.griddynamics.serializer.base.Serializable;

@Serializable
public class Dog implements Printable {

    private final String name;

    private final int age;


    public Dog(final String name, final int age) {

        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {

        return "Dog{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

}
