package com.survey.api.models.dtos.send;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeQuestionResponse {
    private Long id;
    private String name;
}
