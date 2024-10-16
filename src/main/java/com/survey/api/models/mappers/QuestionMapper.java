package com.survey.api.models.mappers;

import com.survey.api.models.dtos.save.QuestionRequest;
import com.survey.api.models.dtos.save.QuestionUpdate;
import com.survey.api.models.dtos.send.QuestionResponse;
import com.survey.api.models.entities.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question requestToQuestion(QuestionRequest questionRequest);
    Question updateToQuestion(QuestionUpdate questionUpdate);
    Question responseToQuestion(QuestionResponse questionResponse);
    QuestionResponse questionToResponse(Question question);
}
