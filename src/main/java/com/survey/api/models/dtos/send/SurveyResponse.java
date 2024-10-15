package com.survey.api.models.dtos.send;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SurveyResponse {
    private Long id;
    private String title;
    private String description;
}
