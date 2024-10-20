package com.survey.api.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "userSurveys")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idSurvey")
    private Survey survey;

    public void updateUserSurvey(UserSurvey userSurvey){
        this.setId(this.getId());
        this.setSurvey(userSurvey.getSurvey());
        this.setUser(userSurvey.getUser());
    }
}
