package com.survey.api.controllers;

import com.survey.api.models.dtos.save.AnswerRequest;
import com.survey.api.models.dtos.save.AnswerUpdate;
import com.survey.api.services.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
@Validated
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(answerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(answerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AnswerRequest answerRequest){
        return ResponseEntity.ok(answerService.save(answerRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody AnswerUpdate answerUpdate){
        return ResponseEntity.ok(answerService.update(id, answerUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        answerService.deleteById(id);
        return ResponseEntity.ok("Answer deleted");
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAll(){
        answerService.deleteAll();
        return ResponseEntity.ok("Answers deleted");
    }
}
