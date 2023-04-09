package com.integration.rest;

import com.integration.domain.dto.ProgramDayDto;
import com.integration.domain.dto.ProgramDeletingRequestDto;
import com.integration.domain.dto.ProgramSavingRequestDto;
import com.integration.domain.dto.ProgramShortInfoDto;
import com.integration.domain.services.ProgramDayService;
import com.integration.domain.services.ProgramService;
import com.integration.rest.util.EmailResolver;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/programs")
public class ProgramController {
    private final EmailResolver resolver;
    private final ProgramService service;
    private final ProgramDayService dayService;

    public ProgramController(
            EmailResolver resolver,
            ProgramService service,
            ProgramDayService dayService
    ) {
        this.resolver = resolver;
        this.service = service;
        this.dayService = dayService;
    }

    @PostMapping
    public int createNewProgram(
            @RequestBody String programName,
            Authentication principal
    ) {
        var userEmail = resolver.getUserEmail(principal);

        var saveRequest = new ProgramSavingRequestDto(userEmail, programName);

        return service.createProgram(saveRequest);
    }

    @DeleteMapping("/{programId}")
    public void deleteUserGroup(
            @PathVariable("programId") int programId,
            Authentication principal
    ) {
        var userEmail = resolver.getUserEmail(principal);

        var deleteRequest = new ProgramDeletingRequestDto(programId, userEmail);

        service.deleteUserProgram(deleteRequest);
    }

    @GetMapping
    public List<ProgramShortInfoDto> getOwnerPrograms(
            Authentication principal
    ) {
        var userEmail = resolver.getUserEmail(principal);

        return service.getOwnerPrograms(userEmail);
    }

    @GetMapping("/{programId}/program-days")
    public List<ProgramDayDto> getAllProgramDays(
            @PathVariable("programId") int programId
    ) {
        return dayService.getALLProgramDays(programId);
    }
}
