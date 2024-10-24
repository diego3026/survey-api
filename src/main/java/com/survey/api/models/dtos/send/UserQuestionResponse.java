package com.survey.api.models.dtos.send;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserQuestionResponse {
    private Long id;
    private UserResponse user;
    private QuestionResponse question;
    private String answer;
}
