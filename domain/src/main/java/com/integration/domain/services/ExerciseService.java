package com.integration.domain.services;

import com.integration.models.exercise.Exercise;
import com.integration.models.exercise.ExerciseUpdateRequest;
import com.integration.persistence.exercise.ExerciseAdapter;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.integration.domain.util.ValidationUtil.validateString;

@Service
public class ExerciseService {
    private static final String EXERCISE_DESCRIPTION = "exercise description";
    private final ExerciseAdapter adapter;

    public ExerciseService(ExerciseAdapter adapter) {
        this.adapter = adapter;
    }

    public void addExercise(ExerciseUpdateRequest updateRequest) {
        validateString(updateRequest.exerciseDescription(), EXERCISE_DESCRIPTION);

        adapter.addExercise(updateRequest);
    }

    public void updateExercise(int exerciseId, ExerciseUpdateRequest updateRequest) {
        validateString(updateRequest.exerciseDescription(), EXERCISE_DESCRIPTION);

        adapter.updateExercise(exerciseId, updateRequest);
    }

    public void deleteExercise(int exerciseId) {
        adapter.deleteExercise(exerciseId);
    }

    public Exercise getExercise(int exerciseId) {
        return adapter.getExercise(exerciseId);
    }

    public List<Exercise> getALLExercise() {
        return adapter.getALLExercise();
    }
}
