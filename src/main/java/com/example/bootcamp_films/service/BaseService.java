package com.example.bootcamp_films.service;

import org.springframework.data.domain.Page;

public interface BaseService<T> {
    T create(T entity);
    T update(Long id, T entity);
    T findById(Long id);  // retorna T diretamente, não Optional
    void delete(Long id);
    Page<T> findAll(int page, int size);
}