package com.example.bootcamp_films.service;

import com.example.bootcamp_films.entity.Film;
import com.example.bootcamp_films.repository.FilmRepository;
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
class FilmServiceTest {

    @Mock
    private FilmRepository repository;

    @InjectMocks
    private FilmService service;

    @Test
    void createFilmSuccessfully() {
        Film film = new Film(
                "John Wick: De Volta ao Jogo",
                "Chad Stahelski",
                2014,
                "Ação",
                101
        );

        Film filmSalvo = new Film(
                "John Wick: De Volta ao Jogo",
                "Chad Stahelski",
                2014,
                "Ação",
                101
        );

        when(repository.save(film)).thenReturn(filmSalvo);

        Film resultado = service.createFilm(film);

        assertNotNull(resultado);
        assertEquals("John Wick: De Volta ao Jogo", resultado.getTitle());
        assertEquals("Chad Stahelski", resultado.getDirector());
        assertEquals(2014, resultado.getReleaseYear());
        assertEquals("Ação", resultado.getGenre());
        assertEquals(101, resultado.getDuration());

        verify(repository, times(1)).save(film);
    }

    @Test
    void getFilmsPagedSuccessfully() {
        int page = 0;
        int size = 2;

        Film film1 = new Film("Interestelar", "Nolan", 2014, "Ficção", 169);
        Film film2 = new Film("Matrix", "Wachowski", 1999, "Ficção", 136);

        List<Film> filmes = List.of(film1, film2);

        Pageable pageableEsperado = PageRequest.of(page, size);
        Page<Film> pageMock = new PageImpl<>(filmes, pageableEsperado, filmes.size());

        when(repository.findAll(pageableEsperado)).thenReturn(pageMock);

        Page<Film> resultado = service.getFilmsPaged(page, size);

        assertNotNull(resultado);
        assertEquals(2, resultado.getContent().size());
        assertEquals("Interestelar", resultado.getContent().get(0).getTitle());
        assertEquals("Matrix", resultado.getContent().get(1).getTitle());
        assertEquals(0, resultado.getNumber());
        assertEquals(2, resultado.getSize());

        verify(repository, times(1)).findAll(pageableEsperado);
    }

    @Test
    void getFilmByIdSuccessfully() {
        Long id = 1L;

        Film film = new Film(
                "Onde os fracos não têm vez",
                "Joel e Ethan Coen",
                2007,
                "Thriller",
                122
        );

        when(repository.findById(id)).thenReturn(Optional.of(film));

        Film resultado = service.getFilmById(id);

        assertNotNull(resultado);
        assertEquals("Onde os fracos não têm vez", resultado.getTitle());
        assertEquals("Joel e Ethan Coen", resultado.getDirector());
        assertEquals(2007, resultado.getReleaseYear());
        assertEquals("Thriller", resultado.getGenre());
        assertEquals(122, resultado.getDuration());

        verify(repository, times(1)).findById(id);
    }

    @Test
    void updateFilmSuccessfully() {
        Film film = new Film(
                "O Senhor dos Anéis: A Sociedade do Anel",
                "Peter Jackson",
                2001,
                "Fantasia",
                178
        );

        when(repository.save(film)).thenReturn(film);

        Film resultado = service.updateFilm(film);

        assertNotNull(resultado);
        assertEquals("O Senhor dos Anéis: A Sociedade do Anel", resultado.getTitle());
        assertEquals("Peter Jackson", resultado.getDirector());
        assertEquals(2001, resultado.getReleaseYear());
        assertEquals("Fantasia", resultado.getGenre());
        assertEquals(178, resultado.getDuration());

        verify(repository, times(1)).save(film);
    }

    @Test
    void deleteFilmSuccessfully() {
        Long id = 1L;

        when(repository.existsById(id)).thenReturn(true);
        doNothing().when(repository).deleteById(id);

        service.deleteFilm(id);

        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).deleteById(id);
    }
}