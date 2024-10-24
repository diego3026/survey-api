package com.survey.api.controllers;

import com.survey.api.models.dtos.save.UserQuestionRequest;
import com.survey.api.models.dtos.save.UserQuestionUpdate;
import com.survey.api.models.dtos.send.UserQuestionResponse;
import com.survey.api.services.UserQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Preguntas por usuario", description = "Recurso para manejar las preguntas por usuario y su respuesta")
@RestController
@RequestMapping("/userQuestions")
public class UserQuestionController {
    private final UserQuestionService userQuestionService;

    public UserQuestionController(UserQuestionService userQuestionService) {
        this.userQuestionService = userQuestionService;
    }

    @Operation(
            summary = "obtener todas las preguntas por usuario",
            description = "El administrador podra obtener todas las preguntas por usuario",
            tags = { "Get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserQuestionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userQuestionService.findAll());
    }

    @Operation(
            summary = "obtener todas la pregunta por usuario por Id",
            description = "El administrador podra obtener todas las pregunta por usuario por Id",
            tags = { "Get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserQuestionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userQuestionService.findById(id));
    }

    @Operation(
            summary = "crear la pregunta por usuario",
            description = "El administrador podra crear la pregunta por usuario",
            tags = { "Post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserQuestionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody UserQuestionRequest userQuestionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userQuestionService.save(userQuestionRequest));
    }

    @Operation(
            summary = "obtener todas la pregunta por usuario por IdUser",
            description = "El administrador podra obtener todas las pregunta por usuario por IdUser",
            tags = { "Get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserQuestionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @GetMapping("/{idUserQuestion}/questions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUserQuestionsByIdUser(@PathVariable Long idUserQuestion) {
        return ResponseEntity.ok(userQuestionService.findUserQuestionsByUser(idUserQuestion));
    }

    @Operation(
            summary = "actualizar la pregunta por usuario",
            description = "El administrador podra actualizar la pregunta por usuario por Id",
            tags = { "Put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserQuestionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserQuestionUpdate userQuestionUpdate) {
        return ResponseEntity.ok(userQuestionService.update(id, userQuestionUpdate));
    }

    @Operation(
            summary = "eliminar la pregunta por usuario por Id",
            description = "El administrador podra eliminar la pregunta por usuario por Id",
            tags = { "Delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserQuestionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userQuestionService.deleteById(id);
        return ResponseEntity.ok("UserQuestion deleted");
    }

    @Operation(
            summary = "eliminar todas las pregunta por usuario",
            description = "El administrador podra eliminar todas las preguntas por usuarios",
            tags = { "Delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserQuestionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAll() {
        userQuestionService.deleteAll();
        return ResponseEntity.ok("UserQuestions deleted");
    }
}
