package com.integration.domain.services;

import com.integration.domain.dto.ExerciseDto;
import com.integration.domain.dto.ExerciseUpdateRequestDto;
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

    public void addExercise(ExerciseUpdateRequestDto updateRequest) {
        validateString(updateRequest.exerciseDescription(), EXERCISE_DESCRIPTION);

        var persistenceRequest = new ExerciseUpdateRequest(
                updateRequest.exerciseDescription(),
                updateRequest.muscleGroupId()
        );

        adapter.addExercise(persistenceRequest);
    }

    public void updateExercise(int exerciseId, ExerciseUpdateRequestDto updateRequest) {
        validateString(updateRequest.exerciseDescription(), EXERCISE_DESCRIPTION);

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
