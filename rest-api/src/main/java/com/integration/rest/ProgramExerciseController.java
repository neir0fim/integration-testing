package com.integration.rest;

import com.integration.domain.dto.ProgramExerciseRequestDto;
import com.integration.domain.services.ProgramExerciseService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/program-exercises")
public class ProgramExerciseController {
    private final ProgramExerciseService programExerciseService;

    public ProgramExerciseController(ProgramExerciseService programExerciseService) {
        this.programExerciseService = programExerciseService;
    }

    @PostMapping
    public int addNewExercise(
            @RequestBody ProgramExerciseRequestDto requestDto
    ) {
        return programExerciseService.addNewExerciseForProgram(requestDto);
    }

    @DeleteMapping("/{programExerciseId}")
    public void deleteProgramExercise(
            @PathVariable("programExerciseId") int programExerciseId
    ) {
        programExerciseService.deleteProgramExercise(programExerciseId);
    }
}
