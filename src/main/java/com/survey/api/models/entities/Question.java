package com.survey.api.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "questions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idSurvey")
    private Survey survey;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idTypeQuestion")
    private TypeQuestion typeQuestion;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;

    public void updateQuestion(Question question) {
        new Question(this.id, question.getTitle(), question.getSurvey(),question.getTypeQuestion(),question.getAnswers());
    }
}
