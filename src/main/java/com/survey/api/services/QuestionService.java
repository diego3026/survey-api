package com.survey.api.services;

import com.survey.api.models.dtos.save.QuestionRequest;
import com.survey.api.models.dtos.save.QuestionUpdate;
import com.survey.api.models.dtos.send.QuestionResponse;

import java.util.List;

public interface QuestionService {
    List<QuestionResponse> findAll();
    QuestionResponse findById(Long id);
    QuestionResponse save(QuestionRequest questionRequest);
    QuestionResponse update(Long id, QuestionUpdate questionUpdate);
    void deleteById(Long id);
    void deleteAll();
}
