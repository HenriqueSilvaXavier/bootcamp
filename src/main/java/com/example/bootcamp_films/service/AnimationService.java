package com.example.bootcamp_films.service;

import com.example.bootcamp_films.entity.Animation;
import com.example.bootcamp_films.repository.AnimationRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.example.bootcamp_films.service.BaseServiceImplementation;

@Service
public class AnimationService extends BaseServiceImplementation<Animation> {

    private final AnimationRepository animationRepository;

    public AnimationService(AnimationRepository animationRepository) {
        super(animationRepository);
        this.animationRepository = animationRepository;
    }

    @Override
    public Animation update(Long id, Animation entity) {
        Animation existing = findById(id);

        // Campos obrigatórios
        existing.setTitle(entity.getTitle());
        existing.setDirector(entity.getDirector());
        existing.setReleaseYear(entity.getReleaseYear());
        existing.setGenre(entity.getGenre());
        existing.setDuration(entity.getDuration());
        existing.setAnimationStudio(entity.getAnimationStudio());
        existing.setDubbed(entity.getDubbed());
        return animationRepository.save(existing);
    }

    @Override
    public Page<Animation> findAll(int page, int size) {
        return animationRepository.findAll(org.springframework.data.domain.PageRequest.of(page, size));
    }
}