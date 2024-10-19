package com.survey.api.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "surveys")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questions;

    @ManyToMany
    @JoinTable(name = "surveysUsers", joinColumns = @JoinColumn(name = "idSurvey", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "idUser", nullable = false))
    private List<User> users;

    public void updateSurvey(Survey survey){
        this.setId(this.id);
        this.setTitle(survey.getTitle());
        this.setDescription(survey.getDescription());
        this.setQuestions(this.getQuestions());
    }
}
