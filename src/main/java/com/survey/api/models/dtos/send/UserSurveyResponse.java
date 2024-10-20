package com.survey.api.models.dtos.send;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSurveyResponse {
    private Long id;
    private UserResponse user;
    private SurveyResponse survey;
}
