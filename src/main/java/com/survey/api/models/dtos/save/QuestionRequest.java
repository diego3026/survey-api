package com.survey.api.models.dtos.save;

import com.survey.api.models.entities.Survey;
import com.survey.api.models.entities.TypeQuestion;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionRequest {
    @NotBlank
    private String title;
    private Survey survey;
    private TypeQuestion typeQuestion;
}
