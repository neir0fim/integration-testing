package com.integration.domain.services;

import com.integration.domain.dto.ProgramExerciseRequestDto;
import com.integration.models.exercise.ProgramExerciseRequest;
import com.integration.persistence.day.ProgramExerciseAdapter;
import org.springframework.stereotype.Service;

@Service
public class ProgramExerciseService {
    private final ProgramExerciseAdapter programExerciseAdapter;

    public ProgramExerciseService(ProgramExerciseAdapter programExerciseAdapter) {
        this.programExerciseAdapter = programExerciseAdapter;
    }

    public int addNewExerciseForProgram(ProgramExerciseRequestDto requestDto) {
        return programExerciseAdapter.addNewExerciseForProgram(
                new ProgramExerciseRequest(
                        requestDto.programDayId(),
                        requestDto.exerciseId()
                )
        );
    }

    public void deleteProgramExercise(int programExerciseId) {
        programExerciseAdapter.deleteExerciseForProgram(programExerciseId);
    }
}
