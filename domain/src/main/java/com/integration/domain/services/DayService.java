package com.integration.domain.services;

import com.integration.domain.dto.DayDto;
import com.integration.persistence.day.DayAdapter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayService {
    private final DayAdapter dayAdapter;

    public DayService(DayAdapter dayAdapter) {
        this.dayAdapter = dayAdapter;
    }

    public List<DayDto> getDays() {
        return dayAdapter.getDays().stream()
                .map(day -> new DayDto(
                        day.id(),
                        day.code(),
                        day.description()
                        )
                )
                .toList();
    }
}
