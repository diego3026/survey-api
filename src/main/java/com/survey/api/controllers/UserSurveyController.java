package com.survey.api.controllers;

import com.survey.api.models.dtos.save.UserSurveyRequest;
import com.survey.api.models.dtos.save.UserSurveyUpdate;
import com.survey.api.services.UserSurveyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "userSurvey")
@RestController
@RequestMapping("/userSurveys")
public class UserSurveyController {
    private final UserSurveyService userSurveyService;

    public UserSurveyController(UserSurveyService userSurveyService) {
        this.userSurveyService = userSurveyService;
    }

    @GetMapping
    private ResponseEntity<?> getAll(){
        return ResponseEntity.ok(userSurveyService.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(userSurveyService.findById(id));
    }

    @PostMapping
    private ResponseEntity<?> save(@RequestBody UserSurveyRequest userSurveyRequest){
        return ResponseEntity.ok(userSurveyService.save(userSurveyRequest));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserSurveyUpdate userSurveyUpdate){
        return ResponseEntity.ok(userSurveyService.update(id, userSurveyUpdate));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteById(@PathVariable Long id){
        userSurveyService.deleteById(id);
        return ResponseEntity.ok("UserSurvey deleted");
    }

    @DeleteMapping
    private ResponseEntity<?> deleteAll(){
        userSurveyService.deleteAll();
        return ResponseEntity.ok("UserSurveys deleted");
    }

}
