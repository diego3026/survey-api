package com.survey.api.controllers;

import com.survey.api.models.dtos.save.SurveyRequest;
import com.survey.api.models.dtos.save.SurveyUpdate;
import com.survey.api.services.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surveys")
@Validated
public class SurveyController {
    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(surveyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(surveyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SurveyRequest surveyDtoSave){
        return ResponseEntity.ok(surveyService.save(surveyDtoSave));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody SurveyUpdate surveyDtoUpdate){
        return ResponseEntity.ok(surveyService.update(id, surveyDtoUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        surveyService.deleteById(id);
        return ResponseEntity.ok("Survey deleted");
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAll(){
        surveyService.deleteAll();
        return ResponseEntity.ok("Surveys deleted");
    }
}
