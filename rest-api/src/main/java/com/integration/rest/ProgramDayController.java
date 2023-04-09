package com.integration.rest;

import com.integration.domain.dto.ProgramDayFullInfoDto;
import com.integration.domain.dto.ProgramDayRequestDto;
import com.integration.domain.services.ProgramDayService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/program-days")
public class ProgramDayController {
    private final ProgramDayService programDayService;

    public ProgramDayController(ProgramDayService programDayService) {
        this.programDayService = programDayService;
    }

    @PostMapping
    public int addProgramDay(
            @RequestBody ProgramDayRequestDto requestDto
    ) {
        return programDayService.addProgramDay(requestDto);
    }

    @PutMapping("/{programDayId})")
    public void updateProgramDay(
            @PathVariable("programDayId") int programDayId,
            @RequestBody ProgramDayRequestDto requestDto
    ) {
        programDayService.updateExercise(programDayId, requestDto);
    }

    @DeleteMapping("/{programDayId})")
    public void deleteProgramDay(
            @PathVariable("programDayId") int programDayId
    ) {
        programDayService.deleteProgramDay(programDayId);
    }

    @GetMapping("/{programDayId})")
    public ProgramDayFullInfoDto getProgramDay(
            @PathVariable("programDayId") int programDayId
    ) {
        return programDayService.getProgramDay(programDayId);
    }
}
