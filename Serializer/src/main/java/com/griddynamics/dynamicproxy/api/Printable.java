package com.griddynamics.dynamicproxy.api;

public interface Printable {

    default void print() {
        System.out.println(this);
    }

}
