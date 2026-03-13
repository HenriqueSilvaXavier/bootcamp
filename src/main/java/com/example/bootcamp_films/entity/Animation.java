package com.example.bootcamp_films.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Animation extends Film {

    private String animationStudio;
    private boolean dubbed;

    public Animation(
            String title,
            String director,
            int releaseYear,
            String genre,
            int duration,
            String animationStudio,
            boolean dubbed
    ) {
        super(title, director, releaseYear, genre, duration);
        setAnimationStudio(animationStudio);
        this.dubbed = dubbed;
    }

    public void setAnimationStudio(String animationStudio) {
        if (animationStudio == null || animationStudio.trim().isEmpty()) {
            throw new IllegalArgumentException("Estúdio não pode ser vazio.");
        }
        this.animationStudio = animationStudio;
    }

    public void setDubbed(boolean dubbed) {
        this.dubbed = dubbed;
    }
}