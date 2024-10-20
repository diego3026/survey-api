package com.survey.api.services;

import com.survey.api.models.dtos.save.UserSurveyRequest;
import com.survey.api.models.dtos.save.UserSurveyUpdate;
import com.survey.api.models.dtos.send.UserSurveyResponse;

import java.util.List;

public interface UserSurveyService {
    List<UserSurveyResponse> findAll();
    UserSurveyResponse findById(Long id);
    UserSurveyResponse save(UserSurveyRequest userSurveyRequest);
    UserSurveyResponse update(Long id, UserSurveyUpdate userSurveyUpdate);
    void deleteById(Long id);
    void deleteAll();
}
