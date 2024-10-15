package com.survey.api.repositories;

import com.survey.api.AbstractIntegrationDBTest;
import com.survey.api.models.entities.Survey;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SurveyRepositoryTest extends AbstractIntegrationDBTest {
    @Autowired
    SurveyRepository surveyRepository;

    @BeforeEach
    void setUp() {
        surveyRepository.deleteAll();
    }

    @Test
    public void surveyRepository_whenSave_shouldReturnSavedSurvey(){
        Survey survey = Survey.builder()
                .title("Survey 1")
                .description("Description 1")
                .build();

        Survey savedSurvey = surveyRepository.save(survey);

        assertThat(savedSurvey).isNotNull();
        assertThat(savedSurvey.getId()).isGreaterThan(0);
    }

    @Test
    public void surveyRepository_whenFindAll_shouldReturnMoreThenOneSurvey(){
        Survey survey = Survey.builder()
                .title("Survey 1")
                .description("Description 1")
                .build();
        Survey survey2 = Survey.builder()
                .title("Survey 1")
                .description("Description 1")
                .build();

        surveyRepository.save(survey);
        surveyRepository.save(survey2);

        List<Survey> surveyList = surveyRepository.findAll();

        AssertionsForInterfaceTypes.assertThat(surveyList).isNotNull();
        AssertionsForInterfaceTypes.assertThat(surveyList).hasSize(2);
    }

    @Test
    public void surveyRepository_whenFindById_shouldReturnSurvey(){
        Survey survey = Survey.builder()
                .title("Survey 1")
                .description("Description 1")
                .build();

        surveyRepository.save(survey);

        Survey surveyById = surveyRepository.findById(survey.getId()).get();

        assertThat(surveyById).isNotNull();
        Assertions.assertEquals(survey.getTitle(), surveyById.getTitle());
    }

    @Test
    public void surveyRepository_whenUpdateById_shouldReturnSurveyNotNull(){
        Survey survey = Survey.builder()
                .title("Survey 1")
                .description("Description 1")
                .build();

        surveyRepository.save(survey);

        Survey savedSurvey = surveyRepository.findById(survey.getId()).get();
        savedSurvey.setDescription("Description 2");

        Survey updatedSurvey = surveyRepository.save(savedSurvey);

        assertThat(updatedSurvey).isNotNull();
        assertThat(updatedSurvey.getDescription()).isNotNull();
        assertThat(updatedSurvey.getDescription()).isEqualTo("Description 2");
    }

    @Test
    public void surveyRepository_whenDeleteById_shouldReturnSurveyIsEmpty(){
        Survey survey = Survey.builder()
                .title("Survey 1")
                .description("Description 1")
                .build();

        surveyRepository.save(survey);

        surveyRepository.deleteById(survey.getId());
        Optional<Survey> taskReturn = surveyRepository.findById(survey.getId());

        assertThat(taskReturn).isEmpty();
    }

    @Test
    public void surveyRepository_whenDeleteAll_shouldReturnNoFoundSurveys(){
        Survey survey = Survey.builder()
                .title("Survey 1")
                .description("Description 1")
                .build();

        surveyRepository.save(survey);

        surveyRepository.deleteAll();
        List<Survey> surveyList = surveyRepository.findAll();

        AssertionsForInterfaceTypes.assertThat(surveyList).isEmpty();
    }
}
