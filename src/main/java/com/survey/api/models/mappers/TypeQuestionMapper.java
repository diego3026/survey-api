package com.survey.api.models.mappers;

import com.survey.api.models.dtos.save.TypeQuestionRequest;
import com.survey.api.models.dtos.save.TypeQuestionUpdate;
import com.survey.api.models.dtos.send.TypeQuestionResponse;
import com.survey.api.models.entities.TypeQuestion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeQuestionMapper {
    TypeQuestion requestToTypeQuestion(TypeQuestionRequest typeQuestionRequest);
    TypeQuestion updateToTypeQuestion(TypeQuestionUpdate typeQuestionUpdate);
    TypeQuestion responseToTypeQuestion(TypeQuestionResponse typeQuestionResponse);
    TypeQuestionResponse TypeQuestionToTypeQuestionResponse(TypeQuestion typeQuestion);
}
