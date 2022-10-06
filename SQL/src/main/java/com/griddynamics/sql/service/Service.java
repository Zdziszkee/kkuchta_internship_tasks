package com.griddynamics.sql.service;

public interface Service<T, ID> {

    T find(ID id);

    void delete(ID id);

    void save(T type);

    void update(ID id, T type);

}
