package com.survey.api.models.mappers;

import com.survey.api.models.dtos.save.UserSurveyRequest;
import com.survey.api.models.dtos.save.UserSurveyUpdate;
import com.survey.api.models.dtos.send.UserSurveyResponse;
import com.survey.api.models.entities.UserSurvey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserSurveyMapper {
    @Mapping(source = "user", target = "user.id")
    @Mapping(source = "survey", target = "survey.id")
    UserSurvey userSurveyRequestToUserSurvey(UserSurveyRequest userSurveyRequest);
    @Mapping(source = "user", target = "user.id")
    @Mapping(source = "survey", target = "survey.id")
    UserSurvey userSurveyUpdateToUserSurvey(UserSurveyUpdate userSurveyUpdate);
    UserSurvey userSurveyResponseToUserSurvey(UserSurveyResponse userSurveyResponse);
    UserSurveyResponse userSurveyToUserSurveyResponse(UserSurvey userSurvey);
}
