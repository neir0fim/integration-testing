package com.integration.rest;

import com.integration.domain.services.ExerciseService;
import com.integration.models.exercise.Exercise;
import com.integration.models.exercise.ExerciseUpdateRequest;
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
    public void addExercise(@RequestBody ExerciseUpdateRequest updateRequest) {
        service.addExercise(updateRequest);
    }

    @PutMapping("/{exerciseId}")
    public void updateExercise(
            @PathVariable("exerciseId") int exerciseId,
            @RequestBody ExerciseUpdateRequest updateRequest
    ) {

        service.updateExercise(exerciseId, updateRequest);
    }

    @DeleteMapping("/{exerciseId}")
    public void deleteExercise(@PathVariable("exerciseId") int exerciseId) {
        service.deleteExercise(exerciseId);
    }

    @GetMapping("/{exerciseId}")
    public Exercise getExercise(@PathVariable("exerciseId") int exerciseId) {
        return service.getExercise(exerciseId);
    }

    @GetMapping
    public List<Exercise> getALLExercise() {
        return service.getALLExercise();
    }
}
