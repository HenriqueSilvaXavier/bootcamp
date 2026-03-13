package com.example.bootcamp_films.service;

import com.example.bootcamp_films.entity.Documentary;
import com.example.bootcamp_films.repository.DocumentaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.example.bootcamp_films.service.BaseServiceImplementation;
@Service
public class DocumentaryService extends BaseServiceImplementation<Documentary> {

    private final DocumentaryRepository documentaryRepository;

    public DocumentaryService(DocumentaryRepository documentaryRepository) {
        super(documentaryRepository);
        this.documentaryRepository = documentaryRepository;
    }

    @Override
    public Documentary update(Long id, Documentary entity) {
        Documentary existing = findById(id);
        existing.setTitle(entity.getTitle());
        existing.setDirector(entity.getDirector());
        existing.setReleaseYear(entity.getReleaseYear());
        existing.setGenre(entity.getGenre());
        existing.setDuration(entity.getDuration());
        existing.setNarrated(entity.isNarrated());

        return documentaryRepository.save(existing);
    }

    @Override
    public Page<Documentary> findAll(int page, int size) {
        return documentaryRepository.findAll(org.springframework.data.domain.PageRequest.of(page, size));
    }
}