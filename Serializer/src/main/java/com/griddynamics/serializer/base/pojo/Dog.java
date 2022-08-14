package com.griddynamics.serializer.base.pojo;

import com.griddynamics.serializer.base.Serializable;

@Serializable
public class Dog {

    private final String name;

    private final int age;

    private Person owner;

    public Dog(final String name, final int age) {

        this.name = name;
        this.age = age;
    }

    public void setOwner(final Person owner) {

        this.owner = owner;
    }

}
