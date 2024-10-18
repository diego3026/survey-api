package com.survey.api.repositories;

import com.survey.api.models.entities.TypeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeQuestionRepository extends JpaRepository<TypeQuestion, Long> {
    TypeQuestion findByName(String name);
}
