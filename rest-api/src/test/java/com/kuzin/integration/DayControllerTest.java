package com.kuzin.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuzin.integration.dto.DayDto;
import com.kuzin.integration.services.DayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = DayController.class)
@ContextConfiguration(classes = {TestApplication.class})
class DayControllerTest {
    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DayService dayService;

    @Test
    void shouldThrowUnauthorized() throws Exception {
        mockMvc.perform(get("/days"))
                .andExpect(status()
                        .isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldReturnListOfDays() throws Exception {
        var testDay = new DayDto(1, "MN", "");
        when(dayService.getDays()).thenReturn(List.of(testDay));

        mockMvc.perform(get("/days").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(mapper.writeValueAsString(List.of(testDay))))
                .andExpect(status().isOk());
    }
}