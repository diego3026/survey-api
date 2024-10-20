package com.survey.api.controllers;

import com.survey.api.models.dtos.save.TypeQuestionRequest;
import com.survey.api.models.dtos.save.TypeQuestionUpdate;
import com.survey.api.models.dtos.send.TypeQuestionResponse;
import com.survey.api.services.TypeQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Tipos de preguntas", description = "Recurso para manejar los tipos de preguntas")
@RestController
@RequestMapping("/typesQuestions")
public class TypeQuestionController {
    private final TypeQuestionService typeQuestionService;

    public TypeQuestionController(TypeQuestionService typeQuestionService) {
        this.typeQuestionService = typeQuestionService;
    }

    @Operation(
            summary = "obtener todas los tipos de preguntas",
            description = "El administrador podrá obtener todas los tipos de preguntas",
            tags = { "Get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TypeQuestionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(typeQuestionService.findAll());
    }

    @Operation(
            summary = "obtener el tipo de pregunta por Id",
            description = "El administrador podrá obtener el tipo de pregunta por Id",
            tags = { "Get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TypeQuestionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(typeQuestionService.findById(id));
    }

    @Operation(
            summary = "crear el tipo de pregunta",
            description = "El administrador podrá crear el tipo de pregunta",
            tags = { "Post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TypeQuestionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> save(@RequestBody TypeQuestionRequest typeQuestionRequest){
        return ResponseEntity.ok(typeQuestionService.save(typeQuestionRequest));
    }

    @Operation(
            summary = "actualizar el tipo de pregunta",
            description = "El administrador podrá actualizar el tipo de pregunta",
            tags = { "Put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TypeQuestionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TypeQuestionUpdate typeQuestionUpdate){
        return ResponseEntity.ok(typeQuestionService.update(id, typeQuestionUpdate));
    }

    @Operation(
            summary = "eliminar el tipo de pregunta",
            description = "El administrador podrá eliminar el tipo de pregunta por Id",
            tags = { "Delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        typeQuestionService.deleteById(id);
        return ResponseEntity.ok("TypeQuestion deleted");
    }

    @Operation(
            summary = "eliminar todas los tipos de preguntas",
            description = "El administrador podrá eliminar todas los tipos de preguntas",
            tags = { "Delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAll(){
        typeQuestionService.deleteAll();
        return ResponseEntity.ok("TypesQuestions deleted");
    }
}
