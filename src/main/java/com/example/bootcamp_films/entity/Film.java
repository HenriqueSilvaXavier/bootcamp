package com.example.bootcamp_films.entity;

import lombok.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

import java.time.Year;

@Getter
@NoArgsConstructor
@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String title;
    public String director;
    public int releaseYear;
    public String genre;
    public int duration;

    public Film(String title, String director, int releaseYear, String genre, int duration) {
        setTitle(title);
        setDirector(director);
        setReleaseYear(releaseYear);
        setGenre(genre);
        setDuration(duration);
    }

    // SETTERS COM VALIDAÇÃO
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser vazio.");
        }
        this.title = title;
    }

    public void setDirector(String director) {
        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException("O diretor não pode ser vazio.");
        }
        this.director = director;
    }

    public void setReleaseYear(int releaseYear) {
        int currentYear = Year.now().getValue();
        if (releaseYear < 1888) {
            throw new IllegalArgumentException("Não existiam filmes antes de 1888.");
        }
        if (releaseYear > currentYear) {
            throw new IllegalArgumentException("O ano não pode ser no futuro.");
        }
        this.releaseYear = releaseYear;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("O gênero não pode ser vazio.");
        }
        this.genre = genre;
    }

    public void setDuration(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("A duração deve ser maior que zero.");
        }
        if (duration > 600) {
            throw new IllegalArgumentException("A duração máxima permitida é 600 minutos.");
        }
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre='" + genre + '\'' +
                ", duration=" + duration + " min" +
                '}';
    }
}