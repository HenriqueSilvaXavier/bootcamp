package com.example.bootcamp_films.dto;

import com.example.bootcamp_films.dto.request.AnimationCreateDTO;
import com.example.bootcamp_films.dto.request.AnimationUpdateDTO;
import com.example.bootcamp_films.dto.response.AnimationResponseDTO;
import com.example.bootcamp_films.entity.Animation;

public class AnimationMapper {

    public static Animation toEntity(AnimationCreateDTO dto) {
        return new Animation(
                dto.title(),
                dto.director(),
                dto.releaseYear(),
                dto.genre(),
                dto.duration(),
                dto.animationStudio(),
                dto.dubbed()
        );
    }

    public static AnimationResponseDTO toResponseDTO(Animation animation) {
        return new AnimationResponseDTO(
                animation.getId(),
                animation.getTitle(),
                animation.getDirector(),
                animation.getReleaseYear(),
                animation.getGenre(),
                animation.getDuration(),
                animation.getAnimationStudio(),
                animation.isDubbed()
        );
    }

    public static void updateEntity(Animation animation, AnimationUpdateDTO dto) {
        animation.setTitle(dto.title());
        animation.setDirector(dto.director());
        animation.setReleaseYear(dto.releaseYear());
        animation.setGenre(dto.genre());
        animation.setDuration(dto.duration());
        animation.setAnimationStudio(dto.animationStudio());
        animation.setDubbed(dto.dubbed());
    }
}