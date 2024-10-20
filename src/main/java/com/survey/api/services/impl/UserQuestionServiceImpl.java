package com.survey.api.services.impl;

import com.survey.api.models.dtos.save.UserQuestionRequest;
import com.survey.api.models.dtos.save.UserQuestionUpdate;
import com.survey.api.models.dtos.send.UserQuestionResponse;
import com.survey.api.models.entities.Question;
import com.survey.api.models.entities.User;
import com.survey.api.models.entities.UserQuestion;
import com.survey.api.models.mappers.UserQuestionMapper;
import com.survey.api.repositories.QuestionRepository;
import com.survey.api.repositories.UserQuestionRepository;
import com.survey.api.repositories.UserRepository;
import com.survey.api.services.UserQuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQuestionServiceImpl implements UserQuestionService {
    private final UserQuestionRepository userQuestionRepository;
    private final UserQuestionMapper userQuestionMapper;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public UserQuestionServiceImpl(UserQuestionRepository userQuestionRepository, UserQuestionMapper userQuestionMapper, UserRepository userRepository, QuestionRepository questionRepository) {
        this.userQuestionRepository = userQuestionRepository;
        this.userQuestionMapper = userQuestionMapper;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<UserQuestionResponse> findAll() {
        List<UserQuestion> userQuestions = userQuestionRepository.findAll();
        return userQuestions.stream().map(userQuestionMapper::userQuestionToUserQuestionResponse).toList();
    }

    @Override
    public UserQuestionResponse findById(Long id) {
        UserQuestion userQuestion = userQuestionRepository.findById(id).orElseThrow(() -> new RuntimeException("UserQuestion not found"));
        return userQuestionMapper.userQuestionToUserQuestionResponse(userQuestion);
    }

    @Override
    public List<UserQuestionResponse> findUserQuestionsByUser(Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User not found"));
        List<UserQuestion> userQuestions = userQuestionRepository.findUserQuestionsByUser(user).stream().toList();
        return userQuestions.stream().map(userQuestionMapper::userQuestionToUserQuestionResponse).toList();
    }

    @Override
    public UserQuestionResponse save(UserQuestionRequest userQuestionRequest) {
        UserQuestion userQuestion = userQuestionMapper.userQuestionRequestToUserQuestion(userQuestionRequest);
        User user = userRepository.findById(userQuestionRequest.getUser()).orElseThrow(() -> new RuntimeException("User not found"));
        Question question = questionRepository.findById(userQuestionRequest.getQuestion()).orElseThrow(() -> new RuntimeException("Question not found"));

        userQuestion.setUser(user);
        userQuestion.setQuestion(question);
        return userQuestionMapper.userQuestionToUserQuestionResponse(userQuestionRepository.save(userQuestion));
    }

    @Override
    public UserQuestionResponse update(Long id, UserQuestionUpdate userQuestionUpdate) {
        UserQuestion userReceived = userQuestionMapper.userQuestionUpdateToUserQuestion(userQuestionUpdate);
        UserQuestion userFind = userQuestionRepository.findById(id).orElseThrow(() -> new RuntimeException("UserQuestion not found"));
        userFind.updateUserQuestion(userReceived);
        User user = userRepository.findById(userQuestionUpdate.getUser()).orElseThrow(() -> new RuntimeException("User not found"));
        Question question = questionRepository.findById(userQuestionUpdate.getQuestion()).orElseThrow(() -> new RuntimeException("Question not found"));
        userFind.setQuestion(question);
        userFind.setUser(user);
        return userQuestionMapper.userQuestionToUserQuestionResponse(userQuestionRepository.save(userFind));
    }

    @Override
    public void deleteById(Long id) {
        UserQuestion userQuestion = userQuestionRepository.findById(id).orElseThrow(() -> new RuntimeException("UserQuestion not found"));
        userQuestionRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userQuestionRepository.deleteAll();
    }
}
