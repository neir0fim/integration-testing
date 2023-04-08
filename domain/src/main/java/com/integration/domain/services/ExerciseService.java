package com.integration.domain.services;

import com.integration.persistence.exercise.ExerciseAdapter;
import org.springframework.stereotype.Service;

import static com.integration.domain.util.ValidationUtil.validateString;

@Service
public class ExerciseService {
    private static final String EXERCISE_DESCRIPTION = "exercise description";
    private final ExerciseAdapter adapter;

    public ExerciseService(ExerciseAdapter adapter) {
        this.adapter = adapter;
    }

    public void addExercise(String description) {
        validateString(description, EXERCISE_DESCRIPTION);

        adapter.addExercise(description);
    }
}
