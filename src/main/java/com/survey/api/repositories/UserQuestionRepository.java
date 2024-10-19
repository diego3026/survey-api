package com.survey.api.repositories;

import com.survey.api.models.entities.User;
import com.survey.api.models.entities.UserQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQuestionRepository extends JpaRepository<UserQuestion, Long> {
    Optional<UserQuestion> findUserQuestionsByUser(User user);
}
