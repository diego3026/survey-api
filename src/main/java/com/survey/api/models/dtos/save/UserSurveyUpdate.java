package com.survey.api.models.dtos.save;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSurveyUpdate {
    private Long user;
    private Long survey;
}
