package com.example.bootcamp_films.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AnimationUpdateDTO(

        @NotBlank String title,
        @NotBlank String director,
        @Positive int releaseYear,
        @NotBlank String genre,
        @Positive int duration,

        @NotBlank String animationStudio,
        @NotNull Boolean dubbed
) {}