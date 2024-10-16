package com.survey.api.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "typesQuestions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "typeQuestion", cascade = CascadeType.ALL)
    private List<Question> questions;

    public void updateTypeQuestion(TypeQuestion typeQuestion) {
        new TypeQuestion(this.id, typeQuestion.getName(), typeQuestion.getQuestions());
    }
}
