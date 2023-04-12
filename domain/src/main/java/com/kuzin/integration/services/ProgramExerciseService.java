package com.kuzin.integration.services;

import com.kuzin.integration.dto.ProgramExerciseRequestDto;
import com.kuzin.integration.models.exercise.ProgramExerciseRequest;
import com.kuzin.integration.persistence.day.ProgramExerciseAdapter;
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
