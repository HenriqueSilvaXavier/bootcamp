package com.example.bootcamp_films.controller;

import com.example.bootcamp_films.entity.Animation;
import com.example.bootcamp_films.service.AnimationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animations")
@CrossOrigin("*")
public class AnimationController extends BaseController<Animation> {

    private final AnimationService animationService;

    public AnimationController(AnimationService animationService) {
        super(animationService);
        this.animationService = animationService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Animation> create(@RequestBody Animation animation) {
        // Validação simples para campos obrigatórios
        if (animation.getAnimationStudio() == null || animation.getAnimationStudio().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Animation savedAnimation = animationService.create(animation);
        return ResponseEntity.ok(savedAnimation);
    }
}