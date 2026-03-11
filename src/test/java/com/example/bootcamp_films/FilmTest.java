package com.example.bootcamp_films;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.bootcamp_films.entity.Film;

class FilmTest {

    @Test
    void setTitleSuccessfully() {
        Film film = new Film("O Senhor dos Anéis: A Sociedade do Anel", "Peter Jackson", 2001, "Fantasia", 178);
        film.setTitle("O Senhor dos Anéis: As Duas Torres");

        assertEquals("O Senhor dos Anéis: As Duas Torres", film.getTitle());
    }

    @Test
    void setDirectorSuccessfully() {
        Film film = new Film("O Senhor dos Anéis: A Sociedade do Anel", "Peter Jackson", 2001, "Fantasia", 178);
        film.setDirector("Martin Scorsese");

        assertEquals("Martin Scorsese", film.getDirector());
    }

    @Test
    void setReleaseYearSuccessfully() {
        Film film = new Film("O Senhor dos Anéis: A Sociedade do Anel", "Peter Jackson", 2001, "Fantasia", 178);
        film.setReleaseYear(2007);

        assertEquals(2007, film.getReleaseYear());
    }

    @Test
    void setGenreSuccessfully() {
        Film film = new Film("O Senhor dos Anéis: A Sociedade do Anel", "Peter Jackson", 2001, "Fantasia", 178);
        film.setGenre("Aventura");

        assertEquals("Aventura", film.getGenre());
    }

    @Test
    void setDurationSuccessfully() {
        Film film = new Film("O Senhor dos Anéis: A Sociedade do Anel", "Peter Jackson", 2001, "Fantasia", 178);
        film.setDuration(201);

        assertEquals(201, film.getDuration());
    }
}