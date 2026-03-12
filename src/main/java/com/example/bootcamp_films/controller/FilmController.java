package com.example.bootcamp_films.controller;

import com.example.bootcamp_films.dto.FilmMapper;
import com.example.bootcamp_films.dto.request.FilmCreateDTO;
import com.example.bootcamp_films.dto.request.FilmUpdateDTO;
import com.example.bootcamp_films.dto.response.FilmResponseDTO;
import com.example.bootcamp_films.entity.Film;
import com.example.bootcamp_films.service.FilmService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService service;

    public FilmController(FilmService service) {
        this.service = service;
    }

    // ==========================
    // 🎬 Criar filme (POST)
    // ==========================
    @PostMapping
    public ResponseEntity<FilmResponseDTO> create(@RequestBody FilmCreateDTO dto) {
        Film film = FilmMapper.toEntity(dto);
        Film saved = service.createFilm(film);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(FilmMapper.toDTO(saved));
    }

    // ==========================
    // 📄 Listar filmes paginados (GET)
    // ==========================
    @GetMapping
    public ResponseEntity<Page<FilmResponseDTO>> getFilmsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<FilmResponseDTO> dtoPage = service.getFilmsPaged(page, size)
                .map(FilmMapper::toDTO);
        return ResponseEntity.ok(dtoPage);
    }

    // ==========================
    // 🔍 Obter filme por ID (GET)
    // ==========================
    @GetMapping("/{id}")
    public ResponseEntity<FilmResponseDTO> getFilmById(@PathVariable Long id) {
        Film film = service.getFilmById(id);
        return ResponseEntity.ok(FilmMapper.toDTO(film));
    }

    // ==========================
    // ✏️ Atualizar filme completo (PUT)
    // ==========================
    @PutMapping("/{id}")
    public ResponseEntity<FilmResponseDTO> update(
            @PathVariable Long id,
            @RequestBody FilmUpdateDTO dto) {

        Film film = service.getFilmById(id);
        FilmMapper.updateEntity(film, dto);

        Film updated = service.updateFilm(film);
        return ResponseEntity.ok(FilmMapper.toDTO(updated));
    }

    // ==========================
    // 🩹 Atualizações parciais (PATCH)
    // ==========================
    @PatchMapping("/{id}/title")
    public ResponseEntity<FilmResponseDTO> updateTitle(
            @PathVariable Long id,
            @RequestBody FilmCreateDTO dto) { // usando CreateDTO apenas para title
        Film film = service.getFilmById(id);
        film.setTitle(dto.getTitle());
        return ResponseEntity.ok(FilmMapper.toDTO(service.updateFilm(film)));
    }

    @PatchMapping("/{id}/director")
    public ResponseEntity<FilmResponseDTO> updateDirector(
            @PathVariable Long id,
            @RequestBody FilmCreateDTO dto) {
        Film film = service.getFilmById(id);
        film.setDirector(dto.getDirector());
        return ResponseEntity.ok(FilmMapper.toDTO(service.updateFilm(film)));
    }

    @PatchMapping("/{id}/genre")
    public ResponseEntity<FilmResponseDTO> updateGenre(
            @PathVariable Long id,
            @RequestBody FilmCreateDTO dto) {
        Film film = service.getFilmById(id);
        film.setGenre(dto.getGenre());
        return ResponseEntity.ok(FilmMapper.toDTO(service.updateFilm(film)));
    }

    @PatchMapping("/{id}/releaseYear")
    public ResponseEntity<FilmResponseDTO> updateReleaseYear(
            @PathVariable Long id,
            @RequestBody FilmCreateDTO dto) {
        Film film = service.getFilmById(id);
        film.setReleaseYear(dto.getReleaseYear());
        return ResponseEntity.ok(FilmMapper.toDTO(service.updateFilm(film)));
    }

    @PatchMapping("/{id}/duration")
    public ResponseEntity<FilmResponseDTO> updateDuration(
            @PathVariable Long id,
            @RequestBody FilmCreateDTO dto) {
        Film film = service.getFilmById(id);
        film.setDuration(dto.getDuration());
        return ResponseEntity.ok(FilmMapper.toDTO(service.updateFilm(film)));
    }

    // ==========================
    // ❌ Deletar filme (DELETE)
    // ==========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }
}