package com.survey.api.repositories;

import com.survey.api.models.entities.Survey;
import com.survey.api.models.entities.User;
import com.survey.api.models.entities.UserSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSurveyRepository extends JpaRepository<UserSurvey, Long> {
    List<UserSurvey> findUserSurveyByUser(User user);
}
