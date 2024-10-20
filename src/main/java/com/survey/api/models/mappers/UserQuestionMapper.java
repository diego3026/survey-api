package com.survey.api.models.mappers;

import com.survey.api.models.dtos.save.UserQuestionRequest;
import com.survey.api.models.dtos.save.UserQuestionUpdate;
import com.survey.api.models.dtos.send.UserQuestionResponse;
import com.survey.api.models.entities.UserQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserQuestionMapper {
    @Mapping(source = "user", target = "user.id")
    @Mapping(source = "question", target = "question.id")
    UserQuestion userQuestionRequestToUserQuestion(UserQuestionRequest userQuestionRequest);
    @Mapping(source = "user", target = "user.id")
    @Mapping(source = "question", target = "question.id")
    UserQuestion userQuestionUpdateToUserQuestion(UserQuestionUpdate userQuestionUpdate);
    UserQuestion userQuestionResponseToUserQuestion(UserQuestionResponse userQuestionResponse);
    UserQuestionResponse userQuestionToUserQuestionResponse(UserQuestion userQuestion);
}
