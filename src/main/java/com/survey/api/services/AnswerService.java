package com.survey.api.services;

import com.survey.api.models.dtos.save.AnswerRequest;
import com.survey.api.models.dtos.save.AnswerUpdate;
import com.survey.api.models.dtos.send.AnswerResponse;

import java.util.List;

public interface AnswerService {
    List<AnswerResponse> findAll();
    AnswerResponse findById(Long id);
    AnswerResponse save(AnswerRequest answerRequest);
    AnswerResponse update(Long id, AnswerUpdate answerUpdate);
    void deleteById(Long id);
    void deleteAll();
}
