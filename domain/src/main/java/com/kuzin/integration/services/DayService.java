package com.kuzin.integration.services;

import com.kuzin.integration.dto.DayDto;
import com.kuzin.integration.persistence.day.DayAdapter;
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
