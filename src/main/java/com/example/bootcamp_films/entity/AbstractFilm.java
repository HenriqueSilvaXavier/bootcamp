package com.example.bootcamp_films.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractFilm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id; // ID único do filme

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private Integer releaseYear;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private Integer duration;

    // Construtor com todos os campos comuns
    public AbstractFilm(String title, String director, int releaseYear, String genre, int duration) {
        setTitle(title);
        this.director = director;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    // Validação do título
    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        this.title = title;
    }

    // Setters adicionais podem ser adicionados aqui se necessário
    public void setDirector(String director) {
        this.director = director;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}