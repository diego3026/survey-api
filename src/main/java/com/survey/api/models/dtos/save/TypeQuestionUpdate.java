package com.survey.api.models.dtos.save;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeQuestionUpdate {
    @NotBlank
    private String name;
}
