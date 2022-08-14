package com.griddynamics.serializer.base.pojo;

import com.griddynamics.serializer.base.Serializable;

@Serializable
public class Person {
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

}
