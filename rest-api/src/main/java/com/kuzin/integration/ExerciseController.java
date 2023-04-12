package com.kuzin.integration;

import com.kuzin.integration.dto.ExerciseDto;
import com.kuzin.integration.dto.ExerciseUpdateRequestDto;
import com.kuzin.integration.services.ExerciseService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    @PostMapping
    public void addExercise(@RequestBody ExerciseUpdateRequestDto updateRequest) {
        service.addExercise(updateRequest);
    }

    @PutMapping("/{exerciseId}")
    public void updateExercise(
            @PathVariable("exerciseId") int exerciseId,
            @RequestBody ExerciseUpdateRequestDto updateRequest
    ) {

        service.updateExercise(exerciseId, updateRequest);
    }

    @DeleteMapping("/{exerciseId}")
    public void deleteExercise(@PathVariable("exerciseId") int exerciseId) {
        service.deleteExercise(exerciseId);
    }

    @GetMapping("/{exerciseId}")
    public ExerciseDto getExercise(@PathVariable("exerciseId") int exerciseId) {
        return service.getExercise(exerciseId);
    }

    @GetMapping
    public List<ExerciseDto> getALLExercise() {
        return service.getALLExercise();
    }
}
