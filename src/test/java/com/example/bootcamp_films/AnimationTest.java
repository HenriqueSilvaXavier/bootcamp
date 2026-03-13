package com.example.bootcamp_films;

import com.example.bootcamp_films.entity.Animation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimationTest {

    @Test
    void createAnimationSuccessfully() {
        Animation animation = new Animation(
                "Toy Story",
                "John Lasseter",
                1995,
                "Animação",
                81,
                "Pixar",
                true
        );

        assertEquals("Toy Story", animation.getTitle());
        assertEquals("John Lasseter", animation.getDirector());
        assertEquals(1995, animation.getReleaseYear());
        assertEquals("Animação", animation.getGenre());
        assertEquals(81, animation.getDuration());
        assertEquals("Pixar", animation.getAnimationStudio());
        assertTrue(animation.getDubbed());
    }

    @Test
    void setAnimationStudioSuccessfully() {
        Animation animation = new Animation(
                "Toy Story",
                "John Lasseter",
                1995,
                "Animação",
                81,
                "Pixar",
                true
        );

        animation.setAnimationStudio("DreamWorks");

        assertEquals("DreamWorks", animation.getAnimationStudio());
    }

    @Test
    void setDubbedSuccessfully() {
        Animation animation = new Animation(
                "Toy Story",
                "John Lasseter",
                1995,
                "Animação",
                81,
                "Pixar",
                true
        );

        animation.setDubbed(false);

        assertFalse(animation.getDubbed());
    }

    @Test
    void setAnimationStudioBlankShouldThrowException() {
        Animation animation = new Animation(
                "Toy Story",
                "John Lasseter",
                1995,
                "Animação",
                81,
                "Pixar",
                true
        );

        assertThrows(IllegalArgumentException.class, () ->
                animation.setAnimationStudio("")
        );
    }

    @Test
    void inheritedSetTitleSuccessfully() {
        Animation animation = new Animation(
                "Toy Story",
                "John Lasseter",
                1995,
                "Animação",
                81,
                "Pixar",
                true
        );

        animation.setTitle("Toy Story 2");

        assertEquals("Toy Story 2", animation.getTitle());
    }

    @Test
    void inheritedSetTitleBlankShouldThrowException() {
        Animation animation = new Animation(
                "Toy Story",
                "John Lasseter",
                1995,
                "Animação",
                81,
                "Pixar",
                true
        );

        assertThrows(IllegalArgumentException.class, () ->
                animation.setTitle(" ")
        );
    }
}