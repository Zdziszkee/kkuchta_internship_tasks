package com.griddynamics.serializer.api;

/**
 * Type adapter used to serialize custom objects
 * @param <T>
 */
public interface TypeAdapter<T> {

    String serialize(T type);

    T deserialize(String serialized);

}
