package com.example.bootcamp_films.controller;

import com.example.bootcamp_films.dto.FilmMapper;
import src.main.java.com.example.bootcamp_films.dto.request.FilmCreateDTO;
import src.main.java.com.example.bootcamp_films.dto.request.FilmUpdateDTO;
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
    // ❌ Deletar filme (DELETE)
    // ==========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }
}