package com.survey.api.models.dtos.save;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSurveyRequest {
    private Long survey;
    private Long user;
}
