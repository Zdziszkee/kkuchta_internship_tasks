package com.griddynamics.sqlutility.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface Service<T,ID> {
    Optional<T> findById(ID id);
    Optional<T> save(T type);
    List<T> findAll();

}
