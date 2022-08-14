package com.griddynamics.serializer;

import com.griddynamics.serializer.base.pojo.Dog;
import com.griddynamics.serializer.base.pojo.Person;
import com.griddynamics.serializer.base.serializers.JSONSerializer;

public class Serializer {

    public static void main(String[] args) {

        final Dog dog = new Dog("name",1);
        final Person person = new Person(1, 1, "name", dog);
        dog.setOwner(person);

        final JSONSerializer jsonSerializer = new JSONSerializer();
        final String serialized = jsonSerializer.serialize(person);
        System.out.println(serialized);
    }

}
