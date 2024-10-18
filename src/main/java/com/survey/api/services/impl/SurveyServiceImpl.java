package com.survey.api.services.impl;

import com.survey.api.models.dtos.save.SurveyRequest;
import com.survey.api.models.dtos.save.SurveyUpdate;
import com.survey.api.models.dtos.send.SurveyResponse;
import com.survey.api.models.entities.Survey;
import com.survey.api.models.mappers.SurveyMapper;
import com.survey.api.repositories.SurveyRepository;
import com.survey.api.services.SurveyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;

    public SurveyServiceImpl(SurveyRepository surveyRepository, SurveyMapper surveyMapper) {
        this.surveyRepository = surveyRepository;
        this.surveyMapper = surveyMapper;
    }

    @Override
    public List<SurveyResponse> findAll() {
        List<Survey> surveyList = surveyRepository.findAll();
        return surveyList.stream().map(surveyMapper::entityToResponse).toList();
    }

    @Override
    public SurveyResponse findById(Long id) {
        Survey survey = surveyRepository.findById(id).orElse(null);
        return surveyMapper.entityToResponse(survey);
    }

    @Override
    public SurveyResponse save(SurveyRequest surveyRequest) {
        Survey survey = surveyMapper.requestToEntity(surveyRequest);
        return surveyMapper.entityToResponse(surveyRepository.save(survey));
    }

    @Override
    public SurveyResponse update(Long id, SurveyUpdate surveyUpdate) {
        Survey survey = surveyMapper.updateToEntity(surveyUpdate);
        Survey data = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data wasn't found"));
        data.updateSurvey(survey);
        return surveyMapper.entityToResponse(surveyRepository.save(data));
    }

    @Override
    public void deleteById(Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data wasn't found"));
        surveyRepository.delete(survey);
    }

    @Override
    public void deleteAll() {
        surveyRepository.deleteAll();
    }
}
