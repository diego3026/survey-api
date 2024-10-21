package com.survey.api.services.impl;

import com.survey.api.models.dtos.save.UserSurveyRequest;
import com.survey.api.models.dtos.save.UserSurveyUpdate;
import com.survey.api.models.dtos.send.SurveyResponse;
import com.survey.api.models.dtos.send.UserSurveyResponse;
import com.survey.api.models.entities.Survey;
import com.survey.api.models.entities.User;
import com.survey.api.models.entities.UserQuestion;
import com.survey.api.models.entities.UserSurvey;
import com.survey.api.models.mappers.SurveyMapper;
import com.survey.api.models.mappers.UserSurveyMapper;
import com.survey.api.repositories.SurveyRepository;
import com.survey.api.repositories.UserRepository;
import com.survey.api.repositories.UserSurveyRepository;
import com.survey.api.services.UserSurveyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSurveyServiceImpl implements UserSurveyService {
    private final UserSurveyRepository userSurveyRepository;
    private final UserSurveyMapper userSurveyMapper;
    private final UserRepository userRepository;
    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;

    public UserSurveyServiceImpl(UserSurveyRepository userSurveyRepository, UserSurveyMapper userSurveyMapper, UserRepository userRepository, UserRepository userRepository1, SurveyRepository surveyRepository, SurveyMapper surveyMapper) {
        this.userSurveyRepository = userSurveyRepository;
        this.userSurveyMapper = userSurveyMapper;
        this.userRepository = userRepository1;
        this.surveyRepository = surveyRepository;
        this.surveyMapper = surveyMapper;
    }

    @Override
    public List<UserSurveyResponse> findAll() {
        List<UserSurvey> userSurveys = userSurveyRepository.findAll();
        return userSurveys.stream().map(userSurveyMapper::userSurveyToUserSurveyResponse).toList();
    }

    @Override
    public List<SurveyResponse> findSurveysByUser(Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User not found"));
        List<UserSurvey> userSurveys = userSurveyRepository.findUserSurveyByUser(user);

        return userSurveys.stream().map(userSurvey -> {
            return surveyMapper.entityToResponse(userSurvey.getSurvey());
        }).toList();
    }

    @Override
    public UserSurveyResponse findById(Long id) {
        UserSurvey userSurvey = userSurveyRepository.findById(id).orElseThrow(() -> new RuntimeException("UserSurvey not found"));
        return userSurveyMapper.userSurveyToUserSurveyResponse(userSurvey);
    }

    @Override
    public UserSurveyResponse save(UserSurveyRequest userSurveyRequest) {
        UserSurvey userSurvey = userSurveyMapper.userSurveyRequestToUserSurvey(userSurveyRequest);
        User user = userRepository.findById(userSurveyRequest.getUser()).orElseThrow(() -> new RuntimeException("User not found"));
        Survey survey = surveyRepository.findById(userSurveyRequest.getSurvey()).orElseThrow(() -> new RuntimeException("Survey not found"));

        userSurvey.setUser(user);
        userSurvey.setSurvey(survey);

        return userSurveyMapper.userSurveyToUserSurveyResponse(userSurveyRepository.save(userSurvey));
    }

    @Override
    public UserSurveyResponse update(Long id, UserSurveyUpdate userSurveyUpdate) {
        UserSurvey userSurveyReceived = userSurveyMapper.userSurveyUpdateToUserSurvey(userSurveyUpdate);
        UserSurvey userSurveyFind = userSurveyRepository.findById(id).orElseThrow(() -> new RuntimeException("UserSurvey not found"));
        userSurveyFind.updateUserSurvey(userSurveyReceived);
        User user = userRepository.findById(userSurveyUpdate.getUser()).orElseThrow(() -> new RuntimeException("User not found"));
        Survey survey = surveyRepository.findById(userSurveyUpdate.getSurvey()).orElseThrow(() -> new RuntimeException("Survey not found"));
        userSurveyFind.setUser(user);
        userSurveyFind.setSurvey(survey);
        return userSurveyMapper.userSurveyToUserSurveyResponse(userSurveyRepository.save(userSurveyFind));
    }

    @Override
    public void deleteById(Long id) {
        userSurveyRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userSurveyRepository.deleteAll();
    }
}
