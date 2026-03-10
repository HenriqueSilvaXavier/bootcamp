package com.example.bootcamp_films.controller;

import com.example.bootcamp_films.entity.Film;
import com.example.bootcamp_films.service.FilmService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService service;

    public FilmController(FilmService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Film> create(@RequestBody Film film) {
        Film salvo = service.createFilm(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<Page<Film>> getFilmsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(service.getFilmsPaged(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFilmById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> update(
            @PathVariable Long id,
            @RequestBody Film updatedFilm) {

        Film film = service.getFilmById(id);

        film.setTitle(updatedFilm.getTitle());
        film.setDirector(updatedFilm.getDirector());
        film.setGenre(updatedFilm.getGenre());
        film.setReleaseYear(updatedFilm.getReleaseYear());
        film.setDuration(updatedFilm.getDuration());

        return ResponseEntity.ok(service.updateFilm(film));
    }

    @PatchMapping("/{id}/title")
    public ResponseEntity<Film> updateTitle(
            @PathVariable Long id,
            @RequestBody String title) {

        Film film = service.getFilmById(id);
        film.setTitle(title);

        return ResponseEntity.ok(service.updateFilm(film));
    }

    @PatchMapping("/{id}/director")
    public ResponseEntity<Film> updateDirector(
            @PathVariable Long id,
            @RequestBody String director) {

        Film film = service.getFilmById(id);
        film.setDirector(director);

        return ResponseEntity.ok(service.updateFilm(film));
    }

    @PatchMapping("/{id}/genre")
    public ResponseEntity<Film> updateGenre(
            @PathVariable Long id,
            @RequestBody String genre) {

        Film film = service.getFilmById(id);
        film.setGenre(genre);

        return ResponseEntity.ok(service.updateFilm(film));
    }

    @PatchMapping("/{id}/releaseYear")
    public ResponseEntity<Film> updateReleaseYear(
            @PathVariable Long id,
            @RequestBody int releaseYear) {

        Film film = service.getFilmById(id);
        film.setReleaseYear(releaseYear);

        return ResponseEntity.ok(service.updateFilm(film));
    }

    @PatchMapping("/{id}/duration")
    public ResponseEntity<Film> updateDuration(
            @PathVariable Long id,
            @RequestBody int duration) {

        Film film = service.getFilmById(id);
        film.setDuration(duration);

        return ResponseEntity.ok(service.updateFilm(film));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }
}