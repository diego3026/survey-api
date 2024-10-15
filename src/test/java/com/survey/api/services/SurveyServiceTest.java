package com.survey.api.services;

import com.survey.api.models.dtos.save.SurveyRequest;
import com.survey.api.models.dtos.save.SurveyUpdate;
import com.survey.api.models.dtos.send.SurveyResponse;
import com.survey.api.models.entities.Survey;
import com.survey.api.models.mappers.SurveyMapper;
import com.survey.api.repositories.SurveyRepository;
import com.survey.api.services.impl.SurveyServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceTest {
    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private SurveyMapper surveyMapper;

    @InjectMocks
    private SurveyServiceImpl surveyService;

    @Test
    public void surveyService_saveTask_shouldGetSurveyResponse() {
        Survey survey = Survey.builder()
                .title("Survey 1")
                .description("Description 1")
                .build();
        SurveyResponse surveyResponse = SurveyResponse.builder()
                .title("Survey 1")
                .description("Description 1")
                .build();
        SurveyRequest surveyRequest = SurveyRequest.builder()
                .title("Survey 1")
                .description("Description 1")
                .build();

        when(surveyMapper.requestToEntity(surveyRequest)).thenReturn(survey);
        when(surveyRepository.save(Mockito.any(Survey.class))).thenReturn(survey);
        when(surveyMapper.entityToResponse(survey)).thenReturn(surveyResponse);

        SurveyResponse surveySaved = surveyService.save(surveyRequest);

        Assertions.assertThat(surveySaved).isNotNull();
        Assertions.assertThat(surveySaved.getDescription()).isNotNull();
    }

    @Test
    public void surveyService_findAll_shouldReturnSurveyResponses() {
        List<Survey> surveys = List.of(
                Survey.builder()
                        .title("Survey 1")
                        .description("Description 1")
                        .build(),
                Survey.builder()
                        .title("Survey 2")
                        .description("Description 2")
                        .build()
        );

        when(surveyRepository.findAll()).thenReturn(surveys);

        when(surveyMapper.entityToResponse(surveys.get(0))).thenReturn(
                SurveyResponse.builder()
                        .title("Survey 1")
                        .description("Description 1")
                        .build()
        );
        when(surveyMapper.entityToResponse(surveys.get(1))).thenReturn(
                SurveyResponse.builder()
                        .title("Survey 2")
                        .description("Description 2")
                        .build()
        );

        List<SurveyResponse> surveyResponses = surveyService.findAll();

        Assertions.assertThat(surveyResponses).isNotNull();
        Assertions.assertThat(surveyResponses.size()).isEqualTo(2);
        Assertions.assertThat(surveyResponses.get(0).getTitle()).isEqualTo("Survey 1");
        Assertions.assertThat(surveyResponses.get(1).getDescription()).isEqualTo("Description 2");
    }

    @Test
    public void surveyService_findById_shouldReturnSurveyResponse() {
        Long surveyId = 1L;
        Survey survey = Survey.builder()
                .id(surveyId)
                .title("Test Survey")
                .description("Test Description")
                .build();

        when(surveyRepository.findById(surveyId)).thenReturn(Optional.ofNullable(survey));
        when(surveyMapper.entityToResponse(survey)).thenReturn(
                SurveyResponse.builder()
                        .id(surveyId)
                        .title("Test Survey")
                        .description("Test Description")
                        .build()
        );

        SurveyResponse surveyResponse = surveyService.findById(surveyId);

        Assertions.assertThat(surveyResponse).isNotNull();
        Assertions.assertThat(surveyResponse.getId()).isEqualTo(surveyId);
        Assertions.assertThat(surveyResponse.getTitle()).isEqualTo("Test Survey");
    }

    @Test
    public void surveyService_update_shouldReturnUpdatedSurveyResponse() {
        Long surveyId = 1L;
        Survey survey = Survey.builder()
                .id(surveyId)
                .title("Original Survey")
                .description("Original Description")
                .build();

        SurveyUpdate surveyUpdate = SurveyUpdate.builder()
                .title("Updated Survey")
                .description("Updated Description")
                .build();

        Survey updatedSurvey = Survey.builder()
                .id(surveyId)
                .title("Updated Survey")
                .description("Updated Description")
                .build();

        SurveyResponse surveyResponse = SurveyResponse.builder()
                .id(surveyId)
                .title("Updated Survey")
                .description("Updated Description")
                .build();

        when(surveyRepository.findById(surveyId)).thenReturn(Optional.ofNullable(survey));
        when(surveyMapper.updateToEntity(surveyUpdate)).thenReturn(updatedSurvey);
        when(surveyRepository.save(Mockito.any(Survey.class))).thenReturn(updatedSurvey);
        when(surveyMapper.entityToResponse(Mockito.any(Survey.class))).thenReturn(surveyResponse);

        SurveyResponse updatedResponse = surveyService.update(surveyId, surveyUpdate);

        Assertions.assertThat(updatedResponse).isNotNull();
        Assertions.assertThat(updatedResponse.getTitle()).isEqualTo("Updated Survey");
        Assertions.assertThat(updatedResponse.getDescription()).isEqualTo("Updated Description");
    }

    @Test
    public void surveyService_deleteById_shouldDeleteSurvey() {
        Long surveyId = 1L;
        Survey survey = Survey.builder()
                .id(surveyId)
                .title("Survey to be deleted")
                .description("Description")
                .build();

        when(surveyRepository.findById(surveyId)).thenReturn(Optional.ofNullable(survey));
        doNothing().when(surveyRepository).delete(survey);

        assertAll(() -> surveyService.deleteById(surveyId));

        verify(surveyRepository, times(1)).delete(survey);
    }
}
