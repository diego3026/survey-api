package com.survey.api.models.mappers;

import com.survey.api.models.dtos.save.AnswerRequest;
import com.survey.api.models.dtos.save.AnswerUpdate;
import com.survey.api.models.dtos.send.AnswerResponse;
import com.survey.api.models.entities.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer requestToAnswer(AnswerRequest answerRequest);
    Answer responseToAnswer(AnswerResponse answerResponse);
    Answer updateToAnswer(AnswerUpdate answerUpdate);
    AnswerResponse answerToAnswerResponse(Answer answer);
}
