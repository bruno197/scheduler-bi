package com.brunofr.services;

import java.util.List;
import java.util.UUID;

public interface IService<T> {

    T getAll();

    T getById(UUID id);

    T saveOrUpdate(Object o);

    void delete(UUID id);
}
