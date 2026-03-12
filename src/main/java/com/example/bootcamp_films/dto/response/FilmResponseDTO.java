package com.example.bootcamp_films.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilmResponseDTO {
    private Long id;
    private String title;
    private String director;
    private int releaseYear;
    private String genre;
    private int duration;
}