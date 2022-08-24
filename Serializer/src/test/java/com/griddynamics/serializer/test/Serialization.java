package com.griddynamics.serializer.test;

import com.google.gson.Gson;
import com.griddynamics.serializer.api.Serializer;
import com.griddynamics.serializer.base.pojo.Dog;
import com.griddynamics.serializer.base.pojo.Person;
import com.griddynamics.serializer.base.serializers.JSONSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Serialization {

    @Test
    public void testSerialization(){

        final Gson gson = new Gson();
        final Serializer jsonSerializer = new JSONSerializer();
        final Person person = new Person(1, 2, "name", new Dog("dogname",1));
        final String gsonString = gson.toJson(person);
        final String serialized = jsonSerializer.serialize(person);
        Assertions.assertEquals(serialized,gsonString);
    }
}
