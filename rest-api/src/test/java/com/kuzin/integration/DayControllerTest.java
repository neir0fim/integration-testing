package com.kuzin.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuzin.integration.services.DayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = DayController.class)
@ContextConfiguration(classes = {Config.class})
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
        mockMvc.perform(get("/days"))
                .andExpect(status()
                        .isOk());
    }
}