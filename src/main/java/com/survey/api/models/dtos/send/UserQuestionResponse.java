package com.survey.api.models.dtos.send;

import com.survey.api.models.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserQuestionResponse {
    private UserResponse user;
    private QuestionResponse question;
    private String answer;
}
