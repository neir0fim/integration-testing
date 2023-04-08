package com.integration.domain.services;

import com.integration.models.day.Day;
import com.integration.persistence.day.DayAdapter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayService {
    private final DayAdapter dayAdapter;

    public DayService(DayAdapter dayAdapter) {
        this.dayAdapter = dayAdapter;
    }

    public List<Day> getDays() {
        return dayAdapter.getDays();
    }
}
