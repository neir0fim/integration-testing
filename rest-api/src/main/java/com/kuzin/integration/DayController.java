package com.kuzin.integration;

import com.kuzin.integration.dto.DayDto;
import com.kuzin.integration.services.DayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/days")
public class DayController {
    private final DayService service;

    public DayController(DayService service) {
        this.service = service;
    }

    @GetMapping
    public List<DayDto> getDays() {
        return service.getDays();
    }
}
