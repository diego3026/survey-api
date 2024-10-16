package com.survey.api.models.dtos.send;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SurveyResponse {
    private Long id;
    private String title;
    private String description;
    private List<QuestionResponse> questions;
}
