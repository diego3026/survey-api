package com.survey.api.services.impl;

import com.survey.api.models.dtos.save.TypeQuestionRequest;
import com.survey.api.models.dtos.save.TypeQuestionUpdate;
import com.survey.api.models.dtos.send.TypeQuestionResponse;
import com.survey.api.models.entities.TypeQuestion;
import com.survey.api.models.mappers.TypeQuestionMapper;
import com.survey.api.repositories.TypeQuestionRepository;
import com.survey.api.services.TypeQuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class TypeQuestionServiceImpl implements TypeQuestionService {
    private final TypeQuestionRepository typeQuestionRepository;
    private final TypeQuestionMapper typeQuestionMapper;

    public TypeQuestionServiceImpl(TypeQuestionRepository typeQuestionRepository, TypeQuestionMapper typeQuestionMapper) {
        this.typeQuestionRepository = typeQuestionRepository;
        this.typeQuestionMapper = typeQuestionMapper;
    }

    @Override
    public List<TypeQuestionResponse> findAll() {
        List<TypeQuestion> typesQuestions = typeQuestionRepository.findAll();
        return typesQuestions.stream().map(typeQuestionMapper::typeQuestionToTypeQuestionResponse).toList();
    }

    @Override
    public TypeQuestionResponse findById(Long id) {
        TypeQuestion typeQuestion = typeQuestionRepository.findById(id).orElse(null);
        return typeQuestionMapper.typeQuestionToTypeQuestionResponse(typeQuestion);
    }

    @Override
    public TypeQuestionResponse findByName(String name) {
        TypeQuestion typeQuestion = typeQuestionRepository.findByName(name);
        if (isNull(typeQuestion)){
            throw new RuntimeException("TypeQuestion not found");
        }
        return typeQuestionMapper.typeQuestionToTypeQuestionResponse(typeQuestion);
    }

    @Override
    public TypeQuestionResponse save(TypeQuestionRequest typeQuestionRequest) {
        TypeQuestion typeQuestion = typeQuestionMapper.requestToTypeQuestion(typeQuestionRequest);
        return typeQuestionMapper.typeQuestionToTypeQuestionResponse(typeQuestionRepository.save(typeQuestion));
    }

    @Override
    public TypeQuestionResponse update(Long id, TypeQuestionUpdate typeQuestionUpdate) {
        TypeQuestion typeQuestion = typeQuestionMapper.updateToTypeQuestion(typeQuestionUpdate);
        TypeQuestion data = typeQuestionRepository.findById(id).orElseThrow(() -> new RuntimeException("Data wasn't found"));
        data.updateTypeQuestion(typeQuestion);
        return typeQuestionMapper.typeQuestionToTypeQuestionResponse(typeQuestionRepository.save(data));
    }

    @Override
    public void deleteById(Long id) {
        TypeQuestion typeQuestion = typeQuestionRepository.findById(id).orElseThrow(() -> new RuntimeException("Data wasn't found"));
        typeQuestionRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        typeQuestionRepository.deleteAll();
    }
}
