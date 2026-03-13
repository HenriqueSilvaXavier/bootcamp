package com.example.bootcamp_films.service;

import com.example.bootcamp_films.entity.Documentary;
import com.example.bootcamp_films.repository.DocumentaryRepository;
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
class DocumentaryServiceTest {

    @Mock
    private DocumentaryRepository repository;

    @InjectMocks
    private DocumentaryService service;

    @Test
    void createDocumentarySuccessfully() {
        Documentary doc = new Documentary(
                "Planeta Terra","BBC",2006,"Natureza",50,true
        );

        when(repository.save(doc)).thenReturn(doc);

        Documentary result = service.create(doc);

        assertNotNull(result);
        assertTrue(result.isNarrated());
        verify(repository).save(doc);
    }

    @Test
    void findDocumentaryByIdSuccessfully() {
        Documentary doc = new Documentary(
                "Cosmos","Carl Sagan",1980,"Ciência",60,true
        );

        when(repository.findById(1L)).thenReturn(Optional.of(doc));

        Documentary result = service.findById(1L);

        assertEquals("Cosmos", result.getTitle());
        verify(repository).findById(1L);
    }

    @Test
    void findAllDocumentariesPagedSuccessfully() {
        Documentary d1 = new Documentary("Planeta Terra","BBC",2006,"Natureza",50,true);
        Documentary d2 = new Documentary("Cosmos","Sagan",1980,"Ciência",60,true);

        Page<Documentary> page = new PageImpl<>(List.of(d1,d2));

        when(repository.findAll(PageRequest.of(0,5))).thenReturn(page);

        Page<Documentary> result = service.findAll(0,5);

        assertEquals(2, result.getContent().size());
        verify(repository).findAll(PageRequest.of(0,5));
    }

    @Test
    void updateDocumentarySuccessfully() {
        Documentary existing = new Documentary(
                "Cosmos","Carl Sagan",1980,"Ciência",60,true
        );

        Documentary updated = new Documentary(
                "Cosmos: A Spacetime Odyssey","Neil Tyson",2014,"Ciência",60,true
        );

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Documentary.class))).thenReturn(updated);

        Documentary result = service.update(1L, updated);

        assertEquals("Cosmos: A Spacetime Odyssey", result.getTitle());
        assertEquals(2014, result.getReleaseYear());
        verify(repository).save(existing);
    }

    @Test
    void deleteDocumentarySuccessfully() {
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository).deleteById(1L);
    }
}