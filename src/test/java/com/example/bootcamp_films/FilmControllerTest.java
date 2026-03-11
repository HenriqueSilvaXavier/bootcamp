package com.example.bootcamp_films;

import com.example.bootcamp_films.entity.Film;
import com.example.bootcamp_films.service.FilmService;
import com.example.bootcamp_films.controller.FilmController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FilmController.class)
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFilmSuccessfully() throws Exception {
        Film film = new Film("Interestelar","Nolan",2014,"Ficção",169);

        Mockito.when(service.createFilm(any(Film.class))).thenReturn(film);

        mockMvc.perform(post("/api/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(film)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Interestelar"))
                .andExpect(jsonPath("$.director").value("Nolan"));
    }

    @Test
    void getFilmsPagedSuccessfully() throws Exception {
        Film film1 = new Film("Matrix","Wachowski",1999,"Ficção",136);
        Film film2 = new Film("Interestelar","Nolan",2014,"Ficção",169);

        List<Film> filmes = List.of(film1, film2);
        Page<Film> page = new PageImpl<>(filmes, PageRequest.of(0,5), filmes.size());

        Mockito.when(service.getFilmsPaged(0,5)).thenReturn(page);

        mockMvc.perform(get("/api/films"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].title").value("Matrix"))
                .andExpect(jsonPath("$.content[1].title").value("Interestelar"));
    }

    @Test
    void getFilmByIdSuccessfully() throws Exception {
        Film film = new Film("Matrix","Wachowski",1999,"Ficção",136);

        Mockito.when(service.getFilmById(1L)).thenReturn(film);

        mockMvc.perform(get("/api/films/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Matrix"));
    }

    @Test
    void updateFilmSuccessfully() throws Exception {
        Film original = new Film("Matrix","Wachowski",1999,"Ficção",136);
        Film updated  = new Film("Matrix Reloaded","Wachowski",2003,"Ficção",138);

        Mockito.when(service.getFilmById(1L)).thenReturn(original);
        Mockito.when(service.updateFilm(any(Film.class))).thenReturn(updated);

        mockMvc.perform(put("/api/films/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Matrix Reloaded"))
                .andExpect(jsonPath("$.releaseYear").value(2003));
    }

    @Test
    void deleteFilmSuccessfully() throws Exception {
        Mockito.doNothing().when(service).deleteFilm(1L);

        mockMvc.perform(delete("/api/films/1"))
                .andExpect(status().isNoContent());
    }
}