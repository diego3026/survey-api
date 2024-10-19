package com.survey.api.services;

import com.survey.api.models.dtos.save.UserQuestionRequest;
import com.survey.api.models.dtos.save.UserQuestionUpdate;
import com.survey.api.models.dtos.send.UserQuestionResponse;
import com.survey.api.models.entities.User;

import java.util.List;

public interface UserQuestionService {
    List<UserQuestionResponse> findAll();
    UserQuestionResponse findById(Long id);
    List<UserQuestionResponse> findUserQuestionsByUser(Long idUser);
    UserQuestionResponse save(UserQuestionRequest userQuestionRequest);
    UserQuestionResponse update(Long id, UserQuestionUpdate userQuestionUpdate);
    void deleteById(Long id);
    void deleteAll();
}
