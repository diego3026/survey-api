package com.survey.api.services;

import com.survey.api.models.dtos.save.UserSurveyRequest;
import com.survey.api.models.dtos.save.UserSurveyUpdate;
import com.survey.api.models.dtos.send.SurveyResponse;
import com.survey.api.models.dtos.send.UserSurveyResponse;
import com.survey.api.models.entities.Survey;

import java.util.List;

public interface UserSurveyService {
    List<UserSurveyResponse> findAll();
    List<SurveyResponse> findSurveysByUser(Long idUser);
    UserSurveyResponse findById(Long id);
    UserSurveyResponse save(UserSurveyRequest userSurveyRequest);
    UserSurveyResponse update(Long id, UserSurveyUpdate userSurveyUpdate);
    void deleteById(Long id);
    void deleteAll();
}
