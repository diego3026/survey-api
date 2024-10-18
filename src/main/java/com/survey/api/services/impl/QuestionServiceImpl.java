package com.survey.api.services.impl;

import com.survey.api.models.dtos.save.QuestionRequest;
import com.survey.api.models.dtos.save.QuestionUpdate;
import com.survey.api.models.dtos.send.QuestionResponse;
import com.survey.api.models.entities.Question;
import com.survey.api.models.entities.Survey;
import com.survey.api.models.entities.TypeQuestion;
import com.survey.api.models.mappers.QuestionMapper;
import com.survey.api.models.mappers.TypeQuestionMapper;
import com.survey.api.models.mappers.TypeQuestionMapperImpl;
import com.survey.api.repositories.QuestionRepository;
import com.survey.api.repositories.SurveyRepository;
import com.survey.api.repositories.TypeQuestionRepository;
import com.survey.api.services.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final SurveyRepository surveyRepository;
    private final TypeQuestionRepository typeQuestionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionMapper questionMapper, SurveyRepository surveyRepository, TypeQuestionRepository typeQuestionRepository, TypeQuestionMapper typeQuestionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.surveyRepository = surveyRepository;
        this.typeQuestionRepository = typeQuestionRepository;
    }

    @Override
    public List<QuestionResponse> findAll() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(questionMapper::questionToQuestionResponse).toList();
    }

    @Override
    public QuestionResponse findById(Long id) {
        Question question = questionRepository.findById(id).orElse(null);
        return questionMapper.questionToQuestionResponse(question);
    }

    @Override
    public QuestionResponse save(QuestionRequest questionRequest) {
        Survey survey = surveyRepository.findById(questionRequest.getSurvey())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));

        TypeQuestion typeQuestion = typeQuestionRepository.findById(questionRequest.getTypeQuestion())
                .orElseThrow(() -> new RuntimeException("Type Question not found"));

        Question question = questionMapper.questionRequestToQuestion(questionRequest);
        question.setSurvey(survey);
        question.setTypeQuestion(typeQuestion);
        return questionMapper.questionToQuestionResponse(questionRepository.save(question));
    }

    @Override
    public QuestionResponse update(Long id, QuestionUpdate questionUpdate) {
        Survey survey = surveyRepository.findById(questionUpdate.getSurvey())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));

        TypeQuestion typeQuestion = typeQuestionRepository.findById(questionUpdate.getTypeQuestion())
                .orElseThrow(() -> new RuntimeException("Type Question not found"));

        Question question = questionMapper.questionUpdateToQuestion(questionUpdate);
        question.setSurvey(survey);
        question.setTypeQuestion(typeQuestion);

        Question data = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data wasn't found"));
        data.updateQuestion(question);


        return questionMapper.questionToQuestionResponse(questionRepository.save(data));
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
