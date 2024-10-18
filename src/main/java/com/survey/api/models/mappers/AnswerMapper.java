package com.survey.api.models.mappers;

import com.survey.api.models.dtos.save.AnswerRequest;
import com.survey.api.models.dtos.save.AnswerUpdate;
import com.survey.api.models.dtos.send.AnswerResponse;
import com.survey.api.models.entities.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    @Mapping(target = "question.id", source = "question")
    Answer requestToAnswer(AnswerRequest answerRequest);
    Answer responseToAnswer(AnswerResponse answerResponse);
    @Mapping(target = "question.id", source = "question")
    Answer updateToAnswer(AnswerUpdate answerUpdate);
    AnswerResponse answerToAnswerResponse(Answer answer);
}
