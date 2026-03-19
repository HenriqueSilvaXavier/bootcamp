package com.example.bootcamp_films.controller;

import com.example.bootcamp_films.entity.Documentary;
import com.example.bootcamp_films.service.DocumentaryService;

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
@RequestMapping("/api/documentaries")
@CrossOrigin("*")
@Tag(name = "Documentaries", description = "Operações relacionadas a documentários")
public class DocumentaryController extends BaseController<Documentary> {

    private final DocumentaryService documentaryService;

    public DocumentaryController(DocumentaryService service) {
        super(service);
        this.documentaryService = service;
    }

    @Override
    @PostMapping
    @Operation(
            summary = "Criar documentário",
            description = "Cria um novo documentário no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Documentário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = Documentary.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Documentary> create(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto do documentário a ser criado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Documentary.class))
            )
            Documentary documentary
    ) {
        return ResponseEntity.ok(documentaryService.create(documentary));
    }

    @Override
    @GetMapping
    @Operation(
            summary = "Listar documentários",
            description = "Retorna uma lista paginada de documentários"
    )
    public ResponseEntity<Page<Documentary>> list(
            @Parameter(description = "Número da página", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Quantidade de registros por página", example = "5")
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(documentaryService.findAll(page, size));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar documentário por ID",
            description = "Retorna um documentário específico pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Documentário encontrado",
                    content = @Content(schema = @Schema(implementation = Documentary.class))),
            @ApiResponse(responseCode = "404", description = "Documentário não encontrado")
    })
    public ResponseEntity<Documentary> findById(
            @Parameter(description = "ID do documentário", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(documentaryService.findById(id));
    }

    @Override
    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar documentário",
            description = "Atualiza os dados de um documentário existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Documentário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Documentário não encontrado")
    })
    public ResponseEntity<Documentary> update(
            @Parameter(description = "ID do documentário", example = "1")
            @PathVariable Long id,

            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novos dados do documentário",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Documentary.class))
            )
            Documentary documentary
    ) {
        return ResponseEntity.ok(documentaryService.update(id, documentary));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar documentário",
            description = "Remove um documentário do sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Documentário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Documentário não encontrado")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do documentário", example = "1")
            @PathVariable Long id
    ) {
        documentaryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}