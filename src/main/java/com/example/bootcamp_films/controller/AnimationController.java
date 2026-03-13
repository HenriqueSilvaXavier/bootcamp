package com.example.bootcamp_films.controller;

import com.example.bootcamp_films.dto.AnimationMapper;
import com.example.bootcamp_films.dto.request.AnimationCreateDTO;
import com.example.bootcamp_films.dto.request.AnimationUpdateDTO;
import com.example.bootcamp_films.dto.response.AnimationResponseDTO;
import com.example.bootcamp_films.entity.Animation;
import com.example.bootcamp_films.service.AnimationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animations")
@CrossOrigin(origins = "*")
public class AnimationController {

    private final AnimationService service;

    public AnimationController(AnimationService service) {
        this.service = service;
    }

    // ==========================
    // 🎞️ Criar animação (POST)
    // ==========================
    @PostMapping
    public ResponseEntity<AnimationResponseDTO> create(
            @RequestBody @Valid AnimationCreateDTO dto
    ) {
        Animation animation = AnimationMapper.toEntity(dto);
        Animation saved = service.create(animation);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AnimationMapper.toResponseDTO(saved));
    }

    // ==========================
    // 📄 Listar animações paginadas (GET)
    // ==========================
    @GetMapping
    public ResponseEntity<Page<AnimationResponseDTO>> listPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<AnimationResponseDTO> dtoPage = service
                .listPaged(page, size)
                .map(AnimationMapper::toResponseDTO);

        return ResponseEntity.ok(dtoPage);
    }

    // ==========================
    // 🔍 Buscar por ID (GET)
    // ==========================
    @GetMapping("/{id}")
    public ResponseEntity<AnimationResponseDTO> findById(@PathVariable Long id) {
        Animation animation = service.findById(id);
        return ResponseEntity.ok(AnimationMapper.toResponseDTO(animation));
    }

    // ==========================
    // ✏️ Atualizar animação (PUT)
    // ==========================
    @PutMapping("/{id}")
    public ResponseEntity<AnimationResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid AnimationUpdateDTO dto
    ) {
        Animation animation = service.findById(id);
        AnimationMapper.updateEntity(animation, dto);

        Animation updated = service.update(id, animation);

        return ResponseEntity.ok(AnimationMapper.toResponseDTO(updated));
    }

    // ==========================
    // ❌ Deletar animação (DELETE)
    // ==========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}