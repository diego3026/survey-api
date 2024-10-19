package com.survey.api.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "userQuestions")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idQuestion")
    private Question question;

    private String answer;

    public void updateUserQuestion(UserQuestion userReceived) {
        this.setId(this.getId());
        this.setUser(userReceived.getUser());
        this.setQuestion(userReceived.getQuestion());
        this.setAnswer(userReceived.getAnswer());
    }
}
