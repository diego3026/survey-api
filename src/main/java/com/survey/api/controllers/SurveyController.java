package com.survey.api.controllers;

import com.survey.api.models.dtos.save.SurveyRequest;
import com.survey.api.models.dtos.save.SurveyUpdate;
import com.survey.api.models.dtos.send.QuestionResponse;
import com.survey.api.models.dtos.send.SurveyResponse;
import com.survey.api.services.SurveyService;
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

@Tag(name = "Encuestas", description = "Recurso para manejar las encuestas")
@RestController
@RequestMapping("/surveys")
@Validated
public class SurveyController {
    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @Operation(
            summary = "Obtener todas las encuestas",
            description = "El administrador podrá obtener todas las encuestas",
            tags = { "Get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = SurveyResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(surveyService.findAll());
    }

    @Operation(
            summary = "Obtener la encuesta por Id",
            description = "El administrador podrá obtener la encuesta por Id",
            tags = { "Get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = SurveyResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(surveyService.findById(id));
    }

    @Operation(
            summary = "crear la encuesta",
            description = "El administrador podrá crear la encuesta",
            tags = { "Post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = SurveyResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody SurveyRequest surveyDtoSave){
        return ResponseEntity.ok(surveyService.save(surveyDtoSave));
    }

    @Operation(
            summary = "actualiar la encuesta",
            description = "El administrador podrá actualizar la encuesta por Id",
            tags = { "Put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = SurveyResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody SurveyUpdate surveyDtoUpdate){
        return ResponseEntity.ok(surveyService.update(id, surveyDtoUpdate));
    }

    @Operation(
            summary = "eliminar la encuesta",
            description = "El administrador podrá eliminar la encuesta por Id",
            tags = { "Delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        surveyService.deleteById(id);
        return ResponseEntity.ok("Survey deleted");
    }

    @Operation(
            summary = "eliminar todas las encuestas",
            description = "El administrador podrá eliminar todas las encuestas",
            tags = { "Delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = SurveyResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAll(){
        surveyService.deleteAll();
        return ResponseEntity.ok("Surveys deleted");
    }
}
