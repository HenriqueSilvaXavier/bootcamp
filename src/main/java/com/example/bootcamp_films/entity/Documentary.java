package com.example.bootcamp_films.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import lombok.NoArgsConstructor;
import com.example.bootcamp_films.entity.AbstractFilm;

@NoArgsConstructor
@Entity
public class Documentary extends AbstractFilm {
    @Column(nullable = false)
    public boolean narrated;

    public Documentary(String title, String director, int releaseYear, String genre, int duration, boolean narrated) {
        super(title, director, releaseYear, genre, duration);
        this.narrated = narrated;
    }

    public boolean isNarrated() {
        return narrated;
    }

    public void setNarrated(boolean narrated) {
        this.narrated = narrated;
    }
}