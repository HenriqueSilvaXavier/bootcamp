package com.example.bootcamp_films.dto.response;

public record AnimationResponseDTO(
        Long id,
        String title,
        String director,
        int releaseYear,
        String genre,
        int duration,
        String animationStudio,
        boolean dubbed
) {}