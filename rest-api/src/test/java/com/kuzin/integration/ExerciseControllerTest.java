package com.kuzin.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuzin.integration.config.SecurityConfig;
import com.kuzin.integration.dto.ExerciseDto;
import com.kuzin.integration.dto.ExerciseUpdateRequestDto;
import com.kuzin.integration.services.ExerciseService;
import com.nimbusds.jose.shaded.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExerciseController.class)
@ContextConfiguration(classes = {SecurityConfig.class, TestApplication.class})
class ExerciseControllerTest {

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExerciseService exerciseService;

    private static ExerciseDto getDto() {
        return new ExerciseDto(1, "test", "test");
    }

    @Test
    void shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/exercises"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldReturnForbiddenIfUserIsNoAdmin() throws Exception {
        var builder = MockMvcRequestBuilders.get("/exercises");

        mockMvc.perform(builder)
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnListOfExercise() throws Exception {
        when(exerciseService.getALLExercise()).thenReturn(List.of(getDto()));

        mockMvc.perform(get("/exercises").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(mapper.writeValueAsString(List.of(getDto()))))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnExerciseById() throws Exception {
        when(exerciseService.getExercise(1)).thenReturn(getDto());

        mockMvc.perform(get("/exercises/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(mapper.writeValueAsString(getDto())))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldSaveExercise() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(new ExerciseUpdateRequestDto("test", 1));

        mockMvc.perform(post("/exercises").accept(MediaType.APPLICATION_JSON)
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(exerciseService, times(1))
                .addExercise(new ExerciseUpdateRequestDto("test", 1));
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteExercise() throws Exception {
        mockMvc.perform(delete("/exercises/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(exerciseService, times(1))
                .deleteExercise(1);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldUpdateExercise() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(new ExerciseUpdateRequestDto("test", 1));

        mockMvc.perform(put("/exercises/{id}", 1).accept(MediaType.APPLICATION_JSON)
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(exerciseService, times(1))
                .updateExercise(1, new ExerciseUpdateRequestDto("test", 1));
    }
}