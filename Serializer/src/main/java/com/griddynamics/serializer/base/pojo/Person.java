package com.griddynamics.serializer.base.pojo;

import com.griddynamics.dynamicproxy.api.Printable;
import com.griddynamics.serializer.base.Serializable;

@Serializable
public class Person implements Printable {

    private final int age;

    private final int money;

    private final String name;

    private final Dog dog;

    public Person(final int age, final int money, final String name, final Dog dog) {
        this.age = age;
        this.money = money;
        this.name = name;
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" + "age=" + age + ", money=" + money + ", name='" + name + '\'' + '}';
    }

}
