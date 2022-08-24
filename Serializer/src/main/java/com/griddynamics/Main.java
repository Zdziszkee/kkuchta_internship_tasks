package com.griddynamics;

import com.griddynamics.dynamicproxy.api.Printable;
import com.griddynamics.dynamicproxy.base.PrintableFactory;
import com.griddynamics.dynamicproxy.base.TimingInvocationHandler;
import com.griddynamics.serializer.base.pojo.Dog;
import com.griddynamics.serializer.base.pojo.Person;
import com.griddynamics.serializer.base.serializers.JSONSerializer;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Main {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        final TimingInvocationHandler<Map<Integer, String>> timingInvocationHandler = new TimingInvocationHandler<>(map);
        final Map<Integer, String> proxiedMap = (Map<Integer, String>) Proxy.newProxyInstance(Map.class.getClassLoader(), new Class[]{Map.class},
                timingInvocationHandler);
        proxiedMap.put(1, "one");
        proxiedMap.get(1);
        //task2
        final Person person = new Person(12, 2342, "name", new Dog("dogname", 2));
        final Printable printable = PrintableFactory.createPrintable(person, new JSONSerializer());
        printable.print();
        //bytebuddy
        try {
            final Person instance = new ByteBuddy().subclass(Person.class)
                                                   .method(named("toString"))
                                                   .intercept(FixedValue.value("Hello world!"))
                                                   .make()
                                                   .load(ClassLoader.getSystemClassLoader())
                                                   .getLoaded()
                                                   .getDeclaredConstructor(int.class, int.class, String.class, Dog.class)
                                                   .newInstance(12, 2312, "name", new Dog("dogname", 2));
            System.out.println(instance);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("Something went wrong using bytebuddy");
        }
    }

}
