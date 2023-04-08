package com.integration.rest;

import com.integration.domain.services.ProgramService;
import com.integration.models.program.ProgramDeletingRequest;
import com.integration.models.program.ProgramSavingRequest;
import com.integration.models.program.ProgramShortInfo;
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

    public ProgramController(
            EmailResolver resolver,
            ProgramService service
    ) {
        this.resolver = resolver;
        this.service = service;
    }

    @PostMapping
    public int createNewProgram(
            @RequestBody String programName,
            Authentication principal
    ) {
        var userEmail = resolver.getUserEmail(principal);

        var saveRequest = new ProgramSavingRequest(userEmail, programName);

        return service.createProgram(saveRequest);
    }

    @DeleteMapping("/{programId}")
    public void deleteUserGroup(
            @PathVariable("programId") int programId,
            Authentication principal
    ) {
        var userEmail = resolver.getUserEmail(principal);

        var deleteRequest = new ProgramDeletingRequest(programId, userEmail);

        service.deleteUserProgram(deleteRequest);
    }

    @GetMapping
    public List<ProgramShortInfo> getOwnerPrograms(
            Authentication principal
    ) {
        var userEmail = resolver.getUserEmail(principal);

        return service.getOwnerPrograms(userEmail);
    }
}
