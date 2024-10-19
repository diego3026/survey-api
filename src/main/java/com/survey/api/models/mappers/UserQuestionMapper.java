package com.survey.api.models.mappers;

import com.survey.api.models.dtos.save.UserQuestionRequest;
import com.survey.api.models.dtos.save.UserQuestionUpdate;
import com.survey.api.models.dtos.send.UserQuestionResponse;
import com.survey.api.models.entities.UserQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserQuestionMapper {
    @Mapping(source = "user.id", target = "user")
    @Mapping(source = "question.id", target = "question")
    UserQuestion userQuestionRequestToUserQuestion(UserQuestionRequest userQuestionRequest);
    @Mapping(source = "user.id", target = "user")
    @Mapping(source = "question.id", target = "question")
    UserQuestion userQuestionUpdateToUserQuestion(UserQuestionUpdate userQuestionUpdate);
    UserQuestion userQuestionResponseToUserQuestion(UserQuestionResponse userQuestionResponse);
    UserQuestionResponse userQuestionToUserQuestionResponse(UserQuestion userQuestion);
}
