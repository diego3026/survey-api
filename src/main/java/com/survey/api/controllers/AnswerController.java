package com.survey.api.controllers;

import com.survey.api.models.dtos.save.AnswerRequest;
import com.survey.api.models.dtos.save.AnswerUpdate;
import com.survey.api.models.dtos.send.AnswerResponse;
import com.survey.api.services.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Respuestas", description = "Recurso para gestionar las respuestas del sistema")
@RestController
@RequestMapping("/answers")
@Validated
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Operation(
            summary = "Obtener todas las respuestas",
            description = "El administrador podrá obtener todas las respuestas registradas en el sistema",
            tags = { "Get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200")})
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(answerService.findAll());
    }

    @Operation(
            summary = "Obtener respuesta por su Id",
            description = "El administrador podrá obtener la respuesta registrada en el sistema por el Id",
            tags = { "Get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = AnswerResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(answerService.findById(id));
    }

    @Operation(
            summary = "Crear respuesta",
            description = "El administrador podrá crear la respuesta",
            tags = { "Post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = AnswerResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody AnswerRequest answerRequest) {
        return ResponseEntity.ok(answerService.save(answerRequest));
    }

    @Operation(
            summary = "Actualizar respuesta por su Id",
            description = "El administrador podrá actualizar la respuesta registrada en el sistema por el Id",
            tags = { "Put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = AnswerResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody AnswerUpdate answerUpdate) {
        return ResponseEntity.ok(answerService.update(id, answerUpdate));
    }

    @Operation(
            summary = "Eliminar respuesta por su Id",
            description = "El administrador podrá eliminar la respuesta registrada en el sistema por el Id",
            tags = { "Delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        answerService.deleteById(id);
        return ResponseEntity.ok("Answer deleted");
    }

    @Operation(
            summary = "Eliminar todas las respuestas",
            description = "El administrador podrá eliminar todas las respuestas registradas en el sistema",
            tags = { "Delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAll() {
        answerService.deleteAll();
        return ResponseEntity.ok("Answers deleted");
    }
}
