package com.griddynamics.serializer.api;

public interface Serializer {

    /**
     * @param type object to serialize
     * @param <T>  type of passed object
     * @return String representing serialized object
     * @throws IllegalArgumentException if class of this object is not annotated with @Serializable
     */
    <T> String serialize(T type) throws IllegalArgumentException;

}
