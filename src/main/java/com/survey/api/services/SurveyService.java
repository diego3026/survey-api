package com.survey.api.services;

import com.survey.api.models.dtos.save.SurveyRequest;
import com.survey.api.models.dtos.save.SurveyUpdate;
import com.survey.api.models.dtos.send.SurveyResponse;

import java.util.List;

public interface SurveyService {
    List<SurveyResponse> findAll();
    SurveyResponse findById(Long id);
    SurveyResponse save(SurveyRequest surveyRequest);
    SurveyResponse update(Long id, SurveyUpdate surveyUpdate);
    void deleteById(Long id);
    void deleteAll();
}
