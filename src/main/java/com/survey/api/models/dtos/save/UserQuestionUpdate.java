package com.survey.api.models.dtos.save;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserQuestionUpdate {
    private Long user;
    private Long question;
    private String answer;
}
