package com.survey.api.models.mappers;

import com.survey.api.models.dtos.save.SurveyRequest;
import com.survey.api.models.dtos.save.SurveyUpdate;
import com.survey.api.models.dtos.send.SurveyResponse;
import com.survey.api.models.entities.Survey;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SurveyMapper {
    Survey requestToEntity(SurveyRequest surveyRequest);
    Survey responseToEntity(SurveyResponse surveyResponse);
    Survey updateToEntity(SurveyUpdate surveyUpdate);
    SurveyResponse entityToResponse(Survey survey);

}
