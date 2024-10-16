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

    private String name;

    @OneToMany(mappedBy = "typeQuestion", cascade = CascadeType.ALL)
    private List<Question> questions;

    public void updateTypeQuestion(TypeQuestion typeQuestion) {
        new TypeQuestion(this.id, typeQuestion.getName(), typeQuestion.getQuestions());
    }
}
