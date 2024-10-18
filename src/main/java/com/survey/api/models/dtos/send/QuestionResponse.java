package com.survey.api.models.dtos.send;

import com.survey.api.models.entities.Answer;
import com.survey.api.models.entities.Survey;
import com.survey.api.models.entities.TypeQuestion;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionResponse {
    private Long id;
    private String title;
    private TypeQuestionResponse typeQuestion;
    private List<AnswerResponse> answers;
}
