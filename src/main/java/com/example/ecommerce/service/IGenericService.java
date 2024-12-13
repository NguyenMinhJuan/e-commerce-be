package com.example.ecommerce.service;

import java.util.Optional;

public interface IGenericService<T, L extends Number>{
    Iterable<T> findAll();
    Optional<T> findById(Long id);
    void save(T t);
    void delete(Long id);
}
