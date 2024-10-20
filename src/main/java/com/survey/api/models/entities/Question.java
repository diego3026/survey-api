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

    @ManyToOne(optional = false)
    @JoinColumn(name = "idSurvey")
    private Survey survey;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idTypeQuestion")
    private TypeQuestion typeQuestion;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;

    @OneToMany(mappedBy = "question")
    private List<UserQuestion> userQuestions;

    public void updateQuestion(Question question) {
        this.setId(this.id);
        this.setTitle(question.getTitle());
        this.setSurvey(question.getSurvey());
        this.setTypeQuestion(question.getTypeQuestion());
        this.setAnswers(this.getAnswers());
    }
}
