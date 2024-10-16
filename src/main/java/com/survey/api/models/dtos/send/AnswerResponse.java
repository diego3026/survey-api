package com.survey.api.models.dtos.send;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerResponse {
    private Long id;
    private String description;
}
