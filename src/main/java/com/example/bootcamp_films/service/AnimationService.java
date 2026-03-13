package com.example.bootcamp_films.service;

import com.example.bootcamp_films.entity.Animation;
import com.example.bootcamp_films.repository.AnimationRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Service
public class AnimationService {

    private final AnimationRepository repository;

    public AnimationService(AnimationRepository repository) {
        this.repository = repository;
    }

    public Animation create(Animation animation) {
        return repository.save(animation);
    }

    public Page<Animation> listPaged(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public Animation findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animação não encontrada"));
    }

    public Animation update(Long id, Animation updated) {
        Animation animation = findById(id);

        animation.setAnimationStudio(updated.getAnimationStudio());
        animation.setDubbed(updated.isDubbed());

        // campos herdados de Film
        animation.setTitle(updated.getTitle());
        animation.setDirector(updated.getDirector());
        animation.setReleaseYear(updated.getReleaseYear());
        animation.setGenre(updated.getGenre());
        animation.setDuration(updated.getDuration());

        return repository.save(animation);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}