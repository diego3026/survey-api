package com.survey.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.survey.api.models.dtos.save.SurveyRequest;
import com.survey.api.models.dtos.save.SurveyUpdate;
import com.survey.api.models.dtos.send.SurveyResponse;
import com.survey.api.models.entities.Survey;
import com.survey.api.services.SurveyService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@WebMvcTest(controllers = SurveyController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class SurveyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyService surveyService;

    @Autowired
    private ObjectMapper objectMapper;
    private Survey survey;
    private SurveyResponse surveyResponse;
    private SurveyRequest surveyRequest;

    @BeforeEach
    void init() {
        survey = Survey.builder()
                .title("Survey 1")
                .description("Description 1")
                .build();
        surveyResponse = SurveyResponse.builder()
                .title("Survey 1")
                .description("Description 1")
                .build();
        surveyRequest = SurveyRequest.builder()
                .title("Survey 1")
                .description("Description 1")
                .build();
    }

    @Test
    public void surveyController_createSurvey_returnCreated() throws Exception {
        SurveyRequest surveyRequest = SurveyRequest.builder().title("Survey 1").description("Description 1").build();
        SurveyResponse surveyResponse = SurveyResponse.builder().id(1L).title("Survey 1").description("Description 1").build();

        given(surveyService.save(ArgumentMatchers.any(SurveyRequest.class))).willReturn(surveyResponse);

        ResultActions response = mockMvc.perform(post("/surveys")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(surveyRequest)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(surveyRequest.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(surveyRequest.getDescription())));
    }

    @Test
    public void surveyController_getAllSurveys_returnResponseList() throws Exception {
        SurveyResponse surveyResponse = SurveyResponse.builder().id(1L).title("Survey 1").description("Description 1").build();
        when(surveyService.findAll()).thenReturn(Arrays.asList(surveyResponse));

        ResultActions response = mockMvc.perform(get("/surveys")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", CoreMatchers.is(surveyResponse.getTitle())));
    }

    @Test
    public void surveyController_getSurveyById_returnSurveyResponse() throws Exception {
        Long surveyId = 1L;
        SurveyResponse surveyResponse = SurveyResponse.builder().id(surveyId).title("Survey 1").description("Description 1").build();

        when(surveyService.findById(surveyId)).thenReturn(surveyResponse);

        ResultActions response = mockMvc.perform(get("/surveys/{id}", surveyId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(surveyResponse.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(surveyResponse.getDescription())));
    }

    @Test
    public void surveyController_updateSurvey_returnUpdatedSurvey() throws Exception {
        Long surveyId = 1L;
        SurveyUpdate surveyUpdate = SurveyUpdate.builder().title("Survey Task").description("Updated Description").build();
        SurveyResponse surveyResponse = SurveyResponse.builder().id(surveyId).title("Survey Task").description("Updated Description").build();

        when(surveyService.update(ArgumentMatchers.eq(surveyId), ArgumentMatchers.any(SurveyUpdate.class))).thenReturn(surveyResponse);

        ResultActions response = mockMvc.perform(put("/surveys/{id}", surveyId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(surveyUpdate)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(surveyResponse.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(surveyResponse.getDescription())));
    }

    @Test
    public void surveyController_deleteSurvey_returnOk() throws Exception {
        Long surveyId = 1L;
        doNothing().when(surveyService).deleteById(surveyId);

        ResultActions response = mockMvc.perform(delete("/surveys/{id}", surveyId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Survey deleted"));
    }
}
