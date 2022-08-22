package com.griddynamics.dynamicproxy.base;

import com.griddynamics.dynamicproxy.api.Printable;
import com.griddynamics.serializer.api.Serializer;

import java.lang.reflect.Proxy;

public class PrintableFactory {


    public static Printable createPrintable(Printable printable, Serializer serializer) {

        return (Printable) Proxy.newProxyInstance(Printable.class.getClassLoader(), new Class[]{Printable.class},
                new PrintInvocationHandler(printable, serializer));
    }

}
