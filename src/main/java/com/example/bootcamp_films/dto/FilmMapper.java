package com.example.bootcamp_films.dto;

import src.main.java.com.example.bootcamp_films.dto.request.FilmCreateDTO;
import src.main.java.com.example.bootcamp_films.dto.request.FilmUpdateDTO;
import com.example.bootcamp_films.dto.response.FilmResponseDTO;
import com.example.bootcamp_films.entity.Film;

public class FilmMapper {

    public static Film toEntity(FilmCreateDTO dto) {
        return new Film(
                dto.getTitle(),
                dto.getDirector(),
                dto.getReleaseYear(),
                dto.getGenre(),
                dto.getDuration()
        );
    }

    public static void updateEntity(Film film, FilmUpdateDTO dto) {
        film.setTitle(dto.getTitle());
        film.setDirector(dto.getDirector());
        film.setReleaseYear(dto.getReleaseYear());
        film.setGenre(dto.getGenre());
        film.setDuration(dto.getDuration());
    }

    public static FilmResponseDTO toDTO(Film film) {
        return new FilmResponseDTO(
                film.getId(),
                film.getTitle(),
                film.getDirector(),
                film.getReleaseYear(),
                film.getGenre(),
                film.getDuration()
        );
    }
}