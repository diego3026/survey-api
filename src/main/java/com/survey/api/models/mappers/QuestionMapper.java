package com.survey.api.models.mappers;

import com.survey.api.models.dtos.save.QuestionRequest;
import com.survey.api.models.dtos.save.QuestionUpdate;
import com.survey.api.models.dtos.send.QuestionResponse;
import com.survey.api.models.entities.Question;
import com.survey.api.models.entities.Survey;
import com.survey.api.models.entities.TypeQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(target = "survey.id", source = "survey")
    @Mapping(target = "typeQuestion.id", source = "typeQuestion")
    Question questionRequestToQuestion(QuestionRequest questionRequest);
    @Mapping(target = "survey.id", source = "survey")
    @Mapping(target = "typeQuestion.id", source = "typeQuestion")
    Question questionUpdateToQuestion(QuestionUpdate questionUpdate);
    Question questionResponseToQuestion(QuestionResponse questionResponse);
    QuestionResponse questionToQuestionResponse(Question question);
}
