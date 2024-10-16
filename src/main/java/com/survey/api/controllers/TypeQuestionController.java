package com.survey.api.controllers;

import com.survey.api.models.dtos.save.TypeQuestionRequest;
import com.survey.api.models.dtos.save.TypeQuestionUpdate;
import com.survey.api.models.entities.TypeQuestion;
import com.survey.api.services.TypeQuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/typesQuestions")
public class TypeQuestionController {
    private final TypeQuestionService typeQuestionService;

    public TypeQuestionController(TypeQuestionService typeQuestionService) {
        this.typeQuestionService = typeQuestionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(typeQuestionService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(typeQuestionService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TypeQuestionRequest typeQuestionRequest) {
        return ResponseEntity.ok(typeQuestionService.save(typeQuestionRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TypeQuestionUpdate typeQuestionUpdate) {
        return ResponseEntity.ok(typeQuestionService.update(id, typeQuestionUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        typeQuestionService.deleteById(id);
        return ResponseEntity.ok("TypeQuestion deleted");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        typeQuestionService.deleteAll();
        return ResponseEntity.ok("TypesQuestions deleted");
    }
}
