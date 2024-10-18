package com.survey.api.models.dtos.save;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TypeQuestionRequest {
    @NotBlank
    private String name;
}
