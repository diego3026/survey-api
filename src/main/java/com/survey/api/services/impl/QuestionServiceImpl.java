package com.survey.api.services.impl;

import com.survey.api.models.dtos.save.QuestionRequest;
import com.survey.api.models.dtos.save.QuestionUpdate;
import com.survey.api.models.dtos.send.QuestionResponse;
import com.survey.api.models.entities.Question;
import com.survey.api.models.mappers.QuestionMapper;
import com.survey.api.repositories.QuestionRepository;
import com.survey.api.services.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    @Override
    public List<QuestionResponse> findAll() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(questionMapper::questionToResponse).toList();
    }

    @Override
    public QuestionResponse findById(Long id) {
        Question question = questionRepository.findById(id).orElse(null);
        return questionMapper.questionToResponse(question);
    }

    @Override
    public QuestionResponse save(QuestionRequest questionRequest) {
        Question question = questionMapper.requestToQuestion(questionRequest);
        return questionMapper.questionToResponse(questionRepository.save(question));
    }

    @Override
    public QuestionResponse update(Long id, QuestionUpdate questionUpdate) {
        Question question = questionMapper.updateToQuestion(questionUpdate);
        Question data = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data wasn't found"));
        data.updateQuestion(question);
        questionRepository.save(data);
        return questionMapper.questionToResponse(data);
    }

    @Override
    public void deleteById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data wasn't found"));
        questionRepository.delete(question);
    }

    @Override
    public void deleteAll() {
        questionRepository.deleteAll();
    }
}
