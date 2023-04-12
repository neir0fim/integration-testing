package com.kuzin.integration;

import com.kuzin.integration.dto.ProgramDayFullInfoDto;
import com.kuzin.integration.dto.ProgramDayRequestDto;
import com.kuzin.integration.services.ProgramDayService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/day-exercises")
public class DayExercisesController {
    private final ProgramDayService programDayService;

    public DayExercisesController(ProgramDayService programDayService) {
        this.programDayService = programDayService;
    }

    @GetMapping("/{dayId}")
    public ProgramDayFullInfoDto getDayExercises(@PathVariable("dayId") int dayId) {
        return programDayService.getProgramDay(dayId);
    }

    @PostMapping
    public int addProgramDay(
            @RequestBody ProgramDayRequestDto requestDto
    ) {
        return programDayService.addProgramDay(requestDto);
    }

    @PutMapping("/{dayId})")
    public void updateProgramDay(
            @PathVariable("dayId") int dayId,
            @RequestBody ProgramDayRequestDto requestDto
    ) {
        programDayService.updateExercise(dayId, requestDto);
    }

    @DeleteMapping("/{dayId}")
    public void deleteProgramDay(@PathVariable("dayId") int dayId) {
        programDayService.deleteProgramDay(dayId);
    }
}
