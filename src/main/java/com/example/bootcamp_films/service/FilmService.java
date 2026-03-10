package com.example.bootcamp_films.service;

import com.example.bootcamp_films.entity.Film;
import com.example.bootcamp_films.repository.FilmRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository repository;

    public FilmService(FilmRepository repository) {
        this.repository = repository;
    }

    public Film createFilm(Film film){
        return repository.save(film);
    }

    public Page<Film> getFilmsPaged(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public Film getFilmById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado: id=" + id));
    }

    public Film updateFilm(Film film){
        return repository.save(film);
    }

    public void deleteFilm(Long id){
        if (!repository.existsById(id)) {
            throw new RuntimeException("Filme não encontrado: id=" + id);
        }
        repository.deleteById(id);
    }
}