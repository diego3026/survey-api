package com.survey.api.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.List;

@Entity
@Table(name = "typesQuestions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TypeQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "typeQuestion")
    private List<Question> questions;

    public void updateTypeQuestion(TypeQuestion typeQuestion) {
        this.setId(this.id);
        this.setName(typeQuestion.getName());
        this.setQuestions(typeQuestion.getQuestions());
    }
}
