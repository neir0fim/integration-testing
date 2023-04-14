package com.kuzin.integration;

import com.kuzin.integration.services.DayService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public DayController dayControllerTest(
            DayService dayService
    ) {
        return new DayController(dayService);
    }

    @Bean
    public DayService dayService() {
        return Mockito.mock(DayService.class);
    }
}
