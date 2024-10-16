package com.survey.api.controllers;

import com.survey.api.models.dtos.save.QuestionRequest;
import com.survey.api.models.dtos.save.QuestionUpdate;
import com.survey.api.services.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@Validated
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(questionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(questionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody QuestionRequest questionRequest) {
        return ResponseEntity.ok(questionService.save(questionRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody QuestionUpdate questionUpdate) {
        return ResponseEntity.ok(questionService.update(id,questionUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
        questionService.deleteById(id);
        return ResponseEntity.ok("Question deleted");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        questionService.deleteAll();
        return ResponseEntity.ok("Questions deleted");
    }
}
