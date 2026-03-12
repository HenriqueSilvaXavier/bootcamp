package com.example.bootcamp_films.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmUpdateDTO {
    private String title;
    private String director;
    private int releaseYear;
    private String genre;
    private int duration;
}