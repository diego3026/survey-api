package com.survey.api.services;

import com.survey.api.models.dtos.save.TypeQuestionRequest;
import com.survey.api.models.dtos.save.TypeQuestionUpdate;
import com.survey.api.models.dtos.send.TypeQuestionResponse;

import java.util.List;

public interface TypeQuestionService {
    List<TypeQuestionResponse> findAll();
    TypeQuestionResponse findById(Long id);
    TypeQuestionResponse save(TypeQuestionRequest typeQuestionRequest);
    TypeQuestionResponse update(Long id, TypeQuestionUpdate typeQuestionUpdate);
    void deleteById(Long id);
    void deleteAll();
}
