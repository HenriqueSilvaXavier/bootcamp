package com.example.bootcamp_films.service;

import com.example.bootcamp_films.entity.Animation;
import com.example.bootcamp_films.repository.AnimationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimationServiceTest {

    @Mock
    private AnimationRepository repository;

    @InjectMocks
    private AnimationService service;

    @Test
    void createAnimationSuccessfully() {
        Animation animation = new Animation(
                "Toy Story","Pixar",1995,"Infantil",81,
                "Pixar", true
        );

        when(repository.save(animation)).thenReturn(animation);

        Animation result = service.create(animation);

        assertNotNull(result);
        assertEquals("Toy Story", result.getTitle());
        verify(repository).save(animation);
    }

    @Test
    void findAnimationByIdSuccessfully() {
        Animation animation = new Animation(
                "Shrek","DreamWorks",2001,"Comédia",90,
                "DreamWorks", true
        );

        when(repository.findById(1L)).thenReturn(Optional.of(animation));

        Animation result = service.findById(1L);

        assertEquals("Shrek", result.getTitle());
        verify(repository).findById(1L);
    }

    @Test
    void findAllAnimationsPagedSuccessfully() {
        Animation a1 = new Animation("Toy Story","Pixar",1995,"Infantil",81,"Pixar",true);
        Animation a2 = new Animation("Shrek","DreamWorks",2001,"Comédia",90,"DreamWorks",true);

        Page<Animation> page = new PageImpl<>(List.of(a1,a2));

        when(repository.findAll(PageRequest.of(0,5))).thenReturn(page);

        Page<Animation> result = service.findAll(0,5);

        assertEquals(2, result.getContent().size());
        verify(repository).findAll(PageRequest.of(0,5));
    }

    @Test
    void updateAnimationSuccessfully() {
        Animation existing = new Animation(
                "Toy Story","Pixar",1995,"Infantil",81,
                "Pixar", true
        );

        Animation updated = new Animation(
                "Toy Story 2","Pixar",1999,"Infantil",92,
                "Pixar", true
        );

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Animation.class))).thenReturn(updated);

        Animation result = service.update(1L, updated);

        assertEquals("Toy Story 2", result.getTitle());
        assertEquals(1999, result.getReleaseYear());
        verify(repository).save(existing);
    }

    @Test
    void deleteAnimationSuccessfully() {
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository).deleteById(1L);
    }
}