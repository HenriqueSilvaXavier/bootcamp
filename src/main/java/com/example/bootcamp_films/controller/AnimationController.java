package com.example.bootcamp_films.controller;

import com.example.bootcamp_films.entity.Animation;
import com.example.bootcamp_films.service.AnimationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animations")
@CrossOrigin("*")
@Tag(name = "Animations", description = "Operações relacionadas a animações")
public class AnimationController extends BaseController<Animation> {

    private final AnimationService animationService;

    public AnimationController(AnimationService animationService) {
        super(animationService);
        this.animationService = animationService;
    }

    @Override
    @PostMapping
    @Operation(
            summary = "Criar animação",
            description = "Cria uma nova animação no sistema. O campo 'animationStudio' é obrigatório."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Animação criada com sucesso",
                    content = @Content(schema = @Schema(implementation = Animation.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Animation> create(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto da animação a ser criada",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Animation.class))
            )
            Animation animation
    ) {
        // Validação simples para campos obrigatórios
        if (animation.getAnimationStudio() == null || animation.getAnimationStudio().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Animation savedAnimation = animationService.create(animation);
        return ResponseEntity.ok(savedAnimation);
    }

    @Override
    @GetMapping
    @Operation(
            summary = "Listar animações",
            description = "Retorna uma lista paginada de animações"
    )
    public ResponseEntity<Page<Animation>> list(
            @Parameter(description = "Número da página", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Quantidade de registros por página", example = "5")
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(animationService.findAll(page, size));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar animação por ID",
            description = "Retorna uma animação específica pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Animação encontrada",
                    content = @Content(schema = @Schema(implementation = Animation.class))),
            @ApiResponse(responseCode = "404", description = "Animação não encontrada")
    })
    public ResponseEntity<Animation> findById(
            @Parameter(description = "ID da animação", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(animationService.findById(id));
    }

    @Override
    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar animação",
            description = "Atualiza os dados de uma animação existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Animação atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Animação não encontrada")
    })
    public ResponseEntity<Animation> update(
            @Parameter(description = "ID da animação", example = "1")
            @PathVariable Long id,

            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novos dados da animação",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Animation.class))
            )
            Animation animation
    ) {
        return ResponseEntity.ok(animationService.update(id, animation));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar animação",
            description = "Remove uma animação do sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Animação removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Animação não encontrada")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID da animação", example = "1")
            @PathVariable Long id
    ) {
        animationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}