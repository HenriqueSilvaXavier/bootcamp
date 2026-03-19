package com.example.bootcamp_films.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import com.example.bootcamp_films.entity.AbstractFilm;

@NoArgsConstructor
@Entity
public class Animation extends AbstractFilm {

    @Column(nullable = false)
    private String animationStudio;

    @Column(nullable = false)
    private Boolean dubbed;

    public Animation(String title, String director, int releaseYear, String genre,
                     int duration, String animationStudio, boolean dubbed) {
        super(title, director, releaseYear, genre, duration);
        this.animationStudio = animationStudio;
        this.dubbed = dubbed;
    }

    public String getAnimationStudio() {
        return animationStudio;
    }

    public void setAnimationStudio(String animationStudio) {
        if (animationStudio == null || animationStudio.isBlank()) {
            throw new IllegalArgumentException("Studio can not be null.");
        }
        this.animationStudio = animationStudio;
    }

    public Boolean getDubbed() {
        return dubbed;
    }

    public void setDubbed(Boolean dubbed) {
        this.dubbed = dubbed;
    }
}