package com.example.bootcamp_films.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseServiceImplementation<T> implements BaseService<T> {

    protected final JpaRepository<T, Long> repository;

    protected BaseServiceImplementation(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public T findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found with id=" + id));
    }

    @Override
    public Page<T> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public abstract T update(Long id, T entity);
}