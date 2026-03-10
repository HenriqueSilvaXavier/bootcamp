package com.example.bootcamp_films.repository;

import com.example.bootcamp_films.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long>{
}
