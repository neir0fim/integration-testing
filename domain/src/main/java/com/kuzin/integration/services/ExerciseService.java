package com.kuzin.integration.services;

import com.kuzin.integration.dto.ExerciseDto;
import com.kuzin.integration.dto.ExerciseUpdateRequestDto;
import com.kuzin.integration.models.exercise.ExerciseUpdateRequest;
import com.kuzin.integration.persistence.exercise.ExerciseAdapter;
import com.kuzin.integration.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    private static final String EXERCISE_DESCRIPTION = "exercise description";
    private final ExerciseAdapter adapter;

    public ExerciseService(ExerciseAdapter adapter) {
        this.adapter = adapter;
    }

    public void addExercise(ExerciseUpdateRequestDto updateRequest) {
        ValidationUtil.validateString(updateRequest.exerciseDescription(), EXERCISE_DESCRIPTION);

        var persistenceRequest = new ExerciseUpdateRequest(
                updateRequest.exerciseDescription(),
                updateRequest.muscleGroupId()
        );

        adapter.addExercise(persistenceRequest);
    }

    public void updateExercise(int exerciseId, ExerciseUpdateRequestDto updateRequest) {
        ValidationUtil.validateString(updateRequest.exerciseDescription(), EXERCISE_DESCRIPTION);

        var persistenceRequest = new ExerciseUpdateRequest(
                updateRequest.exerciseDescription(),
                updateRequest.muscleGroupId()
        );

        adapter.updateExercise(exerciseId, persistenceRequest);
    }

    public void deleteExercise(int exerciseId) {
        adapter.deleteExercise(exerciseId);
    }

    public ExerciseDto getExercise(int exerciseId) {
        var exercise = adapter.getExercise(exerciseId);
        return new ExerciseDto(
                exercise.exerciseId(),
                exercise.name(),
                exercise.group()
        );
    }

    public List<ExerciseDto> getALLExercise() {
        return adapter.getALLExercise().stream()
                .map(exercise -> new ExerciseDto(
                        exercise.exerciseId(),
                        exercise.name(),
                        exercise.group())
                )
                .toList();
    }
}
