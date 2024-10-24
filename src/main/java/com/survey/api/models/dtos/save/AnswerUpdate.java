package com.survey.api.models.dtos.save;

import com.survey.api.models.entities.Question;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerUpdate {
    @NotBlank
    private String description;
    private Long question;
}
