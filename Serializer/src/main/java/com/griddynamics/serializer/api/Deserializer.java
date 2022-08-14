package com.griddynamics.serializer.api;

public interface Deserializer {

    <T> T deserialize(String serialized);

}
