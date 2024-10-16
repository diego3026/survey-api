package com.survey.api.services.impl;

import com.survey.api.models.dtos.save.AnswerRequest;
import com.survey.api.models.dtos.save.AnswerUpdate;
import com.survey.api.models.dtos.send.AnswerResponse;
import com.survey.api.models.entities.Answer;
import com.survey.api.models.mappers.AnswerMapper;
import com.survey.api.repositories.AnswerRepository;
import com.survey.api.services.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    public AnswerServiceImpl(AnswerRepository answerRepository, AnswerMapper answerMapper) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
    }

    @Override
    public List<AnswerResponse> findAll() {
        List<Answer> answers = answerRepository.findAll();
        return answers.stream().map(answerMapper::answerToAnswerResponse).toList();
    }

    @Override
    public AnswerResponse findById(Long id) {
        Answer answer = answerRepository.findById(id).orElse(null);
        return answerMapper.answerToAnswerResponse(answer);
    }

    @Override
    public AnswerResponse save(AnswerRequest answerRequest) {
        Answer answer = answerMapper.requestToAnswer(answerRequest);
        return answerMapper.answerToAnswerResponse(answerRepository.save(answer));
    }

    @Override
    public AnswerResponse update(Long id, AnswerUpdate answerUpdate) {
        Answer answer = answerMapper.updateToAnswer(answerUpdate);
        Answer data = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data wasn't found"));
        data.updateAnswer(answer);
        answerRepository.save(data);
        return answerMapper.answerToAnswerResponse(data);
    }

    @Override
    public void deleteById(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new RuntimeException("Data wasn't found"));
        answerRepository.delete(answer);
    }

    @Override
    public void deleteAll() {
        answerRepository.deleteAll();
    }
}
